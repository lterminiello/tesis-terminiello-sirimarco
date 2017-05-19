package server.home.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import server.home.json.JsonFactory;
import server.home.model.CronJob;
import server.home.model.House;

import java.io.*;
import java.util.List;

//TODO: cambiar nombre a a SchemeJsonLoader
public class HouseJsonLoader {


    //TODO ver donde se pone el archivo cuando se genera el jar, ejecutandolo por ide esta dentro de la carpeta target
    public House getSchemeHouse(){
        InputStream inputStream = HouseJsonLoader.class.getResourceAsStream("/HouseScheme.json");
        return new JsonFactory().fromJson(new InputStreamReader(inputStream),new TypeReference<House>() {});
    }


    public void setSchemeHouse(String json) {
        File archivo = new File(HouseJsonLoader.class.getResource("/HouseScheme.json").getPath());
        persist(archivo,json);

    }

    public List<CronJob> getSchemeCronos(){
        InputStream inputStream = HouseJsonLoader.class.getResourceAsStream("/CronosScheme.json");
        return new JsonFactory().fromJson(new InputStreamReader(inputStream),new TypeReference<List<CronJob>>() {});
    }

    public void setSchemeCronos(String json) {
        File archivo = new File(HouseJsonLoader.class.getResource("/CronosScheme.json").getPath());
        persist(archivo,json);
    }

    private void persist(File archivo, String json){
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
