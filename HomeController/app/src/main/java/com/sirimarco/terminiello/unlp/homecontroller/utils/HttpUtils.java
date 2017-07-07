package com.sirimarco.terminiello.unlp.homecontroller.utils;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtils {

    public static void excutedUrl(final String url, final Callback callback) {
        ThreadUtils.executedRunnable(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                client.newCall(request).enqueue(callback);
            }
        });
    }

    public static void excutedUrl(final String url) {
        ThreadUtils.executedRunnable(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try {
                    client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
