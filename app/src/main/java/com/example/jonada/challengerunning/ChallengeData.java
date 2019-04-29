package com.example.jonada.challengerunning;

public class ChallengeData {
    public ChallengeData(int id, Challenger initiator, Challenger receiver) {
        this.id = id;
        this.initiator = initiator;
        this.receiver = receiver;
    }

    public Challenger getInitiator() {
        return initiator;
    }

    public void setInitiator(Challenger initiator) {
        this.initiator = initiator;
    }

    public Challenger getReceiver() {
        return receiver;
    }

    public void setReceiver(Challenger receiver) {
        this.receiver = receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private Challenger initiator;
    private Challenger receiver;
}