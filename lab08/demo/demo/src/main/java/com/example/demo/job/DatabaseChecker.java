package com.example.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class DatabaseChecker implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        //sprawdz nowe filmy
        System.out.println("odpala sie, gut");
    }
}
