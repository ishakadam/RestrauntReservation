package com.example.ishakadam.restrauntreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {

    EditText email_id, password;
    Button sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email_id=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
    }

    public void signin(View view) {
        if(email_id.getText().toString().equals("admin")&&password.getText().toString().equals("admin")){
            Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"wrong credentials",Toast.LENGTH_SHORT).show();
        }
    }
}
