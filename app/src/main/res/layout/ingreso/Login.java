package com.plasmadev.captiondad.cardview_food2.ingreso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.plasmadev.captiondad.cardview_food2.ActivityHome;
import com.plasmadev.captiondad.cardview_food2.R;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private Button login;
    private TextView text;
    private EditText t1, t2;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btn_login);
        t1 = findViewById(R.id.txt1);
        t2 = findViewById(R.id.tx7);
        text = findViewById(R.id.intro_icon);
        text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.user__1, 0);


        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (t1.getText().toString().isEmpty() || t2.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Rellena los campos", Toast.LENGTH_SHORT);
                    toast.show();
                    t1.setBackgroundResource(R.drawable.background_btn_error);
                    t2.setBackgroundResource(R.drawable.background_btn_error);
                }else {
                    if (t1.getText().toString().equals("admin") && t2.getText().toString().equals("123")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent i = new Intent(Login.this, ActivityHome.class );
                        startActivity(i);
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT);
                        toast.show();
                        t1.setBackgroundResource(R.drawable.background_btn_error);
                        t2.setBackgroundResource(R.drawable.background_btn_error);
                    }
                }

            }
        });
    }

    private void RegistrarDatos(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Datos Registrados", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                }

                //LimpiarFormulario();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
            }

        }){
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("usuario", t1.getText().toString());
                parametros.put("pass", t2.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}