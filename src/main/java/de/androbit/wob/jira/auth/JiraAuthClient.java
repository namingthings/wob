package de.androbit.wob.jira.auth;

import de.androbit.wob.configuration.Configuration;
import de.androbit.wob.util.http.RestClient;

public class JiraAuthClient {
    final JiraAuth jiraAuth;

    public JiraAuthClient(Configuration configuration) {
        this.jiraAuth = new RestClient()
                .withEndPoint(configuration.wobConfig().getJiraUrl())
                .api(JiraAuth.class);
    }

    public JiraAuth client() {
        return jiraAuth;
    }
}
