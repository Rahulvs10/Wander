package com.example.wander.feed;

public class FeedInteractor {

    private int id,likes,profile_pic,posted_pic,comments;
    String name,time,status;
    private int type_of_posts; //0=>friend's posts, 1=>public posts

    public FeedInteractor(int id, int likes, int profile_pic, int posted_pic, int comments, String name, String time, String status,int type_of_posts) {
        this.id = id;
        this.likes = likes;
        this.profile_pic = profile_pic;
        this.posted_pic = posted_pic;
        this.comments = comments;
        this.name = name;
        this.time = time;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }

    public int getPosted_pic() {
        return posted_pic;
    }

    public void setPosted_pic(int posted_pic) {
        this.posted_pic = posted_pic;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public int getType_of_posts() {
        return type_of_posts;
    }

    public void setType_of_posts(int type_of_posts) {
        this.type_of_posts = type_of_posts;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
