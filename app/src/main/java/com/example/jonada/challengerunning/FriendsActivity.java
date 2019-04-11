package com.example.jonada.challengerunning;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class FriendsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        FriendsData[] myListData = new FriendsData[] {
                new FriendsData("Jonada Ferracaku", R.drawable.baseline_account_circle_black_24dp, android.R.drawable.ic_delete),
                new FriendsData("Nejdi Kroi", R.drawable.baseline_account_circle_black_24dp, android.R.drawable.ic_delete),
                new FriendsData("Mobile Computing", R.drawable.baseline_account_circle_black_24dp, android.R.drawable.ic_delete),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.friends_view);
        FriendsListAdapter adapter = new FriendsListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.text_view_add_friend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddFriendIntent = new Intent(FriendsActivity.this, AddFriendActivity.class);
                startActivity(AddFriendIntent);
            }
        });
    }
}
