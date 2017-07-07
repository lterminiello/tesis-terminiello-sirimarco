package com.sirimarco.terminiello.unlp.homecontroller.ui.login;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.NetworkData;

public class NetworkDataDialogFragment extends DialogFragment {

    public interface InfoDialogListener {
        void networkDataReciver(NetworkData networkData);
    }

    private InfoDialogListener infoDialogListener;
    private TextView txtSsid;
    private TextView txtPsk;
    private Dialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.network_data_dialog, null);
        txtPsk = view.findViewById(R.id.psk);
        txtSsid = view.findViewById(R.id.ssid);
        builder.setView(view);
        builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (infoDialogListener != null) {
                    NetworkData networkData = new NetworkData(txtSsid.getText().toString(), txtPsk.getText().toString());
                    infoDialogListener.networkDataReciver(networkData);
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        dialog = builder.create();
        return dialog;
    }

    public void setInfoDialogListener(InfoDialogListener infoDialogListener) {
        this.infoDialogListener = infoDialogListener;
    }
}
