package com.example.minishop;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import adapters.ItemsAdapter;
import models.Item;

public class MainActivity extends AppCompatActivity implements interfaces.OnChangeListener, View.OnClickListener {
    private LinearLayout lin_layout;
    private ListView list_view;
    private ArrayList<Item> items = new ArrayList<Item>();
    private final int ARR_SIZE = 5;
    private ItemsAdapter items_adapter;
    private TextView items_count, tv_text;
    private ArrayList<Item> checked_items = new ArrayList<Item>();
    private Button list_checked_button;
    private LayoutInflater layoutInflater;
    private View header, footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    private void initViews() {
        list_view = (ListView) findViewById(R.id.listGoods);
        fillData();
        items_adapter = new ItemsAdapter(this, items, this);
        layoutInflater = LayoutInflater.from(this);
        header = layoutInflater.inflate(R.layout.items_header, null);
        tv_text = (TextView) header.findViewById(R.id.tv_text);
        tv_text.setText("My Goods List");

        footer = layoutInflater.inflate(R.layout.items_footer, null);
        list_checked_button = (Button) footer.findViewById(R.id.list_checked_button);
        list_checked_button.setOnClickListener(this);
        items_count = (TextView) footer.findViewById(R.id.count);

        list_view.addHeaderView(header);
        list_view.addFooterView(footer);

        list_view.setAdapter(items_adapter);

    }

    private void fillData() {
        int price = 0;
        for(int i = 0; i < ARR_SIZE; i++) {
            price += 100;
            items.add(new Item(i, "Item" + i, price,false));
        }
    }

    @Override
    public void onDataChanged() {
        int size = items_adapter.checked_items().size();
        items_count.setText(size + " items");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.list_checked_button) {
            checked_items = items_adapter.checked_items();
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putParcelableArrayListExtra("MyList", checked_items);
            startActivity(intent);
        }
    }
}
