package de.androbit.wob.jira.gadget;

import java.util.List;

public class StatisticsJson {
    String statTypeDescription;
    List<StatisticsRow> rows;
    Integer totalIssueCount;
    String filterOrProjectName;
    String filterOrProjectLink;

    public String getStatTypeDescription() {
        return statTypeDescription;
    }

    public List<StatisticsRow> getRows() {
        return rows;
    }

    public Integer getTotalIssueCount() {
        return totalIssueCount;
    }

    public String getFilterOrProjectName() {
        return filterOrProjectName;
    }

    public String getFilterOrProjectLink() {
        return filterOrProjectLink;
    }

    @Override
    public String toString() {
        return "StatisticsJson{" +
                "statTypeDescription='" + statTypeDescription + '\'' +
                ", rows=" + rows +
                ", totalIssueCount=" + totalIssueCount +
                ", filterOrProjectName='" + filterOrProjectName + '\'' +
                ", filterOrProjectLink='" + filterOrProjectLink + '\'' +
                '}';
    }
}
