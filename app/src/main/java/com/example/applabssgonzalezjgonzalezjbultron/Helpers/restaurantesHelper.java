package com.example.applabssgonzalezjgonzalezjbultron.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class restaurantesHelper extends SQLiteOpenHelper {

    String queryCreate = "CREATE TABLE restaurantes(id NUMBER PRIMARY KEY, nombre TEXT, horario TEXT, ubicacion TEXT, catego TEXT)";

    public restaurantesHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
