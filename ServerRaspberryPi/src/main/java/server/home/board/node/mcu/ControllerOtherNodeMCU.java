package server.home.board.node.mcu;


import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerOtherInterface;

public class ControllerOtherNodeMCU extends AbstracControlerNodeMcu implements ControllerOtherInterface{

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerOtherNodeMCU();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }
}
