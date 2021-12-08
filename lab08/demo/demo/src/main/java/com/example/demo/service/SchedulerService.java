package com.example.demo.service;

import com.example.demo.info.DailyTimerInfo;
import com.example.demo.util.DailyTimerUtil;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.logging.Logger;

@Service
public class SchedulerService {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(SchedulerService.class));

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(final Class jobClass, final DailyTimerInfo info){
        final JobDetail jobDetail = DailyTimerUtil.buildJobDetail(jobClass, info);
        final Trigger trigger = DailyTimerUtil.buildTrigger(jobClass, info);

        try{
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOGGER.severe( e.getMessage() + e);
        }
    }

    @PostConstruct
    public void init(){
        try {
            scheduler.start();
        }catch (SchedulerException e){
            LOGGER.severe(e.getMessage() + e);
        }
    }

    @PreDestroy
    public void preDestroy(){
        try {
            scheduler.shutdown();
        }catch (SchedulerException e){
            LOGGER.severe(e.getMessage() + e);
        }
    }
}
