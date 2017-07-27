package com.sirimarco.terminiello.unlp.homecontroller.model;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable {

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
        for (Artifact artifact : artifacts) {
            if (name.equals(artifact.getName())) {
                return artifact;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Room) obj).getName());
    }
}
