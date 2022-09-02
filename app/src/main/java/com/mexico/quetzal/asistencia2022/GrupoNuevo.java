package com.mexico.quetzal.asistencia2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mexico.quetzal.asistencia2022.BaseDatos.Enlace;
import com.mexico.quetzal.asistencia2022.BaseDatos.Grupos;

public class GrupoNuevo extends AppCompatActivity {

    private EditText materia, universidad, semestre;
    private Spinner nivel;
    private Button guardar;
    private boolean control = false;
    private String mat, uni, sem, niv;
    private Enlace enlace;
    private Grupos grupos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grupo_nuevo);
        overridePendingTransition(R.transition.slide_up, R.transition.slide_off);
        getSupportActionBar().hide();

        materia = findViewById(R.id.materia);
        universidad = findViewById(R.id.universidad);
        semestre = findViewById(R.id.semestre);
        nivel = findViewById(R.id.nivel);
        guardar = findViewById(R.id.guardar);
        enlace = Enlace.get(getApplicationContext());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mat = materia.getText().toString().toUpperCase();
                uni = universidad.getText().toString().toUpperCase();
                sem = semestre.getText().toString().toUpperCase();
                niv = nivel.getSelectedItem().toString().toUpperCase();
                if(VerificarCampos()){
                    //Guardar
                    grupos = new Grupos(mat,uni,sem,niv);
                    enlace.addGrupo(grupos);
                    Toast.makeText(getApplicationContext(), "Grupo agregado satisfactoriamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private boolean VerificarCampos(){
        if(mat.isEmpty()){
            materia.setError("Indica la materia");
            return false;
        }
        if(uni.isEmpty()){
            universidad.setError("Indica la universidad");
            return false;
        }
        if(sem.isEmpty()){
            semestre.setError("Indica el semestre");
            return false;
        }
        return true;
    }
}