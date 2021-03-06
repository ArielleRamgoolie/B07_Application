package com.example.javaproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomerListAdapter extends ArrayAdapter<Store> {
    private static final String TAG = "CustomerListAdapter";
    private final Context mcontext;
    private final int mresource;

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */

    public CustomerListAdapter(Context context, int resource, ArrayList<Store> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get store information
        String storeName = getItem(position).getStoreName();


        // create a store object
        Store store = new Store(storeName);

        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(mresource, parent, false);

        TextView tvStoreName = convertView.findViewById(R.id.textView1);
        tvStoreName.setText(storeName);

        Button tvOrderBtn = convertView.findViewById(R.id.orderBtn);

        tvOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, Add_order.class);
                intent.putExtra("Storename", storeName);
                mcontext.startActivity(intent);
            }
        });

        return convertView;
    }
}
