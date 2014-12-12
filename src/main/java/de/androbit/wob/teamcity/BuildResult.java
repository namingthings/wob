package de.androbit.wob.teamcity;

public class BuildResult {
    String id;
    Boolean running = false;
    String percentageComplete;
    Integer number;
    String status;
    String buildTypeId;
    String startDate;
    String href;
    String webUrl;

    public String getId() {
        return id;
    }

    public Boolean isRunning() {
        return running;
    }

    public String getPercentageComplete() {
        return percentageComplete;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public String getBuildTypeId() {
        return buildTypeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getHref() {
        return href;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
