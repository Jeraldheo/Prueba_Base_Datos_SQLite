package com.pyo.prueba_base_datos_sqlite;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class PersonaSQLiteHelper extends  SQLiteOpenHelper
{
    String creaTabla = "CREATE TABLE Persona (nombre TEXT, edad INTEGER)";
    public PersonaSQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version)
    {

        super(contexto, nombre, factory, version);
    }
    public void onCreate(SQLiteDatabase baseDatos)
    {
        baseDatos.execSQL(creaTabla);
    }

    public void onUpgrade(SQLiteDatabase baseDatos, int antes, int despues)
    {
        baseDatos.execSQL("DROP TRABLE IF EXISTS Persona");
        baseDatos.execSQL(creaTabla);

    }
    /*public ArrayList llenarLista()
    {
       ArrayList<String> lista   =  new ArrayList<>();
        SQLiteDatabase baseDatos = this.getWritableDatabase();
        String consulta = "SELECT * FROM Persona";
        Cursor registro = baseDatos.rawQuery(consulta, null);
        if(registro.moveToFirst()) {
            do {
                lista.add(registro.getString(1));

            }
            while (registro.moveToNext());
        }
        return lista;
    }*/
}
