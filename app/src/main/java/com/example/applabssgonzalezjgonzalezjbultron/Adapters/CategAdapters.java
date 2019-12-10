package com.example.applabssgonzalezjgonzalezjbultron.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applabssgonzalezjgonzalezjbultron.Entidades.Categ;
import com.example.applabssgonzalezjgonzalezjbultron.Entidades.Restaurants;
import com.example.applabssgonzalezjgonzalezjbultron.R;

import java.util.ArrayList;
import java.util.List;

public class CategAdapters extends ArrayAdapter<Categ> {

    private List<Categ> opciones = new ArrayList<>();

    public CategAdapters(Context context, List<Categ> datos){
        super(context, R.layout.lst_categ, datos);
        opciones= datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lst_categ, null);

        TextView lblNombre = (TextView)item.findViewById(R.id.lblNomC);
        lblNombre.setText(opciones.get(position).getNombre());


        return(item);
    }
}