package com.jje.programacion.escuela;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jje.programacion.escuela.ServicioEscuela.Config;
import com.jje.programacion.escuela.ServicioEscuela.VolleyEscuela;
import com.jje.programacion.escuela.utilerias.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText ETUsuario;
    private EditText ETContrasena;
    private Button BEntrar;
    private String usuario;
    private String contrasena;
    private MainActivity mainActivity = this;

    /* DECLARACION DE LAS VARIABLES PARA LOS LISTENER DEL JSON*/
    private Response.Listener listener;
    private Response.ErrorListener listenerError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        redondearImagePerfil();
        usuario = verLlave("usuario");
        contrasena = verLlave(usuario);
        ETUsuario.setText(usuario);
        ETContrasena.setText(contrasena);
        initListenerJSON();
    }

    /* INICIALIZAR LOS LISTENER PARA LOS EVENTOS DE RESPONSE Y RESONSE ERROR*/
    private void initListenerJSON(){
        listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("confirmacion")){
                        startActivity(new Intent(mainActivity,Principal.class));
                        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
                        alertaMensaje();
                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                    Log.e("jma","Response--->"+response.toString());
                }catch(Exception e){

                }

            }
        };

        listenerError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jma", "Error Respuesta en JSON: " + error.getMessage());
                error.printStackTrace();

            }
        };
    }

    public void initComponents(){
        añadirLlave("usuario","1111");
        añadirLlave("1111","jorge123");
        ETUsuario = (EditText) findViewById(R.id.ETUsuario);
        ETContrasena = (EditText) findViewById(R.id.ETContrasena);
        BEntrar = (Button) findViewById(R.id.BEntrar);
        BEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject json = new JSONObject();
                JSONObject rqt = new JSONObject();
                try{
                    rqt.put("aplicacion","app");
                    rqt.put("usuario",ETUsuario.getText());
                    rqt.put("contrasenia",ETContrasena.getText());
                    json.put("rqt",rqt);
                    Log.e("jma",json.toString());
                }catch(Exception e){
                    Log.e("jma",e.getMessage());
                }
                VolleyEscuela escuela = new VolleyEscuela(getApplicationContext());
                escuela.sendJSON(Config.url_login,json, listener, listenerError);
            }
        });
    }

    public void redondearImagePerfil(){
        Bitmap originalBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.portada)).getBitmap();
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());
        ImageView imageView = (ImageView) findViewById(R.id.IPerfil);
        imageView.setImageDrawable(roundedDrawable);
    }

    public void añadirLlave(String llave, String valor){
        SharedPreferences prefs = getSharedPreferences("escuela", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(llave, valor);
        editor.commit();
    }

    public String verLlave(String llave){
        SharedPreferences prefs = getSharedPreferences("escuela",Context.MODE_PRIVATE);
        return prefs.getString(llave,"null");
    }


    private static final int NOTIF_ALERTA_ID = 1;
    public void alertaMensaje(){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setLargeIcon((((BitmapDrawable)getResources()
                                .getDrawable(R.drawable.dgti)).getBitmap()))
                        .setContentTitle("Mensaje de Alerta")
                        .setContentText("Ejemplo de notificación.")
                        .setContentInfo("4")
                        .setTicker("Alerta!");

        Intent notIntent =
                new Intent(MainActivity.this, MainActivity.class);

        PendingIntent contIntent = PendingIntent.getActivity(
                MainActivity.this, 0, notIntent, 0);

        mBuilder.setContentIntent(contIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());
    }
}
