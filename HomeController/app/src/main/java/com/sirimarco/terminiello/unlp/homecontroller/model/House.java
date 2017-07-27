package com.sirimarco.terminiello.unlp.homecontroller.model;

import java.io.Serializable;
import java.util.List;

public class House implements Serializable {

    private List<Room> rooms;

    public House() {
    }

    public House(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public Room getRoom(String name) {
        for (Room room : rooms) {
            if (name.equals(room.getName())) {
                return room;
            }
        }
        return null;
    }
}
