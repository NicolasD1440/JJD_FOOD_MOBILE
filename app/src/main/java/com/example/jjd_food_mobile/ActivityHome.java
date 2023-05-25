package com.example.jjd_food_mobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jjd_food_mobile.ingreso.Login;
import com.example.jjd_food_mobile.ingreso.MainActivity;
import com.example.jjd_food_mobile.pedido.Pedido;
import com.example.jjd_food_mobile.platos.Plato;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ActivityHome extends AppCompatActivity {
  private Button btn1, btn2, btn3,btn4,btn5,btn6;
  private TextView txt, nombre1,nombre2, nombre3,nombre4,nombre5,nombre6, precio1, precio2,
          precio3,precio4,precio5,precio6;
  private DatabaseReference mDataBase;
  private int i = 0;
  private List<Plato> listaPlatos = new ArrayList<Plato>();
  private int precioTotal;

  private int[] miArray = new int[6];
  private String prueba;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //referencia a componentes graficos
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        //boton flotante
        FloatingActionButton fab_cart;
        fab_cart = findViewById(R.id.cart);
        //texto contador
        txt = findViewById(R.id.contador);
        //nombres platos
        nombre1 = findViewById(R.id.Nombre1);
        nombre2 = findViewById(R.id.Nombre2);
        nombre3 = findViewById(R.id.Nombre3);
        nombre4 = findViewById(R.id.Nombre4);
        nombre5 = findViewById(R.id.Nombre5);
        nombre6 = findViewById(R.id.Nombre6);
        //precios platos
        precio1 = findViewById(R.id.precio1);
        precio2 = findViewById(R.id.precio2);
        precio3 = findViewById(R.id.precio3);
        precio4 = findViewById(R.id.precio4);
        precio5 = findViewById(R.id.precio5);
        precio6 = findViewById(R.id.precio6);

        NumberFormat nf = NumberFormat.getInstance();
        //Instancia Firebase
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("JJD_FOOD");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Crear una lista de platos

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    String nombre = itemSnapshot.child("nombre").getValue(String.class);
                    String precio = itemSnapshot.child("precio").getValue(String.class);
                    Plato plato1 = new Plato(nombre, Integer.parseInt(precio));
                    listaPlatos.add(plato1);
                }
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    String nombre = itemSnapshot.child("nombre").getValue(String.class);
                    String precio = itemSnapshot.child("precio").getValue(String.class);
                    Plato plato1 = new Plato(nombre, Double.parseDouble(precio.replace(".", ",")));
                    //plato1
                    nombre1.setText(listaPlatos.get(0).getNombre());
                    precio1.setText(nf.format(listaPlatos.get(0).getPrecio()).replace(".", ","));
                    //plato2
                    nombre2.setText(listaPlatos.get(1).getNombre());
                    precio2.setText(nf.format(listaPlatos.get(1).getPrecio()));
                    //plato3
                    nombre3.setText(listaPlatos.get(2).getNombre());
                    precio3.setText(nf.format(listaPlatos.get(2).getPrecio()));
                    //plato4
                    nombre4.setText(listaPlatos.get(3).getNombre());
                    precio4.setText(nf.format(listaPlatos.get(3).getPrecio()));
                    //plato5
                    nombre5.setText(listaPlatos.get(4).getNombre());
                    precio5.setText(nf.format(listaPlatos.get(4).getPrecio()));
                    //plato6
                    nombre6.setText(listaPlatos.get(5).getNombre());
                    precio6.setText(nf.format(listaPlatos.get(5).getPrecio()));

                    //prueba = String.valueOf(listaPlatos.get(0).getNombre());

                }

                // Aquí puedes hacer lo que necesites con los ArrayLists de nombres y precios.
               // prueba = String.valueOf((int) listaPlatos.get(0).getPrecio());
                miArray[0] = (int) listaPlatos.get(0).getPrecio();
                miArray[1] = (int) listaPlatos.get(1).getPrecio();
                miArray[2] = (int) listaPlatos.get(2).getPrecio();
                miArray[3] = (int) listaPlatos.get(3).getPrecio();
                miArray[4] = (int) listaPlatos.get(4).getPrecio();
                miArray[5] = (int) listaPlatos.get(5).getPrecio();

                //miArray[0] = Integer.parseInt(String.valueOf(listaPlatos.get(0).getPrecio()).trim());

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Maneja el error aquí.
            }
        });


        //Toast.makeText(this, "Mensaje: "+prueba, Toast.LENGTH_SHORT).show();

        fab_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityHome.this, Pedido.class );
                String p = String.valueOf(precioTotal);
                i.putExtra("TEXTO", p);
                startActivity(i);
                txt.setText("");

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                txt.setText("# " + i);
                //Toast.makeText(ActivityHome.this, "Mnesaje" + prueba +" "+ miArray[0], Toast.LENGTH_SHORT).show();
                precioTotal = precioTotal + miArray[0];

                //Toast.makeText(ActivityHome.this, "pre: " + precioTotal, Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                txt.setText("# " + i);
                precioTotal = precioTotal + miArray[1];
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                txt.setText("# " + i);
                precioTotal = precioTotal + miArray[2];
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                txt.setText("# " + i);
                precioTotal = precioTotal + miArray[3];
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                txt.setText("# " + i);
                precioTotal = precioTotal + miArray[4];
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                txt.setText("# " + i);
                precioTotal = precioTotal + miArray[5];
            }
        });



      /*  SharedPreferences sharedPref = getSharedPreferences("TotalPrecio", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("TotalP", aux);*/


    }




}
