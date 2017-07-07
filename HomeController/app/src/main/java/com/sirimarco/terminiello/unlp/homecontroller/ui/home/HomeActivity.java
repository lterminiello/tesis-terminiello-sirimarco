package com.sirimarco.terminiello.unlp.homecontroller.ui.home;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sirimarco.terminiello.unlp.homecontroller.R;

public class HomeActivity extends AppCompatActivity {

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);


        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.container, homeFragment);
            fragmentTransaction.commit();
        }

    }
}
