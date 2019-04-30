package com.example.jonada.challengerunning;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class FriendsActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeMainView(R.layout.activity_friends, "My Friends");

        FriendData[] myListData = new FriendData[] {
                new FriendData("Jonada", R.drawable.baseline_account_circle_black_24dp, android.R.drawable.ic_delete),
                new FriendData("Nejdi", R.drawable.baseline_account_circle_black_24dp, android.R.drawable.ic_delete),
                new FriendData("Ornela", R.drawable.baseline_account_circle_black_24dp, android.R.drawable.ic_delete),
                new FriendData("Nijar", R.drawable.baseline_account_circle_black_24dp, android.R.drawable.ic_delete),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.friends_view);
        FriendsListAdapter adapter = new FriendsListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_add_friend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddFriendIntent = new Intent(FriendsActivity.this, AddFriendActivity.class);
                startActivity(AddFriendIntent);
            }
        });
    }
}
