package server.home.utils;

import com.pi4j.io.gpio.RaspiPin;
import server.home.json.JsonFactory;
import server.home.model.*;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by lterminiello on 11/10/16.
 */
public class JsonCreates {

    public static void main(String[] args) throws IOException {
        int timeout = 100;
        for (int i = 1; i < 255; i++) {
            String host = "10.221.20." + i;
            if (isReachableByTcp(host,3047,100)) {
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
