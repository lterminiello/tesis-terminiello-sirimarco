package server.home.model;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import server.home.service.HouseService;
import server.home.utils.ApplicationContextProvider;

import java.util.List;

/**
 * Created by default on 18/05/17.
 */
public class CronJob implements Job {

    private String roomName;
    private String arctifactName;
    private String action;
    private String value;
    //Time
    private String min;
    private String hour;
    //TODO: puede ser numeros del 1 al 7, 1=domingo 7=sabado, o las primeras 3 letras del nombre del dia SUN-SAT. *<- es todos los dias
    //TODO: Para indicar varios dias se separa con -
    private List<String> days;

    public CronJob(String roomName, String arctifactName, String action, String value) {
        this.roomName = roomName;
        this.arctifactName = arctifactName;
        this.action = action;
        this.value = value;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        HouseService houseService = ApplicationContextProvider.getApplicationContext().getBean("houseService",HouseService.class);
        if (value != null){
            houseService.getHouse().getRoom(roomName).getArtifact(arctifactName).runAction(action, Integer.valueOf(value));
        }else {
            houseService.getHouse().getRoom(roomName).getArtifact(arctifactName).runAction(action, null);
        }
    }
}
