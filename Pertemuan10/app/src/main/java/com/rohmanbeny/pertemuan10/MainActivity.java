package com.rohmanbeny.pertemuan10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> stringArrayList;
    Button btnStore, btnGet;
    DatabaseHelper databaseHelper;
    EditText txtName;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        tvName = findViewById(R.id.tv_name);
        txtName = findViewById(R.id.txt_name);

        btnStore = findViewById(R.id.btn_store);
        btnGet = findViewById(R.id.btn_get);

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtName.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Masukkan nama!", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.addStudentDetail(txtName.getText().toString());
                    txtName.setText("");
                    Toast.makeText(MainActivity.this, "Store Succesfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringArrayList = databaseHelper.getAllStudentsList();
                tvName.setText("");
                for (int i = 0; i < stringArrayList.size(); i++) {
                    tvName.setText(tvName.getText().toString()+", "+stringArrayList.get(i));
                }
            }
        });

    }
}