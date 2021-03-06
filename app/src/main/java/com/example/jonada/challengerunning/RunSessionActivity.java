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
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonada.challengerunning.LocalDB.StaticMemoryDatabase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class RunSessionActivity extends FragmentActivity implements SensorEventListener, OnMapReadyCallback {
    /* MAP */
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    private boolean isInitiator;

    private double kmH;
    private double total_distance;

    private int timePassed;
    private boolean timerPaused;
    private int challengeId;
    final Timer timer = new Timer();
    int totalDuration;

    Button bt_pause;
    Button bt_play;
    Button bt_finish;
    Button bt_stop;
    TextView speed;
    TextView steps;
    TextView distance;

    SensorManager sensorManager;
    Sensor mSensor;
    private boolean isSensorPresent = false;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        kmH = 0;
        total_distance = 0;
        timePassed = 55;
        timerPaused = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_session);

        Bundle extras = getIntent().getExtras();

        challengeId = (int)extras.get("challengeId");
        totalDuration = (int) extras.get("time");
        isInitiator = (boolean)extras.get("isInitiator");

        assignViews();
        startMapAndSensor();
        createTimer();
    }
    private void setKmh(double kmh) {
        this.kmH = kmh;
        speed.setText(String.format("%.2f", kmh));
    }

    private void setTotalDistance(double distance) {
        this.total_distance = distance;
        double totalSteps = (distance * 1250);

        this.distance.setText(String.format( "%.2f", distance));
        this.steps.setText(String.format("%d", (long) totalSteps));

    }

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

    private void createTimer() {
        final TextView duration = (TextView) findViewById(R.id.textView_timer);
        final Handler handler = new Handler();
        final Challenger challenger = getCurrentChallenger();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                if(!timerPaused) {
                    timePassed ++;

                    Random random = new Random();
                    final double kmH = (7.5 + random.nextDouble() * 2);
                    setKmh(kmH);
                    setTotalDistance(total_distance + kmH/3600);
                    challenger.setDistance(total_distance + kmH/3600);
                    duration.setText(convertToMinuteSeconds(totalDuration - timePassed));

                }
                if((timePassed  >= totalDuration)) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            bt_finish.setVisibility(bt_finish.VISIBLE);
                            bt_pause.setVisibility(bt_pause.GONE);
                            bt_stop.setVisibility(bt_stop.GONE);
                        }
                    });

                    timer.cancel();
                }
            }
        },0,1000);
    }

    private Challenger getCurrentChallenger() {
        Challenger challenger = new Challenger(1, "Some default");
        for(ChallengeData challenge : StaticMemoryDatabase.Challenges) {
            if(challenge.getId() == challengeId) {
                challenger = isInitiator ? challenge.getInitiator() : challenge.getReceiver();
            }
        }
        return challenger;
    }

    private void startMapAndSensor() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_running);
        mapFragment.getMapAsync(this);

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
    }

    private void assignViews() {
        steps = (TextView) findViewById(R.id.steps);
        speed = (TextView) findViewById(R.id.speed);
        distance = (TextView) findViewById(R.id.distance);
        bt_pause = (Button) findViewById(R.id.bt_pause);
        bt_play = (Button) findViewById(R.id.bt_play);
        bt_finish = (Button) findViewById(R.id.btn_finish);
        bt_stop = (Button) findViewById(R.id.btn_stop);
        bt_pause.setVisibility(View.VISIBLE);
        bt_play.setVisibility(View.GONE);
        bt_finish.setVisibility(View.GONE);
    }

    public void onToggle(View view) {
        timerPaused = !timerPaused;
        bt_pause.setVisibility(bt_pause.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        bt_play.setVisibility(bt_play.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    public  void onFinish(View view){
        for(ChallengeData challenge : StaticMemoryDatabase.Challenges) {
            if(challenge.getId() == challengeId) {
                Challenger challenger = isInitiator ? challenge.getInitiator() : challenge.getReceiver();
                Random random = new Random();
                challenger.setDistance((double) (random.nextInt(10 + 1) + 1));
            }
        }
        Intent NewChallengeIntent = new Intent(RunSessionActivity.this, CompletedChallengesActivity.class);
        startActivity(NewChallengeIntent);
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

        if(isSensorPresent)
        {
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
