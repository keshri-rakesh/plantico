package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //variables
    Animation top_anim,bottom_anim,fade_out;
    ImageView logo,splashImg;
    //TextView Pr_name,Tagline;

    private static final int NUM_PAGES=4;
    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation
        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        fade_out= AnimationUtils.loadAnimation(this,R.anim.fade_out);

        //Hooks
        //logo=findViewById(R.id.logo);
        splashImg=findViewById(R.id.bg);
        //Pr_name=findViewById(R.id.Pr_name);
        //Tagline=findViewById(R.id.Tagline);



        //logo.setAnimation(top_anim);
        //Pr_name.setAnimation(bottom_anim);
        //Tagline.setAnimation(bottom_anim);

       // splashImg.animate().translationY(6000).setDuration(6000);
        splashImg.getVisibility();
        //logo.animate().translationY(-2000).setDuration(1000).setStartDelay(2000);
        //Pr_name.animate().translationY(1400).setDuration(1000).setStartDelay(2000);
        //Tagline.animate().translationY(1400).setDuration(1000).setStartDelay(2000);
        splashImg.animate().translationX(-3000).setDuration(900).setStartDelay(2000);
        //splashImg.setAnimation(fade_out);
        //splashImg.setVisibility(View.INVISIBLE);
        //splashImg.animate().translationY(-3000).setDuration(10000);

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

    }

    static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{


        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                    return tab1;
                case 1:
                    OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                    return tab2;
                case 2:
                    OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                    return tab3;
                case 3:
                    OnBoardingFragment4 tab4 = new OnBoardingFragment4();
                    return tab4;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}