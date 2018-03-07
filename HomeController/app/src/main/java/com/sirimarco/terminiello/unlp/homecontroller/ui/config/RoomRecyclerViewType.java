package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.Room;
import com.sirimarco.terminiello.unlp.homecontroller.utils.RecyclerViewType;


public class RoomRecyclerViewType extends RecyclerViewType<Room, RoomRecyclerViewType.RoomViewHolder> {

    public interface OnClickRoomListener{
        void onClickDeletRoom(Room room);
        void onClickEdiiRoom(Room room,int pos);
        void onClickAddRoom(Room room);
    }

    private Activity activity;
    private Context context;
    private OnClickRoomListener listener;

    public RoomRecyclerViewType(Activity activity, Context context,OnClickRoomListener listener) {
        this.activity = activity;
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Integer getLayoutResourceId() {
        return R.layout.room_add_item;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolderFromView(View view) {
        RoomViewHolder holder = new RoomViewHolder(view);
        holder.textView = findView(view, R.id.roomName);
        holder.delete = findView(view, R.id.roomDelete);
        holder.edit = findView(view, R.id.roomEdit);
        holder.add = findView(view, R.id.roomAdd);

        return holder;
    }

    @Override
    public void fillHolderFromItem(final Room room, final RoomViewHolder holder) {
        holder.textView.setText(room.getName());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDeletRoom(room);
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickAddRoom(room);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickEdiiRoom(room,holder.getAdapterPosition());
            }
        });

    }

    @NonNull
    @Override
    public Context getContextFragment() {
        return context;
    }

    @NonNull
    @Override
    public Context getActivityFragment() {
        return activity;
    }

    @Override
    protected Class<Room> getItemClass() {
        return Room.class;
    }

    @Override
    public void onClick(View view) {

    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView delete;
        private ImageView edit;
        private ImageView add;

        public RoomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
