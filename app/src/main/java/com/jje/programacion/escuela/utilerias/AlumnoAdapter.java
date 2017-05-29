package com.jje.programacion.escuela.utilerias;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jje.programacion.escuela.R;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.MyViewHolder> {

    private List<Alumno> alumnoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvnombre, tvcarrera, tvsemestre;
        public ImageView ivfoto;

        public MyViewHolder(View view) {
            super(view);
            tvnombre = (TextView) view.findViewById(R.id.tvNombre);
            tvcarrera = (TextView) view.findViewById(R.id.tvCarrera);
            tvsemestre = (TextView) view.findViewById(R.id.tvSemestre);
            ivfoto = (ImageView) view.findViewById(R.id.ivfoto);
        }
    }


    public AlumnoAdapter(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemalumno, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Alumno alumno = alumnoList.get(position);
        holder.tvnombre.setText("Nombre: "+alumno.getNombre());
        holder.tvcarrera.setText("Carrera: "+alumno.getCarrera());
        holder.tvsemestre.setText("Semestre: "+alumno.getSemestre());
        holder.ivfoto.setImageResource(alumno.getFoto());
    }

    @Override
    public int getItemCount() {
        return alumnoList.size();
    }
}
