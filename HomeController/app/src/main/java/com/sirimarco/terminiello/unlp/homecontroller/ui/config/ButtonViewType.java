package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.sirimarco.terminiello.unlp.homecontroller.R;

import com.sirimarco.terminiello.unlp.homecontroller.utils.RecyclerViewType;

public class ButtonViewType extends RecyclerViewType<String, ButtonViewType.ButtonViewHolder> {

    public interface OnClickButtonListener {
        void onClickButton();
    }

    private Activity activity;
    private Context context;
    private OnClickButtonListener listener;

    public ButtonViewType(Activity activity, Context context, OnClickButtonListener listener) {
        this.activity = activity;
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected Integer getLayoutResourceId() {
        return R.layout.button_view_type;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolderFromView(View view) {
        ButtonViewHolder holder = new ButtonViewHolder(view);
        holder.button = findView(view, R.id.button);
        return holder;
    }

    @Override
    public void fillHolderFromItem(final String text, final ButtonViewHolder holder) {
        holder.button.setText(text);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickButton();
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
    protected Class<String> getItemClass() {
        return String.class;
    }

    @Override
    public void onClick(View view) {

    }

    public static class ButtonViewHolder extends RecyclerView.ViewHolder {

        private Button button;

        public ButtonViewHolder(View itemView) {
            super(itemView);
        }
    }
}