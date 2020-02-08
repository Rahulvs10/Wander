package com.example.wander.feed;

import java.util.ArrayList;
import android.os.Handler;

public class FeedInteractor {

    interface onPostsFetchedListener {
        void onSuccess(int type);

        void onFailure();
    }

    void fetchPosts(ArrayList<FeedItem> feedItemArrayList,int type_of_post, final onPostsFetchedListener listener) {

        Handler handler = new Handler();
        handler.postDelayed(()->{
            //Fetch the posts from the database and add it to the feedItemArrayList
            //If there is an error,display onFailure() else onSuccess()

            if (type_of_post == 0){
                //Fetch friends posts
                listener.onSuccess(0);
                return;
            } else if(type_of_post == 1) {
                //Fetch public posts
                listener.onSuccess(1);
                return;
            }
            listener.onFailure();


        },1000);
    }
}
