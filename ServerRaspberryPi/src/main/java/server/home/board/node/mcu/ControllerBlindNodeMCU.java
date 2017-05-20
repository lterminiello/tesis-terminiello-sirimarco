package server.home.board.node.mcu;

import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerBlindInterface;

public class ControllerBlindNodeMCU implements ControllerBlindInterface {

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerBlindNodeMCU();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }
}
