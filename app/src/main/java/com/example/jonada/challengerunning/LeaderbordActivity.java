package com.example.jonada.challengerunning;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LeaderbordActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeMainView(R.layout.activity_leaderbord);

        LeaderboardData[] ldList = new LeaderboardData[] {
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Jonada Ferracaku", "1520 km", R.drawable.badge),
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Jonada Ferracaku", "1520 km", R.drawable.badge),
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Jonada Ferracaku", "1520 km", R.drawable.badge),
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Jonada Ferracaku", "1520 km", R.drawable.badge),
                new LeaderboardData( R.drawable.baseline_account_circle_black_24dp, "Jonada Ferracaku", "1520 km", R.drawable.badge),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.leaderboard_view);
        LeaderboardListAdapter lDadapter = new LeaderboardListAdapter(ldList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lDadapter);
    }
}
