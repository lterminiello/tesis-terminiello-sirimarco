package com.sirimarco.terminiello.unlp.homecontroller.connect;


import android.os.AsyncTask;

import com.sirimarco.terminiello.unlp.homecontroller.model.IpDeviceInfo;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ConnectionUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ToolsConnectionsInterface;

public class ObtainIpServerTask extends AsyncTask<IpDeviceInfo,Void,String>{

    private ToolsConnectionsInterface toolsConnectionsInterface;

    public ObtainIpServerTask(ToolsConnectionsInterface toolsConnectionsInterface) {
        this.toolsConnectionsInterface = toolsConnectionsInterface;
    }

    @Override
    protected String doInBackground(IpDeviceInfo... ipDeviceInfos) {
        return ConnectionUtils.findServerOpen(ipDeviceInfos[0].getIpRed(),ipDeviceInfos[0].getIpMask());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        toolsConnectionsInterface.onFinish(s);
    }
}
