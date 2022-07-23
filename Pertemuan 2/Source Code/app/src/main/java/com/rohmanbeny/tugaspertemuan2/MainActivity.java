package com.rohmanbeny.tugaspertemuan2;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    // Default nilai phi
    public static final double PI = 3.141592653589793d;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mendapatkan View dari Layout activity_main.xml
        TextView hasilLuas = findViewById(R.id.tv_luas);
        TextView hasilKeliling = findViewById(R.id.tv_keliling);
        EditText nilaiR = findViewById(R.id.et_nilai_r);
        Button btnHitung = findViewById(R.id.btn_hitung_lingkar);
        Button btnPindah = findViewById(R.id.btn_pindah);


        // Set OnClickListener untuk Button
        btnHitung.setOnClickListener(v -> {

            // Verifikasi apakah nilai R kosong atau tidak
            if (nilaiR.getText().toString().isEmpty()) {
                nilaiR.setError("Nilai R tidak boleh kosong");
                return;
            }

            // Menconvert nilai R ke double
            double r = Double.parseDouble(nilaiR.getText().toString());

            // Menghitung luas lingkaran dengan rumus luas lingkaran = PI * r * r
            double luas = PI * r * r;

            // Menghitung keliling lingkaran dengan rumus keliling lingkaran = 2 * PI * r
            double keliling = 2 * PI * r;

            // Menampilkan hasil luas dan keliling lingkaran ke TextView
            hasilLuas.setText(MessageFormat.format("{0}{1}", getString(R.string.hasil_luas) + " ", String.format("%.2f", luas)));
            hasilKeliling.setText(MessageFormat.format("{0}{1}", getString(R.string.hasil_kel) + " ", String.format("%.2f", keliling)));
        });

        // Set OnClickListener untuk Button Pindah ke Activity selanjutnya
        btnPindah.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HubunganDuaVariabel.class));
        });
    }
}