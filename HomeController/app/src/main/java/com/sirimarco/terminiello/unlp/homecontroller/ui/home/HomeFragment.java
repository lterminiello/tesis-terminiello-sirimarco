package com.sirimarco.terminiello.unlp.homecontroller.ui.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.json.JsonFactory;
import com.sirimarco.terminiello.unlp.homecontroller.model.Artifact;
import com.sirimarco.terminiello.unlp.homecontroller.model.House;
import com.sirimarco.terminiello.unlp.homecontroller.model.Room;
import com.sirimarco.terminiello.unlp.homecontroller.ui.config.ConfigHouseActivity;
import com.sirimarco.terminiello.unlp.homecontroller.utils.GenerateUrlServer;
import com.sirimarco.terminiello.unlp.homecontroller.utils.HttpUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.Lists;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ThreadUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeFragment extends Fragment implements Callback {

    private JsonFactory jsonFactory;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton fab;

    private House house;

    private final int REQ_CODE_SPEECH_INPUT = 100;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        jsonFactory = new JsonFactory();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_house, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getActivity(), ConfigHouseActivity.class);
        intent.putExtra("HOUSE", house);
        getActivity().startActivityForResult(intent, 1);
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager = view.findViewById(R.id.pager);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });

        HttpUtils.excutedUrl(GenerateUrlServer.getHouseSchemeUrl(), this);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String data = response.body().string();
        house = jsonFactory.fromJson(data, new TypeReference<House>() {
        });

        if (!Lists.isNullOrEmpty(house.getRooms())) {
            final HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getFragmentManager(), house);
            ThreadUtils.executeOnUIThread(this, new Runnable() {
                @Override
                public void run() {
                    mViewPager.setAdapter(homePagerAdapter);
                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                    tabLayout.setupWithViewPager(mViewPager);
                }
            });
        } else {
            Intent intent = new Intent(getActivity(), ConfigHouseActivity.class);
            intent.putExtra("HOUSE", house);
            getActivity().startActivityForResult(intent, 1);
        }
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "holiiiiii");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getContext(), "NO SOPORTADO",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void actionMic(String data) {
        List<String> wordsList = new ArrayList<String>();
        String[] words = data.split(" ");
        wordsList.addAll(Arrays.asList(words));
        Room roomSelected = getRoomInHouse(wordsList);
        if (roomSelected != null) {
            Artifact artifactSelected = getArtifactInRoom(wordsList, roomSelected);
            if (artifactSelected != null) {
                executeAction(wordsList, artifactSelected, roomSelected);
                return;
            }
        }
        Snackbar.make(getView(), data, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private Room getRoomInHouse(List<String> wordsList) {
        for (String word : wordsList) {
            for (Room room : house.getRooms()) {
                if (word.equals(room.getName())) {
                    return room;
                }
            }
        }
        return null;
    }

    private Artifact getArtifactInRoom(List<String> wordsList, Room room) {
        for (String word : wordsList) {
            for (Artifact artifact : room.getArtifacts()) {
                if (word.equals(artifact.getName())) {
                    return artifact;
                }
            }
        }
        return null;
    }

    private void executeAction(List<String> wordsList, Artifact artifact, Room room) {
        for (String word : wordsList) {
            switch (artifact.getTypeArtifact()) {
                case LIGHT:
                    if ("encender".equals(word)) {
                        HttpUtils.excutedUrl(GenerateUrlServer.getArtifactActionUrl(artifact, room, "on", ""));
                        Snackbar.make(getView(), "se encendio " + artifact.getName() + " de " + room.getName(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return;
                    } else if ("apagar".equals(word)) {
                        HttpUtils.excutedUrl(GenerateUrlServer.getArtifactActionUrl(artifact, room, "off", ""));
                        Snackbar.make(getView(), "se apago " + artifact.getName() + " de " + room.getName(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return;
                    }
                    break;
                case OTHER:
                    if ("encender".equals(word)) {
                        HttpUtils.excutedUrl(GenerateUrlServer.getArtifactActionUrl(artifact, room, "on", ""));
                        Snackbar.make(getView(), "se encendio " + artifact.getName() + " de " + room.getName(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return;
                    } else if ("apagar".equals(word)) {
                        HttpUtils.excutedUrl(GenerateUrlServer.getArtifactActionUrl(artifact, room, "off", ""));
                        Snackbar.make(getView(), "se apago " + artifact.getName() + " de " + room.getName(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return;
                    }
                    break;
                case DIMMER:
                    if (word.contains("%")) {
                        String value = word.replace("%","");
                        int pwd = Integer.valueOf(value) / 10;
                        HttpUtils.excutedUrl(GenerateUrlServer.getArtifactActionUrl(artifact, room, "on", String.valueOf(pwd)));
                        Snackbar.make(getView(), "se encendio al " + word + " la " + artifact.getName() + " de " + room.getName(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return;
                    } else if ("apagar".equals(word)) {
                        HttpUtils.excutedUrl(GenerateUrlServer.getArtifactActionUrl(artifact, room, "on", String.valueOf(0)));
                        Snackbar.make(getView(), "se apago " + artifact.getName() + " de " + room.getName(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        return;
                    }
                    break;
            }
        }
        Snackbar.make(getView(), "no se reconoce la accion para " + artifact.getName() + " de " + room.getName(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
