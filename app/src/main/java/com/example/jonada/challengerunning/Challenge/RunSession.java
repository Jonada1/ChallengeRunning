package com.example.jonada.challengerunning.Challenge;

public class RunSession {
    private String id;
    private String UserId;
    private String duration;
    private String distance;

    public RunSession(String id, String userId, String duration, String distance) {
        this.setId(id);
        setUserId(userId);
        this.setDuration(duration);
        this.setDistance(distance);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
