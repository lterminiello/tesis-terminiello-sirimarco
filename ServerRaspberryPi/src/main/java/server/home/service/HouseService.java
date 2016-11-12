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
    private HouseJsonLoader houseJsonLoader;

    public HouseService(JsonFactory jsonFactory,HouseJsonLoader houseJsonLoader) {
        this.jsonFactory = jsonFactory;
        this.houseJsonLoader = houseJsonLoader;
        this.house = houseJsonLoader.getSchemeHouse();

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

    public HouseJsonLoader getHouseJsonLoader() {
        return houseJsonLoader;
    }

    public void setHouseJsonLoader(HouseJsonLoader houseJsonLoader) {
        this.houseJsonLoader = houseJsonLoader;
    }

    public void setHouseScheme(String jsonHouse){
        houseJsonLoader.setSchemeHouse(jsonHouse);
        this.house = houseJsonLoader.getSchemeHouse();
    }

}
