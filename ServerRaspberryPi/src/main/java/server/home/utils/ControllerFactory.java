package server.home.utils;

import server.home.board.node.mcu.ControllerLightNodeMCU;
import server.home.board.rasp.ControllerLightRasp;
import server.home.board.type.AbstractControllerInterface;
import server.home.model.TypeArtifact;

public class ControllerFactory {

    public static AbstractControllerInterface getController(TypeArtifact typeArtifact, String idBoard) {
        return idBoard.equals(Constants.RASPBERRY) ? getControllerRasp(typeArtifact) : getControllerNodeMCU(typeArtifact);
    }

    private static AbstractControllerInterface getControllerRasp(TypeArtifact typeArtifact) {
        AbstractControllerInterface abstractController = null;
        switch (typeArtifact) {
            case LIGHT:
                abstractController = ControllerLightRasp.getInstance();
                break;
            case DIMMER:
                break;
            case AIR_CONDITIONER:
                break;
            case TV:
                break;
            case BLIND:
                break;
            case OTHER:

        }
        return abstractController;
    }


    private static AbstractControllerInterface getControllerNodeMCU(TypeArtifact typeArtifact) {
        AbstractControllerInterface abstractController = null;
        switch (typeArtifact) {
            case LIGHT:
                abstractController = ControllerLightNodeMCU.getInstance();
                break;
            case DIMMER:
                break;
            case AIR_CONDITIONER:
                break;
            case TV:
                break;
            case BLIND:
                break;
            case OTHER:
                return null;
        }
        return abstractController;
    }

}
