package de.androbit.wob.configuration;

public class StepConfig {
    String buildType;
    String description;

    public String getBuildType() {
        return buildType;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "StepConfig{" +
                "buildType='" + buildType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
