package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.Artifact;
import com.sirimarco.terminiello.unlp.homecontroller.utils.RecyclerViewType;

/**
 * Created by default on 10/09/17.
 */

public class ArtifactRecyclerViewType extends RecyclerViewType<Artifact, ArtifactRecyclerViewType.ArtifactViewHolder> {

    public interface OnClickArtifactListener {
        void onClickDeleteArtifact(Artifact artifact, int pos);

        void onClickEditArtifact(Artifact artifact, int pos);
    }

    private Activity activity;
    private Context context;
    private OnClickArtifactListener listener;

    public ArtifactRecyclerViewType(Activity activity, Context context, OnClickArtifactListener onClickArtifactListener) {
        this.activity = activity;
        this.context = context;
        this.listener = onClickArtifactListener;
    }

    @Override
    protected Integer getLayoutResourceId() {
        return R.layout.artifact_add_item;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolderFromView(View view) {
        ArtifactViewHolder holder = new ArtifactViewHolder(view);
        holder.textView = findView(view, R.id.artifactName);
        holder.icon = findView(view, R.id.artifactIcon);
        holder.edit = findView(view, R.id.artifactEdit);
        holder.delete = findView(view, R.id.artifactDelete);

        return holder;
    }

    @Override
    public void fillHolderFromItem(final Artifact artifact, final ArtifactViewHolder holder) {
        holder.textView.setText(artifact.getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDeleteArtifact(artifact, holder.getAdapterPosition());
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickEditArtifact(artifact, holder.getAdapterPosition());
            }
        });
        holder.icon.setVisibility(View.VISIBLE);
        switch (artifact.getTypeArtifact()) {
            case LIGHT:
                holder.icon.setImageResource(R.drawable.bulb_on);
                break;
            case DIMMER:
                holder.icon.setImageResource(R.drawable.bulb_on);
                break;
            case OTHER:
                holder.icon.setImageResource(R.drawable.button_other);
                break;
            default:
                holder.icon.setVisibility(View.GONE);
        }

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
    protected Class<Artifact> getItemClass() {
        return Artifact.class;
    }

    @Override
    public void onClick(View view) {

    }

    public static class ArtifactViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView icon;
        private ImageView edit;
        private ImageView delete;


        public ArtifactViewHolder(View itemView) {
            super(itemView);
        }
    }
}
