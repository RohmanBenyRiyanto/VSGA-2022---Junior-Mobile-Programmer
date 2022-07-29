package com.rohmanbeny.tugaspertemuan5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class KalkulatorActivity extends AppCompatActivity {

   private String HasilHitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        EditText nilaiA = findViewById(R.id.et_angkat_one);
        EditText NilaiB = findViewById(R.id.et_angkat_two);

        ImageButton btnTambah = findViewById(R.id.ib_add);
        ImageButton btnKurangi = findViewById(R.id.ib_kurang);
        ImageButton btnKali = findViewById(R.id.ib_kali);
        ImageButton btnBagi = findViewById(R.id.ib__bagi);
        Button reset = findViewById(R.id.btn_bershihkan);

        TextView hasil = findViewById(R.id.tv_hasil_kalku);


        btnTambah.setOnClickListener(v -> {
            String nilaiA1 = nilaiA.getText().toString();
            String NilaiB1 = NilaiB.getText().toString();
            int nilaiA2 = Integer.parseInt(nilaiA1);
            int NilaiB2 = Integer.parseInt(NilaiB1);
            int hasil1 = nilaiA2 + NilaiB2;
            HasilHitung = String.valueOf(hasil1);
            hasil.setText(HasilHitung);
        });

        btnKurangi.setOnClickListener(v -> {
            String nilaiA1 = nilaiA.getText().toString();
            String NilaiB1 = NilaiB.getText().toString();
            int nilaiA2 = Integer.parseInt(nilaiA1);
            int NilaiB2 = Integer.parseInt(NilaiB1);
            int hasil1 = nilaiA2 - NilaiB2;
            HasilHitung = String.valueOf(hasil1);
            hasil.setText(HasilHitung);
        });

        btnKali.setOnClickListener(v -> {
            String nilaiA1 = nilaiA.getText().toString();
            String NilaiB1 = NilaiB.getText().toString();
            int nilaiA2 = Integer.parseInt(nilaiA1);
            int NilaiB2 = Integer.parseInt(NilaiB1);
            int hasil1 = nilaiA2 * NilaiB2;
            HasilHitung = String.valueOf(hasil1);
            hasil.setText(HasilHitung);
        });

        btnBagi.setOnClickListener(v -> {
            String nilaiA1 = nilaiA.getText().toString();
            String NilaiB1 = NilaiB.getText().toString();
            int nilaiA2 = Integer.parseInt(nilaiA1);
            int NilaiB2 = Integer.parseInt(NilaiB1);
            int hasil1 = nilaiA2 / NilaiB2;
            HasilHitung = String.valueOf(hasil1);
            hasil.setText(HasilHitung);
        });

        reset.setOnClickListener(v -> {
            nilaiA.setText("");
            NilaiB.setText("");
            hasil.setText("");
        });
    }
}