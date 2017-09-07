package server.home.board.rasp;

import server.home.board.type.AbstractControllerInterface;
import server.home.board.type.ControllerDimmerInterface;
import server.home.model.Artifact;

public class ControllerDimmerRasp implements ControllerDimmerInterface {

    private static AbstractControllerInterface abstractController;

    public static AbstractControllerInterface getInstance() {
        if (abstractController == null){
            abstractController = new ControllerDimmerRasp();
        }
        return abstractController;
    }

    @Override
    public String getState() {
        return "";
    }

    @Override
    public void on(Artifact artifact, Integer pwd) {

    }

}
