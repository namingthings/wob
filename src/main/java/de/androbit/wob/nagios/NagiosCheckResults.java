package de.androbit.wob.nagios;

public class NagiosCheckResults {
    NagiosStatus status;

    public NagiosStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "NagiosCheckResults{" +
                "status=" + status +
                '}';
    }
}
