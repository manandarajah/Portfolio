package com.mygdx.game;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.android.vending.billing.IInAppBillingService;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.Main;

import java.util.ArrayList;

public class AndroidLauncher extends AndroidApplication {
	IInAppBillingService mService;

	ServiceConnection mServiceConn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService = IInAppBillingService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mService = null;
		}
	};

	ArrayList<String> skuList = new ArrayList<>();
	Bundle skuDetails;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
		serviceIntent.setPackage("com.android.vending");
		bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Main(), config);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (mService != null)
			unbindService(mServiceConn);
	}
}
