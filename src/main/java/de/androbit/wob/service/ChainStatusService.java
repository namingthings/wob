package de.androbit.wob.service;

import de.androbit.wob.configuration.ChainConfig;
import de.androbit.wob.configuration.Configuration;
import de.androbit.wob.configuration.StepConfig;
import de.androbit.wob.teamcity.BuildResult;
import de.androbit.wob.teamcity.TeamCity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChainStatusService {
    Logger log = LoggerFactory.getLogger(ChainStatusService.class);

    Configuration configuration;
    TeamCity teamCity;

    public ChainStatusService(Configuration configuration, TeamCity teamCity) {
        this.configuration = configuration;
        this.teamCity = teamCity;
    }

    public List<StepStatus> getStatus(String chainName) {
        ChainConfig configWithName = configuration.chainConfigs().getChains()
                .stream()
                .filter(config -> config.getName().equalsIgnoreCase(chainName))
                .findFirst().get();

        return configWithName.getSteps().stream()
                .map(step -> {
                    Optional<BuildResult> firstBuild = getFirstBuild(step);
                    return stepStatus(step, firstBuild);
                }).collect(Collectors.toList());
    }

    private StepStatus stepStatus(StepConfig step, Optional<BuildResult> firstBuild) {
        if (firstBuild.isPresent()) {
            String status = firstBuild.get().isRunning() ? "RUNNING" : firstBuild.get().getStatus();
            return new StepStatus(step.getDescription(), status);
        } else {
            return new StepStatus(step.getDescription(), "UNKNOWN");
        }
    }

    private Optional<BuildResult> getFirstBuild(StepConfig step) {
        try {
            return Optional.of(teamCity.getBuilds(step.getBuildType()).getBuilds().get(0));
        } catch (Exception e) {
            log.error("cant get build information for " + step, e);
            return Optional.empty();
        }
    }
}
