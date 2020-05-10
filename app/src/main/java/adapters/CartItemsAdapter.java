package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.minishop.R;

import java.util.ArrayList;

import models.Item;

public class CartItemsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater layoutInflater;

    public CartItemsAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_in_cart, null, false);
        }
        Item good_temp = items.get(position);
        TextView tv_goodId = (TextView) view.findViewById(R.id.id);
        tv_goodId.setText(Integer.toString(good_temp.id()));

        TextView tv_goodName = (TextView) view.findViewById(R.id.name);
        tv_goodName.setText(good_temp.name());

        return view;
    }
}