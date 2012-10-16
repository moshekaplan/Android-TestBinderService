package com.example.testbinderservice;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection;
	
	// A reference to the service.
	// We'll call methods belonging to this instance to communicate with the Service
	// The ServiceConnection will populate this and mBound after it connects
    private ILocalService mService = null;
    
	// True if mService is bound to a service
	private boolean mBound = false;
	
    // Getters and setters are needed for the ServiceConnection
	public void setmService(ILocalService mService) {
		this.mService = mService;
	}

	public boolean ismBound() {
		return mBound;
	}

	public void setmBound(boolean mBound) {
		this.mBound = mBound;
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConnection = new LocalServiceConnection(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Create an Intent to represent a LocalService 
        Intent intent = new Intent(this, com.example.testbinderservice.LocalService.class);
        // Bind a LocalService to our ServiceConnection
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
        	mBound = false;
        }
    }

    /** Called when a button is clicked (the button in the layout file attaches to
      * this method with the android:onClick attribute) */
    public void onButtonClick(View v) {
    	if (mBound) {
            // Call a method from the LocalService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            int num = mService.getRandomNumber();
            Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
        }
    }
}