package com.example.jjd_food_mobile.pedido;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.jjd_food_mobile.ActivityHome;
import com.example.jjd_food_mobile.R;
import com.example.jjd_food_mobile.geo.Rastreo;
import com.example.jjd_food_mobile.ingreso.Login;
import com.example.jjd_food_mobile.ingreso.signin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.HashMap;
import java.util.Map;

public class FinalizarCompra extends AppCompatActivity {
    private Window window;
    private Button btn, btn2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_compra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        this.window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFF"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        window.setNavigationBarColor(Color.parseColor("#FFFFFF"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setElevation(0);


        //referencia los botones
        btn = findViewById(R.id.pedido);
        btn2 = findViewById(R.id.inicio);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(FinalizarCompra.this, Rastreo.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(FinalizarCompra.this, ActivityHome.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}