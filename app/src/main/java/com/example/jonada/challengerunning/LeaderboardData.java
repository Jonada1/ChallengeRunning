package com.example.jonada.challengerunning;

public class LeaderboardData {
    private int imgId;
    private String name;
    private String distance;
    private int img;

    public LeaderboardData(int imgId, String name, String distance, int img) {
        this.imgId = imgId;
        this.name = name;
        this.distance = distance;
        this.img = img;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
