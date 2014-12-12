package de.androbit.wob.teamcity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BuildResults {
    Integer count;

    @JsonProperty("build")
    List<BuildResult> builds;

    public Integer getCount() {
        return count;
    }

    public List<BuildResult> getBuilds() {
        return builds;
    }
}
