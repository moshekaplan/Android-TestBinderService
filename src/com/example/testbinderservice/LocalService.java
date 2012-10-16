package com.example.testbinderservice;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class LocalService extends Service implements ILocalService{
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

	
    @Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Service created...", Toast.LENGTH_LONG).show();
	}

    // Effectively return a reference to ourself
    @Override
    public IBinder onBind(Intent intent) {
    	Toast.makeText(this, "Service bound...", Toast.LENGTH_LONG).show();
        return mBinder;
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
    	ILocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }
    
    /** All methods available for clients are defined below:*/
    public int getRandomNumber() {
      return mGenerator.nextInt(100);
    }

}