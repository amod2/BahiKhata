package com.example.bahikhata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class udhar_info extends AppCompatActivity {
    database  db=new database(this);
    ArrayList<Customer> cus;
    int id,pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udhar_info);
        id= Integer.parseInt(getIntent().getExtras().getString("key"));
        String id1=getIntent().getExtras().getString("key");
        cus=recycleAdapter.getCustomers();
        getInfo();

    }

    private void removeButton() {
            Button btn=findViewById(R.id.view1);
            btn.setVisibility(View.INVISIBLE);
            getInfo();
    }

    private void getInfo() {
        TextView user_name;
        TextView user_add;TextView user_phone;TextView user_amount;TextView user_date;TextView user_status;TextView user_desc;
        user_name=findViewById(R.id.user_info_name);
        user_add=findViewById(R.id.user_info_addr);
        user_phone=findViewById(R.id.user_info_phone);
        user_amount=findViewById(R.id.user_info_amount);
        user_date=findViewById(R.id.user_info_date);
        user_status=findViewById(R.id.user_info_status);
        user_desc=findViewById(R.id.user_info_desc);
        Button button=findViewById(R.id.button6);

      for(int i=0;i<cus.size();i++){
          if(cus.get(i).getId()==id){
              pos=i;
              user_name.setText(cus.get(i).getName());
              user_add.setText(cus.get(i).getAddress());
              user_amount.setText(cus.get(i).getAmount());
              user_phone.setText(cus.get(i).getPhone());
              user_date.setText(cus.get(i).getDate());
              user_desc.setText(cus.get(i).getDescription());
              if(cus.get(i).getStatus().equals("unpaid")){
                  user_status.setBackgroundColor(R.drawable.unpaid);
                  user_status.setText(cus.get(i).getStatus());

              }else{
                  user_status.setText(cus.get(i).getStatus());
                  user_status.setBackgroundColor(R.drawable.sucsess);
                  Button btn=findViewById(R.id.button3);
                  btn.setVisibility(View.GONE);
              }
              button.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent callIntent = new Intent(Intent.ACTION_DIAL);
                      callIntent.setData(Uri.parse("tel:"+cus.get(id).getPhone()));//change the number
                      if (Build.VERSION.SDK_INT > 23) {
                          startActivity(callIntent);
                      } else {

                          if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                  Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                              Toast.makeText(getApplicationContext(), "Permission Not Granted ", Toast.LENGTH_SHORT).show();
                          } else {
                              final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                              ActivityCompat.requestPermissions((Activity) getApplicationContext(), PERMISSIONS_STORAGE, 9);
                              startActivity(callIntent);
                          }
                      }
                  }
              });

          }
      }
    }

    public void updateClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Enter Security Code");
       final   EditText input = new EditText(getApplicationContext());
        input.setBackgroundColor(R.drawable.input_style);
        input.setMaxWidth(100);
        input.setPadding(300,60,300,60);
        builder.setView(input);
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              String secKey=input.getText().toString();
              if(secKey.equals("24689")){
                  boolean res= db.updateStatus(String.valueOf(id));
                  if(res){
                      Intent intent=new Intent(getApplicationContext(),dashboard.class);
                      startActivity(intent);
                      Toast.makeText(udhar_info.this, "Paid Success ", Toast.LENGTH_SHORT).show();
                  }
              }else {
                  Toast.makeText(udhar_info.this, "Invalid Key", Toast.LENGTH_SHORT).show();
              }


            }
        });
        builder.create().show();

    }
}