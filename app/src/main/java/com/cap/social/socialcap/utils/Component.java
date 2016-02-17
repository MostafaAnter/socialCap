package com.cap.social.socialcap.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by mostafa on 16/02/16.
 */
public class Component {
    public static void make_snackbar(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
