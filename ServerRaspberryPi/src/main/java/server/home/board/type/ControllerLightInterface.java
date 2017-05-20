package server.home.board.type;

import server.home.model.Artifact;

public interface ControllerLightInterface extends AbstractControllerInterface {

    public String on(Artifact artifact);
    public String off(Artifact artifact);
}
