package com.example.bahikhata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {
database db=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        insertAll();
        insertUnpaid();
        insertPaid();

    }

    private void insertPaid() {
        TextView tv=findViewById(R.id.paid_udhari);
        String qry="select amount from udhari_tbl where status='paid'";
        Cursor cr=db.getData(qry);
        int s=0;
        int sum;
        while (cr.moveToNext()){
            s+=cr.getInt(0);

        }
        tv.setText(String.valueOf(s));
    }
    private void insertAll(){
        TextView tv=findViewById(R.id.total_udhari);
        String qry="select amount from udhari_tbl";
        Cursor cr=db.getData(qry);
        int s=0;
        int sum;
        while (cr.moveToNext()){
            s+=cr.getInt(0);

        }
        tv.setText(String.valueOf(s));
    }
    private void insertUnpaid(){
        TextView tv=findViewById(R.id.unpaid_udhari);
        String qry="select amount from udhari_tbl where status='unpaid'";
        Cursor cr=db.getData(qry);
        int s=0;
        int sum;
        while (cr.moveToNext()){
            s+=cr.getInt(0);

        }
        tv.setText(String.valueOf(s));
    }

    public void totalClick(View view) {
        Intent intent=new Intent(getApplicationContext(),seeUdhari.class);
        startActivity(intent);

    }

    public void unpaidClick(View view) {
    Intent in=new Intent(getApplicationContext(),unpaid.class);
    startActivity(in);

    }

    public void paidClick(View view) {
        Intent in=new Intent(getApplicationContext(),paid.class);
        startActivity(in);

    }
}