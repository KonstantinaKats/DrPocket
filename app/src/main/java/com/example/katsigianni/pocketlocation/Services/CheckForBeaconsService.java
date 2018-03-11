package com.example.katsigianni.pocketlocation.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.katsigianni.pocketlocation.Activities.LoggedActivity;
import com.example.katsigianni.pocketlocation.SaveSharedPreference;

/**
 * Created by KonstantinaKats on 08-Mar-18.
 */

public class CheckForBeaconsService extends Service {
    private Handler handler;
    private long timeRemaining = 5000;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        handler = new Handler();

        Log.e("CheckForBeacons", "CheckForBeacons");
        if(Boolean.valueOf(SaveSharedPreference.getExitBedroom(CheckForBeaconsService.this)) && Boolean.valueOf(SaveSharedPreference.getExitLivingRoom(CheckForBeaconsService.this)))
        {
            Log.i("CheckForBeacons","Vgikan kai ta 2");
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    Log.d("CheckForBeacons","I'm running");
                    timeRemaining = timeRemaining - 1000;
                    if(timeRemaining > 0) {
                        handler.postDelayed(this, 1000);
                    }
                    else{
                        if(Boolean.valueOf(SaveSharedPreference.getExitBedroom(CheckForBeaconsService.this)) && Boolean.valueOf(SaveSharedPreference.getExitLivingRoom(CheckForBeaconsService.this))) {
                            Log.d("CheckForBeacons", "Perasa ta deuterolepta kai tora arxise to GPS");
                            //startService(new Intent(CheckForBeaconsService.this, GPSService.class));
                        }
                    }
                }
            };
            handler.postDelayed(runnable, 1000);

        }
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }
}
