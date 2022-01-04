package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.splashscreen.Domain.FoodDomain;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt, priceTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, foodPic;
    private FoodDomain object;
    private int numberOrder = 1;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Double plantPrice ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        initView();
        getBundle();
    }
    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");
        plantPrice = object.getFee();

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(foodPic);

        titleTxt.setText(object.getTitle());
        priceTxt.setText("₹" + object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                priceTxt.setText("₹" + object.getFee()*numberOrder);
                plantPrice = object.getFee()* numberOrder;

            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                priceTxt.setText("₹" + object.getFee()*numberOrder);
                numberOrderTxt.setText(String.valueOf(numberOrder));
                plantPrice = object.getFee()* numberOrder;
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);*/
                cartUpload();
            }
        });
    }
    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        foodPic = findViewById(R.id.foodPic);
    }
    private void cartUpload(){
        final HashMap<String,Object> cartMap= new HashMap<>();
        cartMap.put("Title",titleTxt.getText());
        cartMap.put("Qty",String.valueOf(numberOrder));
        cartMap.put("TotalPrice",String.valueOf(plantPrice));
        firestore.collection("currentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                Toast.makeText(ShowDetailActivity.this,"Added to cart",Toast.LENGTH_LONG).show();
                Intent i = new Intent(ShowDetailActivity.this,ProfileActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}