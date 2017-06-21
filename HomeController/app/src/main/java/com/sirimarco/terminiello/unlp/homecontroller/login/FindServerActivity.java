package com.sirimarco.terminiello.unlp.homecontroller.login;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.sirimarco.terminiello.unlp.homecontroller.R;

public class FindServerActivity extends AppCompatActivity {

    private FindServerFragment findServerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_server_activity);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            findServerFragment = new FindServerFragment();
            fragmentTransaction.add(R.id.container, findServerFragment);
            fragmentTransaction.commit();
        }
    }
}
