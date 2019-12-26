package com.example.wander.home;


import android.os.Handler;
import android.widget.ImageView;

public class HomeInteractor {

    interface onImageStoredListener {
        void onSuccess();

        void onFailure();
    }

    void storeImage(ImageView imageView,final onImageStoredListener listener) {
        Handler handler = new Handler();
        //Database storage operation , call onSuccess() on successful storage of the image else call onFailure()
        handler.postDelayed(()->{
            if (true) {
                //Store the imageView in Database

                listener.onSuccess();
                return;
            }
            listener.onFailure();
        },2000);
    }
}
