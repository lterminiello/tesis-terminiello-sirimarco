package server.home.service;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import server.home.json.JsonFactory;
import server.home.model.CronJob;
import server.home.model.CronManager;
import server.home.utils.HouseJsonLoader;
import server.home.utils.tests.MyJob;

import java.util.List;

/**
 * Created by lterminiello on 19/05/17.
 */
public class CronService {

    private JsonFactory jsonFactory;
    private HouseJsonLoader houseJsonLoader;
    private CronManager cronJobs;
    private Scheduler sch;
    private HouseService houseService;

    public CronService(JsonFactory jsonFactory, HouseJsonLoader houseJsonLoader,HouseService houseService) {
        this.jsonFactory = jsonFactory;
        this.houseJsonLoader = houseJsonLoader;
        this.houseService = houseService;
        this.cronJobs = houseJsonLoader.getSchemeCronos();
        try {
            initCronos();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    private void initCronos() throws SchedulerException {
        if (sch == null) {
            SchedulerFactory schfa = new StdSchedulerFactory();
            sch = schfa.getScheduler();
        }
        for (CronJob cronJob : cronJobs.getJobs()) {
            JobDetail jobdetail = JobBuilder.newJob(CronJob.class)
                    .withIdentity(cronJob.getArctifactName() + cronJob.cronoExpression(), cronJob.getRoomName()).build();
            Trigger crontrigger = TriggerBuilder.newTrigger().withIdentity(cronJob.getArctifactName() + cronJob.cronoExpression(), cronJob.getRoomName())
                    .withSchedule(craeteSchedule(cronJob.cronoExpression())).build();
            sch.scheduleJob(jobdetail, crontrigger);
            cronJob.setJobKey(jobdetail.getKey());
        }
        sch.start();
    }

    public void addCrono(CronJob cronJob) throws SchedulerException {
        if (sch == null) {
            SchedulerFactory schfa = new StdSchedulerFactory();
            sch = schfa.getScheduler();
        }
        JobDetail jobdetail = JobBuilder.newJob(CronJob.class)
                .withIdentity(cronJob.getArctifactName() + cronJob.cronoExpression(), cronJob.getRoomName()).build();
        Trigger crontrigger = TriggerBuilder.newTrigger().withIdentity(cronJob.getArctifactName() + cronJob.cronoExpression(), cronJob.getRoomName())
                .withSchedule(craeteSchedule(cronJob.cronoExpression())).build();
        sch.scheduleJob(jobdetail, crontrigger);
        cronJob.setJobKey(jobdetail.getKey());
        cronJobs.add(cronJob);
        houseJsonLoader.setSchemeCronos(jsonFactory.toJson(cronJobs));
    }

    public void deleteCrono(CronJob cronJob) throws SchedulerException {
        if (sch == null) {
            SchedulerFactory schfa = new StdSchedulerFactory();
            sch = schfa.getScheduler();
        }
        CronJob cronoTodelete = cronJobs.getEquals(cronJob);
        sch.deleteJob(cronoTodelete.getJobKey());
        cronJobs.delete(cronoTodelete);
        houseJsonLoader.setSchemeCronos(jsonFactory.toJson(cronJobs));
    }

    public CronManager getCronJobs(){
        return cronJobs;
    }


    private static ScheduleBuilder craeteSchedule(String cronExpression) {
        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExpression);
        return builder;
    }

    public void executed(JobKey jobKey){
        CronJob cronJob = cronJobs.getCronForJobKey(jobKey);
        if (cronJob.getValue() != null){
            houseService.getHouse().getRoom(cronJob.getRoomName()).getArtifact(cronJob.getArctifactName()).runAction(cronJob.getAction(), Integer.valueOf(cronJob.getValue()));
        }else {
            houseService.getHouse().getRoom(cronJob.getRoomName()).getArtifact(cronJob.getArctifactName()).runAction(cronJob.getAction(), null);
        }
    }


}
