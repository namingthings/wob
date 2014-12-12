package de.androbit.wob.jira.auth;

import retrofit.RequestInterceptor;

public class JiraAuthentication implements RequestInterceptor {
    JiraAuth jiraAuth;
    JiraCredentials jiraCredentials;

    public JiraAuthentication(JiraAuth jiraAuth, JiraCredentials jiraCredentials) {
        this.jiraAuth = jiraAuth;
        this.jiraCredentials = jiraCredentials;
    }

    @Override
    public void intercept(RequestFacade requestFacade) {
        JiraSession session = jiraAuth.login(jiraCredentials);
        String sessionToken = session.getSession().getValue();
        requestFacade.addHeader("Cookie", String.format("JSESSIONID=%s", sessionToken));
    }
}
