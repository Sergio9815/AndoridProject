package com.example.applabssgonzalezjgonzalezjbultron.Restaurantes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applabssgonzalezjgonzalezjbultron.Adapters.RestaurantsAdapters;
import com.example.applabssgonzalezjgonzalezjbultron.Categorias.CategoriasActivity;
import com.example.applabssgonzalezjgonzalezjbultron.Entidades.Restaurants;
import com.example.applabssgonzalezjgonzalezjbultron.Helpers.restaurantesHelper;
import com.example.applabssgonzalezjgonzalezjbultron.Login.MainActivity;
import com.example.applabssgonzalezjgonzalezjbultron.Login.RegistroActivity;
import com.example.applabssgonzalezjgonzalezjbultron.R;

import java.util.ArrayList;
import java.util.List;

public class RestaurantesActivity extends AppCompatActivity {

    int maxID;
    ListView lstRest;
    EditText ediCodigo;
    Button search;
    String perfil, perfil2, tipoP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantes);

        search = (Button)findViewById(R.id.btnBuscar);
        ediCodigo = (EditText)findViewById(R.id.EtxtValor);
        lstRest = (ListView)findViewById(R.id.lstRestau);

        this.insertarDatos();
        this.barraDeMenu();
        this.LoadListViewTemplate();
        this.lista();

        search.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               buscar();
           }
        });

    }

    private void buscar() {

        String codiP = ediCodigo.getText().toString();
        codiP = codiP + "%";
        List<Restaurants> opciones = new ArrayList<Restaurants>();
        restaurantesHelper arDB = new restaurantesHelper(this,"restaurantes",null,1);

        SQLiteDatabase db = arDB.getReadableDatabase();

        if (db!= null)
        {

            //OBTINE LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA
            Cursor cursor = db.rawQuery("SELECT * FROM restaurantes WHERE nombre like '" + codiP + "'", null);

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

    private void insertarDatos() {

        restaurantesHelper arDB = new restaurantesHelper(this,"restaurantes",null,1);

        SQLiteDatabase db = arDB.getReadableDatabase();

        if (db != null) {

            try {

                db.execSQL("INSERT INTO restaurantes (id, nombre, horario, ubicacion, catego) " + "VALUES ('" + 1 + "', '" + "KFC" + "', '" + "10:00 - 21:00" + "' , '" + "Centennial Plaza" + "' , '" + "Comida Rápida" + "')");
                db.execSQL("INSERT INTO restaurantes (id, nombre, horario, ubicacion, catego) " + "VALUES ('" + 2 + "', '" + "Subway" + "', '" + "6:00 - 22:00" + "' , '" + "Camino de cruces" + "' , '" + "Emparedados" + "')");
                db.execSQL("INSERT INTO restaurantes (id, nombre, horario, ubicacion, catego) " + "VALUES ('" + 3 + "', '" + "Burger King" + "', '" + "10:00 - 22:00" + "' , '" + "El Dorado" + "' , '" + "Comida Rápida" + "')");
                db.execSQL("INSERT INTO restaurantes (id, nombre, horario, ubicacion, catego) " + "VALUES ('" + 4 + "', '" + "McDonalds" + "', '" + "6:30 - 23:00" + "' , '" + "El Dorado" + "' , '" + "Comida Rápida" + "')");
                db.execSQL("INSERT INTO restaurantes (id, nombre, horario, ubicacion, catego) " + "VALUES ('" + 5 + "', '" + "Dominos Pizza" + "', '" + "10:00 - 22:00" + "' , '" + "Altaplaza Mall" + "' , '" + "Pizza" + "')");
                db.execSQL("INSERT INTO restaurantes (id, nombre, horario, ubicacion, catego) " + "VALUES ('" + 6 + "', '" + "Starbucks" + "', '" + "6:00 - 22:00" + "' , '" + "Altaplaza Mall" + "' , '" + "Postres" + "')");
                db.execSQL("INSERT INTO restaurantes (id, nombre, horario, ubicacion, catego) " + "VALUES ('" + 7 + "', '" + "Pizza Hut" + "', '" + "11:00 - 22:30" + "' , '" + "El Dorado" + "' , '" + "Pizza" + "')");
                db.execSQL("INSERT INTO restaurantes (id, nombre, horario, ubicacion, catego) " + "VALUES ('" + 8 + "', '" + "Dunkin Donuts" + "', '" + "9:00 - 20:30" + "' , '" + "Albrook Mall" + "' , '" + "Postres" + "')");
		        db.execSQL("INSERT INTO restaurantes (id, nombre, horario, ubicacion, catego) " + "VALUES ('" + 9 + "', '" + "Helados Keenes" + "', '" + "14:00 - 22:00" + "' , '" + "Calle 67 Este, Panamá" + "' , '" + "Helados" + "')");

            } catch (Exception e) {
                //Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
            } finally {
                db.close();
            }

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.LoadListViewTemplate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.LoadListViewTemplate();
    }

    private void lista() {
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



    private void LoadListViewTemplate() {
        List<Restaurants> opciones = this.ObtenerDatos();
        RestaurantsAdapters adapter = new RestaurantsAdapters(this, opciones);
        lstRest.setAdapter(adapter);
    }

    private List<Restaurants> ObtenerDatos(){

        List<Restaurants> lista = new ArrayList<Restaurants>();

        try{
            restaurantesHelper arDB = new restaurantesHelper(this,"restaurantes",null,1);

            SQLiteDatabase db = arDB.getReadableDatabase();

            if (db!= null)
            {

                Cursor cursor = db.rawQuery("SELECT * FROM restaurantes", null);

                if (cursor.moveToFirst()){
                    do {
                        Restaurants r = new Restaurants(); //INSERTA LOS DATOS EN LA LISTA

                        r.setImag_Rest(cursor.getInt(0));
                        r.setNombre(cursor.getString(1));
                        r.setHorario(cursor.getString(2));
                        r.setUbicacion(cursor.getString(3));
                        r.setCat(cursor.getString(4));

                        lista.add(r);

                    }while (cursor.moveToNext());
                }
                db.close();
                cursor.close();
            }
        }
        catch (Exception e){
            Toast.makeText(this,"Error -> " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

        return lista;
    }

    //Metodos para mostrar y ocultar nuestro menú
    private void barraDeMenu() {
        //Obtener perfil con sesion iniciada
        SharedPreferences prePerfil = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        perfil = prePerfil.getString("perfil","Invitado");
        perfil2 = prePerfil.getString("perfil2","Invitado");
        tipoP = prePerfil.getString("tipoP","Invitado");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(perfil + " " + perfil2);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//ACCIONES DE LOS BOTONES DE LA BARRA DE MENU
        switch (item.getItemId()){
            case R.id.categ:
                Log.i("ActionBar","Categorías");
                Intent j = new Intent(this, CategoriasActivity.class);
                startActivity(j);
                break;
            case R.id.cerSesion:
                Log.i("ActionBar","Cerrar");
                SharedPreferences preSesion = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preSesion.edit();
                editor.putString("sesion", "0");
                editor.commit();
                Intent z = new Intent(this, MainActivity.class);
                startActivity(z);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
