package com.siianko.weather.screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.perf.metrics.AddTrace;
import com.siianko.weather.R;
import com.siianko.weather.screen.cities.CityListActivity;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {

    private Button loginButton;
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    @AddTrace(name = "onCreateTrace", enabled = true/*Optional*/)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        if (AccessToken.getCurrentAccessToken() != null) {
            goToTownList();
            finish();
            return;
        }

        loginButton = (Button) findViewById(R.id.btn_sign_in_facebook);

        callbackManager = CallbackManager.Factory.create();

        loginManager = LoginManager.getInstance();

        loginManager.registerCallback(callbackManager,this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FacebookLoginClick();
            }
        });

    }

    private void goToTownList() {
        Intent i = new Intent(this, CityListActivity.class);
        startActivity(i);
    }


    //region FacebookCallback<LoginResult>
    @Override
    public void onSuccess(LoginResult loginResult) {
        String auth = loginResult.getAccessToken().getUserId();
        goToTownList();
    }

    @Override
    public void onCancel() {
        // App code
    }

    @Override
    public void onError(FacebookException error) {
        // App code
    }

    public void FacebookLoginClick(){
        loginManager.logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
    }
    //endregion


}
