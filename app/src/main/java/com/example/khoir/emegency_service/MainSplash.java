package com.example.khoir.emegency_service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by khoir on 5/14/2016.
 */
public class MainSplash extends Activity {
    private static int splashInterval = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Thread() {


            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainSplash.this, MainActivity.class);
                MainSplash.this.startActivity(i);
                //jeda selesai Splashscreen
                MainSplash.this.finish();
            }
        }, splashInterval);
    }
}

