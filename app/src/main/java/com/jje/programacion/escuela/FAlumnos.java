package com.jje.programacion.escuela;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.jje.programacion.escuela.utilerias.Alumno;
import com.jje.programacion.escuela.utilerias.AlumnoAdapter;
import com.jje.programacion.escuela.utilerias.RecyclerTouchListener;
import com.jje.programacion.escuela.utilerias.SpinnerUtileria;

import java.util.ArrayList;
import java.util.List;

public class FAlumnos extends Fragment {

    /*componentes*/
    private Spinner sCarrera,sSemestre,sGrupo;
    private RecyclerView recyclerView;
    private AlumnoAdapter mAdapter;
    private List<Alumno> alumnoList = new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    public FAlumnos() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Metodo para castear los componentes del layout en el fragmento
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        sCarrera = (Spinner) view.findViewById(R.id.sCarrera);
        sSemestre = (Spinner) view.findViewById(R.id.sSemestre);
        sGrupo = (Spinner) view.findViewById(R.id.sGrupo);

        String[] columnas = new String[]{"_id","nombre"};
        List id = new ArrayList();
        id.add(1);
        id.add(2);
        id.add(3);
        id.add(4);

        List nombre = new ArrayList();
        nombre.add("Ingenieria en Sistemas Computacionales");
        nombre.add("Ingenieria Civil");
        nombre.add("Ingenieria en Informatica");
        nombre.add("Contabilidad");

        SpinnerUtileria.spinner(getContext(),sCarrera,columnas,id,nombre,0);
        SpinnerUtileria.spinner(getContext(),sSemestre,columnas,id,nombre,0);
        SpinnerUtileria.spinner(getContext(),sGrupo,columnas,id,nombre,0);

        /* RECYCLER */
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerAlumno);

        mAdapter = new AlumnoAdapter(alumnoList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(view.getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Alumno alumno = alumnoList.get(position);
                Toast.makeText(view.getContext(), alumno.getNombre() + " es seleccionado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.falumnos, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void prepareMovieData() {
        alumnoList.add(new Alumno("Jorge", "ISC", "7 Semestre",R.drawable.dgti));
        alumnoList.add(new Alumno("Armando", "II", "8 Semestre",R.drawable.dgti));
        alumnoList.add(new Alumno("Julio", "C", "2 Semestre",R.drawable.dgti));
        alumnoList.add(new Alumno("Martin", "C", "3 Semestre",R.drawable.dgti));
        alumnoList.add(new Alumno("Manuel", "ISC", "5 Semestre",R.drawable.dgti));
        mAdapter.notifyDataSetChanged();
    }
}
