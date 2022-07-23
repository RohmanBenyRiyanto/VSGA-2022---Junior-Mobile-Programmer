package com.rohmanbeny.tugaspertemuan2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;
import java.util.Objects;

public class HubunganDuaVariabel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hubungan_dua_variabel);

        // Menambahkan button kembali
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        // Mendapatkan View dari Layout activity_hubungan_dua_variabel.xml
        TextView hasilCek = findViewById(R.id.tv_hasil_h2ab);
        EditText nilaiA = findViewById(R.id.et_nilai_a);
        EditText nilaiB = findViewById(R.id.et_nilai_b);
        Button btnHitung = findViewById(R.id.btn_cek_h2ab);


        // Set OnClickListener untuk Button
        btnHitung.setOnClickListener(v -> {
            // Verifikasi apakah nilai A dan B kosong atau tidak
            if (nilaiA.getText().toString().isEmpty()) {
                nilaiA.setError("Nilai A tidak boleh kosong");
            } else if (nilaiB.getText().toString().isEmpty()) {
                nilaiB.setError("Nilai B tidak boleh kosong");
            } else {
                int a = Integer.parseInt(nilaiA.getText().toString());
                int b = Integer.parseInt(nilaiB.getText().toString());

                // Property yang menampung String hasil dari pengecekan variavel a dan b
                String Message;

                // Pencecekan nilai a dan b
                if (a < b) {
                    //get a in message
                    Message = MessageFormat.format("{0}{1}", getString(R.string.hasil) + " ", a + " " + getString(R.string.levih_kecil_dari) + " " + b);
                } else if (a > b) {
                    Message = MessageFormat.format("{0}{1}", getString(R.string.hasil) + " ", a + " " + getString(R.string.lebih_besar_dari) + " " + b);
                } else {
                    Message = MessageFormat.format("{0}{1}", getString(R.string.hasil) + " ", a + " " + getString(R.string.sama_dengan) + " " + b);
                }

                // Menampilkan hasil pengecekan ke TextView
                hasilCek.setText(Message);
            }
        });
    }


}