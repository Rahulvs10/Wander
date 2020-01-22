package com.example.wander.feed;

import java.util.ArrayList;

public class FeedPresenter implements FeedInteractor.onPostsFetchedListener{

    private FeedView feedView;
    private FeedInteractor feedInteractor;

    public FeedPresenter(FeedView feedView,FeedInteractor feedInteractor) {
        this.feedView = feedView;
        this.feedInteractor = feedInteractor;
    }

    public void fetchPosts(ArrayList<FeedItem> feedItemArrayList,int type_of_post) {
        if(feedView != null){
            feedView.showProgress();
            //if type_of_post is 0, display friends posts
            //else if type_of_post is 1, display public posts
        }
        feedInteractor.fetchPosts(feedItemArrayList,type_of_post,this);
    }

    public void onDestroy(){
        feedView=null;
    }

    @Override
    public void onSuccess(int type) {
        if (type == 1) {
            feedView.showFriendsPosts();
        } else {
            feedView.showPublicPosts();
        }
        feedView.hideProgress();
    }

    @Override
    public void onFailure() {
        feedView.displayError();
        feedView.hideProgress();
    }
}
