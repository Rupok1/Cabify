package com.example.cabify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.window.SplashScreen;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private ViewPager screenPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    Button nxtBtn,getStartBtn;
    int position = 0;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(restorePrefData())
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();


        tabLayout = findViewById(R.id.tabLayout);
        nxtBtn = findViewById(R.id.next);
        getStartBtn = findViewById(R.id.getStartBtn);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_anim);



        List<ScreenItem>mList = new ArrayList<>();
        mList.add(new ScreenItem("Title_1","Lorem Ipsum is simply dummy text of the printing and typesetting industry.",R.drawable.l2));
        mList.add(new ScreenItem("Title_2","Lorem Ipsum is simply dummy text of the printing and typesetting industry.",R.drawable.logo1));
        mList.add(new ScreenItem("Title_3","Lorem Ipsum is simply dummy text of the printing and typesetting industry.",R.drawable.l3));

        screenPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this,mList);
        screenPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(screenPager);


        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if(position<mList.size())
                {
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if(position == mList.size()-1)
                {
                    loadLastScreen();
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() == mList.size()-1)
                {
                    loadLastScreen();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                savePrefsData();
                finish();
            }
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myprefs",MODE_PRIVATE);
        Boolean isIntroOpenBefore = pref.getBoolean("isIntroOpen",false);
        return isIntroOpenBefore;
    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myprefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpen",true);
        editor.commit();

    }

    private void loadLastScreen() {

        nxtBtn.setVisibility(View.INVISIBLE);
        getStartBtn.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);

        getStartBtn.setAnimation(btnAnim);

    }
}