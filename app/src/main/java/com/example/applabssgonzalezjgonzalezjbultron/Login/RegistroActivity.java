package com.example.applabssgonzalezjgonzalezjbultron.Login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.applabssgonzalezjgonzalezjbultron.Helpers.usersHelper;
import com.example.applabssgonzalezjgonzalezjbultron.R;

public class RegistroActivity extends AppCompatActivity {

    EditText txtUserEmail;
    EditText txtCon;
    EditText txtName;
    EditText txtApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.barraDeMenu();
        this.InicializarControles();
    }

    private void InicializarControles() {
        txtName = (EditText)findViewById(R.id.ediNameUser);
        txtApellido=(EditText)findViewById(R.id.ediLastNameUser);
        txtUserEmail = (EditText)findViewById(R.id.editUser);
        txtCon = (EditText)findViewById(R.id.editCon);
    }

    public void registrarse(View view){
        usersHelper userDB = new usersHelper(this,"Usuarios",null,1);

        SQLiteDatabase db = userDB.getWritableDatabase();

        String us = txtUserEmail.getText().toString();
        String co = txtCon.getText().toString();
        String name = txtName.getText().toString();
        String apellido =txtApellido.getText().toString();
        String tipo = String.valueOf("1");

        if(name.trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Por favor ingrese su Nombre de usuario", Toast.LENGTH_LONG).show();
        }else if(apellido.trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Por favor ingrese su Apellido de usuario", Toast.LENGTH_LONG).show();
        }else if(us.trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Por favor ingrese su Email de usuario", Toast.LENGTH_LONG).show();
        }else if(co.trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Por favor ingrese una contraseña válida", Toast.LENGTH_LONG).show();
        }else if (db != null) {
            try {

                db.execSQL("INSERT INTO usuariosR (email, contra, tipo, nombre, apellido) " + "VALUES ('" + us + "', '" + co + "', '" + tipo + "' , '"+ name +"','"+apellido+"')");

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
                Intent z = new Intent(this, MainActivity.class);
                startActivity(z);
                finish();

                } catch (Exception e) {
                    Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_LONG).show();
                } finally {
                    db.close();
                }
            }
    }

    //Metodos para mostrar y ocultar nuestro menú
    private void barraDeMenu() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Crear usuario");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        return true;
    }

}