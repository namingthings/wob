package de.androbit.wob.teamcity;

import de.androbit.wob.configuration.Configuration;
import de.androbit.wob.util.http.RestClient;

public class TeamCityClient {
    final TeamCity teamCity;

    public TeamCityClient(Configuration configuration) {
        this.teamCity = new RestClient()
                .withEndPoint(configuration.wobConfig().getTeamCityUrl())
                .withBasicAuth(configuration.wobConfig().getTeamCityUser(),
                        configuration.wobConfig().getTeamCityPassword())
                .api(TeamCity.class);
    }

    public TeamCity client() {
        return teamCity;
    }
}
