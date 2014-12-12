package de.androbit.wob.nagios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NagiosCheckSummary {
    List<Integer> issueCountsOrderedByPrio = new ArrayList<>();

    private final NagiosCheckResults checkResults;

    public NagiosCheckSummary(NagiosCheckResults checkResults) {
        this.checkResults = checkResults;
        Optional<NagiosCounts> counts = checkResults.getStatus().getHostgroupSummary().stream().findFirst();
        if (counts.isPresent()) {
            issueCountsOrderedByPrio.add(counts.get().getServicesCritical());
            issueCountsOrderedByPrio.add(counts.get().getServicesWarning());
        } else {
            issueCountsOrderedByPrio.add(-1);
            issueCountsOrderedByPrio.add(-1);
        }
    }

    public List<Integer> getIssueCountsOrderedByPrio() {
        return issueCountsOrderedByPrio;
    }
}
