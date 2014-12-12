package de.androbit.wob.configuration;

import java.util.List;

public class ChainConfig {
    String name;
    List<StepConfig> steps;

    public String getName() {
        return name;
    }

    public List<StepConfig> getSteps() {
        return steps;
    }
}
