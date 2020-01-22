package com.example.wander.feed;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.wander.R;


import java.util.ArrayList;


public class FeedFragment extends Fragment implements FeedView{

    RecyclerView friends_feeds_recycler,public_feeds_recycler;
    ArrayList<FeedItem> feedItemArrayList = new ArrayList<>();
    FeedAdapter feedAdapter;
    private Switch switch_posts;
    private ProgressBar progressBar;

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
        progressBar = view.findViewById(R.id.progress);

        FeedPresenter presenter = new FeedPresenter(this,new FeedInteractor());

        //By default display Friends posts
        showFriendsPosts();

        //Switch for friends or public posts
        switch_posts.setOnCheckedChangeListener((compoundButton, isSwitchedOn) -> {
            if (isSwitchedOn) {
                //Friends posts
                presenter.fetchPosts(feedItemArrayList,0);
            }else {
                //Public posts
                presenter.fetchPosts(feedItemArrayList,1);
            }
        });
        return view;
    }


    public void showFriendsPosts() {
        public_feeds_recycler.setVisibility(View.GONE);
        friends_feeds_recycler.setVisibility(View.VISIBLE);
        friends_feeds_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        feedAdapter = new FeedAdapter(getContext(),feedItemArrayList,0);
        friends_feeds_recycler.setAdapter(feedAdapter);

        //In order to avoid duplicating the posts
        feedAdapter.feedInteractorArrayList.clear();
        fillFriendsFeedRecycler();
    }


    public void showPublicPosts() {
        friends_feeds_recycler.setVisibility(View.GONE);
        public_feeds_recycler.setVisibility(View.VISIBLE);
        public_feeds_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        feedAdapter = new FeedAdapter(getContext(),feedItemArrayList,1);
        public_feeds_recycler.setAdapter(feedAdapter);

        //In order to avoid duplicating the posts
        feedAdapter.feedInteractorArrayList.clear();
        fillPublicFeedRecycler();
    }


    public void fillFriendsFeedRecycler() {
        FeedItem feedItem = new FeedItem(1,7,R.drawable.profile_pic,R.drawable.img1,14,"Manish","3 hrs", "Time's ticking!",false);
        feedItemArrayList.add(feedItem);

        feedItem = new FeedItem(3,16,R.drawable.profile_pic,R.drawable.img3,24,"Manish","1 hrs", "Octopus invasion!!",false);
        feedItemArrayList.add(feedItem);
    }

    public void fillPublicFeedRecycler() {
        FeedItem feedItem = new FeedItem(2,26,R.drawable.profile_pic,R.drawable.img2,10,"Manish","2 hrs", "#Photography",false);
        feedItemArrayList.add(feedItem);
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(),"An error occurred while fetching the posts!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

}
