package com.example.testbinderservice;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.testbinderservice.LocalService.LocalBinder;

// The responsibility of the ServiceConnection is to extract the Object that
// has the methods that we'll be calling remotely, and to set the parent Activity's
// internal reference to that Object.
// Because we want it to have more than just an "Object", we define it in a custom class.
// 
public class LocalServiceConnection implements ServiceConnection{
		// A reference to the parent, so we can give it a reference to the service.
		private MainActivity parentActivity;
		
		public LocalServiceConnection(MainActivity parentActivity){
			this.parentActivity = parentActivity;
		}
	
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalBinder binder = (LocalBinder) service;
            this.parentActivity.setmService(binder.getService());
            this.parentActivity.setmBound(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
        	this.parentActivity.setmBound(false);
        	this.parentActivity.setmService(null);
        }
}
