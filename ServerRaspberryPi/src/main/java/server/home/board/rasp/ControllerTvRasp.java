package server.home.board.rasp;


import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerTvInterface;

public class ControllerTvRasp implements ControllerTvInterface {

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerTvRasp();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }
}
