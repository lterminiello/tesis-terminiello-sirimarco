package server.home.utils;

import server.home.board.node.mcu.ControllerLightNodeMCU;
import server.home.board.rasp.ControllerLightRasp;
import server.home.board.AbstractController;
import server.home.model.PinRaspberry;
import server.home.model.TypeArtifact;

/**
 * Created by default on 08/10/16.
 */
public class ControllerFactory {

    public static AbstractController getController(TypeArtifact typeArtifact, String idBoard) {
        return idBoard.equals(Constants.RASPBERRY)?getControllerRasp(typeArtifact):getControllerNodeMCU(typeArtifact);
    }

    private static AbstractController getControllerRasp(TypeArtifact typeArtifact){
        AbstractController abstractController = null;
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



    private static AbstractController getControllerNodeMCU(TypeArtifact typeArtifact){
        AbstractController abstractController = null;
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
