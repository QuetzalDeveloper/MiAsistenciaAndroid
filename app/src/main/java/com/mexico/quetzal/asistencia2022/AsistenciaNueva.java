package com.mexico.quetzal.asistencia2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.mexico.quetzal.asistencia2022.BaseDatos.Asistencias;
import com.mexico.quetzal.asistencia2022.BaseDatos.Enlace;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AsistenciaNueva extends AppCompatActivity {

    private EditText fecha;
    private AppCompatSpinner parcial;
    private Calendar calendar = Calendar.getInstance();
    private TextView paterno, materno, nombre;
    private Button asistencia, falta;
    private int idg,con = 0, tam = 0;
    private boolean control = false;
    private String[] ida, fal, ret, asi;
    private String[] pat, mat, nom;
    private Enlace enlace;
    private Asistencias lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asistencia_nueva);

        idg = Integer.parseInt(getIntent().getStringExtra("idg"));
        pat = getIntent().getStringArrayExtra("paterno");
        mat = getIntent().getStringArrayExtra("materno");
        nom = getIntent().getStringArrayExtra("nombre");
        ida = getIntent().getStringArrayExtra("ida");
        fal = getIntent().getStringArrayExtra("falta");
        ret = getIntent().getStringArrayExtra("retardo");
        asi = getIntent().getStringArrayExtra("asistencia");

        enlace = Enlace.get(getApplicationContext());

        paterno = findViewById(R.id.paterno);
        materno = findViewById(R.id.materno);
        nombre = findViewById(R.id.nombre);
        fecha = findViewById(R.id.fecha);
        parcial = findViewById(R.id.parcial);
        asistencia = findViewById(R.id.asistencia);
        falta = findViewById(R.id.falta);

        tam = pat.length;

        LlenarDatos();

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AsistenciaNueva.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control = VerificarDatos();
                if(control){
                    lista = new Asistencias(idg, Integer.parseInt(ida[con]), fecha.getText().toString(), parcial.getSelectedItemPosition()+1, 0);
                    enlace.addAsistencia(lista);
                    int i = Integer.parseInt(asi[con]) + 1;
                    enlace.asistenciaAsistencia(Integer.parseInt(ida[con]), i);
                    con++;
                    LlenarDatos();
                }
            }
        });

        falta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control = VerificarDatos();
                if(control){
                    lista = new Asistencias(idg, Integer.parseInt(ida[con]), fecha.getText().toString(), parcial.getSelectedItemPosition()+1, 1);
                    enlace.addAsistencia(lista);
                    int i = Integer.parseInt(fal[con]) + 1;
                    enlace.faltaAsistencia(Integer.parseInt(ida[con]), i);
                    con++;
                    LlenarDatos();
                }
            }
        });
    }

    private void LlenarDatos(){
        if(con < tam){
            paterno.setText(pat[con]);
            materno.setText(mat[con]);
            nombre.setText(nom[con]);
        }
        else{
            finish();
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int mes, int dia) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, mes);
            calendar.set(Calendar.DAY_OF_MONTH, dia);
            String formato = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault());
            fecha.setText(sdf.format(calendar.getTime()));
        }
    };

    private boolean VerificarDatos(){
        if(fecha.getText().toString().isEmpty()){
            fecha.setError("Selecciona la fecha");
            return false;
        }
        else{
            return true;
        }
    }
}