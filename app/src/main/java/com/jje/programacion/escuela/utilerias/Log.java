package com.jje.programacion.escuela.utilerias;

public class Log {

    public static final boolean visible = true;

    public static void e(String tag,String mensaje){
        if(visible) android.util.Log.e(tag,mensaje);
    }

    public static void d(String tag,String mensaje){
        if(visible) android.util.Log.d(tag,mensaje);
    }

    public static void w(String tag,String mensaje){
        if(visible) android.util.Log.w(tag,mensaje);
    }
    public static void i(String tag,String mensaje){
        if(visible) android.util.Log.i(tag,mensaje);
    }

}
