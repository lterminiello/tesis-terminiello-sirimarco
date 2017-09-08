package com.sirimarco.terminiello.unlp.homecontroller.ui.config;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sirimarco.terminiello.unlp.homecontroller.R;

/**
 * Created by default on 07/09/17.
 */

public class ConfigHouseActivity extends AppCompatActivity {


    private ConfigHouseFragment configHouseFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);


        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            configHouseFragment = new ConfigHouseFragment();
            fragmentTransaction.add(R.id.container, configHouseFragment);
            fragmentTransaction.commit();
        }

    }
}
