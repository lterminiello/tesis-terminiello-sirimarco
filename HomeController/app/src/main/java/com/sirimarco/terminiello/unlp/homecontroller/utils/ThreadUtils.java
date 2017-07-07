package com.sirimarco.terminiello.unlp.homecontroller.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class ThreadUtils {

    public static void executeOnUIThread(Fragment fragment, Runnable runnable) {
        Activity activity = fragment.getActivity();
        if (activity != null) {
            activity.runOnUiThread(new SafeExecuteWrapperRunnable(fragment, runnable));
        }
    }

    public static Thread executedRunnable(Runnable runnable){
        Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }
}
