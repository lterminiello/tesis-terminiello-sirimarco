package com.sirimarco.terminiello.unlp.homecontroller.ui.home;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.Artifact;
import com.sirimarco.terminiello.unlp.homecontroller.model.Room;
import com.sirimarco.terminiello.unlp.homecontroller.model.TypeArtifact;
import com.sirimarco.terminiello.unlp.homecontroller.utils.GenerateUrlServer;
import com.sirimarco.terminiello.unlp.homecontroller.utils.HttpUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ThreadUtils;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomItemViewHolder> {


    private Room room;

    public RoomAdapter(Room room) {
        this.room = room;
    }

    @Override
    public RoomItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artifact_item, parent, false);


        return new RoomItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RoomItemViewHolder holder, int position) {
        final Artifact artifact = room.getArtifacts().get(position);
        showCorrectCard(artifact.getTypeArtifact(), holder);
        holder.nameLuz.setText(artifact.getName());
        holder.nameDimmer.setText(artifact.getName());
        holder.nameOther.setText(artifact.getName());

        holder.cardLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.switchLuz.setChecked(!holder.switchLuz.isChecked());
            }
        });
        holder.cardOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.switchOther.setChecked(!holder.switchOther.isChecked());
            }
        });

        holder.seekBarDimmer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                HttpUtils.excutedUrl(GenerateUrlServer.getArtifactActionUrl(artifact, room,"on", String.valueOf(i)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                holder.iconDimmer.setImageResource(seekBar.getProgress() == 0 ? R.drawable.bulb_off : R.drawable.bulb_on);
            }
        });

        holder.switchLuz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                holder.iconLuz.setImageResource(isChecked ? R.drawable.bulb_on : R.drawable.bulb_off);
                HttpUtils.excutedUrl(GenerateUrlServer.getArtifactActionUrl(artifact, room, isChecked ? "on" : "off", ""));
            }
        });

        holder.switchOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean isChecked) {
                HttpUtils.excutedUrl(GenerateUrlServer.getArtifactActionUrl(artifact, room, isChecked ? "on" : "off", ""));
            }
        });
    }

    //Hablame de villero un swich ni en pedo
    private void showCorrectCard(TypeArtifact typeArtifact, RoomItemViewHolder holder) {
        if (TypeArtifact.LIGHT.equals(typeArtifact)) {
            holder.cardLuz.setVisibility(View.VISIBLE);
            holder.cardDimmer.setVisibility(View.GONE);
            holder.cardOther.setVisibility(View.GONE);
        } else if (TypeArtifact.DIMMER.equals(typeArtifact)) {
            holder.cardLuz.setVisibility(View.GONE);
            holder.cardDimmer.setVisibility(View.VISIBLE);
            holder.cardOther.setVisibility(View.GONE);
        } else if (TypeArtifact.OTHER.equals(typeArtifact)) {
            holder.cardLuz.setVisibility(View.GONE);
            holder.cardDimmer.setVisibility(View.GONE);
            holder.cardOther.setVisibility(View.VISIBLE);
        } else {
            holder.cardLuz.setVisibility(View.GONE);
            holder.cardDimmer.setVisibility(View.GONE);
            holder.cardOther.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return room.getArtifacts().size();
    }

    public static class RoomItemViewHolder extends RecyclerView.ViewHolder {

        private CardView cardLuz;
        private ImageView iconLuz;
        private TextView nameLuz;
        private Switch switchLuz;
        private CardView cardDimmer;
        private ImageView iconDimmer;
        private TextView nameDimmer;
        private SeekBar seekBarDimmer;
        private CardView cardOther;
        private TextView nameOther;
        private Switch switchOther;

        public RoomItemViewHolder(View itemView) {
            super(itemView);
            cardLuz = itemView.findViewById(R.id.cardLuz);
            iconLuz = itemView.findViewById(R.id.iconLuz);
            nameLuz = itemView.findViewById(R.id.nameLuz);
            switchLuz = itemView.findViewById(R.id.switchLuz);
            cardDimmer = itemView.findViewById(R.id.cardDimmer);
            iconDimmer = itemView.findViewById(R.id.iconDimmer);
            nameDimmer = itemView.findViewById(R.id.nameDimmer);
            seekBarDimmer = itemView.findViewById(R.id.seekBarDimmer);
            cardOther = itemView.findViewById(R.id.cardOther);
            nameOther = itemView.findViewById(R.id.nameOther);
            switchOther = itemView.findViewById(R.id.switchOther);
        }
    }
}
