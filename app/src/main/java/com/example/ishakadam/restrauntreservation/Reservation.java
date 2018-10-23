package com.example.ishakadam.restrauntreservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Reservation extends AppCompatActivity implements View.OnClickListener {
        EditText time,date;
    private int mYear, mMonth, mDay, mHour, mMinute;
        DatabaseReference dbTable;
        Button btchk,resv;
        List<Tables> tableList,tlist2;
        TextView txtv;
        int avail;
        Tables reserve;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this,"Please refresh/check before " +
                        "selecting a table to reserve",
                Toast.LENGTH_SHORT).show();
        avail=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        btchk=(Button)findViewById(R.id.check);
        time=(EditText)findViewById(R.id.time);
        date=(EditText)findViewById(R.id.date);
            dbTable = FirebaseDatabase.getInstance().getReference("tables");
        resv=(Button)findViewById(R.id.reserve);
        time.setOnClickListener(this);
        date.setOnClickListener(this);
        btchk.setOnClickListener(this);
        tableList= new ArrayList<>();
        tlist2= new ArrayList<>();
        txtv=(TextView)findViewById(R.id.t1);
        reserve=new Tables();
        resv.setOnClickListener(this);
        dbTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tableList.clear();
                for(DataSnapshot tablesnap : dataSnapshot.getChildren()){
                    Tables tab=tablesnap.getValue(Tables.class);
                    tableList.add(tab);
                }
                for(Tables tb: tableList){
                    if("y".equals(tb.isReserved)){
                        txtv=(TextView)findViewById(Integer.parseInt(tb.tableId));
                        txtv.setTextColor(Color.parseColor("#ff0000"));
                    }
                    if("n".equals(tb.isReserved)){
                        txtv=(TextView)findViewById(Integer.parseInt(tb.tableId));
                        txtv.setTextColor(Color.parseColor("#00ff00"));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tableList.clear();
                for(DataSnapshot tablesnap : dataSnapshot.getChildren()){
                    Tables tab=tablesnap.getValue(Tables.class);
                    tableList.add(tab);
                }
                for(Tables tb: tableList){
                    if("y".equals(tb.isReserved)){
                        txtv=(TextView)findViewById(Integer.parseInt(tb.tableId));
                        txtv.setTextColor(Color.parseColor("#ff0000"));
                    }
                    if("n".equals(tb.isReserved)){
                        txtv=(TextView)findViewById(Integer.parseInt(tb.tableId));
                        txtv.setTextColor(Color.parseColor("#00ff00"));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        else if (v == time) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        else if (v==btchk){
            dbTable.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    tlist2.clear();
                    for(DataSnapshot tablesnap : dataSnapshot.getChildren()){
                        Tables tab=tablesnap.getValue(Tables.class);
                        tlist2.add(tab);
                    }
                    for(Tables tb: tlist2){
                        if("y".equals(tb.isReserved)){
                            txtv=(TextView)findViewById(Integer.parseInt(tb.tableId));
                            txtv.setTextColor(Color.parseColor("#ff0000"));
                        }
                        if("n".equals(tb.isReserved)){
                            txtv=(TextView)findViewById(Integer.parseInt(tb.tableId));
                            txtv.setTextColor(Color.parseColor("#00ff00"));
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        else if(v== resv){
            if(avail==0){
                Toast.makeText(this,"Please select a table to reserve",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                if("Time".equalsIgnoreCase(time.getText().toString())){
                    if("Date".equalsIgnoreCase(date.getText().toString())){
                        Toast.makeText(this,"Please select a time and date to " +
                                        "reserve",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this,"Please select a time to " +
                                        "reserve",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if("Date".equalsIgnoreCase(date.getText().toString())){
                        Toast.makeText(this,"Please select a time to " +
                                        "reserve",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        AlertDialog.Builder permit = new AlertDialog.Builder(this);
                        permit.setMessage("Do you want to reserve this table "+reserve.tablename)
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                updateTable(reserve.tableId,reserve.tablename,"y",date.getText().toString(),time.getText().toString());
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                            AlertDialog alert=permit.create();
                            alert.setTitle("Reservation");
                            alert.show();
                    }
                }
            }
        }
        else{
            txtv=(TextView)findViewById(v.getId());
            for(Tables tb: tableList){
                if(tb.tableId.equals(""+v.getId()) && "n".equals(tb.isReserved)){
                    avail=v.getId();
                    Toast.makeText(this," "+avail,
                            Toast.LENGTH_SHORT).show();
                    reserve=tb;
                    break;
                }

            }
            if(avail!=0){

            }
            else{
                Toast.makeText(this,"Sorry, this table is already reserved",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
        private boolean updateTable(String id, String name, String status, String date, String time){
             DatabaseReference dtab= FirebaseDatabase.getInstance().getReference("tables").child(id);
             Tables tupd= new Tables(id,name,status,date,time);
             dtab.setValue(tupd);
            Toast.makeText(this,"Table " + name +
                            " has been updated",
                    Toast.LENGTH_LONG).show();
            return true;
        }

}
