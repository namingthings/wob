package de.androbit.wob.nagios;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NagiosStatus {
    @JsonProperty("hostgroup_summary")
    List<NagiosCounts> hostgroupSummary;

    public List<NagiosCounts> getHostgroupSummary() {
        return hostgroupSummary;
    }

    @Override
    public String toString() {
        return "NagiosStatus{" +
                "hostgroupSummary=" + hostgroupSummary +
                '}';
    }
}
