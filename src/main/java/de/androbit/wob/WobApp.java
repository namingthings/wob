package de.androbit.wob;

import de.androbit.nibbler.RestHttpServerConfiguration;
import de.androbit.nibbler.json.JacksonConverter;
import de.androbit.nibbler.netty.NettyHttpServer;
import de.androbit.wob.configuration.Configuration;
import de.androbit.wob.nagios.NagiosClient;
import de.androbit.wob.service.ChainStatusService;
import de.androbit.wob.teamcity.TeamCity;
import de.androbit.wob.teamcity.TeamCityClient;
import de.androbit.wob.jira.Jira;
import de.androbit.wob.jira.auth.JiraAuth;
import de.androbit.wob.jira.auth.JiraAuthClient;
import de.androbit.wob.jira.JiraClient;

public class WobApp {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();

        TeamCity teamCity = new TeamCityClient(configuration).client();
        JiraAuth jiraAuth = new JiraAuthClient(configuration).client();

        Jira jira = new JiraClient(configuration, jiraAuth).client();
        NagiosClient nagiosClient = new NagiosClient(configuration);

        ChainStatusService chainStatusService = new ChainStatusService(configuration, teamCity);

        RestHttpServerConfiguration httpServerConfiguration = new RestHttpServerConfiguration()
                .withService(new WobService(configuration, chainStatusService, jira, nagiosClient))
                .withConverter(new JacksonConverter())
                .withPort(4242);

        new NettyHttpServer().startAndWait(httpServerConfiguration);
    }
}
