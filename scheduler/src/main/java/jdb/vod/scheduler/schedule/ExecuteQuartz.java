package jdb.vod.scheduler.schedule;

import jdb.vod.scheduler.service.ApiCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


@Slf4j
@RequiredArgsConstructor
public class ExecuteQuartz implements Job {

    private final ApiCallService apiCallService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        apiCallService.init();
        apiCallService.produceTrendingList();
        apiCallService.produceSynopsis();
        System.out.println("end");
    }
}
