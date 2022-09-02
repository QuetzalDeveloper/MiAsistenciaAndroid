package com.mexico.quetzal.asistencia2022.BaseDatos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.mexico.quetzal.asistencia2022.R;

@Entity(tableName = "grupos")
public class Grupos {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idg")
    private int idg;
    @NonNull
    @ColumnInfo(name = "materia")
    private String materia;
    @NonNull
    @ColumnInfo(name = "escuela")
    private String escuela;
    @NonNull
    @ColumnInfo(name = "periodo")
    private String periodo;
    @NonNull
    @ColumnInfo(name = "nivel")
    private String nivel;

    public Grupos(){}

    public Grupos(int idg, @NonNull String materia, @NonNull String escuela, @NonNull String periodo, @NonNull String nivel) {
        this.idg = idg;
        this.materia = materia;
        this.escuela = escuela;
        this.periodo = periodo;
        this.nivel = nivel;
    }

    public Grupos(@NonNull String materia, @NonNull String escuela, @NonNull String periodo, @NonNull String nivel) {
        this.materia = materia;
        this.escuela = escuela;
        this.periodo = periodo;
        this.nivel = nivel;
    }

    public int getIdg() {
        return idg;
    }

    public void setIdg(int idg) {
        this.idg = idg;
    }

    @NonNull
    public String getMateria() {
        return materia;
    }

    public void setMateria(@NonNull String materia) {
        this.materia = materia;
    }

    @NonNull
    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(@NonNull String escuela) {
        this.escuela = escuela;
    }

    @NonNull
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(@NonNull String periodo) {
        this.periodo = periodo;
    }

    @NonNull
    public String getNivel() {
        return nivel;
    }

    public void setNivel(@NonNull String nivel) {
        this.nivel = nivel;
    }
}
