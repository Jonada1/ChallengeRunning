package com.example.jonada.challengerunning.LocalDB;

import com.example.jonada.challengerunning.ChallengeData;
import com.example.jonada.challengerunning.Challenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class StaticMemoryDatabase {
    static {
        Challenges = new ArrayList(Arrays.asList(
                new ChallengeData(
                        1,
                        new Challenger(1, "Jonada", 5.2, true),
                        new Challenger(2, "Nejdi", (double)3.3, true)
                ),
                new ChallengeData(
                        2,
                        new Challenger(1, "Jonada", 20.5, true),
                        new Challenger(3, "Nijar", 1.9, true)
                ),
                new ChallengeData(
                        3,
                        new Challenger(4, "Nijar", 0.0, true),
                        new Challenger(3, "Jonada", 1.9, true)
                )
        ));
        Challengers = new ArrayList(Arrays.asList());
    }
    public final static List<ChallengeData> Challenges;
    public final static List<Challenger> Challengers;
}