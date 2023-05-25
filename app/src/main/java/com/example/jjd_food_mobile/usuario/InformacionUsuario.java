package com.example.jjd_food_mobile.usuario;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjd_food_mobile.ActivityHome;
import com.example.jjd_food_mobile.R;
import com.example.jjd_food_mobile.adapter.comidaAdapter;
import com.example.jjd_food_mobile.ingreso.Login;
import com.example.jjd_food_mobile.pedido.HistorialPedidos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class InformacionUsuario extends AppCompatActivity {
    private TextView NombreUser, mail, nombreCom,telefono, idusuario;
    private Button btn1, btn2, btn3;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    private String idUser;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_usuario);

      NombreUser =  findViewById(R.id.userNombre);
      mail = findViewById(R.id.userEmail);
      nombreCom = findViewById(R.id.NombreCom);
      telefono = findViewById(R.id.Tel);
      idusuario = findViewById(R.id.userId);
      btn1 =  findViewById(R.id.btnPedidos);
      btn2 = findViewById(R.id.btnInicio);
      btn3 = findViewById(R.id.btnSalir);

      mAuth = FirebaseAuth.getInstance();
      final FirebaseUser user = mAuth.getCurrentUser();
      fStore = FirebaseFirestore.getInstance();
      idUser =mAuth.getCurrentUser().getUid();
        idusuario.setText(user.getUid());
        mail.setText(user.getEmail());




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InformacionUsuario.this, HistorialPedidos.class);
                startActivity(i);
                Toast.makeText(InformacionUsuario.this, "Historial de compras", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InformacionUsuario.this, ActivityHome.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InformacionUsuario.this, Login.class);
                startActivity(i);
            }
        });

        DocumentReference documentReference = fStore.collection("user").document(idUser);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


                NombreUser.setText(value.getString("nameusu"));
                telefono.setText("Telefono: "+value.getString("phone"));
                String nombre = value.getString("name");
                String apellido = value.getString("lastname");
                nombreCom.setText("Nombre: " +nombre + " "+ apellido);
            }
        });


    }
}