package server.home.model;

import server.home.exeption.UnknownArtifactExeption;

import java.util.List;
import java.util.Optional;

public class Room {

    private List<Artifact> artifacts;
    private String name;

    public Room() {
    }

    public Room(List<Artifact> artifacts, String name) {
        this.artifacts = artifacts;
        this.name = name;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artifact getArtifact(String name) {
        Optional<Artifact> optional = this.artifacts.stream().filter(x -> x.getName().equals(name)).findFirst();
        if (!optional.isPresent()) {
            throw new UnknownArtifactExeption("El artefacto solicidato no existe");
        }
        return optional.get();
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Room) obj).getName());
    }
}
