package com.example.jonada.challengerunning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LeaderbordActivity extends SenseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeMainView(R.layout.activity_leaderbord);
    }
}
