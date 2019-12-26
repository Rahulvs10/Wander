package com.example.wander.feed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.wander.R;
import com.example.wander.home.HomeActivity;

public class FeedActivity extends AppCompatActivity implements FeedView {

    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    navigateToHome();
                }else {
                    Toast.makeText(FeedActivity.this,"End",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return false;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter,R.anim.exit);
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(FeedActivity.this, HomeActivity.class));
        overridePendingTransition(R.anim.enter,R.anim.exit);
    }
}
