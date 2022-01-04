package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.splashscreen.Adapter.FoodAdapter;
import com.example.splashscreen.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewFoodList;
    private FloatingActionButton add_to_cart,cart;
    boolean doubleBackToExitPressedOnce = false;
    //ImageView cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        cart = findViewById(R.id.imageView6);
        add_to_cart = findViewById(R.id.add_to_cart);

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,CartActivity.class);
                startActivity(i);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                onStart();

                Toast.makeText(getBaseContext(),"Successfully Logged Out",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        recyclerViewFood();
        recyclerViewFood2();
    }

    private void recyclerViewFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFoodList = findViewById(R.id.recyclerView2);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Areca Palm Plant", "image22", "Once an endangered species, the Areca Palm is a staple in Indian spaces." +
                "Its delicate leaves/fronds grow outwards in a curved manner and add a tropical feel to any space and can adapt to a variety of growing conditions.", 199.00));
        foodlist.add(new FoodDomain("Snake Plant", "image2", "The snake plant, commonly referred to as mother-in-law’s tongue, is a resilient succulent that can grow anywhere between 6 inches to several feet." +
                "\n What’s unique about this particular plant is that it’s one of the few plants that can convert carbon dioxide (CO2) into oxygen at night.", 149.00));
        foodlist.add(new FoodDomain("Eco Ocean Aglaonema Plant", "image44", " Aglaonemas are evergreens with large, oval-shaped leaves that grow in various shades of green on top of short stems." +
                "As a houseplant with proper care, aglaonemas can thrive wonderfully through any season; as long as they are kept in a warm place, away from direct light.", 249.00));
        foodlist.add(new FoodDomain("Cactus", "image4", "The name cactus is derived from the Greek word 'kaktos', which means 'spiky plant'." +
                "\nA cactus is perfect for the less green-fingered as you only need to water moderately – in fact, it's actually better to underwater than it is to overwater.", 299.00));

        adapter = new FoodAdapter(foodlist);
        recyclerViewFoodList.setAdapter(adapter);
    }
    private void recyclerViewFood2() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFoodList = findViewById(R.id.recyclerView3);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist2 = new ArrayList<>();
        foodlist2.add(new FoodDomain("Aloe Veera", "image6", "The Aloe vera plant has been known and used for centuries for its health, beauty, medicinal and skin care properties. The name Aloe vera derives from the Arabic word “Alloeh” meaning “shining bitter substance,” while “vera” in Latin means “true.”", 299.00));
        foodlist2.add(new FoodDomain("ZZ Plant", "image7", "The ZZ is everything you'd want in a beginner plant. Stunning + impossible to kill + air purifying \n\n Botanical name: Zamioculcas zamiifolia", 249.00));
        foodlist2.add(new FoodDomain("Bird of Paradise", "image8", "With its broad vibrant green leaves, the Bird of Paradise brings a touch of the tropics to any room. " +
                "It is named after its unique flowers which resemble brightly colored birds in flight. The Bird of Paradise thrives in warmer conditions with plenty of sunlight.", 149.00));
        foodlist2.add(new FoodDomain("Peperomia Plant", "image9", "Peperomia belongs to a wonderful genus of tropical plants native to Mexico, South America, and the Caribbean." +
                "Its leaves can be textured or smooth in red, green, gray, or purple; variegated, marbled, or solid; large, heart-shaped, or tiny. ", 199.00));

        adapter2 = new FoodAdapter(foodlist2);
        recyclerViewFoodList.setAdapter(adapter2);
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