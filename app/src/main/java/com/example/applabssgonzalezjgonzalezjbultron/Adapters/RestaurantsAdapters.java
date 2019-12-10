package com.example.applabssgonzalezjgonzalezjbultron.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applabssgonzalezjgonzalezjbultron.Entidades.Restaurants;
import com.example.applabssgonzalezjgonzalezjbultron.R;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapters extends ArrayAdapter<Restaurants> {

    private List<Restaurants> opciones = new ArrayList<>();

    public RestaurantsAdapters(Context context, List<Restaurants> datos){
        super(context, R.layout.lst_restau, datos);
        opciones= datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lst_restau, null);

        TextView lblNombre = (TextView)item.findViewById(R.id.lblNom);
        lblNombre.setText(opciones.get(position).getNombre());

        /*TextView lblId = (TextView)item.findViewById(R.id.lblDistancia);
        lblId.setText("Distancia: "+opciones.get(position).getDistancia());*/

        TextView lblHora = (TextView)item.findViewById(R.id.lblHorario);
        lblHora.setText("Horario: "+opciones.get(position).getHorario());

        TextView lblUbicacion = (TextView)item.findViewById(R.id.lblUbi);
        lblUbicacion.setText("Ubicación: "+opciones.get(position).getUbicacion());

        ImageView ImagRes = (ImageView)item.findViewById(R.id.imgRestaurante);
        if(opciones.get(position).getImag_Rest() == 1) {
            ImagRes.setImageResource(R.drawable.kfc);
        }
        else if(opciones.get(position).getImag_Rest() == 2) {
            ImagRes.setImageResource(R.drawable.subway);
        }
        else if(opciones.get(position).getImag_Rest() == 3) {
            ImagRes.setImageResource(R.drawable.burger);
        }
        else if(opciones.get(position).getImag_Rest() == 4) {
            ImagRes.setImageResource(R.drawable.mcd);
        }
        else if(opciones.get(position).getImag_Rest() == 5) {
            ImagRes.setImageResource(R.drawable.domino);
        }
        else if(opciones.get(position).getImag_Rest() == 6) {
            ImagRes.setImageResource(R.drawable.star);
        }
        else if(opciones.get(position).getImag_Rest() == 7) {
            ImagRes.setImageResource(R.drawable.hut);
        }
        else if(opciones.get(position).getImag_Rest() == 8) {
            ImagRes.setImageResource(R.drawable.donuts);
        }
	else if(opciones.get(position).getImag_Rest() == 9) {
            ImagRes.setImageResource(R.drawable.helados);
        }

        return(item);
    }
}
