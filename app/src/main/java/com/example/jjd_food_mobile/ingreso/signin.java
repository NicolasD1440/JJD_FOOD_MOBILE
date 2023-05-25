package com.example.jjd_food_mobile.ingreso;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.jjd_food_mobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class signin extends AppCompatActivity {
    private EditText txt1, txtnombre, txtapellido, txtedad, txtcorreo, txttel, txtpassword;
    private TextView text;
    private Button login;
    FirebaseFirestore mfirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        txt1 = findViewById(R.id.txt1);
        txtnombre = findViewById(R.id.tx2);
        txtapellido = findViewById(R.id.tx3);
        txtedad = findViewById(R.id.tx4);
        txtcorreo = findViewById(R.id.tx5);
        txttel = findViewById(R.id.tx6);
        txtpassword = findViewById(R.id.tx7);

        text = findViewById(R.id.intro_icon);
        text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.user__1, 0);
        login = findViewById(R.id.btn_login);

        //llamada a firebase




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String Nombreusu = txt1.getText().toString().trim();
              String Nombre = txtnombre.getText().toString().trim();
              String Apellido = txtapellido.getText().toString().trim();
              String Edad = txtedad.getText().toString().trim();
              String Correo = txtcorreo.getText().toString().trim();
              String Tel = txttel.getText().toString().trim();
              String Pass = txtpassword.getText().toString().trim();

              mfirestore = FirebaseFirestore.getInstance();
              mAuth = FirebaseAuth.getInstance();

                if (Nombreusu.isEmpty() && Nombre.isEmpty() && Apellido.isEmpty() && Edad.isEmpty() &&
                        Correo.isEmpty() &&Tel.isEmpty() && Pass.isEmpty()) {
                    Toast.makeText(signin.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }else {
                   registrarUsuario(Nombreusu,Nombre,Apellido,Edad,Correo,Tel,Pass);
                }

            }


        });
    }
    private void registrarUsuario(String nombreusu, String nombre, String apellido, String edad, String correo, String tel, String pass) {
            mAuth.createUserWithEmailAndPassword(correo,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    String id = mAuth.getCurrentUser().getUid();
                   Map<String, Object> map = new HashMap<>();
                   map.put("id", id);
                           map.put("nameusu", nombreusu);
                           map.put("name", nombre);
                           map.put("lastname", apellido);
                           map.put("age", edad);
                           map.put("mail", correo);
                           map.put("phone", tel);
                           map.put("password", pass);

                           mfirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void unused) {
                                  finish();
                                  startActivity(new Intent(signin.this, Login.class));
                                   Toast.makeText(signin.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                               }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(signin.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                               }
                           });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(signin.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                }
            });
    }

}
