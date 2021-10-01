package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fenix.fenixapiconnection.ApiConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txtUser, txtTitle, txtBody, txtToken;
    Button btnEnviar, btnRecibir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBody = findViewById(R.id.txtBody);
        txtUser = findViewById(R.id.txtUser);
        txtTitle = findViewById(R.id.txtTitle);
        txtToken = findViewById(R.id.txtToken);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnRecibir = findViewById(R.id.btnRecibir);
        btnRecibir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeerWS();
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarWS();
            }
        });
    }

    private void LeerWS(){
        /*String url = "https://jsonplaceholder.typicode.com/posts/1";
        System.out.println("GET: ");
        StringRequest getRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("GET: " + response);
                    JSONObject jsonObject = new JSONObject(response);
                    txtUser.setText(jsonObject.getString("userId"));
                    txtTitle.setText(jsonObject.getString("title"));
                    txtBody.setText(jsonObject.getString("body"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("GET: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("GET: " + error.getMessage());
                Log.e("Error", error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(getRequest);*/
        try {
            ApiConnection apiConnection = new ApiConnection(this, "https://jsonplaceholder.typicode.com/posts/1");
            Object response = apiConnection.Get();
            try {
                System.out.println("GET: " + response);
                JSONObject jsonObject = new JSONObject(response.toString());
                txtUser.setText(jsonObject.getString("userId"));
                txtTitle.setText(jsonObject.getString("title"));
                txtBody.setText(jsonObject.getString("body"));
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("GET: " + e.getMessage());
            }
        }catch (Exception ex){
            System.out.println("Error" + ex.getMessage());
        }
    }

    private void EnviarWS(){
        String url = "https://appzeusmobile.com/apizeuslic/api/device";
        System.out.println("POST: ");
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println( "POST: " + response);
                    JSONObject jsonObject = new JSONObject(response);
                    txtToken.setText(jsonObject.getString("token"));
                } catch (JSONException e) {
                    System.out.println( "POST: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("POST: " + error.getMessage());
                Log.e("Error2", error.getMessage());
            }
        }){
            protected Map<String, String> getParams() {
                HashMap<String, String> params = new HashMap<>();
                params.put("empUser", "zeusmobile");
                params.put("empPass", "654321");
                params.put("descripcion", "DIEGO FALCONI");
                params.put("tipo", "M");
                params.put("serie", "868484045136410");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }
}