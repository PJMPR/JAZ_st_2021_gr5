package com.pjwstk.sakila.filmsupdater.steps;

public interface ProcessStep {

    boolean canExecute();
    void execute();

}
