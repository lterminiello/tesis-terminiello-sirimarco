package server.home.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import server.home.service.CronService;
import server.home.service.HouseService;
import server.home.utils.ApplicationContextProvider;

public class CronJob implements Job {

    @JsonIgnore
    private JobKey jobKey;

    private String roomName;
    private String arctifactName;
    private String action;
    private String value;
    //Time
    private String min;
    private String hour;
    //TODO: puede ser numeros del 1 al 7, 1=domingo 7=sabado, o las primeras 3 letras del nombre del dia SUN-SAT. *<- es todos los dias
    //TODO: Para indicar varios dias se separa con ,
    private String days;

    public CronJob(){

    }

    public CronJob(String roomName, String arctifactName, String action, String value, String min, String hour, String days) {
        this.roomName = roomName;
        this.arctifactName = arctifactName;
        this.action = action;
        this.value = value;
        this.min = min;
        this.hour = hour;
        this.days = days;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        CronService cronService = ApplicationContextProvider.getApplicationContext().getBean("cronService",CronService.class);
        cronService.executed(jobExecutionContext.getJobDetail().getKey());
        System.out.println("me ejecute!!");
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getArctifactName() {
        return arctifactName;
    }

    public void setArctifactName(String arctifactName) {
        this.arctifactName = arctifactName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    //TODO hacer esto mas prolijo
    public String cronoExpression(){
        return "0" + " " + min + " " + hour + " " + "?" + " " + "*" + " " + days;
    }

    public JobKey getJobKey() {
        return jobKey;
    }

    public void setJobKey(JobKey jobKey) {
        this.jobKey = jobKey;
    }

    @Override
    public boolean equals(Object obj) {
        return this.roomName.equals(((CronJob)obj).getRoomName())
                && this.arctifactName.equals(((CronJob)obj).getArctifactName())
                && this.hour.equals(((CronJob)obj).getHour())
                && this.min.equals(((CronJob)obj).getMin())
                && this.days.equals(((CronJob)obj).getDays());
    }
}
