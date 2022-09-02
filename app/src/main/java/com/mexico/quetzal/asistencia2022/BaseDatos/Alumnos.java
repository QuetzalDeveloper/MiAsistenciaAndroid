package com.mexico.quetzal.asistencia2022.BaseDatos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alumnos")
public class Alumnos {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ida")
    private int ida;
    @NonNull
    @ColumnInfo(name = "idgr")
    private int idgr;
    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;
    @NonNull
    @ColumnInfo(name = "paterno")
    private String paterno;
    @NonNull
    @ColumnInfo(name = "materno")
    private String materno;
    @NonNull
    @ColumnInfo(name = "correo")
    private String correo;
    @NonNull
    @ColumnInfo(name = "falta")
    private int falta;
    @NonNull
    @ColumnInfo(name = "retardo")
    private int retardo;
    @NonNull
    @ColumnInfo(name = "asistencia")
    private int asistencia;

    public Alumnos(){}

    public Alumnos(int idgr, @NonNull String nombre, @NonNull String paterno, @NonNull String materno, @NonNull String correo, int falta, int retardo, int asistencia) {
        this.idgr = idgr;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.correo = correo;
        this.falta = falta;
        this.retardo = retardo;
        this.asistencia = asistencia;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public int getIdgr() {
        return idgr;
    }

    public void setIdgr(int idgr) {
        this.idgr = idgr;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(@NonNull String paterno) {
        this.paterno = paterno;
    }

    @NonNull
    public String getMaterno() {
        return materno;
    }

    public void setMaterno(@NonNull String materno) {
        this.materno = materno;
    }

    @NonNull
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(@NonNull String correo) {
        this.correo = correo;
    }

    public int getFalta() {
        return falta;
    }

    public void setFalta(int falta) {
        this.falta = falta;
    }

    public int getRetardo() {
        return retardo;
    }

    public void setRetardo(int retardo) {
        this.retardo = retardo;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }
}
