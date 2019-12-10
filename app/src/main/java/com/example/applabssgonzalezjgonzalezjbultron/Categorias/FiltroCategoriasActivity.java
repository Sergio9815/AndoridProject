package com.example.applabssgonzalezjgonzalezjbultron.Categorias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applabssgonzalezjgonzalezjbultron.Adapters.RestaurantsAdapters;
import com.example.applabssgonzalezjgonzalezjbultron.Entidades.Restaurants;
import com.example.applabssgonzalezjgonzalezjbultron.Helpers.restaurantesHelper;
import com.example.applabssgonzalezjgonzalezjbultron.Login.RegistroActivity;
import com.example.applabssgonzalezjgonzalezjbultron.R;
import com.example.applabssgonzalezjgonzalezjbultron.Restaurantes.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class FiltroCategoriasActivity extends AppCompatActivity {

    ListView lstRest;
    String codiP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_categorias);
        lstRest = (ListView)findViewById(R.id.lstRestau);

        SharedPreferences preCa = getSharedPreferences("MisPreferenciasC", Context.MODE_PRIVATE);
        codiP = preCa.getString("categ","0");

        this.filtrar();
        this.ubicacion();
    }

    private void ubicacion() {
            final Intent z = new Intent(this, RegistroActivity.class);

            lstRest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    String nomRes =((Restaurants) a.getItemAtPosition(position)).getNombre();

                    Ubicacion u = new Ubicacion();
                    u.setUb(nomRes);
                    String n = u.getUb();
                    Uri uri = Uri.parse(n);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });


    }


    private void filtrar() {

        List<Restaurants> opciones = new ArrayList<Restaurants>();
        restaurantesHelper arDB = new restaurantesHelper(this,"restaurantes",null,1);

        SQLiteDatabase db = arDB.getReadableDatabase();

        if (db!= null)
        {

            //OBTINE LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA
            Cursor cursor = db.rawQuery("SELECT * FROM restaurantes WHERE catego like '" + codiP + "'", null);

            if (cursor.moveToFirst()){
                do {
                    Restaurants r = new Restaurants(); //INSERTA LOS DATOS EN LA LISTA

                    r.setImag_Rest(cursor.getInt(0));
                    r.setNombre(cursor.getString(1));
                    r.setHorario(cursor.getString(2));
                    r.setUbicacion(cursor.getString(3));

                    opciones.add(r);
                }while (cursor.moveToNext());
            }
            RestaurantsAdapters adapter = new RestaurantsAdapters(this, opciones);
            lstRest.setAdapter(adapter);
            db.close();
            cursor.close();
        }

    }
}
