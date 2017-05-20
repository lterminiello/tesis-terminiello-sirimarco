package server.home.board.rasp;

import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerBlindInterface;

public class ControllerBlindRasp implements ControllerBlindInterface {

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerBlindRasp();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }
}
