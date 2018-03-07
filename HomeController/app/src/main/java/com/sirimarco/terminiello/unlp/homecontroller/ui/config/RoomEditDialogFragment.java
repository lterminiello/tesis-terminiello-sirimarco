package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.Artifact;
import com.sirimarco.terminiello.unlp.homecontroller.model.PinRaspberry;
import com.sirimarco.terminiello.unlp.homecontroller.model.Room;
import com.sirimarco.terminiello.unlp.homecontroller.model.TypeArtifact;

import java.util.ArrayList;


public class RoomEditDialogFragment extends DialogFragment {

    private Button btnAccept;
    private Button btnCancel;
    private EditText name;
    private Dialog dialog;
    private Room roomEdit;

    private OnClickDialogRoomListener listener;

    public interface OnClickDialogRoomListener {
        void onClickAccept(Room room);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.room_dialog_fragment, null);
        builder.setView(view);
        name = view.findViewById(R.id.roomName);
        btnAccept = view.findViewById(R.id.accept);
        btnCancel = view.findViewById(R.id.cancel);
        dialog = builder.create();

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roomEdit == null) {
                    Room room = new Room();
                    room.setName(name.getText().toString());
                    room.setArtifacts(new ArrayList<Artifact>());
                    listener.onClickAccept(room);
                } else {
                    roomEdit.setName(name.getText().toString());
                    listener.onClickAccept(roomEdit);
                }
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (roomEdit != null) {
            name.setText(roomEdit.getName());
        }
        return dialog;
    }


    public void setOnClickDialogRoomListenerr(OnClickDialogRoomListener listener) {
        this.listener = listener;
    }


    public void setRoomEdit(Room roomEdit) {
        this.roomEdit = roomEdit;
    }
}