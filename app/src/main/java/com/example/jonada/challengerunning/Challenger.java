package com.example.jonada.challengerunning;

public class Challenger {
    public Challenger(int id, String name, Double distance) {
        this.id = id;
        this.name = name;
        this.distance = distance;
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
}
