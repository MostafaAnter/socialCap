package com.cap.social.socialcap.log_in_and_registration;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.cap.social.socialcap.R;
import com.cap.social.socialcap.utils.Keys;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

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
        emailEditText.addTextChangedListener(new MyTextWatcher(emailEditText));
        passwordEditText.addTextChangedListener(new MyTextWatcher(passwordEditText));

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

    /*
    * this section belong authenticate checker
    * */
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edit_register_email:
                    emailInputLayout.setErrorEnabled(false);
                    break;
                case R.id.edit_register_password:
                    passwordInputLayout.setErrorEnabled(false);
                    break;
            }
        }
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateEmailAndPassword(){
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            emailInputLayout.setError("invalid email");
            requestFocus(emailEditText);
            return false;
        } else if (password.isEmpty() || password.length() < 4){
            passwordInputLayout.setError("password can't be less 4 digits");
            requestFocus(passwordEditText);
            return false;

        }else {
            return true;
        }

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            // show error message
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("Please check your Network connection!")
                    .show();
            return false;
        }
    }


    public void createUserAccount(View view) {
        if (validateEmailAndPassword() && isOnline()) {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            /*
            * Creating User Account
            * */

            // show loading message
            final SweetAlertDialog pDialog = new SweetAlertDialog(RegistrationActivity.this
                    , SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading..");
            pDialog.setCancelable(false);
            pDialog.show();

            Firebase ref = new Firebase(Keys.FIREBASE_APP);
            ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> result) {
                    // dismiss loading dialog
                    pDialog.dismissWithAnimation();
                    // A success message
                    new SweetAlertDialog(RegistrationActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Good job!")
                            .setContentText("creating new account success")
                            .setConfirmText("Sign In!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    RegistrationActivity.this.finish();
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                    Log.d("creating account", "Successfully created user account with uid: " + result.get("uid"));
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    // dismiss loading dialog
                    pDialog.dismissWithAnimation();
                    // there was an error so show error message
                    new SweetAlertDialog(RegistrationActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong try again!")
                            .show();
                }
            });
        }

    }
}
