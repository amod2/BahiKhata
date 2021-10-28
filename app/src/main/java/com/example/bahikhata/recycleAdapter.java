package com.example.bahikhata;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.ViewHolder > {
    Context context;
    static ArrayList<Customer> customers;
    String difference_In_Years;
    String difference_In_Days;

    public recycleAdapter(Context context, ArrayList<Customer> customers) {
        this.context = context;
        this.customers = customers;
    }
   public static ArrayList<Customer> getCustomers(){
        return customers;
   }

    @Override

    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull  recycleAdapter.ViewHolder holder, int position) {
          holder.name.setText(customers.get(position).getName());
          holder.addressV.setText(customers.get(position).address);
          holder.amountV.setText(customers.get(position).getAmount());
          holder.days.setText(customers.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView amountV;
        TextView addressV,status,days;
        ImageView callView;
        Button button;
        Button btn;
        public ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name2);
            days=itemView.findViewById(R.id.dt);
            callView=itemView.findViewById(R.id.call_id1);
            amountV=itemView.findViewById(R.id.amt1);
            addressV=itemView.findViewById(R.id.addr1);
            button=itemView.findViewById(R.id.button3);
            btn=itemView.findViewById(R.id.view1);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(),udhar_info.class);
                    int a=customers.get(getAdapterPosition()).getId();
                    intent.putExtra("key",String.valueOf(a));
                    itemView.getContext().startActivity(intent);
                }
            });
            callView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+customers.get(getAdapterPosition()).getPhone()));//change the number
                    if (Build.VERSION.SDK_INT > 23) {
                        itemView.getContext().startActivity(callIntent);
                    } else {

                        if (ActivityCompat.checkSelfPermission(itemView.getContext(),
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(itemView.getContext(), "Permission Not Granted ", Toast.LENGTH_SHORT).show();
                        } else {
                            final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                            ActivityCompat.requestPermissions((Activity) itemView.getContext(), PERMISSIONS_STORAGE, 9);
                            itemView.getContext().startActivity(callIntent);
                        }
                    }
                }
            });
        }
    }

}

