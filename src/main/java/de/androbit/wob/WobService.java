package de.androbit.wob;

import de.androbit.nibbler.RestServiceBuilder;
import de.androbit.nibbler.converter.TypedOutput;
import de.androbit.nibbler.http.*;
import de.androbit.wob.configuration.Configuration;
import de.androbit.wob.nagios.NagiosCheckSummary;
import de.androbit.wob.nagios.NagiosClient;
import de.androbit.wob.service.ChainStatusService;
import de.androbit.wob.util.IOUtil;
import de.androbit.wob.jira.Jira;
import de.androbit.wob.jira.gadget.StatisticsValues;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static de.androbit.nibbler.file.FileSupport.classPathResource;
import static de.androbit.nibbler.handler.FileHandlers.fileContent;
import static de.androbit.nibbler.json.JsonSupport.json;
import static java.util.Arrays.asList;

public class WobService extends RestServiceBuilder {

    Jira jira;
    NagiosClient nagiosClient;
    Configuration configuration;
    ChainStatusService chainStatusService;

    public WobService(Configuration configuration, ChainStatusService chainStatusService, Jira jira, NagiosClient nagiosClient) {
        this.configuration = configuration;
        this.chainStatusService = chainStatusService;
        this.jira = jira;
        this.nagiosClient = nagiosClient;
    }

    @Override
    public void define() {
        path("/").get((request, response) -> response.status(301).header(Header.Location, "/web/teamwall/index.html?dashboard=/config"));

        path("web").get((RestRequest request, RestResponse response) -> classPathResource(request.path().value(), response));

        path("wob/config").get(wobConfig());

        path("config").get((r, response) -> response.with(fileContent(configuration.teamWallJsonConfig())));

        path("chains").get(chainConfigs());

        path("status/{chainName}").get(chainStatus);

        path("jira-filter/{filterId}").get(filterStatistics());

        path("jira-statistics").get(jiraStatistics());

        path("nagios/{hostGroup}").get(nagiosCheckResults());

        path("data").get(dataFromFile());
    }

    RestRequestHandler chainConfigs() {
        return (rq, rs) -> rs.with(json(configuration.chainConfigs()));
    }

    RestRequestHandler wobConfig() {
        return (rq, rs) -> rs.with(json(configuration.wobConfig()));
    }

    RestRequestHandler chainStatus = (request, response) -> {
        String chainName = request.pathParams().get("chainName").get();
        return response.with(json(chainStatusService.getStatus(chainName)));
    };

    RestRequestHandler filterStatistics() {
       return (request, response) -> {
           String filterId = request.pathParams().get("filterId").get();
           return response.with(json(jira.getStatisticsJson(filterId)));
       };
    }

    RestRequestHandler jiraStatistics() {
        return (request, response) -> {
            String filters = request.queryParams().get("filters").get();
            String name = request.queryParams().get("name").orElse("filterBasedStatistics");

            List<Integer> filterCounts = asList(filters.split(",")).stream().map(
                    filter -> jira.getStatisticsJson(filter).getTotalIssueCount()
            ).collect(Collectors.toList());

            return response.with(json(new StatisticsValues(name, filterCounts)));
        };
    }

    RestRequestHandler nagiosCheckResults() {
        return (request, response) -> {
            String hostGroup = request.pathParams().get("hostGroup").get();
            return response.with(json(new NagiosCheckSummary(nagiosClient.getCheckResults(hostGroup))));
        };
    }

    RestRequestHandler dataFromFile() {
        return (request, response) -> {
            String path = request.path().suffixAfter("data");
            return response.with(fileContent(new File(path), MediaType.APPLICATION_JSON));
        };
    }

}
