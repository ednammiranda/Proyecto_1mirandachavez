package com.miranda.edna.a1mirandachavez;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*****************************************************************************************************
 *   Main4Activity que Borra todos los datos en la Base de Datos CursoAgenda SqLite
 ****************************************************************************************************/

public class Main4Activity extends AppCompatActivity {


    DBController dbController;
    /*************************************************************************************************
     * Se relaciona el XML con JAVA
     * el diseño con la programación
     ************************************************************************************************/

    TextView txtContenido2;
    Button btnEliminar;
    Button btnRegresar4;

    /***********************************************************************************************************
     * Se ejecuta inicia la aplicación
     * Se crea la ventana (activity_main)
     * Se activan e inicializan los botones, editText, TextViewe, DatePicker, Spinner
     *********************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        dbController = new DBController(Main4Activity.this);

        txtContenido2 = (TextView) findViewById(R.id.txtContenido2);

        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnRegresar4 = (Button) findViewById(R.id.btnRegresar4);

        dbController.open();

        txtContenido2.setText("");
        Cursor cursor = dbController.getData();
        if (cursor.moveToFirst()) {
            do {
                String mes = cursor.getString(1);
                String dia = cursor.getString(2);
                String nombre = cursor.getString(7);

                txtContenido2.append("Mes: " + mes
                        + " Dia: " + dia
                        + " Nombre: " + nombre  + "\n\n\n");
            } while (cursor.moveToNext());
        }
        dbController.close();
    }


    @Override
       protected void onResume () {
        super.onResume();


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btnEliminar_onClick();
            }
        });

        btnRegresar4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btnRegresar4_onClick();
            }
        });
    }

     private void btnRegresar4_onClick() {
        Intent intent3 = new Intent (this, MainActivity.class);
        startActivity(intent3);
    }

    private void btnEliminar_onClick() {
        dbController.open();
        Cursor cursor = dbController.getData();
        if (cursor.moveToFirst()) {
        do {
        int id = cursor.getInt(0);
        dbController.delete(id);
        } while (cursor.moveToNext());
        }

        showData();
        dbController.close();
        }

    public void showData() {

        txtContenido2.setText("");
        Cursor cursor = dbController.getData();

        if (cursor.moveToFirst()) {
            do {
                //               int id = cursor.getInt(0);
                String mes = cursor.getString(1);
                String dia = cursor.getString(2);
                String nombre = cursor.getString(7);
                txtContenido2.append("Mes: " + mes
                        + " Dia: "  + dia +
                        " Nombre: " + nombre + "\n\n");
            } while (cursor.moveToNext());
        }
        Toast.makeText(getApplicationContext(),"Se han borrado todos los cursos, cuando regrese a la ventana principal se cargan los cursos originales de IT-Center ",Toast.LENGTH_LONG).show();

    }
}









