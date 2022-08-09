package com.rohmanbeny.latihanstorage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String FILENAME = "namafile.txt";
    Button btn_create, btn_update, btn_read, btn_detele;
    TextView textBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        btn_create = (Button) findViewById(R.id.btn_create);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_detele = (Button) findViewById(R.id.btn_delete);
        textBaca = (TextView) findViewById(R.id.tv_baca);

        btn_create.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        btn_detele.setOnClickListener(this);
    }

    void createFile() {
        String isiFile = "Ini Adalah Create isi file";
        File file = new File(this.getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {

            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(this, "File berhasil dibuat", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    void updateFile() {
        String update = "Ini adalah Update isi file";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(update.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(this, "File berhasil diupdate", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void readFile() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new java.io.FileReader(file));

                String line = br.readLine();

                while(line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();

            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
            textBaca.setText(text.toString());
            Toast.makeText(this, "File berhasil dibaca", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteFile() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            file.delete();
            textBaca.setText(R.string.data_delete_done);

            Toast.makeText(this, "File berhasil dihapus", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        jalankanPerintah(v.getId());

    }

    public void jalankanPerintah(int id) {
        switch (id) {
            case R.id.btn_create:
                createFile();
                break;
            case R.id.btn_update:
                updateFile();
                break;
            case R.id.btn_read:
                readFile();
                break;
            case R.id.btn_delete:
                deleteFile();
                break;
        }
    }
}