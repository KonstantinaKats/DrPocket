package com.example.katsigianni.pocketlocation;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

/**
 * Created by Dimitra on 1/16/2018.
 */

public class ActivityRecognizedService extends IntentService implements LocationListener {

//    public void onCreate() {
//        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//
//        Criteria criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
//        criteria.setAltitudeRequired(false);
//        criteria.setBearingRequired(false);
//        criteria.setCostAllowed(true);
//
//        String provider = mLocationManager.getBestProvider(criteria, true);
//        mLocationManager.requestLocationUpdates(provider, LOCATION_UPDATE_INTERVAL_MILLIS, LOCATION_UPDATE_INTERVAL_METERS, this);
//
//    }

    final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.e("ActivityRecogition LC", location.toString());
            Log.e("ActivityRecogition", "Location Changes");
            Log.e("ActivityRecogition", location.getLatitude() +""+ location.getLongitude()+"");

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("Status Changed", String.valueOf(status));
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Provider Enabled", provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Provider Disabled", provider);
        }
    };

    // Now create a location manager
    private LocationManager locationManager;

    Criteria criteria = new Criteria();

    public ActivityRecognizedService() {
        super("ActivityRecognizedService");
    }

    public ActivityRecognizedService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities(result.getProbableActivities());
        }
    }


    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        Log.e("ActivityRecogition", "Mpikeee");

        if ( ActivityCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

         return;
        }
        locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestSingleUpdate(criteria, locationListener, null);


        for (DetectedActivity activity : probableActivities) {
            switch (activity.getType()) {
                case DetectedActivity.IN_VEHICLE: {
                    Log.e("ActivityRecogition", "In Vehicle: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    Log.e("ActivityRecogition", "On Bicycle: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    Log.e("ActivityRecogition", "On Foot: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.RUNNING: {
                    Log.e("ActivityRecogition", "Running: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.e("ActivityRecogition", "Still: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.TILTING: {
                    Log.e("ActivityRecogition", "Tilting: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.WALKING: {
                    Log.e("ActivityRecogition", "Walking: " + activity.getConfidence());
//                    if (activity.getConfidence() >= 75) {
//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//                        builder.setContentText("Are you walking?");
//                        builder.setSmallIcon(R.mipmap.ic_launcher);
//                        builder.setContentTitle(getString(R.string.app_name));
//                        NotificationManagerCompat.from(this).notify(0, builder.build());
//                    }
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    Log.e("ActivityRecogition", "Unknown: " + activity.getConfidence());
                    break;
                }
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}