package de.androbit.wob.nagios;

import retrofit.http.GET;
import retrofit.http.Query;

public interface Nagios {
    // default style = "summary"
    // default jsonoutput = true
    @GET("/cgi-bin/icinga/status.cgi?&style=summary&jsonoutput")
    NagiosCheckResults getCheckResults(@Query("hostgroup") String hostgroup, @Query("style") String style, @Query("jsonoutput") Boolean jsonoutput);
}
