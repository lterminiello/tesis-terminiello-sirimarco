package server.home.utils.tests;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class MyJob implements Job {
    @Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobKey key = jobDetail.getKey();
		System.out.println("For Job key:"+ key +", Current Date:"+ new Date());
	}
} 