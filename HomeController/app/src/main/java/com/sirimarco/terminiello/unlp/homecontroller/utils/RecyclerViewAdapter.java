package com.sirimarco.terminiello.unlp.homecontroller.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Map<Integer, RecyclerViewType> recyclerViewTypeMap = Maps.newHashMap();

    private List<Object> items;

    private int selectedItemPosition = RecyclerView.NO_POSITION;
    private RecyclerViewType.RecyclerViewTypeListener recyclerViewTypeListener = new RecyclerViewType.RecyclerViewTypeListener() {
        @Override
        public void onItemSelected(int position) {
            setSelectedItemPosition(position);
        }
    };

    public RecyclerViewAdapter(RecyclerViewType recyclerViewType) {
        this(Lists.newArrayList(recyclerViewType), Lists.newArrayList());
    }

    public RecyclerViewAdapter(RecyclerViewType recyclerViewType, List<? extends Object> items) {
        this(Lists.newArrayList(recyclerViewType), items);
    }

    public RecyclerViewAdapter(List<RecyclerViewType> recyclerViewTypes) {
        this(recyclerViewTypes, Lists.newArrayList());
    }

    public RecyclerViewAdapter(List<RecyclerViewType> recyclerViewTypes, List<? extends Object> items) {
        this.items = (List<Object>) items;
        for (RecyclerViewType each : recyclerViewTypes) {
            addRecyclerViewType(each);
        }
    }

    public int addRecyclerViewType(RecyclerViewType recyclerViewType) {
        recyclerViewType.setRecyclerViewTypeListener(recyclerViewTypeListener);
        int viewType = recyclerViewTypeMap.size() + 1;
        recyclerViewTypeMap.put(viewType, recyclerViewType);
        return viewType;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);
        for (Map.Entry<Integer, RecyclerViewType> entry : recyclerViewTypeMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue().matchViewType(item)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerViewType recyclerViewType = recyclerViewTypeMap.get(viewType);
        View view = recyclerViewType.inflateView(inflater, parent);
        return recyclerViewType.createViewHolderFromView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Integer itemViewType = holder.getItemViewType();
        Object item = getItem(position);
        RecyclerViewType recyclerViewType = recyclerViewTypeMap.get(itemViewType);

        if (recyclerViewType.isClickable()) {
            holder.itemView.setOnClickListener(recyclerViewType);
        } else {
            holder.itemView.setOnClickListener(null);
        }

        if (recyclerViewType.isSelectable()) {
            holder.itemView.setSelected(holder.getLayoutPosition() == selectedItemPosition);
        }

        recyclerViewType.fillHolderFromItem(item, holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        // The view could be reused while an animation is been happening.
        // In order to avoid that is recommendable to clear the animation when is detached.
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public <T> void addItem(T item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public <T> void addItems(List<T> newItems) {
        items.addAll(newItems);
        notifyItemRangeInserted(getItemCount() - newItems.size(), newItems.size());
    }

    public <T> void removeItem(T item) {
        int position = getPosition(item);
        items.remove(item);
        if (selectedItemPosition == position) {
            selectedItemPosition = RecyclerView.NO_POSITION;
        } else if (selectedItemPosition > position) {
            selectedItemPosition--;
        }
        notifyItemRemoved(position);
    }

    public void removeItemByPosition(int position) {
        Object item = getItem(position);
        int pos = items.indexOf(item);
        items.remove(item);
        if (selectedItemPosition == position) {
            selectedItemPosition = RecyclerView.NO_POSITION;
        } else if (selectedItemPosition > position) {
            selectedItemPosition--;
        }
        notifyItemRemoved(pos);
    }

    public void clear() {
        int size = getItemCount();
        selectedItemPosition = RecyclerView.NO_POSITION;
        items.clear();
        notifyItemRangeRemoved(0, size);
    }

    public List<Object> getItems() {
        return items;
    }

    public Object getItem(Integer position) {
        Object item;
        item = items.get(position);
        return item;
    }

    public <T> int getPosition(T item) {
        int pos = items.indexOf(item);
        return pos;
    }

    public <T> void setSelectedItem(T item) {
        setSelectedItemPosition(getPosition(item));
    }

    public void setSelectedItemPosition(int position) {
        if (position != RecyclerView.NO_POSITION) {
            if (position < getItemCount()) {
                int itemViewType = getItemViewType(position);
                RecyclerViewType recyclerViewType = recyclerViewTypeMap.get(itemViewType);
                if (recyclerViewType.isSelectable()) {
                    int previousSelectedItemPosition = selectedItemPosition;
                    selectedItemPosition = position;
                    notifyItemChanged(previousSelectedItemPosition);
                    notifyItemChanged(selectedItemPosition);
                }
            }
        } else {
            int oldPosition = selectedItemPosition;
            selectedItemPosition = position;
            notifyItemChanged(oldPosition);
        }
    }

    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }
}