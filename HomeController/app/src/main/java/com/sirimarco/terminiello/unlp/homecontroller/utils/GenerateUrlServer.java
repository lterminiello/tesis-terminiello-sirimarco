package com.sirimarco.terminiello.unlp.homecontroller.utils;

import com.sirimarco.terminiello.unlp.homecontroller.model.Artifact;
import com.sirimarco.terminiello.unlp.homecontroller.model.Confite;
import com.sirimarco.terminiello.unlp.homecontroller.model.NetworkData;
import com.sirimarco.terminiello.unlp.homecontroller.model.Room;

public class GenerateUrlServer {

    public static String getAddNetworkUrl(NetworkData networkData) {
        return "http://" + Confite.getInstance().getIpServer() + ":" + Confite.getPORT() + "/server/addNetwork?ssid=" + networkData.getSsdi() + "&psk=" + networkData.getPsk();
    }

    public static String getHouseSchemeUrl() {
        return "http://" + Confite.getInstance().getIpServer() + ":" + Confite.getPORT() + "/house/houseScheme";
    }

    public static String getArtifactActionUrl(Artifact artifact, Room room, String action, String power) {
        return "http://" + Confite.getInstance().getIpServer() + ":" + Confite.getPORT() + "/house/control?"
                + "roomName=" + room.getName()
                + "&arctifactName=" + artifact.getName()
                + "&action=" + action
                + "&power=" + power;
    }
}
