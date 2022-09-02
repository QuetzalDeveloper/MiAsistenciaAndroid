package com.mexico.quetzal.asistencia2022.BaseDatos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "asistencias")
public class Asistencias {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ids")
    private int ids;
    @NonNull
    @ColumnInfo(name = "idgr")
    private int idgr;
    @NonNull
    @ColumnInfo(name = "idal")
    private int idal;
    @NonNull
    @ColumnInfo(name = "fecha")
    private String fecha;
    @NonNull
    @ColumnInfo(name = "parcial")
    private int parcial;
    @NonNull
    @ColumnInfo(name = "estado")
    private int estado;

    public Asistencias(){}

    public Asistencias(int ids, int idgr, int idal, @NonNull String fecha, int parcial, int estado) {
        this.ids = ids;
        this.idgr = idgr;
        this.idal = idal;
        this.fecha = fecha;
        this.parcial = parcial;
        this.estado = estado;
    }

    public Asistencias(int idgr, int idal, @NonNull String fecha, int parcial, int estado) {
        this.idgr = idgr;
        this.idal = idal;
        this.fecha = fecha;
        this.parcial = parcial;
        this.estado = estado;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getIdgr() {
        return idgr;
    }

    public void setIdgr(int idgr) {
        this.idgr = idgr;
    }

    public int getIdal() {
        return idal;
    }

    public void setIdal(int idal) {
        this.idal = idal;
    }

    @NonNull
    public String getFecha() {
        return fecha;
    }

    public void setFecha(@NonNull String fecha) {
        this.fecha = fecha;
    }

    public int getParcial() {
        return parcial;
    }

    public void setParcial(int parcial) {
        this.parcial = parcial;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
