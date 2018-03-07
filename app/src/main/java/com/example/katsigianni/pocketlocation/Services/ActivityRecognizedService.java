package com.example.katsigianni.pocketlocation.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.katsigianni.pocketlocation.SaveSharedPreference;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

/**
 * Created by Dimitra on 1/16/2018.
 */

public class ActivityRecognizedService extends IntentService {

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

        Log.e("ActivityRecogition", "Longitude: " + SaveSharedPreference.getLastLocationLongitude(ActivityRecognizedService.this));
        Log.e("ActivityRecogition", "Latitude: " + SaveSharedPreference.getLastLocationLatitude(ActivityRecognizedService.this));

        for (DetectedActivity activity : probableActivities) {
            switch (activity.getType()) {
                case DetectedActivity.IN_VEHICLE: {
                    if (activity.getConfidence() >= 75) {
                        Log.e("ActivityRecognition", "In Vehicle: " + activity.getConfidence());
                    }
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    if (activity.getConfidence() >= 75) {
                        Log.e("ActivityRecognition", "On Bicycle: " + activity.getConfidence());
                    }
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    if (activity.getConfidence() >= 75) {
                        Log.e("ActivityRecognition", "On Foot: " + activity.getConfidence());
                    }
                    break;
                }
                case DetectedActivity.RUNNING: {
                    if (activity.getConfidence() >= 75) {
                        Log.e("ActivityRecognition", "Running: " + activity.getConfidence());
                    }
                    break;
                }
                case DetectedActivity.STILL: {
                    if (activity.getConfidence() >= 75) {
                        Log.e("ActivityRecognition", "Still: " + activity.getConfidence());
                    }
                    break;
                }
                case DetectedActivity.TILTING: {
                    if (activity.getConfidence() >= 75) {
                        Log.e("ActivityRecognition", "Tilting: " + activity.getConfidence());
                    }
                    break;
                }
                case DetectedActivity.WALKING: {
                    if (activity.getConfidence() >= 75) {
                        Log.e("ActivityRecognition", "Walking: " + activity.getConfidence());
//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//                        builder.setContentText("Are you walking?");
//                        builder.setSmallIcon(R.mipmap.ic_launcher);
//                        builder.setContentTitle(getString(R.string.app_name));
//                        NotificationManagerCompat.from(this).notify(0, builder.build());
                    }
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    if (activity.getConfidence() >= 75) {
                        Log.e("ActivityRecognition", "Unknown: " + activity.getConfidence());
                    }
                    break;
                }
            }
        }
    }
}