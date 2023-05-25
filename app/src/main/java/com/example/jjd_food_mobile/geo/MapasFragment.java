package com.example.jjd_food_mobile.geo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jjd_food_mobile.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapasFragment extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView distancia, tiempo;
    private ActionBar actionBar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MapasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Mapas.
     */
    // TODO: Rename and change types and number of parameters
    public static MapasFragment newInstance(String param1, String param2) {
        MapasFragment fragment = new MapasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mapas, container, false);

        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Datos de rastreo");
        distancia = v.findViewById(R.id.Distancia);
        tiempo = v.findViewById(R.id.Tiempo);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.my_menu, menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // acción cuando se presiona el botón de regreso
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    GoogleMap map;
    Boolean actualPosition = true;
    JSONObject json;
    double logOrigen, latOrigen;

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

       /*LatLng point1 = new LatLng( 4.57656217966166, -74.22413045096404);
       LatLng point2 = new LatLng(4.575547634908466, -74.22731253581371);
        MarkerOptions marker1 = new MarkerOptions().position(point1).title("Point 1");
        MarkerOptions marker2 = new MarkerOptions().position(point2).title("Point 2");

        map.addMarker(marker1);
        map.addMarker(marker2);

        float distance = getDistance(point1, point2);

        LatLng center = new LatLng((point1.latitude + point2.latitude)/2, (point1.longitude + point2.longitude)/2);
        MarkerOptions distanceMarker = new MarkerOptions().position(center).title("Distancia").snippet("La distancia es " + distance + " metros.");
        map.addMarker(distanceMarker);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 10));*/



        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                
            }else{
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {

            }else{
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            return;
        }
        map.setMyLocationEnabled(true);
        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(@NonNull Location location) {
                if (actualPosition) {
                    //Punto de origen - el esreaturante
                    LatLng point2 = new LatLng(4.575547634908466, -74.22731253581371);
                    MarkerOptions marker2 = new MarkerOptions().position(point2).title("Restaurante");
                    map.addMarker(marker2);
                    // Punto de destino Uniminuto - coordenadas fijas
                    latOrigen = 4.57656217966166;
                    logOrigen = -74.22413045096404;
                    actualPosition = false;

                    LatLng miposicion = new LatLng(latOrigen,logOrigen);
                    map.addMarker(new MarkerOptions().position(miposicion).title("Aqui estoy yo"));

                    CameraPosition cameraPosition =  new CameraPosition.Builder()
                            .target(new LatLng(latOrigen, logOrigen))
                            .zoom(17)
                            .build();
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    //obtner distancia
                    DecimalFormat df = new DecimalFormat("#");
                    float distance = getDistance(miposicion, point2);
                    distancia.setText("Distancia: "+df.format(distance) +" Metros");
                    tiempo.setText("Tiempo de entrega: "+"20 minutos");

                    //Codigo no funcional - hay que pagar :(
                    String url =  "https://maps.googleapis.com/maps/api/directions/json?origin="+latOrigen+","+logOrigen+"&destination=4.5755009960688895, -74.22738282417419";


                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    StringRequest stringRequest =  new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                json = new JSONObject(response);
                                trazarRuta(json);
                                Log.i("jsonRuta: ",""+response);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }


                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(stringRequest);
                }
            }
        });

    }
    private float getDistance(LatLng point1, LatLng point2) {
        Location location1 = new Location("");
        location1.setLatitude(point1.latitude);
        location1.setLongitude(point1.longitude);

        Location location2 = new Location("");
        location2.setLatitude(point2.latitude);
        location2.setLongitude(point2.longitude);

        return location1.distanceTo(location2);
    }



    private void trazarRuta(JSONObject json) {
    }
    interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the feature requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }
}



