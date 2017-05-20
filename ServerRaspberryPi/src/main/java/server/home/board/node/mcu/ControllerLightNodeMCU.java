package server.home.board.node.mcu;


import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerLightInterface;

public class ControllerLightNodeMCU implements ControllerLightInterface {

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
    public String on() {
        return null;
    }

    @Override
    public String off() {
        return null;
    }
}
