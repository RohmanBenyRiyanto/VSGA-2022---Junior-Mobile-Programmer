package com.rohmanbeny.latihanstorage;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.rohmanbeny.latihanstorage.handler.DatabaseHandler;
import com.rohmanbeny.latihanstorage.model.BukuModel;
import com.rohmanbeny.latihanstorage.model.Card;
import com.rohmanbeny.latihanstorage.model.Post;

import java.util.List;


public class InternalActivity extends AppCompatActivity {

    DatabaseHandler databaseHelper = DatabaseHandler.getInstance(this);
    EditText et_judul, et_text;
    Button btn_add, btn_delete_data;
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);


        List<Post> posts = databaseHelper.getAllPosts();
        for (Post p : posts) {
            TextView textView = (TextView) findViewById(R.id.tv_baca);
            textView.setText(p.text);
        }

        btn_add = (Button) findViewById(R.id.btn_post);
        btn_delete_data = (Button) findViewById(R.id.btn_delete_data);
        et_judul = (EditText) findViewById(R.id.et_judul_buku);
        et_text = (EditText) findViewById(R.id.et_desc);
        listView = (ListView) findViewById(R.id.card_listView);

        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));

        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.activity_listview);


        btn_add.setOnClickListener(v -> {
            BukuModel buku = new BukuModel();
            buku.judul = et_judul.getText().toString();
            Post post = new Post();
            post.buku = buku;
            post.text = et_text.getText().toString();
            databaseHelper.addBuku(post);


            Toast.makeText(getApplicationContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
            passData();
            listView.setAdapter(cardArrayAdapter);

        });

        btn_delete_data.setOnClickListener(v -> {
            BukuModel buku = new BukuModel();
            databaseHelper.deleteAllPostsAndUsers();
            Toast.makeText(getApplicationContext(), "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
            passData();
            listView.setAdapter(cardArrayAdapter);
        });


    }

    public void passData() {
        List<Post> posts = databaseHelper.getAllPosts();

        for (Post p : posts) {
            if (p.buku == null && p.text == null) {
                Card card = new Card("Buku Tidak Tersedia", "-");
                cardArrayAdapter.add(card);
            } else {
                assert p.buku != null;
                Card card = new Card(p.buku.judul, p.text);
                cardArrayAdapter.add(card);
            }


        }
    }


}

