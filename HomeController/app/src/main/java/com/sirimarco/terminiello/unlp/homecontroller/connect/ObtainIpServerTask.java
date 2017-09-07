package com.sirimarco.terminiello.unlp.homecontroller.connect;


import android.os.AsyncTask;

import com.sirimarco.terminiello.unlp.homecontroller.model.IpDeviceInfo;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ConnectionUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ToolsConnectionsInterface;

public class ObtainIpServerTask extends AsyncTask<IpDeviceInfo,Void,String>{

    private ToolsConnectionsInterface toolsConnectionsInterface;
    private String ipServer;

    public ObtainIpServerTask(ToolsConnectionsInterface toolsConnectionsInterface,String ipServer) {
        this.toolsConnectionsInterface = toolsConnectionsInterface;
        this.ipServer = ipServer;
    }

    @Override
    protected String doInBackground(IpDeviceInfo... ipDeviceInfos) {
        if (ipServer != null && ConnectionUtils.isServerAvailability(ipServer)){
            return ipServer;
        }
        return ConnectionUtils.findServerOpen(ipDeviceInfos[0].getIpRed(),ipDeviceInfos[0].getIpMask());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        toolsConnectionsInterface.onFinish(s);
    }
}
