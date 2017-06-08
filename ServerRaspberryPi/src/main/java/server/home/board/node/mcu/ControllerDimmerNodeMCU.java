package server.home.board.node.mcu;

import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerDimmerInterface;

public class ControllerDimmerNodeMCU extends AbstractControllerNodeMcu implements ControllerDimmerInterface {

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerDimmerNodeMCU();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }
}
