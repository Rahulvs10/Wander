package com.example.wander.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.wander.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    Context context;
    ArrayList<FeedInteractor> feedInteractorArrayList;
    RequestManager glide;
    int switch_status; //0=>friend's posts, 1=>public posts
    int type_of_posts; //0=>friend's posts, 1=>public posts

    public FeedAdapter(Context context, ArrayList<FeedInteractor> feedInteractorArrayList,int switch_status) {
        this.context = context;
        this.feedInteractorArrayList = feedInteractorArrayList;
        this.switch_status = switch_status;
        glide = Glide.with(context);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feed,parent,false);
        FeedViewHolder viewHolder = new FeedViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        final FeedInteractor feedInteractor = feedInteractorArrayList.get(position);

        holder.name.setText(feedInteractor.getName());
        holder.timestamp.setText(feedInteractor.getTime());
        //Number of likes should be in number format
        holder.likes.setText(String.valueOf(feedInteractor.getLikes()));
        holder.comments.setText(feedInteractor.getComments() + " comments");
        holder.status.setText(feedInteractor.getStatus());

        //Load the image into the image view corresponding to profile pic
        glide.load(feedInteractor.getProfile_pic()).into(holder.profile_pic);

        //Check if there is an image in database and accordingly populate the image view corresponding to posted_pic
        if (feedInteractor.getPosted_pic() == 0) {
            holder.posted_pic.setVisibility(View.GONE);
        } else {
            holder.posted_pic.setVisibility(View.VISIBLE);
            glide.load(feedInteractor.getPosted_pic()).into(holder.posted_pic);
        }

    }

    @Override
    public int getItemCount() {
        return feedInteractorArrayList.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {

        TextView name,timestamp,likes,comments, status;
        ImageView profile_pic,posted_pic;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_pic = itemView.findViewById(R.id.profile_pic);
            posted_pic = itemView.findViewById(R.id.posted_pic);

            name = itemView.findViewById(R.id.tv_name);
            timestamp = itemView.findViewById(R.id.tv_timestamp);
            likes = itemView.findViewById(R.id.tv_likes);
            comments = itemView.findViewById(R.id.tv_comments);
            status = itemView.findViewById(R.id.tv_status);

        }
    }
}
