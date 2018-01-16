package com.example.katsigianni.pocketlocation;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

import java.util.Date;

public class LoggedActivity extends AppCompatActivity implements BeaconConsumer {

    public static final String TAG = "BeaconsEverywhere";
    private Button b;
    private TextView t;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button logout;
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);

        //   TextView welc=(TextView)findViewById(R.id.welcome);
        // welc.setText(getIntent().getExtras().getString("fullname"));

        t = (TextView) findViewById(R.id.coors);
        b = (Button) findViewById(R.id.buttoncoor);
        logout = (Button) findViewById(R.id.logoutbut);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                t.append("\n " + location.getLongitude() + " " + location.getLatitude());

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {


            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }else{
            configureButton();

        }

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
                new PostData(region.getUniqueId().toString(),new Date()).execute(Common.postNewLocation(SaveSharedPreference.getPersonalNumber(LoggedActivity.this)));
                Log.i(TAG, "User is in the" + region.getId1() + " " + region.getId2() + " " + region.getId3() + " " + region.getUniqueId());
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "User exited the" + region.getId1() + " " + region.getId2() + " " + region.getId3() + " " + region.getUniqueId());
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {

            }


        });



    /*    beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                for(Beacon oneBeacon :  beacons){
                    Log.d(TAG,"distance: " + oneBeacon.getDistance() + "id: " + oneBeacon.getId1() + "/" + oneBeacon.getId2() + "/" + oneBeacon.getId3());
                }

            }
        }); */

        try {

            beaconManager.startMonitoringBeaconsInRegion(new Region("bedroom", Identifier.parse("EBEFD083-70A2-47C8-9837-E7B5634DF524"), Identifier.parse("571"), Identifier.parse("7552")));
            beaconManager.startMonitoringBeaconsInRegion(new Region("livingroom", Identifier.parse("4B495443-4845-4E0D-0A00-000000000000"), Identifier.parse("53075"), Identifier.parse("29692")));
        } catch (RemoteException e) {

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configureButton();
                break;
            default:
                break;
        }
    }

    private void configureButton() {

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                locationManager.requestLocationUpdates("gps", 5000, 5, locationListener);

            }
        });
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
