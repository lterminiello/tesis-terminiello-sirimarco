package com.sirimarco.terminiello.unlp.homecontroller.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sirimarco.terminiello.unlp.homecontroller.R;
import com.sirimarco.terminiello.unlp.homecontroller.utils.GenerateUrlServer;
import com.sirimarco.terminiello.unlp.homecontroller.utils.HttpUtils;
import com.sirimarco.terminiello.unlp.homecontroller.utils.ThreadUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeFragment extends Fragment implements Callback {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        textView = view.findViewById(R.id.info);

        HttpUtils.excutedUrl(GenerateUrlServer.getHouseSchemeUrl(), this);

        return view;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String data = response.body().string();
        ThreadUtils.executeOnUIThread(this, new Runnable() {
            @Override
            public void run() {
                textView.setText(data);
            }
        });
    }
}
