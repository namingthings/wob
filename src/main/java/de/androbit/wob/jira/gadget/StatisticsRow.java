package de.androbit.wob.jira.gadget;

public class StatisticsRow {
    String html;
    Integer count;
    Integer percentage;

    public String getHtml() {
        return html;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return "StatisticsRow{" +
                "html='" + html + '\'' +
                ", count=" + count +
                ", percentage=" + percentage +
                '}';
    }
}
