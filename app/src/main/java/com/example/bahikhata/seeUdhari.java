package com.example.bahikhata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class seeUdhari extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Customer> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_udhari);

        recyclerView=findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        data=new ArrayList<>();
        database db=new database(getApplicationContext());
        Cursor cr=db.getCursorData();
        while (cr.moveToNext()){
            data.add(new Customer(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5),cr.getString(6),
                    cr.getString(7),cr.getString(8)));
        }
        recycleAdapter adapter=new recycleAdapter(getApplicationContext(),data);
        recyclerView.setAdapter(adapter);

    }
}