package com.example.wander.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wander.R;
import com.example.wander.login.LoginActivity;

public class HomeFragment extends Fragment implements HomeView {

    private static final int CAMERA_REQUEST = 8888;
    private ImageView imageView;
    private Button cameraButton,logoutButton;
    private ProgressBar progressBar;
    private HomePresenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.img_camera);
        cameraButton = view.findViewById(R.id.btn_camera);
        logoutButton = view.findViewById(R.id.btn_logout);
        progressBar = view.findViewById(R.id.progress);

        presenter = new HomePresenter(this,new HomeInteractor());

        cameraButton.setOnClickListener(v -> openCamera());
        logoutButton.setOnClickListener(v -> logout());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displaySuccess() {
        Toast.makeText(getContext(),"Successfully stored in the database",Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayFailure() {
        Toast.makeText(getContext(),"Error in storing the image",Toast.LENGTH_LONG).show();
    }

    @Override
    public void logout() {
        try {
            getActivity().finishAffinity();
        }catch (NullPointerException e) {
            Log.d("Logout exception", "Error while logging out");
            return;
        }
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
