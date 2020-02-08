package com.example.wander.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.camerakit.CameraKit;
import com.camerakit.CameraKitView;
import com.example.wander.R;

public class HomeFragment extends Fragment implements HomeView{

    private ProgressBar progressBar;
    private HomePresenter presenter;
    private CameraKitView cameraKitView;
    private Button captureImage;
    private ImageButton switch_camera;
    private ImageView preview_img;
    private ImageView zoomed_img;

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

        cameraKitView = view.findViewById(R.id.camera);
        captureImage = view.findViewById(R.id.capture_image);
        switch_camera = view.findViewById(R.id.switch_camera_facing);
        preview_img = view.findViewById(R.id.img_preview);
        zoomed_img = view.findViewById(R.id.zoomed_image);
        progressBar = view.findViewById(R.id.progress);
        presenter = new HomePresenter(this,new HomeInteractor());
        switch_camera.setOnClickListener(v->SwitchFacing());
        preview_img.setOnClickListener(v->zoom());

        captureImage.setOnClickListener(v->ImageCapture());

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA},
                    50);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    public void onResume() {
        cameraKitView.onResume();
        super.onResume();
        Log.d("Camera","Camera has been resumed");
        SwitchFacing();
    }

    @Override
    public void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void zoom() {
        if (zoomed_img.getVisibility() == View.GONE) {
            zoomed_img.setVisibility(View.VISIBLE);
        } else {
            zoomed_img.setVisibility(View.GONE);
        }
    }

    public void SwitchFacing() {
        if (cameraKitView.getFacing() == CameraKit.FACING_BACK){
            cameraKitView.setFacing(CameraKit.FACING_FRONT);
        }else {
            cameraKitView.setFacing(CameraKit.FACING_BACK);
        }
    }

    private void ImageCapture() {
        cameraKitView.captureImage((cameraKitView, capturedImage) -> {
            //Preview of the captured image
            Bitmap bitmap_preview = BitmapFactory.decodeByteArray(capturedImage, 0, capturedImage.length);
            preview_img.setImageBitmap(bitmap_preview);
            zoomed_img.setImageBitmap(bitmap_preview);

            //Captured image should be stored in the database
            presenter.storeImage(bitmap_preview);

        });

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

}
