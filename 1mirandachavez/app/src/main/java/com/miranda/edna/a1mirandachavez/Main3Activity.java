package com.miranda.edna.a1mirandachavez;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*****************************************************************************************************
 *   Main3Activity que Consultar datos de la Base de Datos CursoAgenda SqLite
 ****************************************************************************************************/

public class Main3Activity extends AppCompatActivity {

        DBController dbController;

        TextView txtContenido1;
        Button btnRegresar1;
        Button btnInscripcion;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        dbController = new DBController(Main3Activity.this);

        txtContenido1 = (TextView) findViewById(R.id.txtContenido1);
        btnRegresar1 = (Button) findViewById(R.id.btnRegresar1);
        btnInscripcion = (Button) findViewById(R.id.btnInscripcion);

        dbController.open();
        txtContenido1.setText("");

        Cursor cursor = dbController.getData();
                if (cursor.moveToFirst()) {
                    do {
                        String mes = cursor.getString(1);
                        String dia = cursor.getString(2);
                        String fecha = cursor.getString(3);
                        String fecha1 = cursor.getString(4);
                        String fecha2 = cursor.getString(5);
                        String fecha3 = cursor.getString(6);
                        String nombre = cursor.getString(7);
                        String descripcion = cursor.getString(8);
                        String duracion = cursor.getString(9);
                        String horario = cursor.getString(10);
                        String ordinario = cursor.getString(11);
                        String costo = cursor.getString(12);
                        String objetivo = cursor.getString(13);
                        String requerimientos = cursor.getString(14);
                        String temario = cursor.getString(15);
                        String direccion = cursor.getString(16);

                        txtContenido1.append("Mes: " + mes
                                + " Dia: " + dia
                                + " Fecha: " + fecha
                                + " Fecha1: " + fecha1
                                + " Fecha2: " + fecha2
                                + " Fecha3: " + fecha3
                                + " Nombre: " + nombre
                                + " Descripcion: " + descripcion
                                + " Duracion: " + duracion
                                + " Horario: " + horario
                                + " Ordinario: " + ordinario
                                + " Costo: " + costo
                                + " Objetivo: " + objetivo
                                + " Requerimientos: " + requerimientos
                                + " Temario: " + temario
                                + " Direccion: " + direccion + "\n\n\n");
                    } while (cursor.moveToNext());
                }
            dbController.close();
            }

    @Override
    protected void onResume() {
        super.onResume();

        btnRegresar1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btnRegresar1_onClick();
            }
        });

        btnInscripcion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btnInscripcion_onClick();
            }
        });
    }


    private void btnRegresar1_onClick() {
        Intent intent3 = new Intent (this, MainActivity.class);
        startActivity(intent3);
    }

    private void btnInscripcion_onClick () {
        Uri uriUrl = Uri.parse("http://www.it-okcenter.com/calendario/");
        Intent intent1 = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(intent1);
    }

}
