package com.sirimarco.terminiello.unlp.homecontroller.model;

public class IpDeviceInfo {

    private String ipRed;
    private String ipMask;
    private String ipDevice;

    public IpDeviceInfo(String ipRed, String ipMask, String ipDevice) {
        this.ipRed = ipRed;
        this.ipMask = ipMask;
        this.ipDevice = ipDevice;
    }

    public String getIpRed() {
        return ipRed;
    }

    public void setIpRed(String ipRed) {
        this.ipRed = ipRed;
    }

    public String getIpMask() {
        return ipMask;
    }

    public void setIpMask(String ipMask) {
        this.ipMask = ipMask;
    }

    public String getIpDevice() {
        return ipDevice;
    }

    public void setIpDevice(String ipDevice) {
        this.ipDevice = ipDevice;
    }
}
