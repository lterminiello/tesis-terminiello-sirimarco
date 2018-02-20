package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.Room;
import com.sirimarco.terminiello.unlp.homecontroller.utils.RecyclerViewType;


public class RoomRecyclerViewType extends RecyclerViewType<Room,RoomRecyclerViewType.RoomViewHolder>{


    private Activity activity;
    private Context context;

    public RoomRecyclerViewType(Activity activity,Context context) {
        this.activity = activity;
        this.context = context;
    }

    @Override
    protected Integer getLayoutResourceId() {
        return R.layout.room_add_item;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolderFromView(View view) {
        RoomViewHolder holder = new RoomViewHolder(view);
        holder.textView = findView(view,R.id.roomName);

        return holder;
    }

    @Override
    public void fillHolderFromItem(Room room, RoomViewHolder holder) {
        holder.textView.setText(room.getName());
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

    public static class  RoomViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public RoomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
