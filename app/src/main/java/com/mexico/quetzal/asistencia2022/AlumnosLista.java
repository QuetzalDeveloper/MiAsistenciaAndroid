package com.mexico.quetzal.asistencia2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mexico.quetzal.asistencia2022.BaseDatos.Alumnos;
import com.mexico.quetzal.asistencia2022.BaseDatos.Enlace;

import org.apache.poi.hssf.usermodel.HSSFAnchor;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class AlumnosLista extends AppCompatActivity {

    private AlumnosAdapter adapter;
    private FloatingActionButton nalumno;
    private ListView lista;
    private Enlace enlace;
    private List<Alumnos> consulta;
    private int idg;
    private BottomAppBar bar;
    private String materia, escuela, semestre, nivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alumnos_lista);
        getSupportActionBar().hide();

        idg = Integer.parseInt(getIntent().getStringExtra("idg"));
        materia = getIntent().getStringExtra("materia");
        escuela = getIntent().getStringExtra("escuela");
        semestre = getIntent().getStringExtra("semestre");
        nivel = getIntent().getStringExtra("nivel");

        enlace = Enlace.get(getApplicationContext());
        bar = findViewById(R.id.baralumnoa);
        enlace = Enlace.get(getApplicationContext());
        nalumno = findViewById(R.id.add);
        lista = findViewById(R.id.lista);

        LlenarLista();

        nalumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "grupo " +idg, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), AlumnosNuevo.class);
                i.putExtra("idg", idg+"");
                startActivity(i);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent j = new Intent(getApplicationContext(), AlumnosDatos.class);
                j.putExtra("ida", consulta.get(i).getIda()+"");
                j.putExtra("nombre", consulta.get(i).getPaterno()+ " "+ consulta.get(i).getMaterno()+" "+consulta.get(i).getNombre());
                j.putExtra("asistencia", consulta.get(i).getAsistencia()+"");
                j.putExtra("falta", consulta.get(i).getFalta()+"");
                j.putExtra("retardo", consulta.get(i).getRetardo()+"");
                startActivity(j);
            }
        });

        bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.asistencia :
                        Asistencia();
                        return true;
                    case R.id.eliminar:
                        EliminarGrupo();
                        return true;
                    case R.id.exportar:
                        ExportarExcel();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void LlenarLista(){
        consulta = enlace.getAlumnos(idg);
        adapter = new AlumnosAdapter(getApplicationContext(), (ArrayList) consulta);
        lista.setAdapter(adapter);
    }

    public void onResume(){
        super.onResume();
        LlenarLista();
        //Toast.makeText(getApplicationContext(), "Alumnos actualizados", Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.itembar_alumnos, menu);
        return true;
    }

    private boolean ExportarExcel(){

        if (ActivityCompat.checkSelfPermission(AlumnosLista.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) ;
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            return false;
        }
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Toast.makeText(getApplicationContext(), "Otorga el permiso para guardar el archivo", Toast.LENGTH_SHORT).show();
            return false;
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(materia);
        Cell cell =null;

        Row row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue(escuela);
        cell = row.createCell(1);
        cell.setCellValue(materia);
        cell = row.createCell(2);
        cell.setCellValue(semestre);
        cell = row.createCell(3);
        cell.setCellValue(nivel);


        row = sheet.createRow(2);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

        cell = row.createCell(0);
        cell.setCellValue("Nombre");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue("Correo");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue("Asistencia");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue("Falta");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue("Retardos");
        cell.setCellStyle(cellStyle);

        for(int i=0; i<consulta.size(); i++){
            row = sheet.createRow(i+3);

            cell = row.createCell(0);
            cell.setCellValue(consulta.get(i).getPaterno()+" "+consulta.get(i).getMaterno()+" "+consulta.get(i).getPaterno());

            cell = row.createCell(1);
            cell.setCellValue(consulta.get(i).getCorreo());

            cell = row.createCell(2);
            cell.setCellValue(consulta.get(i).getAsistencia()+"");

            cell = row.createCell(3);
            cell.setCellValue(consulta.get(i).getFalta()+"");

            cell = row.createCell(4);
            cell.setCellValue(consulta.get(i).getRetardo()+"");
        }
        String nombre = materia+" "+FechaActual()+".xls";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),nombre);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error al escribir el archivo // "+e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("Escribir", e.getMessage());
            return false;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error al guardar el archivo // "+e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //Abrir archivo
        Uri path = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/"+nombre);
        Log.e("Ruta", path.toString());
        Intent excelIntent = new Intent(Intent.ACTION_VIEW);
        excelIntent.setDataAndType(path , "application/vnd.ms-excel");
        excelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            startActivity(excelIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("Excel", e.getMessage());
        }

        Toast.makeText(getApplicationContext(), "Archivo guardado en la carpeta Documentos", Toast.LENGTH_LONG).show();
        return true;
    }

    private void Asistencia(){
        int tam = consulta.size();
        String[] ida = new String[tam];
        String[] paterno = new String[tam];
        String[] materno = new String[tam];
        String[] nombre = new String[tam];
        String[] falta = new String[tam];
        String[] retardo = new String[tam];
        String[] asistencia = new String[tam];

        final AlertDialog.Builder d = new AlertDialog.Builder(AlumnosLista.this);
        Toast.makeText(getApplicationContext(), idg+"", Toast.LENGTH_SHORT).show();
        d.setTitle(getResources().getString(R.string.Asistencia));
        d.setMessage(getResources().getString(R.string.pre_asistencia));
        d.setCancelable(false);
        d.setPositiveButton(getResources().getString(R.string.confirmar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i=0 ;  i < tam; i++){
                    ida[i] = consulta.get(i).getIda()+"";
                    paterno[i] = consulta.get(i).getPaterno();
                    materno[i] = consulta.get(i).getMaterno();
                    nombre[i] = consulta.get(i).getNombre();
                    falta[i] = consulta.get(i).getFalta()+"";
                    retardo[i] = consulta.get(i).getRetardo()+"";
                    asistencia[i] = consulta.get(i).getAsistencia()+"";
                }
                Intent i = new Intent(getApplicationContext(), AsistenciaNueva.class);
                i.putExtra("idg", "0"+idg);
                i.putExtra("ida", ida);
                i.putExtra("paterno", paterno);
                i.putExtra("materno", materno);
                i.putExtra("nombre", nombre);
                i.putExtra("falta", falta);
                i.putExtra("retardo", retardo);
                i.putExtra("asistencia", asistencia);
                startActivity(i);
            }
        });
        d.setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        d.show();
    }

    private void EliminarGrupo(){
        final AlertDialog.Builder d = new AlertDialog.Builder(AlumnosLista.this);
        d.setTitle(getResources().getString(R.string.eliminar));
        d.setMessage(getResources().getString(R.string.eli_grupo));
        d.setCancelable(false);
        d.setPositiveButton(getResources().getString(R.string.confirmar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enlace.eliminarGrupo(idg);
                enlace.eliminarGrupoAlumnos(idg);
                enlace.eliminarGrupoAsistencias(idg);
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

    private static boolean isExternalStorageReadOnly() {
        String externalStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalStorageState);
    }

    private static boolean isExternalStorageAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(externalStorageState);
    }

    public static String FechaActual() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        return sdf.format(date);
    }
}