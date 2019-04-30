package com.example.jonada.challengerunning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonada.challengerunning.LocalDB.StaticMemoryDatabase;

import java.util.Random;

public class NewChallengeActivity extends BaseNavigationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeMainView(R.layout.activity_new_challenge, "New Challenge");

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
        String friend = getSelectedFriend();
        EditText durationTime = (EditText) findViewById(R.id.editText_duration);
        Intent runningSessionIntent = new Intent(this, RunSessionActivity.class );
        Random r = new Random();
        int challengeId = r.nextInt((99999999 - 1) + 1) + 1;
        ChallengeData newChallenge = new ChallengeData(
                challengeId,
                new Challenger(1, "Jane"),
                new Challenger(2, friend)
        );
        StaticMemoryDatabase.Challenges.add(newChallenge);

        runningSessionIntent.putExtra("time", Integer.parseInt(durationTime.getText().toString()) * 60);
        runningSessionIntent.putExtra("challengeId", challengeId);
        runningSessionIntent.putExtra("isInitiator", true);
        Toast.makeText(this, "Notification sent", Toast.LENGTH_SHORT).show();
        startActivity(runningSessionIntent);
    }

    private String getSelectedFriend() {

        Spinner friendSpinner = (Spinner)findViewById(R.id.spinner);
        String text = friendSpinner.getSelectedItem().toString();
        return text;
    }
}
