package com.jje.programacion.escuela.ServicioEscuela;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Config extends Activity{
    public static final String servidor = "http://192.168.15.36/";
    public static final String url_login = servidor+"Servicio/Login.php";
    public static RequestQueue requestQueue;

    public static RequestQueue getInstance(Context context){
        if(requestQueue == null){
            requestQueue= Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

}
