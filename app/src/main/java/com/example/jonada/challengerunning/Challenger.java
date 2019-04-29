package com.example.jonada.challengerunning;

import java.util.Random;

public class Challenger {
    public Challenger(int id, String name, Double distance, boolean finished) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.finished = finished;
    }

    public Challenger(int id, String name) {
        this.id = id;
        this.name = name;
        this.distance = (double)0;
        this.finished = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    private int id;
    private String name;
    private Double distance;
    private boolean finished;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
