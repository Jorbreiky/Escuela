package com.jje.programacion.escuela.ServicioEscuela;

import android.content.Context;
import com.jje.programacion.escuela.utilerias.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class VolleyEscuela {
    private RequestQueue requestQueue;
    private Context context;


    public VolleyEscuela(Context context){
        requestQueue= Volley.newRequestQueue(context);
        this.context = context;
    }

    public void sendJSON(String url,JSONObject json, Response.Listener<JSONObject> listener, Response.ErrorListener listenerError){
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                json,
                listener,
                listenerError
        );

        requestQueue.add(jsArrayRequest);
    }
}
