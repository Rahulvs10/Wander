package com.example.wander.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wander.R;
import com.example.wander.feed.FeedActivity;
import com.example.wander.login.LoginActivity;
import com.example.wander.profile.ProfileActivity;

public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final int CAMERA_REQUEST = 8888;
    float x1,x2,y1,y2;
    private ImageView imageView;
    private Button cameraButton,logoutButton;
    private ProgressBar progressBar;
    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView = findViewById(R.id.img_camera);
        cameraButton = findViewById(R.id.btn_camera);
        logoutButton = findViewById(R.id.btn_logout);
        progressBar = findViewById(R.id.progress);

        presenter = new HomePresenter(this,new HomeInteractor());

        cameraButton.setOnClickListener(view -> openCamera());
        logoutButton.setOnClickListener(view -> logout());

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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
                    navigateToProfile();
                }else if(x1 > x2){
                    navigateToFeed();
                }
                break;
        }
        return false;
    }

    public void navigateToProfile() {
        startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
        overridePendingTransition(R.anim.enter,R.anim.exit);
    }

    public void navigateToFeed() {
        startActivity(new Intent(HomeActivity.this,FeedActivity.class));
        overridePendingTransition(R.anim.enter2,R.anim.exit2);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displaySuccess() {
        Toast.makeText(HomeActivity.this,"Successfully stored in the database",Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayFailure() {
        Toast.makeText(HomeActivity.this,"Error in storing the image",Toast.LENGTH_LONG).show();
    }

    @Override
    public void logout() {
        this.finishAffinity();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST) {
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
                presenter.storeImage(imageView);
            }catch (Exception e) {
                Log.d("Setting imageView Error : ", e.toString());
            }
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Click logout to exit",Toast.LENGTH_SHORT).show();
    }
}
