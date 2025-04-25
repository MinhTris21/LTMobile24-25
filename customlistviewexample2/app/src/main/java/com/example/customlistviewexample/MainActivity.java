package com.example.customlistviewexample;
//SO LUOC CODE JAVA SU DUNG, CAC THU VIEN TRONG BAI LAM
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Data model for ListView 1
    static class Item1 {
        String title;
        String subtitle;
        int imageResId;

        Item1(String title, String subtitle, int imageResId) {
            this.title = title;
            this.subtitle = subtitle;
            this.imageResId = imageResId;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup ListView 1 (Android Versions)
        ListView listView1 = findViewById(R.id.listView1);
        ArrayList<Item1> items1 = new ArrayList<>();
        //CAC NOI DUNG TRONG LISTVIEW
        items1.add(new Item1("Android Cupcake", "Version: 1.5", R.drawable.cupcake));
        items1.add(new Item1("Android Donut", "Version: 1.6", R.drawable.androiddonut));
        items1.add(new Item1("Android Eclair", "Version: 2.0", R.drawable.eclair));
        items1.add(new Item1("Android Froyo", "Version: 2.2", R.drawable.froyo));
        items1.add(new Item1("Android Gingerbread", "Version: 2.3", R.drawable.gingerbeard));
        items1.add(new Item1("Android Honeycomb", "Version: 3.0", R.drawable.honeycomb));

        CustomAdapter1 adapter1 = new CustomAdapter1(items1);
        listView1.setAdapter(adapter1);

        // Setup button to navigate to SecondActivity
        Button buttonToSecondActivity = findViewById(R.id.buttonToSecondActivity);
        buttonToSecondActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }

    // Adapter for ListView 1
    class CustomAdapter1 extends BaseAdapter {
        private ArrayList<Item1> items;

        CustomAdapter1(ArrayList<Item1> items) {
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
                        .inflate(R.layout.list_item_1, parent, false);
            }

            Item1 item = items.get(position);
            ImageView imageView = convertView.findViewById(R.id.imageView1);
            TextView titleTextView = convertView.findViewById(R.id.titleTextView1);
            TextView subtitleTextView = convertView.findViewById(R.id.subtitleTextView1);

            imageView.setImageResource(item.imageResId);
            titleTextView.setText(item.title);
            subtitleTextView.setText(item.subtitle);

            return convertView;
        }
    }
}