package server.home.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import server.home.json.JsonFactory;
import server.home.model.House;

import java.io.*;

/**
 * Created by lterminiello on 11/10/16.
 */
public class HouseJsonLoader {

    public House getSchemeHouse(){
        InputStream inputStream = HouseJsonLoader.class.getResourceAsStream("/HouseScheme.json");
        return new JsonFactory().fromJson(new InputStreamReader(inputStream),new TypeReference<House>() {});
    }


    public void setSchemeHouse(String json) {
        File archivo = new File(HouseJsonLoader.class.getResource("/HouseScheme.json").getPath());
        BufferedWriter bw;
        if (archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(json);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
