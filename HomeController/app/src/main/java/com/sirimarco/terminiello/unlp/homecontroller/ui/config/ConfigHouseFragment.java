package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.Artifact;
import com.sirimarco.terminiello.unlp.homecontroller.model.House;
import com.sirimarco.terminiello.unlp.homecontroller.model.Room;
import com.sirimarco.terminiello.unlp.homecontroller.utils.GenerateUrlServer;
import com.sirimarco.terminiello.unlp.homecontroller.utils.HttpUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.RecyclerViewAdapter;
import com.sirimarco.terminiello.unlp.homecontroller.utils.RecyclerViewType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ConfigHouseFragment extends Fragment implements ArtifactRecyclerViewType.OnClickArtifactListener, RoomRecyclerViewType.OnClickRoomListener, ButtonViewType.OnClickButtonListener {

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_config, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        HttpUtils.excutedUrl(GenerateUrlServer.setHouseSchemeUrl(house), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Intent returnIntent = new Intent();
                getActivity().setResult(Activity.RESULT_OK,returnIntent);
                getActivity().finish();
            }
        });
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_fragment, container, false);
        setHasOptionsMenu(true);

        house = (House) getArguments().getSerializable("HOUSE");

        recyclerView = view.findViewById(R.id.recycler);

        recyclerList = new LinkedList<>();
        List<RecyclerViewType> recyclerViewTypes = new LinkedList<>();

        recyclerViewTypes.add(new RoomRecyclerViewType(getActivity(), getContext(), this));
        recyclerViewTypes.add(new ButtonViewType(getActivity(), getContext(), this));
        recyclerViewTypes.add(new ArtifactRecyclerViewType(getActivity(), getContext(), this));

        if (house.getRooms() != null) {
            initRecyclerInfo();
        }

        recyclerViewAdapter = new RecyclerViewAdapter(recyclerViewTypes, recyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    @Override
    public void onClickDeleteArtifact(Artifact artifact, int pos) {
        for (Room room : house.getRooms()) {
            room.getArtifacts().remove(artifact);
        }
        recyclerList.remove(pos);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickEditArtifact(Artifact artifact, final int pos) {
        ArtifactEditDialogFragment artifactEditDialogFragment = new ArtifactEditDialogFragment();
        artifactEditDialogFragment.setArtifactEdit(artifact);
        artifactEditDialogFragment.setOnClickDialogArtifactListener(new ArtifactEditDialogFragment.OnClickDialogArtifactListener() {
            @Override
            public void onClickAccept(Artifact artifact) {
                recyclerViewAdapter.notifyItemChanged(pos);
            }
        });
        artifactEditDialogFragment.show(getActivity().getFragmentManager(), ArtifactEditDialogFragment.class.getName());
    }

    @Override
    public void onClickDeletRoom(Room room) {
        house.getRooms().remove(room);
        recyclerViewAdapter.clear();
        initRecyclerInfo();

    }

    private void initRecyclerInfo() {
        for (Room roomlist : house.getRooms()) {
            recyclerList.add(roomlist);
            if (roomlist.getArtifacts() != null) {
                if (roomlist.getArtifacts() != null) {
                    for (Artifact artifact : roomlist.getArtifacts()) {
                        recyclerList.add(artifact);
                    }
                }
            }
        }
        recyclerList.add("Agregar habitacion");
    }

    @Override
    public void onClickEdiiRoom(Room room, final int pos) {
        RoomEditDialogFragment roomEditDialogFragment = new RoomEditDialogFragment();
        roomEditDialogFragment.setRoomEdit(room);
        roomEditDialogFragment.setOnClickDialogRoomListenerr(new RoomEditDialogFragment.OnClickDialogRoomListener() {
            @Override
            public void onClickAccept(Room room) {
                recyclerViewAdapter.notifyItemChanged(pos);
            }
        });
        roomEditDialogFragment.show(getActivity().getFragmentManager(), RoomEditDialogFragment.class.getName());
    }

    @Override
    public void onClickAddRoom(final Room room) {
        ArtifactEditDialogFragment artifactEditDialogFragment = new ArtifactEditDialogFragment();
        artifactEditDialogFragment.setOnClickDialogArtifactListener(new ArtifactEditDialogFragment.OnClickDialogArtifactListener() {
            @Override
            public void onClickAccept(Artifact artifact) {
                room.getArtifacts().add(artifact);
                recyclerViewAdapter.clear();
                initRecyclerInfo();
            }
        });
        artifactEditDialogFragment.show(getActivity().getFragmentManager(), ArtifactEditDialogFragment.class.getName());
    }

    @Override
    public void onClickButton() {
        RoomEditDialogFragment roomEditDialogFragment = new RoomEditDialogFragment();
        roomEditDialogFragment.setOnClickDialogRoomListenerr(new RoomEditDialogFragment.OnClickDialogRoomListener() {
            @Override
            public void onClickAccept(Room room) {
                house.getRooms().add(room);
                recyclerViewAdapter.clear();
                initRecyclerInfo();
            }
        });
        roomEditDialogFragment.show(getActivity().getFragmentManager(), RoomEditDialogFragment.class.getName());
    }

}
