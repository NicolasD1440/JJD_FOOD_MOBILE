package com.example.jjd_food_mobile.pedido;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.example.jjd_food_mobile.R;
import com.example.jjd_food_mobile.adapter.comidaAdapter;
import com.example.jjd_food_mobile.modelo.comida;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HistorialPedidos extends AppCompatActivity {
    RecyclerView mRecicleview;
    comidaAdapter mAdapter;
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    private String idUser;
    private Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_pedidos);
        //ActionBar boton hacia atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        this.window = getWindow();
        window.setStatusBarColor(Color.parseColor("#FFFFFF"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        window.setNavigationBarColor(Color.parseColor("#FFFFFF"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");


        fStore = FirebaseFirestore.getInstance();
        mRecicleview = findViewById(R.id.reciclerView);
        mRecicleview.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        idUser = mAuth.getCurrentUser().getUid();

        Query query = fStore.collection("pedido").whereEqualTo("id", idUser);
        FirestoreRecyclerOptions<comida> firestoreReciclerOptions =
                new FirestoreRecyclerOptions.Builder<comida>().setQuery(query, comida.class).build();

        mAdapter =  new comidaAdapter(firestoreReciclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecicleview.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}