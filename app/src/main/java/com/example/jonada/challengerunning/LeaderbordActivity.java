package com.example.jonada.challengerunning;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LeaderbordActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeMainView(R.layout.activity_leaderbord, "Leaderboard");

        LeaderboardData[] ldList = new LeaderboardData[] {
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Jonada", "2.1 km", R.drawable.badge),
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Alex", "2.0 km", R.drawable.two),
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Nijar", "1.8 km", R.drawable.three),
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Nejdi", "1.7 km", R.drawable.four),
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Jane", "0.5 km", R.drawable.five),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.leaderboard_view);
        LeaderboardListAdapter lDadapter = new LeaderboardListAdapter(ldList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lDadapter);
    }
}
