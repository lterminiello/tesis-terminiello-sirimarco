package server.home.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerService {

    public ServerService() {
    }

    public String addNetwork(String ssid, String psk){
        StringBuffer output = new StringBuffer();
        String[] command ={ "bash", "-c", "/home/pi/addNetwork.sh "+ssid+" "+psk };

        System.out.println(command);
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}
