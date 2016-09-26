package com.miranda.edna.a1mirandachavez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by HP_Pavilion on 19/09/2016.
 */

public class DBController {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;


    public DBController(Context context) {
        this.context = context;
    }

    public DBController open() {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertData(String mes, String dia, String fecha, String fecha1, String fecha2, String fecha3, String nombre, String descripcion, String duracion, String horario, String ordinario, String costo, String objetivo, String requerimientos, String temario, String direccion) {
        ContentValues values = new ContentValues();
        values.put("mes", mes);
        values.put("dia", dia);
        values.put("fecha", fecha);
        values.put("fecha1", fecha1);
        values.put("fecha2", fecha2);
        values.put("fecha3", fecha3);
        values.put("nombre", nombre);
        values.put("descripcion", descripcion);
        values.put("duracion", duracion);
        values.put("horario", horario);
        values.put("ordinario", ordinario);
        values.put("costo", costo);
        values.put("objetivo", objetivo);
        values.put("requerimientos", requerimientos);
        values.put("temario", temario);
        values.put("direccion", direccion);
        db.insert(DBHelper.TABLE_NAME, null, values);
    }

    public Cursor getData() {
        String[] columnas = new String[]{
                DBHelper._ID, "mes", "dia", "fecha", "fecha1", "fecha2", "fecha3", "nombre", "descripcion", "duracion", "horario", "ordinario", "costo", "objetivo", "requerimientos", "temario", "direccion"
        };


        Cursor cursor = db.query(
                DBHelper.TABLE_NAME,
                columnas, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void delete(int id) {
        db.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + id, null);
    }

 }
