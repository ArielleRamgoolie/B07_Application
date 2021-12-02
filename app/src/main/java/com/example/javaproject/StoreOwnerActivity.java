package com.example.javaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoreOwnerActivity extends AppCompatActivity {
    private ArrayList<Product> products;
    private String storename = "Mcdonalds";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storeowner_homepage);

        ArrayList<Product> products = new ArrayList<Product>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stores").child(storename).child("Inventory");
        InventoryAdapter inventoryAdapter = new InventoryAdapter(this, R.layout.inventory_list_item, products);
        ListView inventory_list = (ListView) findViewById(R.id.inventory_list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                products.clear();
                for(DataSnapshot item: snapshot.getChildren()){
                    Product newP = new Product(item.child("Name").getValue().toString(),
                            item.child("Count").getValue(int.class), item.child("Price").getValue(double.class),
                            item.child("Brand").getValue().toString());
                    products.add(newP);
                    inventory_list.setAdapter(inventoryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("LIST IS ", products.toString());



        //Log.d("NOW LIST IS ", products.toString());

    }



    public void addInvProduct(View v){
        Intent intent = new Intent(this,AddInventoryProductActivity.class);
        intent.putExtra("Storename", storename);
        startActivity(intent);
    }

    public void editInvProduct(View v){
        Intent intent = new Intent(this,AddInventoryProductActivity.class);
        intent.putExtra("Storename", storename);
        startActivity(intent);
    }

    public void viewProfile(View v){
        startActivity(new Intent(this, ProfilePage.class));
    }
    public void viewNotification(View v){
        startActivity(new Intent(this, NotificationPage.class));
    }
    public void viewOrder(View v){
        startActivity(new Intent(this, OrderPage.class));
    }
}