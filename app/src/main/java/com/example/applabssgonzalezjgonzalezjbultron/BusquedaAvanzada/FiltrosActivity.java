package com.example.applabssgonzalezjgonzalezjbultron.BusquedaAvanzada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.applabssgonzalezjgonzalezjbultron.Adapters.RestaurantsAdapters;
import com.example.applabssgonzalezjgonzalezjbultron.Entidades.Restaurants;
import com.example.applabssgonzalezjgonzalezjbultron.Helpers.restaurantesHelper;
import com.example.applabssgonzalezjgonzalezjbultron.Login.RegistroActivity;
import com.example.applabssgonzalezjgonzalezjbultron.R;
import com.example.applabssgonzalezjgonzalezjbultron.Restaurantes.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class FiltrosActivity extends AppCompatActivity {

    ListView lstRestaurantes;
    String perfil, perfil2, tipoP, opcionSeleccionada;
    Button btnBuscar;
    EditText EditexValor;
    Spinner spnOpcion;
    Integer i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);
        this.Inicializar_Controles();
        this.Cargar_Spinner();
        this.LoadListViewTemplate();
        this.barraDeMenu();
        this.registerForContextMenu(lstRestaurantes);
        this.Ubicacion();
        spnOpcion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(adapterView.getContext(),  "Seleccionado "+adapterView.getItemAtPosition(i), Toast.LENGTH_LONG).show();
                String seleccion = adapterView.getItemAtPosition(i).toString();
                i++;
                if(seleccion.compareTo("Seleccione el tipo de filtrado") != 0)
                {
                    Toast.makeText(adapterView.getContext(), "Filtrando por!"+ seleccion, Toast.LENGTH_LONG).show();
                    LoadListViewTemplate();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                LoadListViewTemplatefind();
            }
        });
        lstRestaurantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id) {
                opcionSeleccionada = ((Restaurants)a.getItemAtPosition(position)).getNombre();
                Toast.makeText(FiltrosActivity.this,"Opción seleccionada: " + opcionSeleccionada, Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }


    private void Ubicacion() {
        final Intent z = new Intent(this, RegistroActivity.class);

        lstRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    private void Inicializar_Controles(){
        spnOpcion = (Spinner)findViewById(R.id.spnEs);
        lstRestaurantes = (ListView)findViewById(R.id.lst_comprar_articulos);
        btnBuscar = (Button)findViewById(R.id.btnbuscar);
        EditexValor = (EditText)findViewById(R.id.editexValor);

    }

    private void Cargar_Spinner(){
        List<String> Tipo_filtrado = new ArrayList<>();
        Tipo_filtrado.add("Seleccione el tipo de filtrado");
        Tipo_filtrado.add("Nombre");
        Tipo_filtrado.add("Horario");
        Tipo_filtrado.add("Ubicación");

        ArrayAdapter<String> adapterList = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Tipo_filtrado);
        spnOpcion.setAdapter(adapterList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual_listview, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.action_add_item:
                //metodo que añade resturante a lista de articulos favoritos
                //Es decir se agrega otra tabla
                return true;
            case R.id.action_info_item:
                Toast.makeText(this,"Información", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void btn(View view){
        Toast.makeText(getApplicationContext(),"Filtrando",Toast.LENGTH_SHORT).show();
        this.LoadListViewTemplatefind();
    }
    private void LoadListViewTemplate()
    {

        List<Restaurants> opciones = this.ObtenerDatos();

        RestaurantsAdapters adapter = new RestaurantsAdapters(this, opciones);

        lstRestaurantes.setAdapter(adapter);
    }
    private void LoadListViewTemplatefind()
    {

        List<Restaurants> opciones = this.buscarArticulo();

        RestaurantsAdapters adapter = new RestaurantsAdapters(this, opciones);

        lstRestaurantes.setAdapter(adapter);
    }

    private List<Restaurants> ObtenerDatos() {

        // String valor_busqueda = EditexValor.getText().toString();
        List<Restaurants> lista = new ArrayList<Restaurants>();
        String est = spnOpcion.getSelectedItem().toString();
        try {
            //Abre la base de datos
            restaurantesHelper arDB = new restaurantesHelper(this, "restaurantes", null, 1);
            //En modo de lectura
            SQLiteDatabase db = arDB.getReadableDatabase();
            //equalsIgnorecase para que no distinga entre minuscula y mayuscula
            //trim() para retornar el valor sin espacios enfrente
            //Toast.makeText(this, "Este es el valor del spinner!"+est, Toast.LENGTH_LONG).show();
            if(est.compareTo("Seleccione el tipo de filtrado") == 0 && i>=1)
            {
                Toast.makeText(this, "Por favor seleccione el Tipo de Filtrado!", Toast.LENGTH_LONG).show();
            }
            else {
                if (db != null) {
                    Cursor cursor;
                    //Toast.makeText(this, "Antes del cursor!", Toast.LENGTH_LONG).show();

                    if(est.equals("Seleccione el tipo de filtrado")) {
                        //Toast.makeText(this, "debe ser 1 vez", Toast.LENGTH_LONG).show();
                        //OBTIENEN LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA USANDO EL Nombre
                        cursor = db.rawQuery("SELECT * FROM restaurantes", null);
                        this.movcursor(cursor, lista);
                        db.close();
                        cursor.close();
                    }
                    else if(est.equals("Nombre")) {

                        //Toast.makeText(this, "Asignando el query!", Toast.LENGTH_LONG).show();
                        // OBTIENEN LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA USANDO EL CODIGO
                        cursor = db.rawQuery("SELECT * FROM restaurantes ORDER BY nombre", null);
                        this.movcursor(cursor, lista);
                        db.close();
                        cursor.close();
                    }
                    else if(est.equals("Horario")) {

                        //OBTIENEN LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA USANDO EL Nombre
                        cursor = db.rawQuery("SELECT * FROM restaurantes ORDER BY horario", null);
                        this.movcursor(cursor, lista);
                        db.close();
                        cursor.close();
                    }
                    else if(est.equals("Ubicación")) {

                        //OBTIENEN LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA USANDO EL Nombre
                        cursor = db.rawQuery("SELECT * FROM restaurantes ORDER BY ubicacion", null);
                        this.movcursor(cursor, lista);
                        db.close();
                        cursor.close();
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error -> " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
        //EditexValor.setText("");
        //this.Cargar_Spinner();
        return lista;
    }
    private List<Restaurants> buscarArticulo() {

        String valor_busqueda = EditexValor.getText().toString();
        List<Restaurants> lista = new ArrayList<Restaurants>();
        String est = spnOpcion.getSelectedItem().toString();
        try {
            //Abre la base de datos
            restaurantesHelper arDB = new restaurantesHelper(this, "restaurantes", null, 1);
            //En modo de lectura
            SQLiteDatabase db = arDB.getReadableDatabase();
            //equalsIgnorecase para que no distinga entre minuscula y mayuscula
            //trim() para retornar el valor sin espacios enfrente
            if(valor_busqueda.trim().equalsIgnoreCase(""))
            {
                Toast.makeText(this, "Por favor ingrese el nombre del Restaurante!", Toast.LENGTH_SHORT).show();
            }
            else {

                if (db != null) {
                    Cursor cursor;
                    //Toast.makeText(this, "Antes del cursor!", Toast.LENGTH_LONG).show();

                    if(est.equals("Nombre")) {

                        Toast.makeText(this, "Asignando el query!", Toast.LENGTH_LONG).show();
                        // OBTIENEN LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA USANDO EL CODIGO
                        cursor = db.rawQuery("SELECT * FROM restaurantes WHERE nombre='" + valor_busqueda + "'", null);
                        this.movcursor(cursor, lista);
                        db.close();
                        cursor.close();
                    }
                    else if(est.equals("Ubicacion")) {

                        //OBTIENEN LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA USANDO EL Nombre
                        cursor = db.rawQuery("SELECT * FROM restaurantes WHERE ubicacion='" + valor_busqueda + "'", null);
                        this.movcursor(cursor, lista);
                        db.close();
                        cursor.close();
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error -> " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
        EditexValor.setText("");
        this.Cargar_Spinner();
        return lista;
    }
    public void movcursor(Cursor cursor, List<Restaurants> lista){
        if (cursor.moveToFirst()) {
            do {
                Restaurants sm = new Restaurants(); //INSERTA LOS DATOS EN LA LISTA

                sm.setImag_Rest(cursor.getInt(0));
                sm.setNombre(cursor.getString(1));
                sm.setHorario(cursor.getString(2));
                sm.setUbicacion(cursor.getString(3));
                        /*byte[] image = cursor.getBlob(4);
                        sm.setImagen(image);*/

                lista.add(sm);
            } while (cursor.moveToNext());
        }
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
}
