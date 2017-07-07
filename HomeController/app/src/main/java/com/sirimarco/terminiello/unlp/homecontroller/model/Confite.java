package com.sirimarco.terminiello.unlp.homecontroller.model;


public class Confite {

    private static Confite confite = new Confite();
    private static final String PORT = "3047";
    private String ipServer;


    private Confite(){

    }

    public static Confite getInstance(){
        return confite;
    }

    public String getIpServer() {
        return ipServer;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public static String getPORT() {
        return PORT;
    }
}
