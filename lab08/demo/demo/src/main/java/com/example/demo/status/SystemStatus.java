package com.example.demo.status;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class SystemStatus {
    boolean isWorking;
    Date reloadStarted;
    Date reloadEnded;
    int moviesToUpdate;
    int progress;
    ArrayList<ProcessStatus> stepsFinished;

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

    public ArrayList<ProcessStatus> getStepsFinished() {
        return stepsFinished;
    }

    public void setStepsFinished(ArrayList<ProcessStatus> stepsFinished) {
        this.stepsFinished = stepsFinished;
    }
}