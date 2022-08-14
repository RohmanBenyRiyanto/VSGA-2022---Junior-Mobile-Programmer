package com.rohmanbeny.pertemuan9;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.rohmanbeny.pertemuan9.model.CatatanModel;
import com.rohmanbeny.pertemuan9.model.Post;

import java.util.ArrayList;
import java.util.Objects;

public class TambahCatatan extends AppCompatActivity {

    private EditText etCatatanName, etCatatanDescription;
    DatabaseHandler databaseHelper = DatabaseHandler.getInstance(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_catatan);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.tittle_app_bar_tambah_catatan);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        etCatatanName = findViewById(R.id.et_judulCatatan);
        etCatatanDescription = findViewById(R.id.et_addCatatan);
        Button btnTambahCatatan = findViewById(R.id.btn_tambah_catatan);



        btnTambahCatatan.setOnClickListener(v -> {
            String catatanName = etCatatanName.getText().toString();
            String catatanDescription = etCatatanDescription.getText().toString();
            if (catatanName.isEmpty() || catatanDescription.isEmpty()) {
                Toast.makeText(TambahCatatan.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                addCatatan();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addCatatan() {
        CatatanModel catatan = new CatatanModel();
        catatan.name = etCatatanName.getText().toString();
        Post post = new Post();
        post.catatan = catatan;
        post.text = etCatatanDescription.getText().toString();
        databaseHelper.addBuku(post);

        Toast.makeText(getApplicationContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
    }

}