package com.sirimarco.terminiello.unlp.homecontroller.model;

import java.io.Serializable;

public enum TypeArtifact implements Serializable {
    LIGHT,
    DIMMER,
    AIR_CONDITIONER,
    BLIND,
    TV,
    OTHER;

    TypeArtifact() {
    }
}
