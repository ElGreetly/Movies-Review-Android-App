package com.example.android.moviesreview;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity implements Runnable {
    final String SPLASH_FRAG_TAG = "splash";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(isNetworkAvailable()){
        getSupportFragmentManager().beginTransaction().add(new SplashFragment(), SPLASH_FRAG_TAG).commit();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();}else{
            run();
        }

    }
    //This function check if there is an Internet connection or no
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
