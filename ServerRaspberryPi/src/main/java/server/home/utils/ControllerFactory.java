package server.home.utils;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import server.home.controller.board.node.mcu.ControllerLightNodeMCU;
import server.home.controller.board.rasp.ControllerLightRasp;
import server.home.controller.board.type.ControllerInterface;
import server.home.model.PinRaspberry;
import server.home.model.TypeArtifact;

/**
 * Created by default on 08/10/16.
 */
public class ControllerFactory {

    public static ControllerInterface getController(TypeArtifact typeArtifact, PinRaspberry pin, String idBoard) {
        return idBoard.equals("raspberry")?getControllerRasp(typeArtifact,pin,idBoard):getControllerNodeMCU(typeArtifact,pin,idBoard);
    }

    private static ControllerInterface getControllerRasp(TypeArtifact typeArtifact, PinRaspberry pin, String idBoard){
        ControllerInterface controllerInterface = null;
        switch (typeArtifact) {
            case LIGHT:
                controllerInterface = new ControllerLightRasp();
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
        return controllerInterface;
    }



    private static ControllerInterface getControllerNodeMCU(TypeArtifact typeArtifact, PinRaspberry pin, String idBoard){
        ControllerInterface controllerInterface = null;
        switch (typeArtifact) {
            case LIGHT:
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
        return controllerInterface;
    }

}
