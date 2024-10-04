package com.grade.studentcalc.utils;

import com.grade.studentcalc.services.GradeService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GpaRecalculationJob implements Job {

    @Autowired
    private GradeService gradeService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            gradeService.recalculateAllGpas();
        } catch (Exception e) {
            throw new JobExecutionException("Error occurred while recalculating GPAs", e);
        }
    }
}

