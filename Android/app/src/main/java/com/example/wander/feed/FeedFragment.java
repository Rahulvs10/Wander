package com.example.wander.feed;


import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.wander.R;

import java.util.ArrayList;

public class FeedFragment extends Fragment {

    RecyclerView friends_feeds_recycler,public_feeds_recycler;
    ArrayList<FeedInteractor> feedInteractorArrayList = new ArrayList<>();
    FeedAdapter feedAdapter;

    private Switch switch_posts;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        switch_posts = view.findViewById(R.id.type_of_posts);
        friends_feeds_recycler = view.findViewById(R.id.friends_feeds_recycler);
        public_feeds_recycler = view.findViewById(R.id.public_feeds_recycler);

        //By default display Friends posts
        showFriendsPosts();

        //Switch for friends or public posts
        switch_posts.setOnCheckedChangeListener((compoundButton, isSwitchedOn) -> {
            if (isSwitchedOn) {
                //Friends posts
                showPublicPosts();
            }else {
                //Public posts
                showFriendsPosts();
            }
        });


        return view;
    }

    private void showFriendsPosts() {
        public_feeds_recycler.setVisibility(View.GONE);
        friends_feeds_recycler.setVisibility(View.VISIBLE);
        friends_feeds_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        feedAdapter = new FeedAdapter(getContext(),feedInteractorArrayList,0);
        friends_feeds_recycler.setAdapter(feedAdapter);

        //In order to avoid duplicating the posts
        feedAdapter.feedInteractorArrayList.clear();
        fillFriendsFeedRecycler();
    }

    private void showPublicPosts() {
        friends_feeds_recycler.setVisibility(View.GONE);
        public_feeds_recycler.setVisibility(View.VISIBLE);
        public_feeds_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        feedAdapter = new FeedAdapter(getContext(),feedInteractorArrayList,1);
        public_feeds_recycler.setAdapter(feedAdapter);

        //In order to avoid duplicating the posts
        feedAdapter.feedInteractorArrayList.clear();
        fillPublicFeedRecycler();
    }


    private void fillFriendsFeedRecycler() {
        FeedInteractor feedItem = new FeedInteractor(1,7,R.drawable.profile_pic,R.drawable.img1,14,"Manish","3 hrs", "Time's ticking!",0,false);
        feedInteractorArrayList.add(feedItem);

        feedItem = new FeedInteractor(3,16,R.drawable.profile_pic,R.drawable.img3,24,"Manish","1 hrs", "Octopus invasion!!",0,false);
        feedInteractorArrayList.add(feedItem);
    }

    private void fillPublicFeedRecycler() {
        FeedInteractor feedItem = new FeedInteractor(2,26,R.drawable.profile_pic,R.drawable.img2,10,"Manish","2 hrs", "#Photography",1,false);
        feedInteractorArrayList.add(feedItem);
    }
}
