package server.home.utils.tests;

import com.pi4j.io.gpio.RaspiPin;
import server.home.json.JsonFactory;
import server.home.model.*;
import server.home.utils.HouseJsonLoader;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by lterminiello on 11/10/16.
 */
public class JsonCreates {

    public static void main(String[] args) throws IOException {
        HouseJsonLoader houseJsonLoader = new HouseJsonLoader();
        House house = houseJsonLoader.getSchemeHouse();
        Room room = house.getRooms().get(0);
        room.setName("cucaaaa");
        house.addRoom(room);
        houseJsonLoader.setSchemeHouse(new JsonFactory().toJson(house));


    }


    //TODO esto es para el lado de la app, queda aca de modo ejemplo
    public void getIpServer() {
        int timeout = 100;
        for (int i = 1; i < 254; i++) {
            String host = "192.168.0." + i;
            if (isReachableByTcp(host, 8443, 100)) {
                System.out.println(host + " is reachable");
            }
        }
    }

    public static boolean isReachableByTcp(String host, int port, int timeout) {
        try {
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            socket.connect(socketAddress, timeout);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
