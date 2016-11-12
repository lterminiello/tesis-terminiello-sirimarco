package server.home.service;

import org.python.antlr.ast.Str;

import java.util.HashMap;

/**
 * Created by lterminiello on 04/11/16.
 */
public class AnnouncementService {

    private HashMap<String,String> devices = new HashMap<>();
    private HashMap<String,String> boards = new HashMap<>();

    public AnnouncementService() {
    }

    public HashMap<String, String> getDevices() {
        return devices;
    }

    public void setDevices(HashMap<String, String> devices) {
        this.devices = devices;
    }

    public HashMap<String, String> getBoards() {
        return boards;
    }

    public void setBoards(HashMap<String, String> boards) {
        this.boards = boards;
    }

    public void addDevice(String name, String ip){
        devices.put(name,ip);
    }

    public void addBoard(String name, String ip){
        boards.put(name,ip);
    }

    public String getIpDevice(String name){
        return devices.get(name);
    }

    public String getIpBoard(String name){
        return boards.get(name);
    }

}
