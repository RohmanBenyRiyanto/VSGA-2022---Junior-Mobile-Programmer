package com.rohmanbeny.tugaspertemuan6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String hasil;
    private int CurrentNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = findViewById(R.id.btn_add);
        TextView title = findViewById(R.id.tv_nilai);
        Button showToast = findViewById(R.id.btn_toast);

        btnAdd.setOnClickListener(v -> {
            CurrentNumber++;
            title.setText(String.valueOf(CurrentNumber));
        });

        showToast.setOnClickListener(v -> {
            hasil = title.getText().toString();
            Toast.makeText(this, "Nilai " + hasil, Toast.LENGTH_SHORT).show();
        });
    }
}