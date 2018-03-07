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
import com.sirimarco.terminiello.unlp.homecontroller.model.TypeArtifact;


public class ArtifactEditDialogFragment extends DialogFragment {

    private Button btnAccept;
    private Button btnCancel;
    private EditText name;
    private EditText boar;
    private Spinner pin;
    private Spinner type;
    private Dialog dialog;
    private ArrayAdapter<PinRaspberry> pinArray;
    private ArrayAdapter<TypeArtifact> typeArray;
    private Artifact artifactEdit;

    private OnClickDialogArtifactListener listener;

    public interface OnClickDialogArtifactListener {
        void onClickAccept(Artifact artifact);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.artifact_dialog_fragment, null);
        builder.setView(view);
        name = view.findViewById(R.id.roomName);
        boar = view.findViewById(R.id.roomIdBoard);
        pin = view.findViewById(R.id.roomPin);
        type = view.findViewById(R.id.roomType);
        btnAccept = view.findViewById(R.id.accept);
        btnCancel = view.findViewById(R.id.cancel);

        pinArray = new ArrayAdapter<>(getActivity().getBaseContext(),
                android.R.layout.simple_list_item_1, PinRaspberry.values());
        pin.setAdapter(pinArray);

        typeArray = new ArrayAdapter<>(getActivity().getBaseContext(),
                android.R.layout.simple_list_item_1, TypeArtifact.values());

        type.setAdapter(typeArray);

        dialog = builder.create();
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (artifactEdit == null) {
                    Artifact artifact = new Artifact();
                    artifact.setName(name.getText().toString());
                    artifact.setIdBoard(boar.getText().toString());
                    artifact.setPin(((PinRaspberry) pin.getSelectedItem()));
                    artifact.setTypeArtifact(((TypeArtifact) type.getSelectedItem()));
                    listener.onClickAccept(artifact);
                } else {
                    artifactEdit.setName(name.getText().toString());
                    artifactEdit.setIdBoard(boar.getText().toString());
                    artifactEdit.setPin(((PinRaspberry) pin.getSelectedItem()));
                    artifactEdit.setTypeArtifact(((TypeArtifact) type.getSelectedItem()));
                    listener.onClickAccept(artifactEdit);
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

        if (artifactEdit != null) {
            name.setText(artifactEdit.getName());
            boar.setText(artifactEdit.getIdBoard());
            pin.setSelection(pinArray.getPosition(artifactEdit.getPin()));
            type.setSelection(typeArray.getPosition(artifactEdit.getTypeArtifact()));
        }
        return dialog;
    }


    public void setOnClickDialogArtifactListener(OnClickDialogArtifactListener listener) {
        this.listener = listener;
    }


    public void setArtifactEdit(Artifact artifactEdit) {
        this.artifactEdit = artifactEdit;
    }
}