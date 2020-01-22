package com.example.wander.feed;

public interface FeedView {

    void showPublicPosts();

    void showFriendsPosts();

    void fillFriendsFeedRecycler();

    void fillPublicFeedRecycler();

    void displayError();

    void showProgress();

    void hideProgress();
}
