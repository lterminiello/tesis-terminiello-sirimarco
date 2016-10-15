package server.home.service;

import server.home.json.JsonFactory;
import server.home.model.House;
import server.home.utils.HouseJsonLoader;

/**
 * Created by default on 10/10/16.
 */
public class HouseService {

    private House house;
    private JsonFactory jsonFactory;

    public HouseService(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
        this.house = HouseJsonLoader.getSchemeHouse();

    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public JsonFactory getJsonFactory() {
        return jsonFactory;
    }

    public void setJsonFactory(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

}
