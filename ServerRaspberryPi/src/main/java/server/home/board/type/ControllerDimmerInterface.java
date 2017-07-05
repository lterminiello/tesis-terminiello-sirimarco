package server.home.board.type;

import server.home.model.Artifact;

public interface ControllerDimmerInterface extends AbstractControllerInterface{

    void on(Artifact artifact,Integer pwd);
    void off(Artifact artifact);
}
