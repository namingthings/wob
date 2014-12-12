package de.androbit.wob.teamcity;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface TeamCity {
    @Headers({"Accept: application/json"})
    @GET("/httpAuth/app/rest/buildTypes/{id}/builds/?locator=running:any")
    BuildResults getBuilds(@Path("id") String buildType);
}
