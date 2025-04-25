package com.example.customlistviewexample;
//CAC THU VIEN SU DUNG TRONG LISTVIEW 2
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    // Data model for ListView 2
    static class Item2 {
        String title;
        String subInfo;
        int imageResId;

        Item2(String title, String subInfo, int imageResId) {
            this.title = title;
            this.subInfo = subInfo;
            this.imageResId = imageResId;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //NOI DUNG CUA LIST VIEW 2, GOM CAC LOAI TRAI CAY VA SO CALO
        ListView listView2 = findViewById(R.id.listView2);
        ArrayList<Item2> items2 = new ArrayList<>();
        items2.add(new Item2("Orange", "47 Calories", R.drawable.orange));
        items2.add(new Item2("Cherry", "50 Calories", R.drawable.cherry));
        items2.add(new Item2("Banana", "89 Calories", R.drawable.banana));
        items2.add(new Item2("Apple", "52 Calories", R.drawable.apple));
        items2.add(new Item2("Kiwi", "61 Calories", R.drawable.kiwi));
        items2.add(new Item2("Pear", "57 Calories", R.drawable.pear));
        items2.add(new Item2("Strawberry", "33 Calories", R.drawable.strawberry));
        items2.add(new Item2("Lemon", "29 Calories", R.drawable.lemon));
        items2.add(new Item2("Peach", "39 Calories", R.drawable.peach));
        items2.add(new Item2("Apricot", "48 Calories", R.drawable.apricot));
        items2.add(new Item2("Mango", "60 Calories", R.drawable.mango));

        CustomAdapter2 adapter2 = new CustomAdapter2(items2);
        listView2.setAdapter(adapter2);
    }

    // Adapter for ListView 2
    class CustomAdapter2 extends BaseAdapter {
        private ArrayList<Item2> items;

        CustomAdapter2(ArrayList<Item2> items) {
            this.items = items;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_2, parent, false);
            }

            Item2 item = items.get(position);
            ImageView imageView = convertView.findViewById(R.id.imageView2);
            TextView titleTextView = convertView.findViewById(R.id.titleTextView2);
            TextView subInfoTextView = convertView.findViewById(R.id.subInfoTextView2);

            imageView.setImageResource(item.imageResId);
            titleTextView.setText(item.title);
            subInfoTextView.setText(item.subInfo);

            return convertView;
        }
    }
}