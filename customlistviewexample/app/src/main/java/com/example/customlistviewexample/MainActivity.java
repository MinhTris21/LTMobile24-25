package com.example.customlistviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView1);

        ArrayList<AndroidVersion> androidVersions = new ArrayList<>();
        androidVersions.add(new AndroidVersion("🧁 Android Cupcake", "Version 1.5"));
        androidVersions.add(new AndroidVersion("🍩 Android Donut", "Version 1.6"));
        androidVersions.add(new AndroidVersion("🥐 Android Eclair", "Version 2.0"));
        androidVersions.add(new AndroidVersion("🍨 Android Froyo", "Version 2.2"));
        androidVersions.add(new AndroidVersion("🍪 Android Gingerbread", "Version 2.3"));
        androidVersions.add(new AndroidVersion("🐝 Android Honeycomb", "Version 3.0"));

        AndroidVersionAdapter adapter = new AndroidVersionAdapter(this, androidVersions);
        listView.setAdapter(adapter);

        Button buttonToListView2 = findViewById(R.id.buttonToListView2);
        buttonToListView2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }
}

class AndroidVersion {
    private String name;
    private String version;

    public AndroidVersion(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() { return name; }
    public String getVersion() { return version; }
}