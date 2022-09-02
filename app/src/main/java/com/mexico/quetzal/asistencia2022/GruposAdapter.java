package com.mexico.quetzal.asistencia2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.mexico.quetzal.asistencia2022.BaseDatos.Grupos;
import java.util.ArrayList;

public class GruposAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Grupos> grupos;

    public GruposAdapter(Context context, ArrayList grupos) {
        super(context, R.layout.item_grupo, grupos);
        this.context = context;
        this.grupos = grupos;
    }

    @Override
    public View getView(int pos, View conV, ViewGroup par){
        View item = conV;
        GruposHolder holder;

        if(item == null){
            item = LayoutInflater.from(context).inflate(R.layout.item_grupo, null);
            holder = new GruposHolder();
            holder.id = item.findViewById(R.id.id);
            holder.materia = item.findViewById(R.id.materia);
            holder.universidad = item.findViewById(R.id.universidad);
            holder.semestre = item.findViewById(R.id.semestre);
            holder.nivel = item.findViewById(R.id.nivel);
            holder.imagen = item.findViewById(R.id.imagen);
            item.setTag(holder);
        }

        holder = (GruposHolder) item.getTag();
        holder.id.setText(grupos.get(pos).getIdg()+"");
        holder.materia.setText(grupos.get(pos).getMateria());
        holder.universidad.setText(grupos.get(pos).getEscuela());
        holder.semestre.setText(grupos.get(pos).getPeriodo());
        holder.nivel.setText(grupos.get(pos).getNivel());
        holder.imagen.setImageResource(getImagen(grupos.get(pos).getMateria()));
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
