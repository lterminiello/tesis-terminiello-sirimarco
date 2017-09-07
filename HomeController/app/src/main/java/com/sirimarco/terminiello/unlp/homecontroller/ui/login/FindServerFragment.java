package com.sirimarco.terminiello.unlp.homecontroller.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.connect.ObtainIpServerTask;
import com.sirimarco.terminiello.unlp.homecontroller.model.Confite;
import com.sirimarco.terminiello.unlp.homecontroller.model.IpDeviceInfo;
import com.sirimarco.terminiello.unlp.homecontroller.model.NetworkData;
import com.sirimarco.terminiello.unlp.homecontroller.ui.home.HomeActivity;
import com.sirimarco.terminiello.unlp.homecontroller.utils.GenerateUrlServer;
import com.sirimarco.terminiello.unlp.homecontroller.utils.HttpUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ThreadUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ToolsConnectionsInterface;
import com.skyfishjy.library.RippleBackground;

import java.util.List;

//FIXME  Para que no quede un quilombo de hilos se tendria que hacer un wmUtils y que se maneje todo ahi y fue
//TODO claramente lo voy a hacer villero
public class FindServerFragment extends Fragment implements ToolsConnectionsInterface<String>,NetworkDataDialogFragment.InfoDialogListener {

    private static final String NETWORK_SSID =  "\"" + "RPi_SERVER" + "\"";
    private static final String NETWORK_PASS = "\"" + "ladesiempre" + "\"";

    private TextView txtIp;
    private RippleBackground rippleBackground;
    private WifiManager wm;
    private String ip;
    private String ipRed;
    private String ipMask;
    private ObtainIpServerTask obtainIpServerTask;
    private IpDeviceInfo ipDeviceInfo;
    private Button btnRetry;
    private TextView txtInfo;
    private boolean firstFailServerFind = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_server_fragment, container, false);
        txtIp = view.findViewById(R.id.myIp);
        rippleBackground = view.findViewById(R.id.animationRipple);
        btnRetry = view.findViewById(R.id.btnRetry);
        txtInfo = view.findViewById(R.id.txtInfo);

        wm = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        ip = Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress);
        ipRed = Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress & wm.getDhcpInfo().netmask);
        ipMask = Formatter.formatIpAddress((wm.getDhcpInfo().ipAddress & wm.getDhcpInfo().netmask) | ~wm.getDhcpInfo().netmask);

        ipDeviceInfo = new IpDeviceInfo(ipRed, ipMask, ip);

        tryConnectToServer();

        return view;
    }

    private void serverAvailability(){

    }

    private void tryConnectToServer() {
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryConnectToServer();
                btnRetry.setVisibility(View.GONE);
            }
        });
        txtInfo.setText(R.string.searchServer);
        rippleBackground.setVisibility(View.VISIBLE);
        rippleBackground.startRippleAnimation();
        if (!wm.isWifiEnabled()) {
            wm.setWifiEnabled(true);
        }
        obtainIpServerTask = new ObtainIpServerTask(this,Confite.getInstance().getIpServer(getActivity()));
        ThreadUtils.executedRunnable(new Runnable() {
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
                        showRetryButton();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showRetryButton() {
        ThreadUtils.executeOnUIThread(this, new Runnable() {
            @Override
            public void run() {
                rippleBackground.stopRippleAnimation();
                btnRetry.setVisibility(View.VISIBLE);
                txtInfo.setText(R.string.noWifiConnect);
            }
        });
    }

    private void connectWiFiServer() {
        txtInfo.setText(R.string.connectToServerWifi);
        if(findNetworkConfigAndConnec()){
            ThreadUtils.executedRunnable(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    checkWifiServerEnable();
                }
            });
        }else{
            WifiConfiguration conf = new WifiConfiguration();
            conf.SSID = NETWORK_SSID;
            conf.preSharedKey = NETWORK_PASS;
            wm.addNetwork(conf);
            findNetworkConfigAndConnec();
        }
    }

    private boolean findNetworkConfigAndConnec(){
        List<WifiConfiguration> list = wm.getConfiguredNetworks();
        for (WifiConfiguration i : list) {
            if (i.SSID != null && i.SSID.equals(NETWORK_SSID)) {
                wm.disconnect();
                wm.enableNetwork(i.networkId, true);
                wm.reconnect();
                return true;
            }
        }
        return false;
    }

    public void checkWifiServerEnable() {
        ThreadUtils.executeOnUIThread(this, new Runnable() {
            @Override
            public void run() {
                if (wm.getConnectionInfo() == null || wm.getConnectionInfo().getNetworkId() < 0) {
                    txtInfo.setText(R.string.noFindServerWifi);
                    btnRetry.setVisibility(View.VISIBLE);
                    rippleBackground.stopRippleAnimation();
                    btnRetry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            connectWiFiServer();
                            btnRetry.setVisibility(View.GONE);
                        }
                    });
                } else {
                    tryConnectToServer();
                }
            }
        });
    }

    private void showDialogConnectWifiServer() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
        builder.setTitle(R.string.noServerInNetwork);
        builder.setMessage(R.string.noServerInNetworkDescription);
        builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                rippleBackground.startRippleAnimation();
                connectWiFiServer();
            }
        });
        builder.setNegativeButton(R.string.retry, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tryConnectToServer();
            }
        });
        builder.show();
        txtInfo.setText(R.string.noFindServer);
    }

    private boolean isWifiOfServer(){
        return NETWORK_SSID.equals(wm.getConnectionInfo().getSSID());
    }

    @Override
    public void onFinish(final String ipServer) {
        obtainIpServerTask.cancel(true);
        ThreadUtils.executeOnUIThread(this, new Runnable() {
            @Override
            public void run() {
                rippleBackground.stopRippleAnimation();
                if (ipServer != null) {
                    Confite.getInstance().setIpServer(getActivity(),ipServer);
                    rippleBackground.setVisibility(View.GONE);
                    startHomeActivity();
                } else {
                    if (firstFailServerFind) {
                        firstFailServerFind = false;
                        showDialogConnectWifiServer();
                    } else {
                        txtInfo.setText(R.string.noFindServer);
                        btnRetry.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void startHomeActivity(){
        if (isWifiOfServer()){
            showDialogNetwork();
        }else{
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            getActivity().startActivity(intent);
        }
    }

    @Override
    public void networkDataReciver(final NetworkData networkData) {
        HttpUtils.excutedUrl(GenerateUrlServer.getAddNetworkUrl(networkData));
        txtIp.setText(R.string.infoReconnect);
        ThreadUtils.executedRunnable(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(240000);
                    tryConnectToServer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showDialogNetwork(){
        NetworkDataDialogFragment networkDataDialogFragment = new NetworkDataDialogFragment();
        networkDataDialogFragment.setInfoDialogListener(this);
        networkDataDialogFragment.show(getActivity().getFragmentManager(),null);
    }
}
