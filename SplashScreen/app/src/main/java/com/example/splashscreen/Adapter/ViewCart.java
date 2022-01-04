package com.example.splashscreen.Adapter;

public class ViewCart {
    String Qty,Title,TotalPrice;

    public ViewCart(String qty, String title, String totalPrice) {
        Qty = qty;
        Title = title;
        TotalPrice = totalPrice;
    }

    public ViewCart() {
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }
}
