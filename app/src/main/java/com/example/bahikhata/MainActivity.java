package com.example.bahikhata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void add(View view) {
        Intent intent=new Intent(getApplicationContext(),add_udhari.class);
        startActivity(intent);

    }

    public void see(View view) {
        Intent intent=new Intent(getApplicationContext(),seeUdhari.class);
        startActivity(intent);

    }

    public void dash(View view) {
        Intent intent=new Intent(getApplicationContext(),dashboard.class);
        startActivity(intent);

    }
}