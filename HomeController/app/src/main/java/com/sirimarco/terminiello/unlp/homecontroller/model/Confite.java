package com.sirimarco.terminiello.unlp.homecontroller.model;


import android.app.Activity;

import com.sirimarco.terminiello.unlp.homecontroller.utils.SharedPreferencesUtils;

public class Confite {

    private static final String IP_KEY = "ip";

    private static Confite confite = new Confite();
    private static final String PORT = "3047";
    private String ipServer;


    private Confite(){

    }

    public static Confite getInstance(){
        return confite;
    }

    public String getIpServer(Activity activity) {
        if (ipServer == null){
            ipServer = SharedPreferencesUtils.getStringValue(activity,IP_KEY);
        }
        return ipServer;
    }

    public String getIpServer(){
        return ipServer;
    }

    public void setIpServer(Activity activity,String ipServer) {
        SharedPreferencesUtils.setStringValue(activity,IP_KEY,ipServer);
        this.ipServer = ipServer;
    }

    public static String getPORT() {
        return PORT;
    }
}
