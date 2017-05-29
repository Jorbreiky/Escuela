package com.jje.programacion.escuela.utilerias;

/**
 * Created by jorgemanon on 5/25/17.
 */

public class Alumno {

    private String nombre;
    private String carrera;
    private String semestre;
    private int foto;

    public Alumno(){

    }

    public Alumno(String nombre,String carrera,String semestre,int foto){
        this.nombre = nombre;
        this.carrera = carrera;
        this.semestre = semestre;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
