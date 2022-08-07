package com.rohmanbeny.pertemuan7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    String[] country = {"Indonesia", "Malaysia", "Singapore", "Thailand", "Vietnam"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, country);

        ListView listView = (ListView) findViewById(R.id.lv_main);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                Toast.makeText(MainActivity.this, country[position], Toast.LENGTH_SHORT).show();
            }
        });


    }
}