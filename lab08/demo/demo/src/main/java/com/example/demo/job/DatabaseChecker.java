package com.example.demo.job;

import com.example.demo.service.DatabaseService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseChecker implements Job {
    private DatabaseService databaseService;

    @Autowired
    DatabaseChecker(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    @Override
    public void execute(JobExecutionContext context) {
        databaseService.runDatabaseChecker();
    }
}
