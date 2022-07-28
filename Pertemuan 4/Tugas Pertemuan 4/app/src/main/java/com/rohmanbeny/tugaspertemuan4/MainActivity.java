package com.rohmanbeny.tugaspertemuan4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    GridView container;
    LayoutInflater inflater;
    ImageView orgImg;
    Bitmap picFor3[] = new Bitmap[9];
    Bitmap picFor4[] = new Bitmap[16];
    Bitmap mixFor3[] = new Bitmap[9];
    Bitmap mixFor4[] = new Bitmap[16];
    MyBaseAdapter adapterFor3, adapterFor4;
    int type = 3;
    int emptyD;
    //Bitmap emptySpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orgImg = (ImageView) findViewById(R.id.orgImageView);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 4;
        options.inJustDecodeBounds = false;
        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.puzzle);
        orgImg.setImageBitmap(Bitmap.createScaledBitmap(bitmapOrg, 520, 520, true));

        slicePic(bitmapOrg, 3);
        adapterFor3 = new MyBaseAdapter(this, picFor3);
        mixFor3 = picFor3.clone();
        slicePic(bitmapOrg, 4);
        adapterFor4 = new MyBaseAdapter(this, picFor4);

        container = (GridView) findViewById(R.id.gridView);
        container.setNumColumns(3);
        container.setAdapter(adapterFor3);
        container.setOnItemClickListener(new GridViewClickListener(MainActivity.this));

        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                type = 3;
                emptyD = 8;
                adapterFor3 = new MyBaseAdapter(MainActivity.this, picFor3);
                container.setNumColumns(3);
                container.setAdapter(adapterFor3);
                mixFor3 = picFor3.clone();
                container.setOnItemClickListener(new GridViewClickListener(MainActivity.this));
            }
        });

        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                type = 4;
                emptyD = 15;
                adapterFor4 = new MyBaseAdapter(MainActivity.this, picFor4);
                container.setNumColumns(4);
                container.setAdapter(adapterFor4);
                mixFor4 = picFor4.clone();
                container.setOnItemClickListener(new GridViewClickListener(MainActivity.this));
            }
        });

        Button btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type == 3){
                    Collections.shuffle(Arrays.asList(mixFor3));
                    adapterFor3 = new MyBaseAdapter(MainActivity.this, mixFor3);
                    container.setNumColumns(3);
                    container.setAdapter(adapterFor3);
                    container.setOnItemClickListener(new GridViewClickListener(MainActivity.this));
                }
                else{
                    mixFor4 = picFor4.clone();
                    Collections.shuffle(Arrays.asList(mixFor4));
                    //mixPic(mixFor4);
                    adapterFor4 = new MyBaseAdapter(MainActivity.this, mixFor4);
                    container.setNumColumns(4);
                    container.setAdapter(adapterFor4);
                    container.setOnItemClickListener(new GridViewClickListener(MainActivity.this));
                }
            }
        });

        //container.setOnItemClickListener(new GridViewClickListener(MainActivity.this));

    }

    void mixPic(Bitmap[] arr){
        Bitmap tmp;
        int b1, b2, cnt;
        if (type == 3){
            cnt = 9;
        }
        else{cnt = 16;}
        for(int i = 0; i < arr.length; i++){
            b1 = (int) (Math.random()*cnt);
            b2 = (int) (Math.random()*cnt);
            tmp = arr[b1];
            arr[b1] = arr[b2];
            arr[b2] = tmp;
        }
    }

    void slicePic(Bitmap b, int state){
        Context context = MainActivity.this;
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display dis = wm.getDefaultDisplay();
        Point size = new Point();
        dis.getSize(size);
        int disH = size.y - 5;
        int disW = size.x - 10;
        int k = 0;
        Bitmap resizedB = Bitmap.createScaledBitmap(b, disW, disW, true);
        //int orgH = resizedB.getHeight();
        //int orgW = resizedB.getWidth();
        int picH = disW / state;
        int picW = disW / state;
        if(state == 3){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3 ; j++){
                    picFor3[k++] = Bitmap.createBitmap(resizedB, j*picW, i*picH, picW, picH);
                }
            }
            picFor3[8] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.white), picW, picH, true);
        }
        else{
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4 ; j++){
                    picFor4[k++] = Bitmap.createBitmap(resizedB, j*picW, i*picH, picW, picH);
                }
            }
            picFor4[15] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.white), picW, picH, true);
        }

    }

    class GridViewClickListener implements AdapterView.OnItemClickListener{
        Context c = MainActivity.this;
        //Bitmap moveArr[];
        Bitmap tmp;
        int k;
        GridViewClickListener(Context c){
            this.c = c;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(c.getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
            k = check(position);
            if(type == 3){
                if(k != -1){
                    //Toast.makeText(c.getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                    tmp = mixFor3[position];
                    mixFor3[position] = mixFor3[k];
                    mixFor3[k] = tmp;
                    adapterFor3 = new MyBaseAdapter(MainActivity.this, mixFor3);
                    //container.setNumColumns(3);
                    container.setAdapter(adapterFor3);
                    if(checkOrg()){
                        Toast.makeText(c.getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            else{
                if(k != -1){
                    //Toast.makeText(c.getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                    tmp = mixFor4[position];
                    mixFor4[position] = mixFor4[k];
                    mixFor4[k] = tmp;
                    adapterFor4 = new MyBaseAdapter(MainActivity.this, mixFor4);
                    //container.setNumColumns(4);
                    container.setAdapter(adapterFor4);
                    if(checkOrg()){
                        Toast.makeText(c.getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

        boolean checkOrg(){
            if(type == 3){
                for(int i = 0; i < 9 ; i++){
                    if(picFor3[i] != mixFor3[i]){
                        return false;
                    }
                }
            }
            else{
                for(int i = 0; i < 16 ; i++){
                    if(picFor4[i] != mixFor4[i]){
                        return false;
                    }
                }
            }
            return true;
        }

        int check(int k){
            Bitmap grr;
            int change = -1;
            //if(moveArr[k] == grr) { return -1;}
            if(type == 3){
                grr = picFor3[8];
                if(mixFor3[k] == grr) { return -1;}
                switch (k){
                    case 0:
                        if(mixFor3[1] == grr){ change = 1; }
                        else if(mixFor3[3] == grr){change = 3;}
                        break;
                    case 1:
                        if(mixFor3[0] == grr){ change = 0; }
                        else if(mixFor3[2] == grr){change = 2;}
                        else if(mixFor3[4] == grr){change = 4;}
                        break;
                    case 2:
                        if(mixFor3[1] == grr){ change = 1; }
                        else if(mixFor3[5] == grr){change = 5;}
                        break;
                    case 3:
                        if(mixFor3[0] == grr){ change = 0; }
                        else if(mixFor3[4] == grr){change = 4;}
                        else if(mixFor3[6] == grr){change = 6;}
                        break;
                    case 4:
                        if(mixFor3[1] == grr){ change = 1; }
                        else if(mixFor3[3] == grr){change = 3;}
                        else if(mixFor3[5] == grr){change = 5;}
                        else if(mixFor3[7] == grr){change = 7;}
                        break;
                    case 5:
                        if(mixFor3[8] == grr){ change = 8; }
                        else if(mixFor3[2] == grr){change = 2;}
                        else if(mixFor3[4] == grr){change = 4;}
                        break;
                    case 6:
                        if(mixFor3[3] == grr){ change = 3; }
                        else if(mixFor3[7] == grr){change = 7;}
                        break;
                    case 7:
                        if(mixFor3[6] == grr){ change = 6; }
                        else if(mixFor3[8] == grr){change = 8;}
                        else if(mixFor3[4] == grr){change = 4;}
                        break;
                    case 8:
                        if(mixFor3[7] == grr){ change = 7; }
                        else if(mixFor3[5] == grr){change = 5;}
                        break;
                }
            }
            else{
                grr = picFor4[15];
                if(mixFor4[k] == grr) { return -1;}
                switch (k){
                    case 0:
                        if(mixFor4[1] == grr){ change = 1; }
                        else if(mixFor4[4] == grr){change = 4;}
                        break;
                    case 1:
                    case 2:
                        if(mixFor4[k-1] == grr){ change = k-1; }
                        else if(mixFor4[k+1] == grr){change = k+1;}
                        else if(mixFor4[k+4] == grr){change = k+4;}
                        break;
                    case 3:
                        if(mixFor4[2] == grr){ change = 2; }
                        else if(mixFor4[7] == grr){change = 7;}
                        break;
                    case 4:
                    case 8:
                        if(mixFor4[k-4] == grr){ change = k-4; }
                        else if(mixFor4[k+4] == grr){change = k+4;}
                        else if(mixFor4[k+1] == grr){change = k+1;}
                        break;
                    case 5:
                    case 6:
                    case 9:
                    case 10:
                        if(mixFor4[k+1] == grr){ change = k+1; }
                        else if(mixFor4[k-1] == grr){change = k-1;}
                        else if(mixFor4[k+4] == grr){change = k+4;}
                        else if(mixFor4[k-4] == grr){change = k-4;}
                        break;
                    case 7:
                    case 11:
                        if(mixFor4[k-1] == grr){ change = k-1; }
                        else if(mixFor4[k-4] == grr){change = k-4;}
                        else if(mixFor4[k+4] == grr){change = k+4;}
                        break;
                    case 12:
                        if(mixFor4[8] == grr){ change = 8; }
                        else if(mixFor4[13] == grr){change = 13;}
                        break;
                    case 13:
                    case 14:
                        if(mixFor4[k+1] == grr){ change = k+1; }
                        else if(mixFor4[k-1] == grr){change = k-1;}
                        else if(mixFor4[k-4] == grr){change = k-4;}
                        break;
                    case 15:
                        if(mixFor4[11] == grr){ change = 11; }
                        else if(mixFor4[14] == grr){change = 14;}
                        break;
                }

            }
            if(change != -1){
                return change;
            }
            else{ return -1; }
        }
    }

    class MyBaseAdapter extends BaseAdapter {
        Context c;
        Bitmap items[];

        MyBaseAdapter(Context c, Bitmap arr[]) {
            this.c = c;
            items = arr;
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;
            if (view == null)
            {
                LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.image, viewGroup, false);
            }
            imageView = view.findViewById(R.id.gridImage);
            imageView.setImageBitmap(items[i]);
            return view;
        }
    }
}