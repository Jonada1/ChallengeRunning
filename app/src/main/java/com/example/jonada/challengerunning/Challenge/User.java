package com.example.jonada.challengerunning.Challenge;

import java.util.Collection;

public class User {
    private String id;
    private String username;
    private String password;
    private String[] friendIds;
    private Collection<RunSession> runningSessions;

    public User(String id, String username, String password, String[] friendIds, Collection<RunSession> runningSessions) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setFriendIds(friendIds);
        this.setRunningSessions(runningSessions);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(String[] friendIds) {
        this.friendIds = friendIds;
    }

    public Collection<RunSession> getRunningSessions() {
        return runningSessions;
    }

    public void setRunningSessions(Collection<RunSession> runningSessions) {
        this.runningSessions = runningSessions;
    }
}
