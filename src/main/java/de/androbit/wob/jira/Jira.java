package de.androbit.wob.jira;

import de.androbit.wob.jira.gadget.StatisticsJson;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface Jira {
    @Headers({"Accept: application/json"})
    @GET("/gadget/1.0/stats/generate.json")
    StatisticsJson getStatisticsJson(@Query("projectOrFilterId") String projectOrFilterId);
}
