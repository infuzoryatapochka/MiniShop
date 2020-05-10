package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.minishop.R;

import java.util.ArrayList;

import interfaces.OnChangeListener;
import models.Item;

public class ItemsAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    private Context context;
    private ArrayList<Item> items_adapter;
    private ArrayList<Item> checked_items_adapter = new ArrayList<Item>();
    private LayoutInflater layoutInflater;
    private OnChangeListener listener;

    public ItemsAdapter(Context context, ArrayList<Item> items_adapter,
                        OnChangeListener listener) {
        this.context = context;
        this.items_adapter = items_adapter;
        this.layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return items_adapter.size();
    }

    @Override
    public Object getItem(int position) {
        return items_adapter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
        if (compoundButton.isShown()) {
            int i = (int) compoundButton.getTag();
            items_adapter.get(i).check(check);
            notifyDataSetChanged();

            if(check){
                checked_items_adapter.add(items_adapter.get(i));
            } else {
                checked_items_adapter.remove(items_adapter.get(i));
            }
            listener.onDataChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item, null, false);
        }
        Item temp_item = items_adapter.get(position);
        TextView tv_goodId = (TextView) view.findViewById(R.id.id);
        tv_goodId.setText(Integer.toString(temp_item.id()));

        TextView tv_goodName = (TextView) view.findViewById(R.id.name);
        tv_goodName.setText(temp_item.name());

        TextView tv_goodPrice = (TextView) view.findViewById(R.id.price);
        tv_goodPrice.setText(Integer.toString(temp_item.price()) + "$");

        CheckBox cb_good = (CheckBox) view.findViewById(R.id.check);
        cb_good.setChecked(temp_item.checked());
        cb_good.setTag(position);
        cb_good.setOnCheckedChangeListener(this);

        return view;
    }

    public ArrayList<Item> checked_items() {
        return checked_items_adapter;
    }
}
