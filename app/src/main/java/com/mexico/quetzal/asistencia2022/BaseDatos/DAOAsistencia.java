package com.mexico.quetzal.asistencia2022.BaseDatos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOAsistencia {

    //Insertar
    @Insert
    void addGrupo(Grupos grupos);

    @Insert
    void addAlumnos(Alumnos alumnos);

    @Insert
    void addAsistencia(Asistencias asistencias);

    //Consulta
    @Query("SELECT * FROM grupos")
    List<Grupos> getGrupos();

    @Query("SELECT * FROM alumnos WHERE idgr = :idg")
    List<Alumnos> getAlumnos(int idg);

    @Query("SELECT * FROM asistencias WHERE idal = :idal")
    List<Asistencias> getAsistencias(int idal);

    //Actualizacion
    @Query("UPDATE alumnos SET falta = :falta WHERE ida = :ida")
    void faltaAsistencia(int ida, int falta);

    @Query("UPDATE alumnos SET asistencia = :asistencia WHERE ida = :ida")
    void asistenciaAsistencia(int ida, int asistencia);

    @Query("UPDATE alumnos SET retardo= :retardo WHERE ida= :ida")
    void retardoAsistencia(int ida, int retardo);

    @Query("UPDATE asistencias SET estado= :edo WHERE ids= :ids")
    void cambiarAsistencia(int edo, int ids);

    //Eliminacion
    @Query("DELETE FROM alumnos WHERE ida= :ida")
    void EliminarAlumno(int ida);

    @Query("DELETE FROM asistencias WHERE idal= :ida")
    void eliminarAsistencias(int ida);

    @Query("DELETE FROM grupos WHERE idg= :idg")
    void eliminarGrupo(int idg);

    @Query("DELETE FROM alumnos WHERE idgr= :idg")
    void eliminarGrupoAlumnos(int idg);

    @Query("DELETE FROM asistencias WHERE idgr= :idg")
    void eliminarGrupoAsistencias(int idg);
}
