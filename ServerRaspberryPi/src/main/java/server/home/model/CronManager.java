package server.home.model;

import org.quartz.JobKey;

import java.util.List;

/**
 * Created by default on 19/05/17.
 */
public class CronManager {

    private List<CronJob> jobs;

    public CronManager(){
    }

    public CronManager(List<CronJob> jobs) {
        this.jobs = jobs;
    }

    public List<CronJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<CronJob> jobs) {
        this.jobs = jobs;
    }

    public void add(CronJob cronJob){
        jobs.add(cronJob);
    }

    public CronJob getEquals(CronJob cronJob){
        return jobs.get(jobs.indexOf(cronJob));
    }

    public void delete(CronJob cronJob){
        jobs.remove(cronJob);
    }

    public CronJob getCronForJobKey(JobKey jobKey){
        for (CronJob cronJob:jobs){
            if (cronJob.getJobKey().equals(jobKey)){
                return cronJob;
            }
        }
        return null;
    }
}
