package com.plasmadev.captiondad.cardview_food2.ingreso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.plasmadev.captiondad.cardview_food2.R;

import java.util.HashMap;
import java.util.Map;

public class signin extends AppCompatActivity {
    private EditText txt1, txtnombre, txtapellido, txtedad, txtcorreo, txttel, txtpassword;
    private TextView text;
    private Button login;
    RequestQueue requestQueue;
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


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarDatos("http://192.168.56.1/jjd_food_movil/insertar.php");

            }
        });
    }

    //METODO PARA REGISTRAR DATOS
    private void RegistrarDatos(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Datos Registrados", Toast.LENGTH_SHORT).show();
                LimpiarFormulario();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
            }


        }){
            protected Map<String, String> getParams() throws AuthFailureError{
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("Nombre", txt1.getText().toString());
            parameters.put("nombre_usu", txtnombre.getText().toString());
            parameters.put("apellido_usu", txtapellido.getText().toString());
            parameters.put("edad", txtedad.getText().toString());
            parameters.put("correo", txtcorreo.getText().toString());
            parameters.put("Telefono", txttel.getText().toString());
            parameters.put("contraseña", txtpassword.getText().toString());
            return parameters;
        }

    };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
 }

 //limpiar cajas de texto
    private void LimpiarFormulario(){
        txt1.setText("");
        txtnombre.setText("");
        txtapellido.setText("");
        txtedad.setText("");
        txtcorreo.setText("");
        txttel.setText("");
        txtpassword.setText("");
    }

}
