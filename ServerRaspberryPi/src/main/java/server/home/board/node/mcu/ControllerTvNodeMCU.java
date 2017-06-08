package server.home.board.node.mcu;


import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerTvInterface;

public class ControllerTvNodeMCU extends AbstractControllerNodeMcu implements ControllerTvInterface {

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerTvNodeMCU();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }
}
