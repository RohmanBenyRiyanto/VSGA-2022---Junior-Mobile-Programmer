package com.rohmanbeny.tugaspertemuan5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rohmanbeny.tugaspertemuan5.model.UserAccount;

public class ListviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ListView listView = (ListView)findViewById(R.id.listView);

        UserAccount tom = new UserAccount("Tom","admin");
        UserAccount jerry = new UserAccount("Jerry","user");
        UserAccount john = new UserAccount("John","admin");
        UserAccount jessy = new UserAccount("Jessy","user");
        UserAccount james = new UserAccount("James","admin");
        UserAccount jessica = new UserAccount("Jessica","user");
        UserAccount donald = new UserAccount("Donald","guest", false);

        UserAccount[] users = new UserAccount[]{tom, jerry, john, jessy, james, jessica, donald};




        ArrayAdapter<UserAccount> arrayAdapter
                = new ArrayAdapter<UserAccount>(this, android.R.layout.simple_list_item_1 , users);

        listView.setAdapter(arrayAdapter);
    }
}