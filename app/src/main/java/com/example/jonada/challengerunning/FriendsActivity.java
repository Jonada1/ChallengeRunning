package com.example.jonada.challengerunning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class FriendsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
//        recyclerView = (RecyclerView) findViewById(R.id.friends_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        FriendsData[] myListData = new FriendsData[] {
                new FriendsData("Jonada Ferracaku", android.R.drawable.ic_dialog_dialer, android.R.drawable.ic_delete),
                new FriendsData("Info", android.R.drawable.ic_dialog_info, android.R.drawable.ic_delete),
                new FriendsData("Delete", android.R.drawable.ic_delete, android.R.drawable.ic_delete),
                new FriendsData("Dialer", android.R.drawable.ic_dialog_dialer, android.R.drawable.ic_delete),
                new FriendsData("Alert", android.R.drawable.ic_dialog_alert, android.R.drawable.ic_delete),
                new FriendsData("Map", android.R.drawable.ic_dialog_map, android.R.drawable.ic_delete),
                new FriendsData("Email", android.R.drawable.ic_dialog_email, android.R.drawable.ic_delete),
                new FriendsData("Info", android.R.drawable.ic_dialog_info, android.R.drawable.ic_delete),
                new FriendsData("Delete", android.R.drawable.ic_delete, android.R.drawable.ic_delete),
                new FriendsData("Dialer", android.R.drawable.ic_dialog_dialer, android.R.drawable.ic_delete),
                new FriendsData("Alert", android.R.drawable.ic_dialog_alert, android.R.drawable.ic_delete),
                new FriendsData("Map", android.R.drawable.ic_dialog_map, android.R.drawable.ic_delete),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.friends_view);
        FriendsListAdapter adapter = new FriendsListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
