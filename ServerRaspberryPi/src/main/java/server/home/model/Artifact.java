package server.home.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import server.home.board.AbstractController;
import server.home.exeption.NotAllowedActionExeption;
import server.home.utils.ControllerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by default on 08/10/16.
 */
public class Artifact {

    private TypeArtifact typeArtifact;
    private String name;
    private PinRaspberry pin;
    private String idBoard;
    @JsonIgnore
    private AbstractController controller;
    @JsonIgnore
    private Class paramPower[] = {Integer.TYPE};
    @JsonIgnore
    private Class noParam[] = {};

    public Artifact() {
    }


    public Artifact(TypeArtifact typeArtifact, String idBoard, PinRaspberry pin, String name) {
        this.pin = pin;
        this.idBoard = idBoard;
        this.typeArtifact = typeArtifact;
        this.controller = ControllerFactory.getController(typeArtifact, pin, idBoard);
        this.name = name;
    }

    public String runAction(@NotNull String action, @Nullable Integer pwd) {
        if (controller == null) {
            this.controller = ControllerFactory.getController(typeArtifact, pin, idBoard);
        }
        Method method;
        String response = null;
        try {
            if (pwd == null) {
                method = controller.getClass().getDeclaredMethod(action, noParam);
                response = (String) method.invoke(controller);
            } else {
                method = controller.getClass().getDeclaredMethod(action, paramPower);
                response = (String) method.invoke(controller, pwd);
            }
        } catch (NoSuchMethodException e) {
            throw new NotAllowedActionExeption("La accion solicitada no esta disponible para este artefacto");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return response;
    }

    public TypeArtifact getTypeArtifact() {
        return typeArtifact;
    }

    public void setTypeArtifact(TypeArtifact typeArtifact) {
        this.typeArtifact = typeArtifact;
    }

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
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

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Artifact) obj).getName());
    }
}
