package com.example.jonada.challengerunning;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jonada.challengerunning.LocalDB.StaticMemoryDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompletedChallengesActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeMainView(R.layout.activity_completed_challenges);

        List<ChallengeData> inProgressChallenges = new ArrayList(Arrays.asList());
        List<ChallengeData> completedChallenges = new ArrayList(Arrays.asList());
        for(ChallengeData challenge : StaticMemoryDatabase.Challenges) {
            if(!challenge.getInitiator().isFinished() || !challenge.getReceiver().isFinished()) {
                inProgressChallenges.add(challenge);
            } else {
                completedChallenges.add(challenge);
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.challenge_completed_view);
        ChallengesListAdapter adapter = new ChallengesListAdapter(completedChallenges);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerViewInProgress = (RecyclerView) findViewById(R.id.challenge_progress_view);
        ChallengesListAdapter adapterForInProgress = new ChallengesListAdapter(inProgressChallenges);
        recyclerViewInProgress.setHasFixedSize(true);
        recyclerViewInProgress.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewInProgress.setAdapter(adapterForInProgress);
    }
}
