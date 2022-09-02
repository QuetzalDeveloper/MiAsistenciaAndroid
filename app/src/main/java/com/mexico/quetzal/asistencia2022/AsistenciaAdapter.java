package com.mexico.quetzal.asistencia2022;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mexico.quetzal.asistencia2022.BaseDatos.Asistencias;

import java.util.ArrayList;

public class AsistenciaAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Asistencias> asistencias;

    public AsistenciaAdapter(Context context, ArrayList asistencias){
        super(context, R.layout.item_datos, asistencias);
        this.context = context;
        this.asistencias = asistencias;
    }

    @Override
    public View getView(int pos, View conV, ViewGroup par){
        View item = conV;
        AsistenciaHolder holder;

        if(item == null){
            item = LayoutInflater.from(context).inflate(R.layout.item_datos, null);
            holder = new AsistenciaHolder();
            holder.fecha = item.findViewById(R.id.fecha);
            holder.estado = item.findViewById(R.id.estado);
            holder.parcial = item.findViewById(R.id.parcial);
            holder.idass = item.findViewById(R.id.idass);
            holder.imagen = item.findViewById(R.id.imagen);
            item.setTag(holder);
        }

        holder = (AsistenciaHolder) item.getTag();
        holder.idass.setText(asistencias.get(pos).getIds()+"");
        holder.fecha.setText(asistencias.get(pos).getFecha());
        int p = asistencias.get(pos).getParcial();
        if(p==1){
            holder.parcial.setText("1");
            holder.parcial.setBackgroundColor(Color.rgb(255,228,225));
        }else if(p==2){
            holder.parcial.setText("2");
            holder.parcial.setBackgroundColor(Color.rgb(255,244,225));
        }else if(p==3){
            holder.parcial.setText("3");
            holder.parcial.setBackgroundColor(Color.rgb(220,249,228));
        }else if(p==4){
            holder.parcial.setText("1");
            holder.parcial.setBackgroundColor(Color.rgb(220,228,249));
        }else{
            holder.parcial.setText("1");
            holder.parcial.setBackgroundColor(Color.rgb(245,220,249));
        }
        int es = asistencias.get(pos).getEstado();
        if(es == 0){
            holder.estado.setText("Asistencia");
            holder.imagen.setImageResource(R.drawable.paloma_verde);
        }else if(es == 1){
            holder.estado.setText("Falta");
            holder.imagen.setImageResource(R.drawable.tache_rojo);
        }else{
            holder.estado.setText("Retardo");
            holder.imagen.setImageResource(R.drawable.reloj);
        }
        return item;
    }
}
