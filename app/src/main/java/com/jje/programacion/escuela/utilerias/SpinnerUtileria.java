package com.jje.programacion.escuela.utilerias;

import android.content.Context;
import android.database.MatrixCursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.jje.programacion.escuela.R;

import java.util.List;

public class SpinnerUtileria{
    public static void spinner(Context context, Spinner spinner,String[] columnas ,List id,List nombre,int posicionSeleccionada){

            MatrixCursor matrixCursor= new MatrixCursor(columnas);
            for (int i=0; i<id.size(); i++){
                matrixCursor.addRow(new Object[] { id.get(i).toString(), nombre.get(i)});
            }
            SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(context, R.layout.estilo_spinner_item, matrixCursor, new String[] {"nombre"}, new int[] {android.R.id.text1}, 0);
            mAdapter.setDropDownViewResource(R.layout.estilo_spinner_dropdown_item);
            spinner.setAdapter(mAdapter);
            spinner.setSelection(posicionSeleccionada);

    }
}
