package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class StakeHolder extends AppCompatActivity {

    Button owner,tenant,manager;
    ImageView pgowner,pgtenant,pgmanager;
    ViewPager viewPager;
    float v=0;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stake_holder);

        viewPager=findViewById(R.id.view_pager);

        owner=findViewById(R.id.owner);
        tenant=findViewById(R.id.tenant);
        manager=findViewById(R.id.manager);
        pgowner=findViewById(R.id.pgowner);
        pgtenant=findViewById(R.id.pgtenant);
        pgmanager=findViewById(R.id.pgmanager);

        owner.setTranslationX(800);
        tenant.setTranslationX(800);
        manager.setTranslationX(800);
        pgowner.setTranslationX(800);
        pgtenant.setTranslationX(800);
        pgmanager.setTranslationX(800);

        owner.setAlpha(v);
        tenant.setAlpha(v);
        manager.setAlpha(v);
        pgowner.setAlpha(v);
        pgtenant.setAlpha(v);
        pgmanager.setAlpha(v);

        owner.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        tenant.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        manager.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        pgowner.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        pgtenant.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        pgmanager.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();

        owner.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StakeHolder.this,LoginActivity.class);
                StakeHolder.this.finish();
                startActivity(intent);
            }
        });

        tenant.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StakeHolder.this,LoginActivity.class);
                StakeHolder.this.finish();
                startActivity(intent);
            }
        });

        manager.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StakeHolder.this,LoginActivity.class);
                StakeHolder.this.finish();
                startActivity(intent);
            }
        });

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