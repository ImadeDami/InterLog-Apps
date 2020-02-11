package com.zeathon.istockinventory;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllFmcg extends AppCompatActivity {
    private static final String TAG = "AllFmcg";
    //Spinner listView;
    ListView listView;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fmcg_all);

        dataBaseHelper = new DataBaseHelper(this);
        listView = findViewById(R.id.listView);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

// get all data and append to list
        Cursor data = dataBaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
            listData.add(data.getString(2));
           // listData.add(data.getString(3));
        }

        //create the list adapter and set adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);
    }

   /* private void populateFullname() {
        //Log.d(TAG, "populateListView: Displaying data in the ListView.");
        dataBaseHelper = new DataBaseHelper(this);
        List<String> lables = dataBaseHelper.getFN();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(dataAdapter);

    } */



}
