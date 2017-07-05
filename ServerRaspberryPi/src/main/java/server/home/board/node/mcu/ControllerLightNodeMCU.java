package server.home.board.node.mcu;


import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerLightInterface;
import server.home.model.Artifact;
import server.home.service.HttpClientService;
import server.home.utils.ApplicationContextProvider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ControllerLightNodeMCU extends AbstractControllerNodeMcu implements ControllerLightInterface {

    private static AbstractControllerInterface abstractController;
    private String state;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerLightNodeMCU();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return state;
    }


    //TODO Thread.currentThread().getStackTrace()[1].getMethodName() ver de usar este metodo
    @Override
    public void on(Artifact artifact) {
        HttpClientService httpClientService = ApplicationContextProvider.getApplicationContext().getBean("httpClientService", HttpClientService.class);
        httpClientService.getResponceFromGet(getUrlWithArtifact(artifact, "on",null));
        state = "on";
    }

    @Override
    public void off(Artifact artifact) {
        HttpClientService httpClientService = ApplicationContextProvider.getApplicationContext().getBean("httpClientService", HttpClientService.class);
        httpClientService.getResponceFromGet(getUrlWithArtifact(artifact, "off",null));
        state = "off";
    }
}
