package server.home.board.rasp;

import server.home.board.AbstractController;
import server.home.board.type.ControllerLightInterface;

/**
 * Created by default on 08/10/16.
 */
public class ControllerLightRasp extends AbstractController implements ControllerLightInterface{

    private static AbstractController abstractController;
    private String state ="off";

    public static AbstractController getInstance() {
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
