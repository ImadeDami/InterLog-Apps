package com.zeathon.istockinventory;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllDc extends AppCompatActivity {
    private static final String TAG = "AllFmcg";
    //Spinner listView;
    ListView listView;
    TextView allData;
    DataBHelper dataBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dc_all);

        dataBHelper = new DataBHelper(this);
        listView = findViewById(R.id.listView);
        allData = findViewById(R.id.allData);

        populateListView();
        populateTextView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

// get all data and append to list
        Cursor data = dataBHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
            listData.add(data.getString(2));
            listData.add(data.getString(3));
            listData.add(data.getString(4));
            listData.add(data.getString(5));
            listData.add(data.getString(6));
        }

        //create the list adapter and set adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);
    }

    private void populateTextView(){
        Cursor data = dataBHelper.getData();
        //allData.setText(data1);
        //data1 = yourGetDataMethod();
        if (data.moveToFirst() ) {
           // allData.setText(data.getString(data.getColumnIndex("your_column_name_to_get_the data_from")));
            allData.setText(data.getString(data.getColumnIndex("COL2")));
        } else {
            allData.setText("Ooops no data extracted");
        }
        data.close();
    }

   /* private void populateFullname() {
        //Log.d(TAG, "populateListView: Displaying data in the ListView.");
        dataBaseHelper = new DataBaseHelper(this);
        List<String> lables = dataBaseHelper.getFN();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_2);
        listView.setAdapter(dataAdapter);

    } */



}
