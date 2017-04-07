package server.home.board.node.mcu;

import server.home.board.AbstractController;

/**
 * Created by default on 08/10/16.
 */
public class ControllerOtherNodeMCU extends AbstractController {

    private static AbstractController abstractController;

    public static AbstractController getInstance() {
        if (abstractController == null){
            abstractController = new ControllerOtherNodeMCU();
        }
        return abstractController;
    }
}
