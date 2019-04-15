package com.example.jonada.challengerunning;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;


public class RunSessionActivity extends FragmentActivity implements SensorEventListener, OnMapReadyCallback {
    /* MAP */
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    private int timePassed;
    private boolean timerPaused;
    final Timer timer = new Timer();
    int totalDuration;
    Button bt_start_pause;
    TextView steps;
    SensorManager sensorManager;
    Sensor mSensor;
    private boolean isSensorPresent = false;
    boolean running = false;

    public void centreMapOnLocation(Location location, String title) {

        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12));
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centreMapOnLocation(lastKnownLocation,"Your Location");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        timePassed = 0;
        timerPaused = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_session);




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_running);
        mapFragment.getMapAsync(this);

        steps = (TextView) findViewById(R.id.steps);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
                != null)
        {
            mSensor =
                    sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;
        }
        else
        {
            isSensorPresent = false;
        }

        Bundle extras = getIntent().getExtras();
        totalDuration = extras != null ? (int) extras.get("time"): 0;
        final TextView duration = (TextView) findViewById(R.id.textView_timer);
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(!timerPaused) {
                    timePassed ++;
                    duration.setText(convertToMinuteSeconds(totalDuration - timePassed));
                }
                if((timePassed  >= totalDuration)) {
                    timer.cancel();

                }
            }
        },0,1000);
    }
    public void onToggle(View view) {
        timerPaused = !timerPaused;
    }
    public void resetTimer(View view) {
        timePassed = 0;
        timerPaused = true;
        final TextView duration = (TextView) findViewById(R.id.textView_timer);
        duration.setText(convertToMinuteSeconds(totalDuration - timePassed));
    }
    private String convertToMinuteSeconds(int seconds) {
        int secondsRemainder = seconds % 60;
        int hours = seconds / 3600;
        int minutes = (seconds / 60)%60;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(hours).append("h:")
                .append(minutes).append("m:")
                .append(secondsRemainder).append("s");
        return stringBuilder.toString();
    }

    @Override
    protected void onResume(){
        super.onResume();
//        running = true;
////        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
////        if(countSensor != null){
////            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
////        } else {
////            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
////        }

        if(isSensorPresent)
        {
            Toast.makeText(this, "Sensor found", Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(this, mSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isSensorPresent)
        {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            Toast.makeText(this, String.valueOf(event.values[0]), Toast.LENGTH_SHORT).show();
            steps.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();
        if (intent.getIntExtra("Place Number",0) == 0 ){

            // Zoom into users location
            locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    centreMapOnLocation(location,"Your Location");
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
            };

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centreMapOnLocation(lastKnownLocation,"Your Location");
            } else {

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

    }
}
