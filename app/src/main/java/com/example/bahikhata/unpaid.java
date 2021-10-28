package com.example.bahikhata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class unpaid extends AppCompatActivity {
    ArrayList<Customer> data;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid);

        recyclerView=findViewById(R.id.recycle2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        data=new ArrayList<>();
        database db=new database(getApplicationContext());
        Cursor cr=db.getCursorData();
        while (cr.moveToNext()){
            if(cr.getString(6).equals("unpaid")){
                data.add(new Customer(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3),cr.getString(4),cr.getString(5),cr.getString(6),
                        cr.getString(7),cr.getString(8)));
            }

        }
        recycleAdapter adapter=new recycleAdapter(getApplicationContext(),data);
        recyclerView.setAdapter(adapter);
    }
}