package com.rohmanbeny.tugaspertemuan5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        EditText editText = findViewById(R.id.et_name);
        Button button = findViewById(R.id.btn_name);
        TextView textView = findViewById(R.id.tv_name);

        button.setOnClickListener(v -> {
            name = editText.getText().toString();
            textView.setText(name);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_nama:
                startActivity(new Intent(this, NameActivity.class));
                return true;
            case R.id.action_kalkulator:
                startActivity(new Intent(this, KalkulatorActivity.class));
                return true;
            case R.id.action_listview:
                startActivity(new Intent(this, ListviewActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}