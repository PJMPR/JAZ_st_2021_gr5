package com.example.demo.Frame;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class SystemStatusInfo {
    boolean isWorking;
    Date reloadStarted;
    Date reloadEnded;
    int moviesToUpdate;
    int progress;
    ArrayList<ProcessStatusInfo> stepsFinished;

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public Date getReloadStarted() {
        return reloadStarted;
    }

    public void setReloadStarted(Date reloadStarted) {
        this.reloadStarted = reloadStarted;
    }

    public Date getReloadEnded() {
        return reloadEnded;
    }

    public void setReloadEnded(Date reloadEnded) {
        this.reloadEnded = reloadEnded;
    }

    public int getMoviesToUpdate() {
        return moviesToUpdate;
    }

    public void setMoviesToUpdate(int moviesToUpdate) {
        this.moviesToUpdate = moviesToUpdate;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public ArrayList<ProcessStatusInfo> getStepsFinished() {
        return stepsFinished;
    }

    public void setStepsFinished(ArrayList<ProcessStatusInfo> stepsFinished) {
        this.stepsFinished = stepsFinished;
    }
}
