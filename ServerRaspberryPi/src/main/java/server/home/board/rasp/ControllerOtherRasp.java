package server.home.board.rasp;


import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerOtherInterface;

public class ControllerOtherRasp implements ControllerOtherInterface {

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerOtherRasp();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }
}
