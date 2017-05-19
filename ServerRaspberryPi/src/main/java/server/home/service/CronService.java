package server.home.service;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import server.home.json.JsonFactory;
import server.home.model.CronJob;
import server.home.utils.HouseJsonLoader;
import server.home.utils.tests.MyJob;

import java.util.List;

/**
 * Created by lterminiello on 19/05/17.
 */
public class CronService {

    private JsonFactory jsonFactory;
    private HouseJsonLoader houseJsonLoader;
    private List<CronJob> cronJobs;
    private Scheduler sch;

    public CronService(JsonFactory jsonFactory, HouseJsonLoader houseJsonLoader) {
        this.jsonFactory = jsonFactory;
        this.houseJsonLoader = houseJsonLoader;
        this.cronJobs = houseJsonLoader.getSchemeCronos();
        try {
            initCronos();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    private void initCronos() throws SchedulerException{
        if (sch == null){
            SchedulerFactory schfa = new StdSchedulerFactory();
            sch = schfa.getScheduler();
        }
        for (CronJob cronJob: cronJobs){
            JobDetail jobdetail = JobBuilder.newJob(MyJob.class)
                    .withIdentity(cronJob.getArctifactName(), cronJob.getRoomName()).build();
            Trigger crontrigger = TriggerBuilder.newTrigger().withIdentity(cronJob.getArctifactName(), cronJob.getRoomName())
                    .withSchedule(craeteSchedule(cronJob.getCronoExpression())).build();
            sch.scheduleJob(jobdetail, crontrigger);
            cronJob.setJobKey(jobdetail.getKey());
        }
        sch.start();
    }

    public void addCrono(CronJob cronJob) throws SchedulerException {
        if (sch == null){
            SchedulerFactory schfa = new StdSchedulerFactory();
            sch = schfa.getScheduler();
        }
        JobDetail jobdetail = JobBuilder.newJob(MyJob.class)
                .withIdentity(cronJob.getArctifactName(), cronJob.getRoomName()).build();
        Trigger crontrigger = TriggerBuilder.newTrigger().withIdentity(cronJob.getArctifactName(), cronJob.getRoomName())
                .withSchedule(craeteSchedule(cronJob.getCronoExpression())).build();
        sch.scheduleJob(jobdetail, crontrigger);
        cronJob.setJobKey(jobdetail.getKey());
        cronJobs.add(cronJob);
        houseJsonLoader.setSchemeCronos(jsonFactory.toJson(cronJobs));
    }

    public void deleteCrono(CronJob cronJob) throws SchedulerException{
        if (sch == null){
            SchedulerFactory schfa = new StdSchedulerFactory();
            sch = schfa.getScheduler();
        }
        CronJob cronoTodelete = cronJobs.get(cronJobs.indexOf(cronJob));
        sch.deleteJob(cronoTodelete.getJobKey());
    }


    private static ScheduleBuilder craeteSchedule(String cronExpression){
        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExpression);
        return builder;
    }


}
