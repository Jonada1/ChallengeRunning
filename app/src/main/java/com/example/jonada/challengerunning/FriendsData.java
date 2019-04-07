package com.example.jonada.challengerunning;

public class FriendsData {
    private String name;
    private int imgId;
    private int delete;

    public FriendsData(String description, int imgId, int delete) {
        this.name = description;
        this.imgId = imgId;
        this.delete = delete;
    }

    public String getDescription() {

        return name;
    }

    public void setDescription(String description) {

        this.name = description;
    }
    public int getImgId() {

        return imgId;
    }
    public void setImgId(int imgId) {

        this.imgId = imgId;
    }

    public int getDelId() {

        return delete;
    }
    public void setDelId(int delete) {

        this.delete = delete;
    }
}
