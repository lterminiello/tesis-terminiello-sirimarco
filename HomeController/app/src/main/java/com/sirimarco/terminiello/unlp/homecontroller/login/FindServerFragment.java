package com.sirimarco.terminiello.unlp.homecontroller.login;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.connect.ObtainIpServerTask;
import com.sirimarco.terminiello.unlp.homecontroller.model.IpDeviceInfo;
import com.sirimarco.terminiello.unlp.homecontroller.utils.SafeExecuteWrapperRunnable;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ThradUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ToolsConnectionsInterface;
import com.skyfishjy.library.RippleBackground;

import java.util.List;

public class FindServerFragment extends Fragment implements ToolsConnectionsInterface<String> {

    private TextView txtIp;
    private RippleBackground rippleBackground;
    private WifiManager wm;
    private String ip;
    private String ipRed;
    private String ipMask;
    private ObtainIpServerTask obtainIpServerTask;
    private IpDeviceInfo ipDeviceInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_server_fragment, container, false);
        txtIp = view.findViewById(R.id.myIp);
        rippleBackground = view.findViewById(R.id.animationRipple);
        wm = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        ip = Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress);
        ipRed = Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress & wm.getDhcpInfo().netmask);
        ipMask = Formatter.formatIpAddress((wm.getDhcpInfo().ipAddress & wm.getDhcpInfo().netmask) | ~wm.getDhcpInfo().netmask);
        obtainIpServerTask = new ObtainIpServerTask(this);

        ipDeviceInfo = new IpDeviceInfo(ipRed, ipMask, ip);

        rippleBackground.startRippleAnimation();

        tryConnectToServer();

        return view;
    }

    private void tryConnectToServer(){
        if (!wm.isWifiEnabled()) {
            wm.setWifiEnabled(true);
            ThradUtils.executedRunnable(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(6000);
                        ip = Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress);
                        ipRed = Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress & wm.getDhcpInfo().netmask);
                        ipMask = Formatter.formatIpAddress((wm.getDhcpInfo().ipAddress & wm.getDhcpInfo().netmask) | ~wm.getDhcpInfo().netmask);
                        ipDeviceInfo = new IpDeviceInfo(ipRed, ipMask, ip);
                        if (!ip.equals("0.0.0.0")) {
                            obtainIpServerTask.execute(ipDeviceInfo);
                        } else {
                            Toast.makeText(getContext(), "No hay conexion a internet bonton reitentar", Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            obtainIpServerTask.execute(ipDeviceInfo);

        }
    }

    private void connectWiFiServer(){
        String networkSSID = "RPi_SERVER";
        String networkPass = "ladesiempre";
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";
        conf.preSharedKey = "\""+ networkPass +"\"";
        wm.addNetwork(conf);
        List<WifiConfiguration> list = wm.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                wm.disconnect();
                wm.enableNetwork(i.networkId, true);
                wm.reconnect();
                break;
            }
        }
    }


    @Override
    public void onFinish(final String info) {
        ThradUtils.executeOnUIThread(this, new Runnable() {
            @Override
            public void run() {
                if (info != null) {
                    txtIp.setText(info);
                    rippleBackground.stopRippleAnimation();
                    rippleBackground.setVisibility(View.GONE);
                }else{
                    //TODO DIALOGO EXPLICANDO acepta y hace la conexion forzada
                    connectWiFiServer();
                }
            }
        });
    }


}
