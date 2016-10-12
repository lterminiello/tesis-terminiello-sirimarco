package server.home.controller.board.rasp;

import server.home.controller.board.type.ControllerInterface;
import server.home.controller.board.type.ControllerLightInterface;

/**
 * Created by default on 08/10/16.
 */
public class ControllerLightRasp implements ControllerInterface, ControllerLightInterface {

    public ControllerLightRasp() {
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
