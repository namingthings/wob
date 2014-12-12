package de.androbit.wob.jira;

import de.androbit.wob.configuration.Configuration;
import de.androbit.wob.util.http.RestClient;
import de.androbit.wob.jira.auth.JiraAuth;
import de.androbit.wob.jira.auth.JiraAuthClient;
import de.androbit.wob.jira.auth.JiraAuthentication;
import de.androbit.wob.jira.auth.JiraCredentials;

public class JiraClient{
    final Jira jira;

    public JiraClient(Configuration configuration) {
        this(configuration, new JiraAuthClient(configuration).client());
    }

    public JiraClient(Configuration configuration, JiraAuth jiraAuth) {
        JiraCredentials jiraCredentials = new JiraCredentials(configuration.wobConfig().getJiraUser(), configuration.wobConfig().getJiraPassword());

        this.jira = new RestClient()
                .withEndPoint(configuration.wobConfig().getJiraUrl())
                .withInterceptor(new JiraAuthentication(jiraAuth, jiraCredentials))
                .api(Jira.class);
    }

    public Jira client() {
        return jira;
    }

    public static void main(String[] args) {
        Configuration config = new Configuration();

        Jira jira = new JiraClient(config).client();

        System.out.println(jira.getStatisticsJson("filter-18947"));
    }
}
