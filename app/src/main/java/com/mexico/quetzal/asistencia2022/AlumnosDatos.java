package com.mexico.quetzal.asistencia2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.mexico.quetzal.asistencia2022.BaseDatos.Asistencias;
import com.mexico.quetzal.asistencia2022.BaseDatos.Enlace;

import java.util.ArrayList;
import java.util.List;

public class AlumnosDatos extends AppCompatActivity {

    private int ida, asi, fal, ret, idas, edo;
    private String nom;
    private TextView nombre, asistencia, falta, retardo;
    private ListView lista;
    private List<Asistencias> consulta;
    private Enlace enlace;
    private AsistenciaAdapter adapter;
    private BottomAppBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alumno_datos);
        getSupportActionBar().hide();

        ida = Integer.parseInt(getIntent().getStringExtra("ida"));
        nom = getIntent().getStringExtra("nombre");
        asi = Integer.parseInt(getIntent().getStringExtra("asistencia"));
        fal = Integer.parseInt(getIntent().getStringExtra("falta"));
        ret = Integer.parseInt(getIntent().getStringExtra("retardo"));

        enlace = Enlace.get(getApplicationContext());
        nombre = findViewById(R.id.nombre);
        asistencia = findViewById(R.id.asistencia);
        falta = findViewById(R.id.falta);
        retardo = findViewById(R.id.retardo);
        lista = findViewById(R.id.lista);
        bar = findViewById(R.id.bar2);

        nombre.setText(nom);
        asistencia.setText(asi+"");
        falta.setText(fal+"");
        retardo.setText(ret+"");

        LlenarLista();

        registerForContextMenu(lista);

        bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.eliminar:
                        EliminarAlumno();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void LlenarLista(){
        consulta = enlace.getAsistencias(ida);
        adapter = new AsistenciaAdapter(getApplicationContext(), (ArrayList) consulta);
        lista.setAdapter(adapter);
    }

    private void ActualizarConteo(){
        asistencia.setText(asi+"");
        falta.setText(fal+"");
        retardo.setText(ret+"");
    }

    private void EliminarAlumno(){
        final AlertDialog.Builder d = new AlertDialog.Builder(AlumnosDatos.this);
        d.setTitle(getResources().getString(R.string.eliminar));
        d.setMessage(getResources().getString(R.string.eli_alumno));
        d.setCancelable(false);
        d.setPositiveButton(getResources().getString(R.string.confirmar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enlace.eliminarAlumno(ida);
                enlace.eliminarAsistencias(ida);
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

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.lista){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            idas = Integer.parseInt(((TextView) info.targetView.findViewById(R.id.idass)).getText().toString());
            if(((TextView) info.targetView.findViewById(R.id.estado)).getText().toString().equals("Asistencia")){
                edo = 0;
            }
            else if(((TextView) info.targetView.findViewById(R.id.estado)).getText().toString().equals("Falta")){
                edo = 1;
            }
            else{
                edo = 2;
            }
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menulista_asistencia, menu);
        }
    }

    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.asistencia:
                CambiarAsistencia();
                return true;
            case R.id.retardo:
                CambiarRetardo();
                return true;
            case R.id.falta:
                CambiarFalta();
                return true;
            default:
                return  super.onContextItemSelected(item);
        }
    }

    private void CambiarAsistencia(){
        if(edo == 1) { //Falta
            fal --;
            asi++;
            enlace.asistenciaAsistencia(ida, asi);
            enlace.faltaAsistencia(ida, fal);
            enlace.cambiarAsistencia(0,idas);
        }else if(edo == 2) { //Retardo
            ret--;
            asi ++;
            enlace.asistenciaAsistencia(ida, asi);
            enlace.retardoAsistencia(ida, ret);
            enlace.cambiarAsistencia(0, idas);
        }else{
            Toast.makeText(getApplicationContext(), "Sin cambio", Toast.LENGTH_SHORT);
        }
        LlenarLista();
        ActualizarConteo();
    }

    private void CambiarFalta(){
        if(edo == 0) {  //Asistencia
            asi--;
            fal++;
            enlace.asistenciaAsistencia(ida, asi);
            enlace.faltaAsistencia(ida, fal);
            enlace.cambiarAsistencia(1, idas);
        }else if(edo == 2) {    //Retardo
            ret--;
            fal++;
            enlace.retardoAsistencia(ida, ret);
            enlace.faltaAsistencia(ida, fal);
            enlace.cambiarAsistencia(1, idas);
        }else{
            Toast.makeText(getApplicationContext(), "Sin cambio", Toast.LENGTH_SHORT);
        }
        LlenarLista();
        ActualizarConteo();
    }

    private void CambiarRetardo(){
        if(edo == 0) {  //Asistencia
            asi--;
            ret++;
            enlace.asistenciaAsistencia(ida, asi);
            enlace.retardoAsistencia(ida, ret);
            enlace.cambiarAsistencia(2, idas);
        }else if(edo == 1                            ) {    //Falta
            fal--;
            ret++;
            enlace.retardoAsistencia(ida, ret);
            enlace.faltaAsistencia(ida, fal);
            enlace.cambiarAsistencia(2, idas);
        }else{
            Toast.makeText(getApplicationContext(), "Sin cambio", Toast.LENGTH_SHORT);
        }
        LlenarLista();
        ActualizarConteo();
    }

}