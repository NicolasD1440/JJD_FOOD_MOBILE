package com.example.jjd_food_mobile.geo;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.jjd_food_mobile.R;

public class Rastreo extends AppCompatActivity implements MapasFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rastreo);
        Fragment fragmento = new MapasFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragmento).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}