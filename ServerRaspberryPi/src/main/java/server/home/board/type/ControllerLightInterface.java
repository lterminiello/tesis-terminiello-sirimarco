package server.home.board.type;

import server.home.model.Artifact;

public interface ControllerLightInterface extends AbstractControllerInterface {

    void on(Artifact artifact);
    void off(Artifact artifact);
}
