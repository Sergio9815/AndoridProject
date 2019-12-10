package com.example.applabssgonzalezjgonzalezjbultron.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applabssgonzalezjgonzalezjbultron.Helpers.usersHelper;
import com.example.applabssgonzalezjgonzalezjbultron.R;
import com.example.applabssgonzalezjgonzalezjbultron.Restaurantes.RestaurantesActivity;

public class MainActivity extends AppCompatActivity {

    private String nombreU, contra, email, con, tipo, name,apellido;
    private EditText txtUsuario, txtPass;
    private Button btn_Perfil, btn_Registrarse;
    private SQLiteDatabase db;

    //web service variables
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cargar_preferencias();
        this.InicializarControles();
        //this.obtener_webservice();

    }

    /*

    private void obtener_webservice(){

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApiArticulos jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApiArticulos.class);
        Call<List<ArticuloService>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<ArticuloService>>() {
            @Override
            public void onResponse(Call<List<ArticuloService>> call, Response<List<ArticuloService>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<ArticuloService> posts = response.body();

                for (ArticuloService post : posts) {
                    String content = "";
                    content += "id: " + post.getId() + "\n";
                    content += "nombre: " + post.getNombre() + "\n";
                    content += "cantidad: " + post.getCantidad() + "\n";
                    content += "estado: " + post.getEstado() + "\n";
                    content += "precio: " + post.getPrecio() + "\n";
                    content += "ubicacion: " + post.getDire()+ "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<ArticuloService>> call, Throwable t) {
                textViewResult.setText("Aviso error: "+t.getMessage());
            }
        });
    }

    */

    private void cargar_preferencias(){

        //VERIFICAR SI LA SESION ESTA ACTIVA
        SharedPreferences preSesion = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String se = preSesion.getString("sesion","0");

        if(se.equals("0")){
            Toast.makeText(this, "Por favor inicie sesión", Toast.LENGTH_SHORT).show();
        }
        else if(se.compareTo("1")==0){
            Intent i = new Intent(getApplicationContext(), RestaurantesActivity.class);
            startActivity(i);
        }
    }

    private void InicializarControles() {

        txtUsuario = (EditText)findViewById(R.id.editUsuario);
        txtPass = (EditText)findViewById(R.id.editPass);

        btn_Perfil = (Button)findViewById(R.id.irAlPerfil);
        btn_Registrarse = (Button)findViewById(R.id.irAlRegistro);

        btn_Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAlPerfil(view);
            }
        });
        btn_Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irRegistrarse(view);
            }
        });

    }

    private void irRegistrarse(View view){

        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
    }

    private void irAlPerfil(View view) {

        nombreU = txtUsuario.getText().toString();
        contra = txtPass.getText().toString();

        //ACTIVAR BASE DE DATOS
        usersHelper userDB = new usersHelper(this, "Usuarios", null, 1);
        db = userDB.getWritableDatabase();

        if (db != null) {

            //OBTENER LOS DATOS DE LA TABLA USUARIOS CORRESPONDIENTES AL USUARIO INGRESADO
            Cursor cursor = db.rawQuery("SELECT * FROM usuariosR WHERE email = '" + nombreU + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    email = cursor.getString(0);
                    con = cursor.getString(1);
                    tipo = cursor.getString(2);
                    name = cursor.getString(3);
                    apellido = cursor.getString(4);

                    //VERIFICA SI LOS DATOS INGRESADOS CONCUERDAN CON LOS OBTENIDOS DE LA TABLA USUARIOS
                    if(nombreU.equals(email) && contra.equals(con))
                    {
                        //ACTIVAR SESION
                        SharedPreferences preSesion = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preSesion.edit();
                        editor.putString("sesion", tipo);
                        editor.putString("perfil", name);
                        editor.putString("perfil2", apellido);

                        if (tipo.equals("1")){//VENDEDOR
                            //GUARDAR PERFIL
                            editor.putString("tipoP", "Cliente");
                            Intent i = new Intent(this, RestaurantesActivity.class);
                            startActivity(i);
                        }
                        editor.commit();//ALMACENAR LOS DATOS DEL SHARE PREFERENCES
                    }
                    else {
                        Toast.makeText(this, "El nombre de usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }while (cursor.moveToNext());
                db.close();
                cursor.close();
            }
            else{
                Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}