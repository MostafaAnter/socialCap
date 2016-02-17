package com.cap.social.socialcap.log_in_and_registration;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.cap.social.socialcap.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity{
    //ui elements
    @Bind(R.id.input_layout_register_email) TextInputLayout emailInputLayout;
    @Bind(R.id.input_layout_register_username) TextInputLayout userNameInputLayout;
    @Bind(R.id.input_layout_register_password) TextInputLayout passwordInputLayout;
    @Bind(R.id.edit_register_email) EditText emailEditText;
    @Bind(R.id.edit_register_username) EditText userNameEditText;
    @Bind(R.id.edit_register_password) EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

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
                // clear this activity from stack
                RegistrationActivity.this.finish();
            }
        });


    }

    // show password method
    public void showPassword(View view) {
        passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        passwordEditText.setSelection(passwordEditText.length());
    }


}
