package com.example.katsigianni.pocketlocation.Services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.katsigianni.pocketlocation.Activities.LoggedActivity;
import com.example.katsigianni.pocketlocation.SaveSharedPreference;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationServices;

/**
 * Created by KonstantinaKats on 08-Mar-18.
 */

public class CheckForBeaconsService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    private Handler handler;
    private long timeRemaining = 15000;
    private GoogleApiClient mGoogleApiClient;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(ActivityRecognition.API)
                .build();

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
                            if (mGoogleApiClient != null) {
                                mGoogleApiClient.connect();
                            }
                            //startService(new Intent(CheckForBeaconsService.this, GPSService.class));
                        }
                        timeRemaining = 15000;
                    }
                }
            };
            handler.postDelayed(runnable, 1000);

        }
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Intent intent = new Intent( this, ActivityRecognizedService.class );
        PendingIntent pendingIntent = PendingIntent.getService( this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates( mGoogleApiClient, 3000, pendingIntent );
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection Suspended!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection Failed!", Toast.LENGTH_SHORT).show();
//        if (connectionResult.hasResolution()) {
//            try {
//                // Start an Activity that tries to resolve the error
//                connectionResult.startResolutionForResult( this , 90000);
//            } catch (IntentSender.SendIntentException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Log.i("Current Location", "Location services connection failed with code " + connectionResult.getErrorCode());
//        }
    }

    public void stopActivityRec(){
        mGoogleApiClient.disconnect();
    }
}
