package com.example.katsigianni.pocketlocation.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.katsigianni.pocketlocation.Common;
import com.example.katsigianni.pocketlocation.HTTPDataHandler;
import com.example.katsigianni.pocketlocation.R;
import com.example.katsigianni.pocketlocation.SaveSharedPreference;
import com.example.katsigianni.pocketlocation.Services.CheckForBeaconsService;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

import java.util.Date;



public class LoggedActivity extends AppCompatActivity implements BeaconConsumer {

    public static final String TAG = "BeaconsEverywhere";
    private Button logout;
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SaveSharedPreference.setExitBedroom(LoggedActivity.this , "true");
        SaveSharedPreference.setExitLivingRoom(LoggedActivity.this , "true");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);

        logout = (Button) findViewById(R.id.logoutbut);

        logoutButton();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {

        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            boolean flag1;
            boolean flag2;



            @Override
            public void didEnterRegion(Region region) {

                SaveSharedPreference.setStopGps(LoggedActivity.this, "true");
                startService(new Intent(LoggedActivity.this, CheckForBeaconsService.class));
                new PostData(region.getUniqueId(),new Date()).execute(Common.postNewLocation(SaveSharedPreference.getPersonalNumber(LoggedActivity.this)));
                Log.i(TAG, "User is in the" + region.getId1() + " " + region.getId2() + " " + region.getId3() + " " + region.getUniqueId());
                if("bedroom".equals(region.getUniqueId())){
                    flag1 = false;
                    SaveSharedPreference.setExitBedroom(LoggedActivity.this , String.valueOf(flag1));
                } else{
                    flag2 = false;
                    SaveSharedPreference.setExitLivingRoom(LoggedActivity.this , String.valueOf(flag2));
                }
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "User exited the" + region.getId1() + " " + region.getId2() + " " + region.getId3() + " " + region.getUniqueId());
                if("bedroom".equals(region.getUniqueId())){
                    flag1 = true;
                    SaveSharedPreference.setExitBedroom(LoggedActivity.this , String.valueOf(flag1));
                } else{
                    flag2 = true;
                    SaveSharedPreference.setExitLivingRoom(LoggedActivity.this , String.valueOf(flag2));
                }
                if(flag1 && flag2){
                    startService(new Intent(LoggedActivity.this, CheckForBeaconsService.class));
                }

            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {

            }


        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("bedroom", Identifier.parse("EBEFD083-70A2-47C8-9837-E7B5634DF524"), Identifier.parse("571"), Identifier.parse("7552")));
            beaconManager.startMonitoringBeaconsInRegion(new Region("livingroom", Identifier.parse("4B495443-4845-4E0D-0A00-000000000000"), Identifier.parse("53075"), Identifier.parse("29692")));
        } catch (RemoteException e) {
            Log.i(TAG, String.valueOf(e));
        }


    }

    private void logoutButton() {

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveSharedPreference.clearUserInfo(LoggedActivity.this);
                Intent intent = new Intent(LoggedActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                break;
            default:
                break;
        }
    }

    private class PostData extends AsyncTask<String,String,String> {
        ProgressDialog pd = new ProgressDialog(LoggedActivity.this);
        String area;
        Date date;

        public PostData(String area,Date date) {
            this.area = area;
            this.date = date;
        }

        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params){
            String urlString = params[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            String json="{\"area\":\""+area+"\"," +
                    "\"date\":\"" + date + "\" }";
            hh.PostHTTPData(urlString,json);
            return "";
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            pd.dismiss();
        }
    }

}
