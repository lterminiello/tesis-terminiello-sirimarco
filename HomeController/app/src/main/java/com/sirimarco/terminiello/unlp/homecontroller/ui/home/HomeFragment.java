package com.sirimarco.terminiello.unlp.homecontroller.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.json.JsonFactory;
import com.sirimarco.terminiello.unlp.homecontroller.model.House;
import com.sirimarco.terminiello.unlp.homecontroller.utils.GenerateUrlServer;
import com.sirimarco.terminiello.unlp.homecontroller.utils.HttpUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ThreadUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeFragment extends Fragment implements Callback {

    private JsonFactory jsonFactory;
    private TabLayout tabLayout;
    private ViewPager mViewPager;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonFactory = new JsonFactory();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager = view.findViewById(R.id.pager);

        HttpUtils.excutedUrl(GenerateUrlServer.getHouseSchemeUrl(), this);

        return view;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String data = response.body().string();
        House house = jsonFactory.fromJson(data,new TypeReference<House>(){});
        final HomePagerAdapter homePagerAdapter= new HomePagerAdapter(getFragmentManager(),house);

        ThreadUtils.executeOnUIThread(this, new Runnable() {
            @Override
            public void run() {
                mViewPager.setAdapter(homePagerAdapter);
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                tabLayout.setupWithViewPager(mViewPager);
            }
        });
    }
}
