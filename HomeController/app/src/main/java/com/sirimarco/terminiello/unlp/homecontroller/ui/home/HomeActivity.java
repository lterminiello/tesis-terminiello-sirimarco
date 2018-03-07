package com.sirimarco.terminiello.unlp.homecontroller.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.utils.Lists;

public class HomeActivity extends AppCompatActivity {

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.container, homeFragment, "HOSEFRAGEMNTE");
            fragmentTransaction.commit();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 1) {
            if (!Lists.isNullOrEmpty(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS))) {
                homeFragment.actionMic(data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
            }
        } else {
            finish();
            Intent intent = new Intent(this, HomeActivity.class);
            this.startActivity(intent);
        }
    }
}
