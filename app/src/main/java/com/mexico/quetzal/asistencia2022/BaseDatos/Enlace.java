package com.mexico.quetzal.asistencia2022.BaseDatos;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class Enlace {
    @SuppressLint("StaticFieldLeak")
    private static Enlace enlace;
    private DAOAsistencia daoAsistencia;

    private Enlace(Context context){
        Context app = context.getApplicationContext();
        Instancia instancia = Room.databaseBuilder(app, Instancia.class, "asistencia2022")
                .allowMainThreadQueries()
                .build();
        daoAsistencia = instancia.getAsisDAO();
    }

    public static Enlace get(Context context){
        if(enlace == null){
            enlace = new Enlace(context);
        }
        return enlace;
    }

    //Insert
    public void addGrupo(Grupos grupos){
        daoAsistencia.addGrupo(grupos);
    }

    public void addAlumno(Alumnos alumnos) { daoAsistencia.addAlumnos(alumnos);}

    public void addAsistencia(Asistencias asistencias){
        daoAsistencia.addAsistencia(asistencias);
    }

    //Query
    public List<Grupos> getGrupos(){
        return daoAsistencia.getGrupos();
    }

    public List<Alumnos> getAlumnos(int idg) { return daoAsistencia.getAlumnos(idg); }

    public List<Asistencias> getAsistencias(int idal){ return daoAsistencia.getAsistencias(idal); }

    //Update
    public void faltaAsistencia(int ida, int falta) { daoAsistencia.faltaAsistencia(ida, falta); }

    public void asistenciaAsistencia(int ida, int asistencia) { daoAsistencia.asistenciaAsistencia(ida, asistencia); }

    public void retardoAsistencia(int ida, int retardo) { daoAsistencia.retardoAsistencia(ida, retardo); }

    public void cambiarAsistencia(int edo, int ids) { daoAsistencia.cambiarAsistencia(edo, ids); }

    //Eliminar
    public void eliminarAlumno(int ida) { daoAsistencia.EliminarAlumno(ida); }

    public void eliminarAsistencias(int ida) { daoAsistencia.eliminarAsistencias(ida); }

    public void eliminarGrupo(int idg) { daoAsistencia.eliminarGrupo(idg); }

    public void eliminarGrupoAlumnos(int idg) { daoAsistencia.eliminarGrupoAlumnos(idg); }

    public void eliminarGrupoAsistencias(int idg) { daoAsistencia.eliminarGrupoAsistencias(idg);}
}
