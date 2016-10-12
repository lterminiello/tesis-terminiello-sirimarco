package server.home.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import server.home.json.JsonFactory;
import server.home.model.House;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by lterminiello on 11/10/16.
 */
public class HouseJsonLoader {

    public static House getSchemeHouse(){
        InputStream inputStream = HouseJsonLoader.class.getResourceAsStream("/HouseScheme.json");
        return new JsonFactory().fromJson(new InputStreamReader(inputStream),new TypeReference<House>() {});
    }

}
