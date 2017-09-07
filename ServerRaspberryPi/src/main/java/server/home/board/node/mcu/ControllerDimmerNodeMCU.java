package server.home.board.node.mcu;

import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerDimmerInterface;
import server.home.model.Artifact;
import server.home.service.HttpClientService;
import server.home.utils.ApplicationContextProvider;

public class ControllerDimmerNodeMCU extends AbstractControllerNodeMcu implements ControllerDimmerInterface {

    private static AbstractControllerInterface abstractController;
    private String state;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerDimmerNodeMCU();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void on(Artifact artifact, Integer pwd) {
        HttpClientService httpClientService = ApplicationContextProvider.getApplicationContext().getBean("httpClientService", HttpClientService.class);
        httpClientService.getResponceFromGet(getUrlWithArtifact(artifact, "on",pwd.toString()));
        state = pwd.toString();
    }

}
