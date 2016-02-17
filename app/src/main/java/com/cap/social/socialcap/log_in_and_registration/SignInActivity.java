package com.cap.social.socialcap.log_in_and_registration;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.cap.social.socialcap.R;
import com.cap.social.socialcap.utils.Component;

public class SignInActivity extends AppCompatActivity {
    // ui elements
    private TextInputLayout email_input_layout
            , userName_input_layout;
    private EditText emailEditText
            , passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        * change font of collapsing
        * */
        CollapsingToolbarLayout mCollapsing = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        Typeface socialCapFont = Typeface.createFromAsset(getAssets(), "fonts/gordon.ttf");
        mCollapsing.setExpandedTitleTypeface(socialCapFont);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, RegistrationActivity.class));
            }
        });
    }

    /* login with facebook */
    public void login_with_facebook(View view) {
        Component.make_snackbar(view, "login with facebook");
    }

    /* login with google plus */
    public void login_with_google(View view) {
        Component.make_snackbar(view, "login with google plus");
    }

    /* login with twitter */
    public void login_with_twitter(View view) {
        Component.make_snackbar(view, "login with twitter");
    }

    /* login with email */
    public void login_with_email(View view) {
        Component.make_snackbar(view, "login with email");
    }

    /* retrieve password */
    public void retrieve_password(View view) {
        Component.make_snackbar(view, "retrieve password");
    }
}
