package com.sirimarco.terminiello.unlp.homecontroller.utils;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ConnectionUtils {

    public static String findServerOpen(String ipRed, String ipBroadcast) {
        String ip;
        long baseIp = ipToLong(ipRed);
        long broadcastIp = ipToLong(ipBroadcast);
        while (baseIp!= broadcastIp){
            ip = longToIp(baseIp);
            if (isReachableByTcp(ip, 3047, 100)) {
                return ip;
            }
            baseIp++;
        }
        return null;
    }

    private static boolean isReachableByTcp(String host, int port, int timeout) {
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

    public static long ipToLong(String ipAddress) {
        long result = 0;
        String[] atoms = ipAddress.split("\\.");
        for (int i = 3; i >= 0; i--) {
            result |= (Long.parseLong(atoms[3 - i]) << (i * 8));
        }
        return result & 0xFFFFFFFF;
    }

    public static String longToIp(long ip) {
        StringBuilder sb = new StringBuilder(15);
        for (int i = 0; i < 4; i++) {
            sb.insert(0, Long.toString(ip & 0xff));
            if (i < 3) {
                sb.insert(0, '.');
            }
            ip >>= 8;
        }
        return sb.toString();
    }
}
