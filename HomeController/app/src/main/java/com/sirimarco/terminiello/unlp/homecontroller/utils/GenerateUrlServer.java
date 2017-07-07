package com.sirimarco.terminiello.unlp.homecontroller.utils;

import com.sirimarco.terminiello.unlp.homecontroller.model.Confite;
import com.sirimarco.terminiello.unlp.homecontroller.model.NetworkData;

public class GenerateUrlServer {

    public static String getAddNetworkUrl(NetworkData networkData){
        return "http://"+ Confite.getInstance().getIpServer()+":"+Confite.getPORT()+"/server/addNetwork?ssid="+networkData.getSsdi()+"&psk="+networkData.getPsk();
    }

    public static String getHouseSchemeUrl(){
        return "http://"+ Confite.getInstance().getIpServer()+":"+Confite.getPORT()+"/house/houseScheme";
    }
}
