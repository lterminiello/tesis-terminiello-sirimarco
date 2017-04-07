package server.home.board.rasp;

import server.home.board.AbstractController;

/**
 * Created by default on 08/10/16.
 */
public class ControllerDimmerRasp extends AbstractController {

    private static AbstractController abstractController;

    public static AbstractController getInstance() {
        if (abstractController == null){
            abstractController = new ControllerDimmerRasp();
        }
        return abstractController;
    }
}
