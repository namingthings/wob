package de.androbit.wob.configuration;

import java.util.List;

public class WobConfig {
    String teamCityUrl;
    String teamCityUser;
    String teamCityPassword;
    String jiraUrl;
    String jiraUser;
    String jiraPassword;
    String nagiosUrl;
    String nagiosUser;
    String nagiosPassword;
    List<String> pageRotation;
    Integer pageRotationInterval;

    public String getTeamCityUrl() {
        return teamCityUrl;
    }

    public String getTeamCityUser() {
        return teamCityUser;
    }

    public String getTeamCityPassword() {
        return teamCityPassword;
    }

    public String getJiraUrl() {
        return jiraUrl;
    }

    public String getJiraUser() {
        return jiraUser;
    }

    public String getJiraPassword() {
        return jiraPassword;
    }

    public String getNagiosUrl() {
        return nagiosUrl;
    }

    public String getNagiosUser() {
        return nagiosUser;
    }

    public String getNagiosPassword() {
        return nagiosPassword;
    }

    public List<String> getPageRotation() {
        return pageRotation;
    }

    public Integer getPageRotationInterval() {
        return pageRotationInterval;
    }
}
