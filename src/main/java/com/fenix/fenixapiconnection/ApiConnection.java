package com.fenix.fenixapiconnection;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiConnection {
    public String Url;
    public String AuthToken;
    public String Payload;
    public Context Context;

    public ApiConnection() {
    }
    public ApiConnection(Context context, String url) {
        Url = url;
        Context = context;
    }
    public ApiConnection(String url, String authToken) {
        Url = url;
        AuthToken = authToken;
    }
    public ApiConnection(String url, String authToken, String payload) {
        Url = url;
        AuthToken = authToken;
        Payload = payload;
    }

    public Object Get(){
        final Object[] result = {null};
        System.out.println("GET: ");
        StringRequest getRequest = new StringRequest(Request.Method.GET, this.Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("GET: " + response);
                result[0] = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result[0] = error.getMessage();
            }
        });
        Volley.newRequestQueue(Context).add(getRequest);
        System.out.println("GET Result: " + result[0]);
        return result[0];
    }
    /*private void EnviarWS(){
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
    }*/
}
