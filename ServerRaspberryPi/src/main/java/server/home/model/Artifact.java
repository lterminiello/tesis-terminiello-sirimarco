package server.home.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import server.home.board.type.AbstractControllerInterface;
import server.home.exeption.NotAllowedActionExeption;
import server.home.utils.ControllerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Artifact {

    private TypeArtifact typeArtifact;
    private String name;
    private PinRaspberry pin;
    private String idBoard;
    private String stateArtifact;
    @JsonIgnore
    private AbstractControllerInterface controller;
    @JsonIgnore
    private Class paramPower[] = {Artifact.class,Integer.TYPE};
    @JsonIgnore
    private Class noParam[] = {Artifact.class};

    public Artifact() {
    }


    public Artifact(TypeArtifact typeArtifact, String idBoard, PinRaspberry pin, String name) {
        this.pin = pin;
        this.idBoard = idBoard;
        this.typeArtifact = typeArtifact;
        this.controller = ControllerFactory.getController(typeArtifact, idBoard);
        this.stateArtifact = controller.getState();
        this.name = name;
    }

    public String runAction(@NotNull String action, @Nullable Integer pwd) {
        if (controller == null) {
            this.controller = ControllerFactory.getController(typeArtifact, idBoard);
        }
        Method method;
        String response = null;
        try {
            if (pwd == null) {
                method = controller.getClass().getDeclaredMethod(action, noParam);
                response = (String) method.invoke(controller,this);
            } else {
                method = controller.getClass().getDeclaredMethod(action, paramPower);
                response = (String) method.invoke(controller,this, pwd);
            }
        } catch (NoSuchMethodException e) {
            throw new NotAllowedActionExeption("La accion solicitada no esta disponible para este artefacto");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        stateArtifact = controller.getState();
        return response;
    }

    public TypeArtifact getTypeArtifact() {
        return typeArtifact;
    }

    public void setTypeArtifact(TypeArtifact typeArtifact) {
        this.typeArtifact = typeArtifact;
    }

    public AbstractControllerInterface getController() {
        return controller;
    }

    public void setController(AbstractControllerInterface controller) {
        this.controller = controller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class[] getParamPower() {
        return paramPower;
    }

    public void setParamPower(Class[] paramPower) {
        this.paramPower = paramPower;
    }

    public Class[] getNoParam() {
        return noParam;
    }

    public void setNoParam(Class[] noParam) {
        this.noParam = noParam;
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

    public String getStateArtifact() {
        if (controller == null) {
            this.controller = ControllerFactory.getController(typeArtifact, idBoard);
        }
        stateArtifact = controller.getState();
        return stateArtifact;
    }

    public void setStateArtifact(String stateArtifact) {
        this.stateArtifact = stateArtifact;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Artifact) obj).getName());
    }
}
