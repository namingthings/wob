package de.androbit.wob.service;

public class StepStatus {
    String name;
    String status;

    public StepStatus(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
