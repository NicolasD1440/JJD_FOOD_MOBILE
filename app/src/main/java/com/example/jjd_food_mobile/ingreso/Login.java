package com.example.jjd_food_mobile.ingreso;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.jjd_food_mobile.R;
import com.example.jjd_food_mobile.usuario.InformacionUsuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    private Button login;
    private TextView text;
    private EditText t1, t2;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btn_login);
        t1 = findViewById(R.id.txt1);
        t2 = findViewById(R.id.tx7);
        text = findViewById(R.id.intro_icon);
        text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.user__1, 0);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

             String mail = t1.getText().toString().trim();
             String pass = t2.getText().toString().trim();

                if (mail.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(Login.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                 loginUser(mail, pass);
                }

            }


        });
    }
    private void loginUser(String mail, String pass) {
     mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             if (task.isSuccessful()) {
                 Intent i = new Intent(Login.this, InformacionUsuario.class );
                 startActivity(i);
                 finish();
                 Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
             }else {
                 Toast.makeText(Login.this, "Sucedio un error inesperado :(", Toast.LENGTH_SHORT).show();
             }
         }
     }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             Toast.makeText(Login.this, "Datos erroneos intentalo de nuevo", Toast.LENGTH_SHORT).show();
         }
     });
    }

}