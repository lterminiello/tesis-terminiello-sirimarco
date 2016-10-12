package server.home.utils;

import com.pi4j.io.gpio.RaspiPin;
import server.home.json.JsonFactory;
import server.home.model.*;

import java.util.ArrayList;

/**
 * Created by lterminiello on 11/10/16.
 */
public class JsonCreates {

    public static void main(String[] args) {
        ArrayList<Artifact> artifacts = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        Artifact a = new Artifact(TypeArtifact.LIGHT, "raspberry", PinRaspberry.GPIO_1, "puta");
        Room room = new Room(artifacts,"puta");
        rooms.add(room);
        artifacts.add(a);
        System.out.println(new JsonFactory().toJson(new House(rooms)));
    }
}
