package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.splashscreen.Adapter.CartAdapter;
import com.example.splashscreen.Adapter.ViewCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CartAdapter adapter;
    List<ViewCart> viewCartList;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String TAG="CartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.cartList);
        viewCartList = new ArrayList<>();
        adapter = new CartAdapter(CartActivity.this,viewCartList);

        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        firestore.collection("currentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                  //  Toast.makeText(CartActivity.this,"Hello",Toast.LENGTH_LONG).show();
                    for(DocumentSnapshot snapshot : task.getResult().getDocuments()){
                        ViewCart model = snapshot.toObject(ViewCart.class);
                        viewCartList.add(model);
                        adapter.notifyDataSetChanged();
                        Log.d(TAG,"Data : "+ model.toString());
                    }
                }
            }
        });
    }
}