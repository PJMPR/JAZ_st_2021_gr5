package com.example.demo.util;

import com.example.demo.info.DailyTimerInfo;
import org.quartz.*;

import static org.quartz.DateBuilder.dateOf;

public final class DailyTimerUtil {
    private DailyTimerUtil(){

    }

    public static JobDetail buildJobDetail(final Class jobClass, final DailyTimerInfo info){
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), info);

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class jobClass, final DailyTimerInfo info){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(info.getRepeatIntervalHs());

        if(info.isRunForever()){
            builder = builder.repeatForever();
        }else {
            builder = builder.withRepeatCount(info.getTotalFireCount() - 1);
        }

        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(builder)
                .startAt(dateOf(info.getStartHour(), 0, 0))
                .build();
    }
}
