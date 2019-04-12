package com.example.jonada.challengerunning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;


public class RunSessionActivity extends AppCompatActivity {
    private int timePassed;
    private boolean timerPaused;
    final Timer timer = new Timer();
    int totalDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        timePassed = 0;
        timerPaused = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_session);
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
}
