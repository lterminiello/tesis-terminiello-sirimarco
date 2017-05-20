package server.home.board.node.mcu;


import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerAirConditionerInterface;

public class ControllerAirConditionerNodeMCU extends AbstracControlerNodeMcu implements ControllerAirConditionerInterface{

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerAirConditionerNodeMCU();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }
}
