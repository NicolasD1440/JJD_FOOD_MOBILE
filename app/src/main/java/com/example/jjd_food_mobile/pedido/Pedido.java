package com.example.jjd_food_mobile.pedido;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjd_food_mobile.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Pedido extends AppCompatActivity {
    private Window window;
    private EditText Nombre, Tel, Dic;
    private TextView id, impuesto,precio,TotalPedido;
    private String idUser;
    private int Impuesto_total = 300;
    private Button btn;
    FirebaseFirestore mfirestore;
    FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.window = getWindow();
        window.setStatusBarColor(Color.parseColor("#A17EFF"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#A17EFF")));
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#A17EFF")));
        window.setNavigationBarColor(Color.parseColor("#A17EFF"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setElevation(0);

        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        idUser = mAuth.getCurrentUser().getUid();


        Nombre = findViewById(R.id.NombreP);
        Tel =  findViewById(R.id.NumeroP);
        Dic = findViewById(R.id.DireccionP);
        id = findViewById(R.id.id_user);
        impuesto = findViewById(R.id.impuestoAda);
        precio = findViewById(R.id.precio);
        TotalPedido = findViewById(R.id.total_pedido);


        //Referencia boton
        btn =  findViewById(R.id.Fpedido);


        Intent intent = getIntent();
        String texto = intent.getStringExtra("TEXTO");
     //   Toast.makeText(this, "Mnesa: "+ variable + texto, Toast.LENGTH_SHORT).show();
        id.setText(idUser);
        impuesto.setText(String.valueOf(Impuesto_total));
        precio.setText(texto);
        TotalPedido.setText(String.valueOf(Integer.parseInt(texto)+Impuesto_total));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nombre = Nombre.getText().toString().trim();
                String telefono = Tel.getText().toString().trim();
                String direccion = Dic.getText().toString().trim();
                String imp = impuesto.getText().toString().trim();
                String pre = precio.getText().toString().trim();
                String tol = TotalPedido.getText().toString().trim();


                if (nombre.isEmpty() && telefono.isEmpty() && direccion.isEmpty()) {
                    Toast.makeText(Pedido.this, "Rellena los campos", Toast.LENGTH_SHORT).show();
                }else{
                    guardarDatos(nombre,telefono,direccion,imp,pre,tol);
                }

                /*finish();
                Intent i = new Intent(Pedido.this, FinalizarCompra.class);
                startActivity(i);*/
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
    private void guardarDatos(String nombre, String telefono, String direccion, String imp, String pre, String tol) {

        Map<String, Object> map = new HashMap<>();
        map.put("nombre",nombre);
        map.put("telefono",telefono);
        map.put("direccion",direccion);
        map.put("id",idUser);
        map.put("precio",pre);
        map.put("impuesto",imp);
        map.put("total", tol);

    mfirestore.collection("pedido").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
        @Override
        public void onSuccess(DocumentReference documentReference) {
            Toast.makeText(Pedido.this, "Datos guardados", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Pedido.this, FinalizarCompra.class );
            startActivity(i);
            String p = "";
            i.putExtra("TEXTO", p);
            finish();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(Pedido.this, "Error al agregar", Toast.LENGTH_SHORT).show();
        }
    });
    }



}