package server.home.board.node.mcu;


import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerLightInterface;
import server.home.model.Artifact;
import server.home.service.HttpClientService;
import server.home.utils.ApplicationContextProvider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ControllerLightNodeMCU extends AbstracControlerNodeMcu implements ControllerLightInterface {

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerLightNodeMCU();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }


    @Override
    public String on(Artifact artifact) {
        HttpClientService httpClientService = ApplicationContextProvider.getApplicationContext().getBean("httpClientService", HttpClientService.class);
        InputStreamReader inputStreamReader = httpClientService.getResponceFromGet(getUrlWithArtifact(artifact, "on",null));
        return new BufferedReader(inputStreamReader)
                .lines().collect(Collectors.joining("\n"));
    }

    @Override
    public String off(Artifact artifact) {
        return null;
    }
}
