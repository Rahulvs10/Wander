package com.example.wander.home;


import android.graphics.Bitmap;
import android.os.Handler;

public class HomeInteractor {

    interface onImageStoredListener {
        void onSuccess();

        void onFailure();
    }

    void storeImage(Bitmap capturedImage, final onImageStoredListener listener) {
        Handler handler = new Handler();
        //Database storage operation , call onSuccess() on successful storage of the image else call onFailure()
        handler.postDelayed(()->{
            if (true) {
                //Store the capturedImage in the database

                listener.onSuccess();
                return;
            }
            listener.onFailure();
        },2000);
    }
}
