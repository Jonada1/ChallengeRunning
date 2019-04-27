package com.example.jonada.challengerunning;

public class ChallengeData {
    public ChallengeData(int id, Challenger initiator, Challenger receiver, boolean finished) {
        this.id = id;
        this.initiator = initiator;
        this.receiver = receiver;
        this.finished = finished;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
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
    private boolean finished;
}