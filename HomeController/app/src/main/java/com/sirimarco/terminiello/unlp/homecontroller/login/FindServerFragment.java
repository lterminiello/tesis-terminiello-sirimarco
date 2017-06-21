package com.sirimarco.terminiello.unlp.homecontroller.login;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.connect.ObtainIpServerTask;
import com.sirimarco.terminiello.unlp.homecontroller.model.IpDeviceInfo;
import com.sirimarco.terminiello.unlp.homecontroller.utils.SafeExecuteWrapperRunnable;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ServerUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ToolsConnectionsInterface;

public class FindServerFragment extends Fragment implements ToolsConnectionsInterface<String> {

    private TextView txtIp;
    private TextView txtIpMask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_server_fragment, container, false);
        txtIp = view.findViewById(R.id.myIp);
        txtIpMask = view.findViewById(R.id.myIpMask);
        WifiManager wm = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress);
        String ipRed = Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress & wm.getDhcpInfo().netmask);
        String ipMask = Formatter.formatIpAddress((wm.getDhcpInfo().ipAddress & wm.getDhcpInfo().netmask) | ~wm.getDhcpInfo().netmask);

        ObtainIpServerTask obtainIpServerTask = new ObtainIpServerTask(this);

        IpDeviceInfo ipDeviceInfo = new IpDeviceInfo(ipRed, ipMask, ip);

        obtainIpServerTask.execute(ipDeviceInfo);
        return view;
    }


    @Override
    public void onFinish(final String info) {
        executeOnUIThread(new Runnable() {
            @Override
            public void run() {
                txtIp.setText(info);
            }
        });
    }


    public void executeOnUIThread(Runnable runnable) {
        Activity activity = this.getActivity();
        if (activity != null) {
            activity.runOnUiThread(new SafeExecuteWrapperRunnable(this, runnable));
        }
    }
}
