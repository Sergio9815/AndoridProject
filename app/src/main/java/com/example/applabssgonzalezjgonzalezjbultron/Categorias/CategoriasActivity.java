package com.example.applabssgonzalezjgonzalezjbultron.Categorias;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applabssgonzalezjgonzalezjbultron.Adapters.CategAdapters;
import com.example.applabssgonzalezjgonzalezjbultron.Entidades.Categ;
import com.example.applabssgonzalezjgonzalezjbultron.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivity extends AppCompatActivity {

    ListView lstCateg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        lstCateg = (ListView)findViewById(R.id.lst_Categ);
        this.LoadListViewTemplate();
        this.categoria();
    }


    private void categoria() {
        final Intent z = new Intent(this, FiltroCategoriasActivity.class);

        lstCateg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                String nomC =((Categ) a.getItemAtPosition(position)).getNombre();

                if((nomC.compareTo("Comida Rápida") == 0)) {

                    SharedPreferences preCate = getSharedPreferences("MisPreferenciasC", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preCate.edit();
                    editor.putString("categ", nomC);
                    editor.commit();//ALMACENAR LOS DATOS DEL SHARE PREFERENCES
                    startActivity(z);

                }
                else if((nomC.compareTo("Postres") == 0)) {

                    SharedPreferences preCate = getSharedPreferences("MisPreferenciasC", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preCate.edit();
                    editor.putString("categ", nomC);
                    editor.commit();//ALMACENAR LOS DATOS DEL SHARE PREFERENCES
                    startActivity(z);

                }
                else if((nomC.compareTo("Helados") == 0)) {

                    SharedPreferences preCate = getSharedPreferences("MisPreferenciasC", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preCate.edit();
                    editor.putString("categ", nomC);
                    editor.commit();//ALMACENAR LOS DATOS DEL SHARE PREFERENCES
                    startActivity(z);

                }
                else if((nomC.compareTo("Pizza") == 0)) {

                    SharedPreferences preCate = getSharedPreferences("MisPreferenciasC", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preCate.edit();
                    editor.putString("categ", nomC);
                    editor.commit();//ALMACENAR LOS DATOS DEL SHARE PREFERENCES
                    startActivity(z);

                }
                else if((nomC.compareTo("Emparedados") == 0)) {

                    SharedPreferences preCate = getSharedPreferences("MisPreferenciasC", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preCate.edit();
                    editor.putString("categ", nomC);
                    editor.commit();//ALMACENAR LOS DATOS DEL SHARE PREFERENCES
                    startActivity(z);

                }

            }
        });


    }

    private void LoadListViewTemplate()
    {
        List<Categ> opciones = this.GetElementsToListViewTemplate();

        CategAdapters adapter = new CategAdapters(this, opciones);

        lstCateg.setAdapter(adapter);
    }


    private List<Categ> GetElementsToListViewTemplate()
    {
        List<Categ> opciones = new ArrayList<Categ>();

        opciones.add(new Categ("Comida Rápida"));
        opciones.add(new Categ("Postres"));
        opciones.add(new Categ("Helados"));
        opciones.add(new Categ("Pizza"));
        opciones.add(new Categ("Emparedados"));

        return  opciones;

    }


    //Metodos para mostrar y ocultar nuestro menú
    private void barraDeMenu() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar2, menu);
        return true;
    }

}
