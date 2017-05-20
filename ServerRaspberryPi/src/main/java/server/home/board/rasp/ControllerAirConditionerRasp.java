package server.home.board.rasp;


import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerAirConditionerInterface;

public class ControllerAirConditionerRasp  implements ControllerAirConditionerInterface{

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerAirConditionerRasp();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }
}
