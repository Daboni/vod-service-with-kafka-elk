package jdb.vod.scheduler.config;

import jdb.vod.scheduler.schedule.ExecuteQuartz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfig {
    private final Scheduler scheduler;

    @PostConstruct
    public void start(){

        JobDetail jobDetail = buildJobDetail(ExecuteQuartz.class, new HashMap());

        try {
//            scheduler.scheduleJob(jobDetail, buildJobTrigger("30 * * * * ?"));
            scheduler.scheduleJob(jobDetail, buildJobTrigger("0 4 * * * ?"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    public Trigger buildJobTrigger(String scheduleExp) {
        return TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
    }

    public JobDetail buildJobDetail(Class job, Map params) {

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return newJob(job).usingJobData(jobDataMap).build();

    }

}
