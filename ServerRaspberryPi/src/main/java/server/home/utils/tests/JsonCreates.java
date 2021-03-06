package server.home.utils.tests;

import com.pi4j.io.gpio.RaspiPin;
import server.home.json.JsonFactory;
import server.home.model.*;
import server.home.utils.SchemeJsonLoader;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by lterminiello on 11/10/16.
 */
public class JsonCreates {

    public static void main(String[] args) throws IOException {
       /* SchemeJsonLoader schemeJsonLoader = new SchemeJsonLoader();
        House house = schemeJsonLoader.getSchemeHouse();
        Room room = house.getRooms().get(0);
        room.setName("cucaaaa");
        house.addRoom(room);
        schemeJsonLoader.setSchemeHouse(new JsonFactory().toJson(house));*/

        System.out.println(isReachableByTcp("www.google.com",80,5000));


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
