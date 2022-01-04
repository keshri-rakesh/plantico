package com.example.splashscreen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreen.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context mContext;
    List<ViewCart> cartModelList;

    public CartAdapter(Context mContext, List<ViewCart> cartModelList) {
        this.mContext = mContext;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewcart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.title.setText(cartModelList.get(position).getTitle());
        holder.price.setText(cartModelList.get(position).getTotalPrice());
        holder.qty.setText(cartModelList.get(position).getQty());

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,qty,price;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Title);
            qty = itemView.findViewById(R.id.Qty);
            price = itemView.findViewById(R.id.Totalprice);
        }
    }
}
