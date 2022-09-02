package com.mexico.quetzal.asistencia2022.BaseDatos;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.DeleteTable;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Grupos.class, Alumnos.class, Asistencias.class}, version =2)
public abstract class Instancia extends RoomDatabase {
    public abstract DAOAsistencia getAsisDAO();
    private static final String DATABASE_NAME = "deberes_db";
    private static Instancia INSTANCE;
    private static final int THREADS = 4;
    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    public static Instancia getInstance(final Context context) {

        if (INSTANCE == null) {

            synchronized (Instancia.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Instancia.class, DATABASE_NAME)
                            .fallbackToDestructiveMigrationFrom(1)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;

    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("Create table asistencias ( ids integer primary key autoincrement, idgr integer, idal integer, fecha DATE, parcial integer, estado integer)");
        }
    };
}
