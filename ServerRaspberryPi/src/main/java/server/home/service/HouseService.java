package server.home.service;

import server.home.model.House;
import server.home.utils.HouseJsonLoader;

/**
 * Created by default on 10/10/16.
 */
public class HouseService {

    private House house;

    public HouseService() {
        this.house = HouseJsonLoader.getSchemeHouse();
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
