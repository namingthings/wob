package de.androbit.wob.jira.auth;

import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface JiraAuth {
    @Headers({"Content-Type: application/json"})
    @POST("/auth/1/session")
    JiraSession login(@Body JiraCredentials jiraCredentials);
}
