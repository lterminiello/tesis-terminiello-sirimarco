package server.home.board.rasp;

import server.home.board.AbstractController;
import server.home.board.type.ControllerLightInterface;

/**
 * Created by default on 08/10/16.
 */
public class ControllerLightRasp extends AbstractController implements ControllerLightInterface{

    private static AbstractController abstractController;

    public static AbstractController getInstance() {
        if (abstractController == null){
            abstractController = new ControllerLightRasp();
        }
        return abstractController;
    }

    @Override
    public String on() {
        return "on";
    }

    @Override
    public String off() {
        return "off";
    }
}
