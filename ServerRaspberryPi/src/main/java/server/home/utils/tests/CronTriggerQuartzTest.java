package server.home.utils.tests;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerQuartzTest {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
		SchedulerFactory schfa = new StdSchedulerFactory();
		Scheduler sch = schfa.getScheduler();
		JobDetail jobdetail = JobBuilder.newJob(MyJob.class)
			    .withIdentity("cronjob1", "crongroup1").build();
		//Executes after every minute 0 0/1 * 1/1 * ? *
		Trigger crontrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "crongroup1")
			    .withSchedule(craeteSchedule("0 24 22 * * ?")).build();
		sch.scheduleJob(jobdetail, crontrigger);
		jobdetail = JobBuilder.newJob(MyJob.class)
			    .withIdentity("cronjob2", "crongroup1").build();
		//Executes after every 2 minute 0 0/2 * 1/1 * ? *
		crontrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "crongroup1")
			    .withSchedule(craeteSchedule("0 46 22 * * ?")).build();
		sch.scheduleJob(jobdetail, crontrigger);
		sch.start();
		Thread.sleep(100L * 1000L);
		sch.shutdown(true);
	}
	private static ScheduleBuilder craeteSchedule(String cronExpression){
		CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExpression);
		return builder;
	}
} 