package com.pyo.prueba_base_datos_sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Prueba_Base_Datos_SQLite extends AppCompatActivity implements View.OnClickListener {
    private final String nombreBase = "Personas";
    private ListView vLista;
    private ArrayAdapter adaptador;
    private Button guardar;
    private EditText nombreT;
    private EditText edadT;
    private String cId = "Id", cNombre = "Nombre", cEdad = "Edad" ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba__base__datos__sqlite);

        guardar = (Button) this.findViewById(R.id.button);
        nombreT = (EditText) this.findViewById(R.id.editText);
        edadT = (EditText) this.findViewById(R.id.editText2);
        vLista = (ListView) this.findViewById(R.id.listView);

        /*vLista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                PersonaSQLiteHelper persona = new PersonaSQLiteHelper(Prueba_Base_Datos_SQLite.this, nombreBase, null, 1);
                SQLiteDatabase baseDatos = persona.getReadableDatabase();

                    Cursor cursor = baseDatos.rawQuery("SELECT * FROM Persona WHERE rowid = " + (position+1), null);
                    String nombre = cursor.getString(0);
                    int  edad = cursor.getInt(1);
                    nombreT.setText(nombre);
                    edadT.setText(edad);
            }


        });*/

        guardar.setOnClickListener(this);
        llenarLista();
        /*PersonaSQLiteHelper persona = new PersonaSQLiteHelper(this,nombreBase,null,1);
        lista = persona.llenarLista();
        adaptador = new ArrayAdaper(this, android.R.layout.simple_list_item_2,lista);
        listaId.setAdapter(adaptador);*/
    }
    public void onClick( View vista) {

            String nombre = nombreT.getText().toString();
            int edad = Integer.parseInt(edadT.getText().toString());


            PersonaSQLiteHelper persona = new PersonaSQLiteHelper(this, nombreBase, null, 1);

            SQLiteDatabase baseDatos = persona.getWritableDatabase();

            if (baseDatos != null) {

                baseDatos.execSQL("INSERT INTO Persona (nombre, edad) VALUES " + "('" + nombre + "', " + edad + ")");
                Toast.makeText(this, "Nuevo Registro", Toast.LENGTH_LONG).show();
                nombreT.setText("");
                edadT.setText("");

            }

            llenarLista();


    }

    public void llenarLista()
    {
        PersonaSQLiteHelper persona = new PersonaSQLiteHelper(this, nombreBase, null, 1);
        SQLiteDatabase baseDatos = persona.getReadableDatabase();

        if (baseDatos != null)
        {
            Cursor cursor = baseDatos.rawQuery("SELECT * FROM Persona", null);
            int cantidad = cursor.getCount();
            int n = 0;

                String[] registros = new String[cantidad];

                if (cursor.moveToFirst())
                {
                    do
                    {
                        String registro = (n+1) + "         "+cursor.getString(0) + "            " + cursor.getInt(1);
                        registros[n] = registro;
                        n++;

                    }
                    while (cursor.moveToNext());
                }
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registros);
                ListView lista  = (ListView)this.findViewById(R.id.listView);
                lista.setAdapter(adaptador);


        }
        baseDatos.close();
    }
}

