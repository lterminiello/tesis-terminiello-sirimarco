package server.home.board.node.mcu;

import server.home.model.Artifact;
import server.home.service.AnnouncementService;
import server.home.utils.ApplicationContextProvider;

public class AbstractControllerNodeMcu {


    private static final String PORT = "3048";

    public String getUrlWithArtifact(Artifact artifact, String action, String value){
        AnnouncementService announcementService = ApplicationContextProvider.getApplicationContext().getBean("announcementService", AnnouncementService.class);
        String ip = announcementService.getIpBoard(artifact.getIdBoard());
        int pin = artifact.getPin().getNumberPin();
        if (value != null){
            return ip+":"+PORT+"/"+artifact.getIdBoard()+"/"+pin+"/"+action+"?"+"value="+value;
        }else{
            return ip+":"+PORT+"/"+artifact.getIdBoard()+"/"+pin+"/"+action;
        }
    }
}
