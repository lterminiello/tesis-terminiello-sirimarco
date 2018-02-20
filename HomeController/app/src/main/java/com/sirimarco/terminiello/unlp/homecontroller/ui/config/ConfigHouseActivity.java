package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.model.House;

/**
 * Created by default on 07/09/17.
 */

public class ConfigHouseActivity extends AppCompatActivity {


    private ConfigHouseFragment configHouseFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        House house = (House) getIntent().getExtras().getSerializable("HOUSE");

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            configHouseFragment =  ConfigHouseFragment.newInstance(house);
            fragmentTransaction.add(R.id.container, configHouseFragment);
            fragmentTransaction.commit();
        }

    }
}
