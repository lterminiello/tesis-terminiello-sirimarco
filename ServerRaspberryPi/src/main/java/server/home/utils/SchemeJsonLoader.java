package server.home.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.home.json.JsonFactory;
import server.home.model.CronManager;
import server.home.model.House;

import java.io.*;

public class SchemeJsonLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemeJsonLoader.class);


    //TODO ver donde se pone el archivo cuando se genera el jar, ejecutandolo por ide esta dentro de la carpeta target
    public House getSchemeHouse() {
        InputStream inputStream = SchemeJsonLoader.class.getResourceAsStream("/HouseScheme.json");
        return new JsonFactory().fromJson(new InputStreamReader(inputStream), new TypeReference<House>() {
        });
    }


    public void setSchemeHouse(String json) {
        File archivo = new File(SchemeJsonLoader.class.getResource("/HouseScheme.json").getPath());
        persist(archivo, json);

    }

    public CronManager getSchemeCronos() {
        InputStream inputStream = SchemeJsonLoader.class.getResourceAsStream("/CronosScheme.json");
        return new JsonFactory().fromJson(new InputStreamReader(inputStream), new TypeReference<CronManager>() {
        });
    }

    public void setSchemeCronos(String json) {
        File archivo = new File(SchemeJsonLoader.class.getResource("/CronosScheme.json").getPath());
        persist(archivo, json);
    }

    private void persist(File archivo, String json) {
        BufferedWriter bw;
        if (archivo.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                LOGGER.info(json);
                LOGGER.info("-------------> ",archivo);
                bw.write(json);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
