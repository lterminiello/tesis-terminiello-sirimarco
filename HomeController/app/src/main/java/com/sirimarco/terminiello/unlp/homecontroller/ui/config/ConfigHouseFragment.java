package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.Artifact;
import com.sirimarco.terminiello.unlp.homecontroller.model.House;
import com.sirimarco.terminiello.unlp.homecontroller.model.Room;
import com.sirimarco.terminiello.unlp.homecontroller.utils.RecyclerViewAdapter;
import com.sirimarco.terminiello.unlp.homecontroller.utils.RecyclerViewType;

import java.util.LinkedList;
import java.util.List;

public class ConfigHouseFragment extends Fragment implements ArtifactRecyclerViewType.OnLongClickArtifactListener {

    private RecyclerView recyclerView;
    private House house;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Object> recyclerList;

    public static ConfigHouseFragment newInstance(House house) {
        Bundle args = new Bundle();
        ConfigHouseFragment fragment = new ConfigHouseFragment();
        args.putSerializable("HOUSE", house);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_fragment, container, false);

        house = (House) getArguments().getSerializable("HOUSE");


        recyclerView = view.findViewById(R.id.recycler);

        recyclerList = new LinkedList<>();
        List<RecyclerViewType> recyclerViewTypes = new LinkedList<>();

        recyclerViewTypes.add(new RoomRecyclerViewType(getActivity(), getContext()));
        recyclerViewTypes.add(new ArtifactRecyclerViewType(getActivity(), getContext(), this));

        if (house.getRooms() != null) {
            for (Room room : house.getRooms()) {
                recyclerList.add(room);
                if (room.getArtifacts() != null) {
                    if (room.getArtifacts() != null) {
                        for (Artifact artifact : room.getArtifacts()) {
                            recyclerList.add(artifact);
                        }
                    }
                }
            }
        }

        recyclerViewAdapter = new RecyclerViewAdapter(recyclerViewTypes, recyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    @Override
    public void onLongClickArtifact(Artifact artifact, int pos) {
        for (Room room : house.getRooms()) {
            room.getArtifacts().remove(artifact);
        }
        recyclerList.remove(pos);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
