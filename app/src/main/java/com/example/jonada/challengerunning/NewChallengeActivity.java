package com.example.jonada.challengerunning;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewChallengeActivity extends SenseNavigationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeMainView(R.layout.activity_new_challenge);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.friends, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    public void startChallenge(View view) {
        EditText durationTime = (EditText) findViewById(R.id.editText_duration);
        Intent runningSessionIntent = new Intent(this, RunSessionActivity.class );
        runningSessionIntent.putExtra("time", Integer.parseInt(durationTime.getText().toString()) * 60);
        startActivity(runningSessionIntent);
    }
}
