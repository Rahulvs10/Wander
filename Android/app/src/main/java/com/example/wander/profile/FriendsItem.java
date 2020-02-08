package com.example.wander.profile;

public class FriendsItem {
    private int profileImage;
    private String name;

    public FriendsItem(int profileImage, String name) {
        this.profileImage = profileImage;
        this.name = name;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }

}
