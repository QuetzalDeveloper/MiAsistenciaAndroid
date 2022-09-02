package com.mexico.quetzal.asistencia2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.mexico.quetzal.asistencia2022.BaseDatos.Alumnos;
import java.util.ArrayList;

public class AlumnosAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Alumnos> alumnos;

    public AlumnosAdapter(Context context, ArrayList alumnos) {
        super(context, R.layout.item_alumno, alumnos);
        this.context = context;
        this.alumnos = alumnos;
    }

    @Override
    public View getView(int pos, View conV, ViewGroup par){
        View item = conV;
        AlumnosHolder holder;

        if(item == null){
            item = LayoutInflater.from(context).inflate(R.layout.item_alumno, null);
            holder = new AlumnosHolder();
            holder.idA = item.findViewById(R.id.idalumno);
            holder.correo = item.findViewById(R.id.correo);
            holder.nombre = item.findViewById(R.id.nombre);
            holder.faltas = item.findViewById(R.id.faltas);
            holder.retardos = item.findViewById(R.id.retardo);
            holder.imagen = item.findViewById(R.id.imagen);
            item.setTag(holder);
        }

        holder = (AlumnosHolder) item.getTag();
        holder.idA.setText(alumnos.get(pos).getIda()+"");
        holder.correo.setText(alumnos.get(pos).getCorreo());
        holder.nombre.setText(alumnos.get(pos).getPaterno() + " " + alumnos.get(pos).getMaterno() +" "+ alumnos.get(pos).getNombre());
        holder.faltas.setText(alumnos.get(pos).getFalta()+"");
        holder.retardos.setText(alumnos.get(pos).getRetardo()+"");
        holder.imagen.setImageResource(getImagen(alumnos.get(pos).getPaterno()));
        return item;
    }

    private int getImagen(String texto){
        String a = texto.toLowerCase().charAt(0) + "";
        int x;
        switch (a){
            case "a": x = R.drawable.a; break;
            case "b": x = R.drawable.b; break;
            case "c": x = R.drawable.c; break;
            case "d": x = R.drawable.d; break;
            case "e": x = R.drawable.e; break;
            case "f": x = R.drawable.f; break;
            case "g": x = R.drawable.g; break;
            case "h": x = R.drawable.h; break;
            case "i": x = R.drawable.i; break;
            case "j": x = R.drawable.j; break;
            case "k": x = R.drawable.k; break;
            case "l": x = R.drawable.l; break;
            case "m": x = R.drawable.m; break;
            case "n": x = R.drawable.n; break;
            case "o": x = R.drawable.o; break;
            case "p": x = R.drawable.p; break;
            case "q": x = R.drawable.q; break;
            case "r": x = R.drawable.r; break;
            case "s": x = R.drawable.s; break;
            case "t": x = R.drawable.t; break;
            case "u": x = R.drawable.u; break;
            case "v": x = R.drawable.v; break;
            case "w": x = R.drawable.w; break;
            case "x": x = R.drawable.x; break;
            case "y": x = R.drawable.y; break;
            case "z": x = R.drawable.z; break;
            default: x = R.drawable.ene; break;

        }
        return x;
    }

}
