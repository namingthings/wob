package de.androbit.wob.nagios;

import de.androbit.wob.configuration.Configuration;
import de.androbit.wob.util.http.RestClient;

import java.util.Optional;

public class NagiosClient {

    final Configuration configuration;
    Nagios nagios;

    public NagiosClient(Configuration configuration) {
        this.configuration = configuration;
        this.nagios = new RestClient()
                .withEndPoint(configuration.wobConfig().getNagiosUrl())
                .withBasicAuth(configuration.wobConfig().getNagiosUser(), configuration.wobConfig().getNagiosPassword())
                .allowAllCertificates()
                .api(Nagios.class);
    }

    public NagiosCheckResults getCheckResults(String hostgroup) {
        return getCheckResults(hostgroup, Optional.<String>empty(), Optional.<Boolean>empty());
    }

    public NagiosCheckResults getCheckResults(String hostgroup, Optional<String> style, Optional<Boolean> jsonoutput) {
        return nagios.getCheckResults(hostgroup, style.orElse("summary"), jsonoutput.orElse(true));
    }

    public static void main(String[] args) {
        System.out.println(new NagiosClient(new Configuration()).getCheckResults("env-l-umg10"));
    }

}
