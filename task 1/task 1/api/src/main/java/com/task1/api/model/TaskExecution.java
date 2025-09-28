package com.task1.api.model;

import java.time.LocalDateTime;

public class TaskExecution {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String output;

    // --- Getters and Setters ---

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
