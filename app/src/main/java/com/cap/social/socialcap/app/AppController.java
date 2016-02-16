package com.cap.social.socialcap.app;

import android.app.Application;


/**
 * Created by mostafa on 12/02/16.
 */
public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Firebase.setAndroidContext(this);
    }
}
