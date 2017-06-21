package com.sirimarco.terminiello.unlp.homecontroller.utils;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Runnable implementation to wrap runnables to run them safely in the UI thread from a fragment. This class only call
 * the "run()" method of the wrapped runnable if the fragment is in the resumed state.
 * 
 */
public class SafeExecuteWrapperRunnable implements Runnable {

    private Fragment fragment;
	private Runnable runnable;
	
	public SafeExecuteWrapperRunnable(Fragment fragment, Runnable runnable) {
		this.fragment = fragment;
		this.runnable = runnable;
	}
	
	@Override
	public void run() {
		if (fragment.isResumed()) {
			runnable.run();
		} else {
			Log.e(SafeExecuteWrapperRunnable.class.getName(),"Ignoring run execution");
		}
	}
	
}