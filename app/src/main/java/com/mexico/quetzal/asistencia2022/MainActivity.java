package com.mexico.quetzal.asistencia2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mexico.quetzal.asistencia2022.BaseDatos.Enlace;
import com.mexico.quetzal.asistencia2022.BaseDatos.Grupos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GruposAdapter adapter;
    private FloatingActionButton ngrupo;
    private ListView lista;
    private Enlace enlace;
    private List<Grupos> consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        enlace = Enlace.get(getApplicationContext());
        ngrupo = findViewById(R.id.add);
        lista = findViewById(R.id.lista);

        LlenarLista();

        ngrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GrupoNuevo.class);
                startActivity(i);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent j = new Intent(getApplicationContext(), AlumnosLista.class);
                j.putExtra("idg", consulta.get(i).getIdg()+"");
                j.putExtra("materia", consulta.get(i).getMateria());
                j.putExtra("escuela", consulta.get(i).getEscuela());
                j.putExtra("semestre", consulta.get(i).getPeriodo());
                j.putExtra("nivel", consulta.get(i).getNivel());
                startActivity(j);
            }
        });

    }

    private void LlenarLista(){
        consulta = enlace.getGrupos();
        adapter = new GruposAdapter(getApplicationContext(), (ArrayList) consulta);
        lista.setAdapter(adapter);
    }

    public void onResume(){
        super.onResume();
        LlenarLista();
        //Toast.makeText(getApplicationContext(), "gupos actualizados", Toast.LENGTH_SHORT).show();
    }


}