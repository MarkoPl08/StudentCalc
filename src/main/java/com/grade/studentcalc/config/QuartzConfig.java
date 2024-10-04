package com.grade.studentcalc.config;

import com.grade.studentcalc.utils.GpaRecalculationJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail gpaRecalculationJobDetail() {
        return JobBuilder.newJob(GpaRecalculationJob.class)
                .withIdentity("gpaRecalculationJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger gpaRecalculationJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(gpaRecalculationJobDetail())
                .withIdentity("gpaRecalculationTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0))
                .build();
    }
}
