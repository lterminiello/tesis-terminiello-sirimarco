package server.home.service;

import server.home.json.JsonFactory;
import server.home.model.House;
import server.home.utils.SchemeJsonLoader;

public class HouseService {

    private House house;
    private JsonFactory jsonFactory;
    private SchemeJsonLoader schemeJsonLoader;

    public HouseService(JsonFactory jsonFactory, SchemeJsonLoader schemeJsonLoader) {
        this.jsonFactory = jsonFactory;
        this.schemeJsonLoader = schemeJsonLoader;
        this.house = schemeJsonLoader.getSchemeHouse();
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

    public void setHouseScheme(String jsonHouse) {
        schemeJsonLoader.setSchemeHouse(jsonHouse);
        this.house = schemeJsonLoader.getSchemeHouse();
    }

}
