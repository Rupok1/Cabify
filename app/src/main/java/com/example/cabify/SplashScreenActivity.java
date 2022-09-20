package com.example.cabify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.window.SplashScreen;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LottieAnimationView view = findViewById(R.id.car);
        view.setVisibility(View.VISIBLE);
        view.playAnimation();

        Handler handler = new Handler();

         handler.postDelayed(new Runnable() {
             @Override
             public void run() {
              startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
              finish();
             }
         },3000);

    }
}