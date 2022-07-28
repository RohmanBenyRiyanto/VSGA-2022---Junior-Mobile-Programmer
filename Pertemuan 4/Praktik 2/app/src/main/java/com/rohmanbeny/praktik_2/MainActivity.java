package com.rohmanbeny.praktik_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private RadioGroup list_jawaban;
    private RadioButton laki_laki, perempuan;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextInputEditText etTinggiBadan = findViewById(R.id.ti_berat_bdn_input);
        Button hitungBeratBadan = findViewById(R.id.btn_hitung_berat);
        TextView tvHasil = findViewById(R.id.tv_hasil_berat);
        laki_laki = findViewById(R.id.rb_laki);
        perempuan = findViewById(R.id.rb_wanita);


        hitungBeratBadan.setOnClickListener(v -> {
            String tinggiBadan = etTinggiBadan.getText().toString();

            if (tinggiBadan.isEmpty()) {
                etTinggiBadan.setError("Tinggi badan tidak boleh kosong");
                return;
            } else {
                double tinggiBadanDouble = Double.parseDouble(tinggiBadan);
                if (tinggiBadanDouble <= 0) {
                    etTinggiBadan.setError("Tinggi badan tidak boleh kurang dari 0");
                    return;
                } else {
                    double hasil = 0;
                    if (laki_laki.isChecked()) {
                        hasil = ((tinggiBadanDouble-100)-(0.10 * (tinggiBadanDouble-100)));
                    } else if (perempuan.isChecked()) {
                        hasil = ((tinggiBadanDouble-100)-(0.15 * (tinggiBadanDouble-100)));
                    } else {
                        Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show();
                    }
                    tvHasil.setText(String.format("Berat badan ideal anda adalah %s kg", hasil));
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.m_berat_ideal){
            startActivity(new Intent(this, MainActivity.class));
        } else if (item.getItemId() == R.id.m_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (item.getItemId() == R.id.m_rumus) {
            startActivity(new Intent(this, RumusActivity.class));
        }

        return true;
    }
}

