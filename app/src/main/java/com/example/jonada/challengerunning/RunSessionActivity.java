package com.example.jonada.challengerunning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;

public class RunSessionActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private Boolean started;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Toast.makeText(this, extras.get("time").toString(), Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_run_session);
        this.started = false;
    }
}
