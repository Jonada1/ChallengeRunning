package com.example.jonada.challengerunning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class AddFriendActivity extends AppCompatActivity {

    private static final String[] friends = new String[]{
            "Nejdi", "Jonada", "Ornela", "Friend", "Someone"
    };
    AutoCompleteTextView friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        String[] countries = getResources().getStringArray(R.array.friends);

        friendsList = findViewById(R.id.autoCompleteTextView_friend_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.search_friend_list_item, R.id.text_view_list_item, friends);
        friendsList.setAdapter(adapter);
        friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                String selection = (String)parent.getItemAtPosition(position);
                Toast.makeText(AddFriendActivity.this, "Friend : \t" + selection + " added!", Toast.LENGTH_LONG).show();
            }});
    }
}


