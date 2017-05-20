package server.home.board.rasp;

import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerLightInterface;

public class ControllerLightRasp implements ControllerLightInterface{

    private static AbstractControllerInterface abstractController;
    private String state ="off";

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerLightRasp();
        }
        return abstractController;
    }

    @Override
    public String on() {
        state = "on";
        return "on";
    }

    @Override
    public String off() {
        state = "off";
        return "off";
    }

    @Override
    public String getState() {
        return state;
    }
}
