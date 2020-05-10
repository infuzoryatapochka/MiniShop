package com.example.minishop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import adapters.CartItemsAdapter;
import models.Item;

public class SecondActivity extends AppCompatActivity {
    private ListView listView;
    private CartItemsAdapter cartGoodsAdapter;
    private LayoutInflater layoutInflater;
    private ArrayList<Item> items;
    private TextView tv_count;
    private View view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        Intent intent = getIntent();
        items = intent.getParcelableArrayListExtra("MyList");
        createMyListView();
    }

    private void createMyListView() {
        cartGoodsAdapter = new CartItemsAdapter(this, items);
        layoutInflater = LayoutInflater.from(this);
        view_header = layoutInflater.inflate(R.layout.cart_header, null);
        tv_count = (TextView) view_header.findViewById(R.id.tv_text);
        tv_count.setText(cartGoodsAdapter.getCount() + " items in cart");
        listView.addHeaderView(view_header);

        listView.setAdapter(cartGoodsAdapter);
    }

    private void initView () {
        listView = (ListView) findViewById(R.id.listGoodsInCart);
    }

}
