package models;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private int id;
    private int price;
    private String name;
    private boolean checked;

    public Item(int id, String name, int price, boolean check) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.checked = check;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    private Item(Parcel parcel) {
        id = parcel.readInt();
        name = parcel.readString();
        checked = false;
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {

        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int id() {
        return  this.id;
    }

    public int price() {
        return this.price;
    }

    public String name() {
        return this.name;
    }

    public boolean checked() { return this.checked; }

    public void id(int id) { this.id = id; }

    public void price(int price) { this.price = price; }

    public void check(boolean check) { this.checked = check; }

}