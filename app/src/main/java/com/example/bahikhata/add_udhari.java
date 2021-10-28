package com.example.bahikhata;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class add_udhari extends AppCompatActivity {
    String st;
    private static final String[] addressArr=new String[]{
            "yadukuha","Nanupati","Dhabauli","Harwada","Hathipur","Hauwahi","nemwatol","Sarabe","Hatletba","Basdepati","Simrapati","Simrari","Sabaila",
            "Kharyani","Gatoli","Gotkuha","Chorakailpur","Pachherba","Bafai","Tinkoriya","Bisharghora","Nathpati","Mangraha","Machi","Lagma","Khaujari",
            "Ekrahi","Gidhha","Mahuliya","Bishargora","Dumariya","Matauna"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_udhari);
        AddressSuggestion();
    }



    private void AddressSuggestion() {
        AutoCompleteTextView address= ( AutoCompleteTextView ) findViewById(R.id.address);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,addressArr);
        address.setAdapter(adapter);
        address.setThreshold(1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addUdhari(View view) {
        Log.i("akm", "addUdhari: started");
        EditText et1,et2,et4,et5,et6;
        AutoCompleteTextView et3;
        database db=new database(this);
        et1=findViewById(R.id.name);
        et2=findViewById(R.id.amount);
        et3=findViewById(R.id.address);
        et4=findViewById(R.id.phone);
        et5=findViewById(R.id.date);
        et6=findViewById(R.id.description);
        String name,address,phone,date,desc;
        int amount;
        name=et1.getText().toString();address=et3.getText().toString();phone=et4.getText().toString();
        date=et5.getText().toString();desc=et6.getText().toString();
        amount=Integer.valueOf(et2.getText().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dt1 = new Date();
         st=(String)formatter.format(dt1);

        String status="unpaid";
        boolean res=db.insert_udhari(name,amount,address,phone,date,status,st,desc);
        if(res)
        {     Log.i("akm", "addUdhari: started true");
            Toast.makeText(getApplicationContext(), "udhari Inserted", Toast.LENGTH_SHORT).show();
        }else {
            Log.i("akm", "addUdhari: started false");
            Toast.makeText(getApplicationContext(), "some coding mistakes", Toast.LENGTH_SHORT).show();

        }

    }
}