package com.example.katsigianni.pocketlocation.Services;

import android.app.IntentService;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.katsigianni.pocketlocation.Common;
import com.example.katsigianni.pocketlocation.HTTPDataHandler;
import com.example.katsigianni.pocketlocation.SaveSharedPreference;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.Date;
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

        String personal_number = SaveSharedPreference.getPersonalNumber(ActivityRecognizedService.this);
        String latitude = SaveSharedPreference.getLastLocationLongitude(ActivityRecognizedService.this);
        String longitude = SaveSharedPreference.getLastLocationLatitude(ActivityRecognizedService.this);

        Log.e("ActivityRecogition", "Longitude: " + latitude);
        Log.e("ActivityRecogition", "Latitude: " + longitude);

        for (DetectedActivity activity : probableActivities) {
            String activityMessage = null;
            switch (activity.getType()) {
                case DetectedActivity.IN_VEHICLE: {
                    if (activity.getConfidence() >= 65) {
                        Log.e("ActivityRecognition", "In Vehicle: " + activity.getConfidence());
                        activityMessage = "In Vehicle";
                    }
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    if (activity.getConfidence() >= 65) {
                        Log.e("ActivityRecognition", "On Bicycle: " + activity.getConfidence());
                        activityMessage = "On Bicycle";
                    }
                    break;
                }
//                case DetectedActivity.ON_FOOT: {
//                    if (activity.getConfidence() >= 65) {
//                        Log.e("ActivityRecognition", "On Foot: " + activity.getConfidence());
//                    }
//                    break;
//                }
                case DetectedActivity.RUNNING: {
                    if (activity.getConfidence() >= 65) {
                        Log.e("ActivityRecognition", "Running: " + activity.getConfidence());
                        activityMessage = "Running";
                    }
                    break;
                }
                case DetectedActivity.STILL: {
                    if (activity.getConfidence() >= 65) {
                        Log.e("ActivityRecognition", "Still: " + activity.getConfidence());
                        activityMessage = "Still";
                    }
                    break;
                }
                case DetectedActivity.TILTING: {
                    if (activity.getConfidence() >= 65) {
                        Log.e("ActivityRecognition", "Tilting: " + activity.getConfidence());
                        activityMessage = "Tilting";
                    }
                    break;
                }
                case DetectedActivity.WALKING: {
                    if (activity.getConfidence() >= 65) {
                        Log.e("ActivityRecognition", "Walking: " + activity.getConfidence());
                        activityMessage = "Walking";
                    }
                    break;
                }
//                case DetectedActivity.UNKNOWN: {
//                    if (activity.getConfidence() >= 65) {
//                        Log.e("ActivityRecognition", "Unknown: " + activity.getConfidence());
//                        activityMessage = "Unknown";
//                    }
//                    break;
//                }
            }

            //don't post data if the previous post and the current post is the same case
            if (activityMessage!=null) {
                if (!(activityMessage.equals(SaveSharedPreference.getActivityCase(ActivityRecognizedService.this)))) {
                    new PostData(activityMessage, longitude, latitude, new Date()).execute(Common.postNewLocation(personal_number));
                    SaveSharedPreference.setActivityCase(ActivityRecognizedService.this, activityMessage);
                }
            }
        }
    }


    private class PostData extends AsyncTask<String,String,String> {

        String activity;
        String longitude;
        String latitude;
        Date date;

        public PostData(String activity, String longitude, String latitude, Date date) {
            this.activity = activity;
            this.longitude = longitude;
            this.latitude = latitude;
            this.date = date;
        }

        @Override
        protected  void onPreExecute(){
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params){
            String urlString = params[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            String json="{\"longitude\":\""+longitude+"\"," +
                    "\"latitude\":\""+latitude+"\"," +
                    "\"activity\":\""+activity+"\"," +
                    "\"date\":\"" + date + "\" }";
            hh.PostHTTPData(urlString,json);
            return "";
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);

        }
    }
}