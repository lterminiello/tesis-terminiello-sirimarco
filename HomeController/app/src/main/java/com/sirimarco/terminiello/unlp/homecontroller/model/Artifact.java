package com.sirimarco.terminiello.unlp.homecontroller.model;

import java.io.Serializable;

public class Artifact implements Serializable {

    private TypeArtifact typeArtifact;
    private String name;
    private PinRaspberry pin;
    private String idBoard;
    private String stateArtifact;


    public Artifact() {
    }

    public TypeArtifact getTypeArtifact() {
        return typeArtifact;
    }

    public void setTypeArtifact(TypeArtifact typeArtifact) {
        this.typeArtifact = typeArtifact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PinRaspberry getPin() {
        return pin;
    }

    public void setPin(PinRaspberry pin) {
        this.pin = pin;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }


    public void setStateArtifact(String stateArtifact) {
        this.stateArtifact = stateArtifact;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Artifact) obj).getName());
    }
}
