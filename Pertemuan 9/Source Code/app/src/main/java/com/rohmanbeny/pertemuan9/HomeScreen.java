package com.rohmanbeny.pertemuan9;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.rohmanbeny.pertemuan9.model.Card;
import com.rohmanbeny.pertemuan9.model.Post;

import java.util.List;

public class HomeScreen extends AppCompatActivity {
    DatabaseHandler databaseHelper = DatabaseHandler.getInstance(this);
    private ListView listView;
    private TextView idTVCatatanName, idTVCatatanNameDescription;
    private ListAdapter adapter;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        listView = findViewById(R.id.lv_home);
        idTVCatatanName = findViewById(R.id.idTVCatatanName);
        idTVCatatanNameDescription = findViewById(R.id.idTVCatatanNameDescription);
        button = findViewById(R.id.btn_load);


        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        adapter = new ListAdapter(getApplicationContext(), R.layout.list_card);
        passData();

        listView.setAdapter(adapter);


        List<Post> posts = databaseHelper.getAllPosts();
        for (Post p : posts) {
            TextView textView = (TextView) findViewById(R.id.idTVCatatanNameDescription);
            textView.setText(p.text);
        }

        button.setOnClickListener(v -> passData());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tools_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, TambahCatatan.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public void passData() {
        List<Post> posts = databaseHelper.getAllPosts();

        if (posts != null) {
            for (Post p : posts) {
                if (p.catatan == null && p.text == null) {
                    Card card = new Card("Buku Tidak Tersedia", "-");
                    adapter.add(card);
                } else {
                    assert p.catatan != null;
                    Card card = new Card(p.catatan.name, p.text);
                    adapter.add(card);
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Data Tidak Tersedia", Toast.LENGTH_LONG).show();
        }
    }
}