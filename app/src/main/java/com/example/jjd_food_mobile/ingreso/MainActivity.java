package com.example.jjd_food_mobile.ingreso;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.example.jjd_food_mobile.R;


public class MainActivity extends AppCompatActivity {
    private Button login, sing_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btn_log);
        sing_in = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class );
                startActivity(i);
            }
        });

        sing_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, signin.class );
                startActivity(i);
            }
        });
    }

}