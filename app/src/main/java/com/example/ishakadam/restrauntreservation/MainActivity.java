package com.example.ishakadam.restrauntreservation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(TextView)findViewById(R.id.tafel);

    }
    public void onClick(View v){
            txt.setTextColor(Color.parseColor("#00ff33"));
            txt=findViewById(R.id.tafel2);
            txt.setTextColor(Color.parseColor("#001f1e"));
        Toast.makeText(this,"Welcome to the restaurant reservation app",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,Reservation.class);
        startActivity(intent);
    }
}
