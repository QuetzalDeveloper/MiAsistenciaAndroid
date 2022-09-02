package com.mexico.quetzal.asistencia2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mexico.quetzal.asistencia2022.BaseDatos.Alumnos;
import com.mexico.quetzal.asistencia2022.BaseDatos.Enlace;

public class AlumnosNuevo extends AppCompatActivity {

    private Enlace enlace;
    private Alumnos alumnos;
    private EditText nombre, paterno, materno, correo;
    private Button guardar;
    private String nom, pat, mat, cor;
    private int idg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alumnos_nuevo);
        overridePendingTransition(R.transition.slide_up, R.transition.slide_off);
        getSupportActionBar().hide();

        idg = Integer.parseInt(getIntent().getStringExtra("idg"));

        enlace = Enlace.get(getApplicationContext());
        nombre = findViewById(R.id.nombre);
        paterno = findViewById(R.id.paterno);
        materno = findViewById(R.id.materno);
        correo = findViewById(R.id.correo);
        guardar = findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom = QuitarAcentos(nombre.getText().toString().trim().toUpperCase());
                pat = QuitarAcentos(paterno.getText().toString().trim().toUpperCase());
                mat = QuitarAcentos(materno.getText().toString().trim().toUpperCase());
                cor = correo.getText().toString().trim();
                if(ValidarDatos()){
                    final AlertDialog.Builder d = new AlertDialog.Builder(AlumnosNuevo.this);
                    d.setTitle(getResources().getString(R.string.guardar));
                    d.setMessage(getResources().getString(R.string.gua_alumno));
                    d.setCancelable(false);
                    d.setPositiveButton(getResources().getString(R.string.confirmar), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alumnos = new Alumnos(idg, nom, pat, mat, cor, 0, 0, 0);
                            enlace.addAlumno(alumnos);
                            Toast.makeText(getApplicationContext(), "Alumno agregado", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    d.setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    d.show();
                }
            }
        });

    }

    private String QuitarAcentos(String texto){
        texto = texto.replace("Á", "A");
        texto = texto.replace("É", "E");
        texto = texto.replace("Í", "I");
        texto = texto.replace("Ó", "O");
        texto = texto.replace("Ú", "U");
        return texto;
    }

    private  boolean ValidarDatos(){
        if(nom.isEmpty()) {
            nombre.setError("Ingresa el nombre");
            return false;
        }
        if(pat.isEmpty()) {
            paterno.setError("Ingresa el apellido paterno");
            return false;
        }
        if(mat.isEmpty()) {
            materno.setError("Ingresa el apellido materno");
            return false;
        }
        if(cor.isEmpty()) {
            correo.setError("Ingresa el correo");
            return false;
        }
        return true;
    }
}