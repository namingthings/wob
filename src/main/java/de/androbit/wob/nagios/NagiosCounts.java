package de.androbit.wob.nagios;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NagiosCounts {
    @JsonProperty("hostgroup_name")
    String hostgroupName;

    @JsonProperty("services_warning")
    Integer servicesWarning;

    @JsonProperty("services_critical")
    Integer servicesCritical;

    public String getHostgroupName() {
        return hostgroupName;
    }

    public Integer getServicesWarning() {
        return servicesWarning;
    }

    public Integer getServicesCritical() {
        return servicesCritical;
    }

    @Override
    public String toString() {
        return "NagiosCounts{" +
                "hostgroupName='" + hostgroupName + '\'' +
                ", servicesWarning=" + servicesWarning +
                ", servicesCritical=" + servicesCritical +
                '}';
    }
}
