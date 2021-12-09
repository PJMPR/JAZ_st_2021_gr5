package com.example.demo.quartz.schedule;

import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
public class ScheduleInfo implements Serializable {
    private int totalFireCount;
    private boolean runForever;
    private int repeatIntervalHs;
    private int startHour;

    public int getTotalFireCount() {
        return totalFireCount;
    }

    public void setTotalFireCount(int totalFireCount) {
        this.totalFireCount = totalFireCount;
    }

    public boolean isRunForever() {
        return runForever;
    }

    public void setRunForever(boolean runForever) {
        this.runForever = runForever;
    }

    public int getRepeatIntervalHs() {
        return repeatIntervalHs;
    }

    public void setRepeatIntervalHs(int repeatIntervalHs) {
        this.repeatIntervalHs = repeatIntervalHs;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
}
