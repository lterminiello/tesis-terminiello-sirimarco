package com.sirimarco.terminiello.unlp.homecontroller.model;


public class NetworkData {

    private String ssdi;
    private String psk;

    public NetworkData(String ssdi, String psk) {
        this.ssdi = ssdi;
        this.psk = psk;
    }

    public String getSsdi() {
        return ssdi;
    }

    public void setSsdi(String ssdi) {
        this.ssdi = ssdi;
    }

    public String getPsk() {
        return psk;
    }

    public void setPsk(String psk) {
        this.psk = psk;
    }
}
