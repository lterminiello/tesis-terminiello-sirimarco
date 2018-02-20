package com.sirimarco.terminiello.unlp.homecontroller.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class RecyclerViewType<ITEM, VIEWHOLDER extends RecyclerView.ViewHolder> implements View.OnClickListener {


	private RecyclerViewTypeListener recyclerViewTypeListener;

	public View inflateView(LayoutInflater inflater, ViewGroup parent) {
		return inflater.inflate(getLayoutResourceId(), parent, false);
	}

	protected abstract Integer getLayoutResourceId();

	/**
	 * Creates a VIEWHOLDER from the given view. Please declare the VIEWHOLDER class as static when possible
	 *
	 * @param view The view from the list.
	 * @return The new VIEWHOLDER.
	 */
	public abstract RecyclerView.ViewHolder createViewHolderFromView(View view);

	/**
	 * Fills the VIEWHOLDER with the Object's data.
	 *
	 * @param item The Object.
	 * @param holder The VIEWHOLDER.
	 */
	public abstract void fillHolderFromItem(ITEM item, VIEWHOLDER holder);


	@NonNull
	public abstract Context getContextFragment();

	@NonNull
	public abstract Context getActivityFragment();

	public Boolean isClickable() {
		return true;
	}


	public void onItemSelected(ITEM item, View view) {
		// Do Nothing
	}

	/**
	 * Finds a view that was identified by the id attribute from the {@link View} view.
	 *
	 * @param containerView The view that contains the view to find.
	 * @param id The id to search for.
	 * @param <V> The {@link View} class.
	 *
	 * @return The view if found or null otherwise.
	 */
	@SuppressWarnings("unchecked")
	public <V extends View> V findView(View containerView, int id) {
		return (V)containerView.findViewById(id);
	}

	protected Context getContext() {
		return getContext();
	}

	protected Activity getActivity() {
		return getActivity();
	}

	public Boolean matchViewType(Object item) {
		Class itemClass = item.getClass();
		while (itemClass != null) {
			if (getItemClass().equals(itemClass)) {
				return true;
			}
			itemClass = itemClass.getSuperclass();
		}
		return false;
	}

	protected abstract Class<ITEM> getItemClass();

	public void setRecyclerViewTypeListener(RecyclerViewType.RecyclerViewTypeListener recyclerViewTypeListener) {
		this.recyclerViewTypeListener = recyclerViewTypeListener;
	}

	public boolean isSelectable() {
		return false;
	}

	public interface RecyclerViewTypeListener {
		void onItemSelected(int position);
	}

}