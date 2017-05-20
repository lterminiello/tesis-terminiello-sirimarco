package server.home.utils;

import server.home.board.node.mcu.*;
import server.home.board.rasp.*;
import server.home.board.type.AbstractControllerInterface;
import server.home.model.TypeArtifact;

public class ControllerFactory {

    public static AbstractControllerInterface getController(TypeArtifact typeArtifact, String idBoard) {
        return idBoard.contains(Constants.RASPBERRY) ? getControllerRasp(typeArtifact) : getControllerNodeMCU(typeArtifact);
    }

    private static AbstractControllerInterface getControllerRasp(TypeArtifact typeArtifact) {
        AbstractControllerInterface abstractController = null;
        switch (typeArtifact) {
            case LIGHT:
                abstractController = ControllerLightRasp.getInstance();
                break;
            case DIMMER:
                abstractController = ControllerDimmerRasp.getInstance();
                break;
            case AIR_CONDITIONER:
                abstractController = ControllerAirConditionerRasp.getInstance();
                break;
            case TV:
                abstractController = ControllerTvRasp.getInstance();
                break;
            case BLIND:
                abstractController = ControllerBlindRasp.getInstance();
                break;
            case OTHER:
                abstractController = ControllerOtherRasp.getInstance();
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
                abstractController = ControllerDimmerNodeMCU.getInstance();
                break;
            case AIR_CONDITIONER:
                abstractController = ControllerAirConditionerNodeMCU.getInstance();
                break;
            case TV:
                abstractController = ControllerTvNodeMCU.getInstance();
                break;
            case BLIND:
                abstractController = ControllerBlindNodeMCU.getInstance();
                break;
            case OTHER:
                abstractController = ControllerOtherNodeMCU.getInstance();
        }
        return abstractController;
    }

}
