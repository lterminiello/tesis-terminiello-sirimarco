package server.home.service;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Timer;
import java.util.TimerTask;

public class ServerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);


    public ServerService() {
    }

    public String addNetwork(String ssid, String psk){
        StringBuffer output = new StringBuffer();
        String[] command ={ "bash", "-c", "/home/pi/addNetwork.sh "+ssid+" "+psk };
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

    private boolean isConnectedInternet(){
        return isReachableByTcp("www.google.com",80,5000);
    }

    public void serverStatus(){
        LOGGER.info("Check Connection");
        GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput pinOk = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "status", PinState.HIGH);
        GpioPinDigitalOutput pinDown = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "status", PinState.HIGH);
        if (isConnectedInternet()){
            pinOk.high();
            pinDown.low();
            LOGGER.info("Connection its ok");
        } else {
            pinOk.low();
            pinDown.high();
            LOGGER.info("Not internet connect");
        }
    }

    private static boolean isReachableByTcp(String host, int port, int timeout) {
        try {
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            socket.connect(socketAddress, timeout);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
