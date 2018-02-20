package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.Artifact;
import com.sirimarco.terminiello.unlp.homecontroller.utils.RecyclerViewType;

/**
 * Created by default on 10/09/17.
 */

public class ArtifactRecyclerViewType extends RecyclerViewType<Artifact,ArtifactRecyclerViewType.ArtifactViewHolder> {

    public interface OnLongClickArtifactListener{
        void onLongClickArtifact(Artifact artifact,int pos);
    }

    private Activity activity;
    private Context context;
    private OnLongClickArtifactListener onLongClickArtifactListener;

    public ArtifactRecyclerViewType(Activity activity,Context context,OnLongClickArtifactListener onLongClickArtifactListener) {
        this.activity = activity;
        this.context = context;
        this.onLongClickArtifactListener = onLongClickArtifactListener;
    }

    @Override
    protected Integer getLayoutResourceId() {
        return R.layout.artifact_add_item;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolderFromView(View view) {
        ArtifactViewHolder holder = new ArtifactViewHolder(view);
        holder.textView = findView(view,R.id.artifactName);

        return holder;
    }

    @Override
    public void fillHolderFromItem(final Artifact artifact, final ArtifactViewHolder holder) {
        holder.textView.setText(artifact.getName());
        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onLongClickArtifactListener.onLongClickArtifact(artifact,holder.getAdapterPosition());
                return true;
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
    protected Class<Artifact> getItemClass() {
        return Artifact.class;
    }

    @Override
    public void onClick(View view) {

    }

    public static class  ArtifactViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ArtifactViewHolder(View itemView) {
            super(itemView);
        }
    }
}
