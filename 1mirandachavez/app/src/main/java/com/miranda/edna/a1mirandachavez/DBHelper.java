package com.miranda.edna.a1mirandachavez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP_Pavilion on 19/09/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    static final String DBNAME = "CursoAgenda.db";
    static final int DBVERSION = 1;
    public static final String TABLE_NAME = "CURSO";
    public static final String _ID = "id";

    String sqliteCreate = "CREATE TABLE "
            + TABLE_NAME
            + " ( "
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "mes TEXT, " +
            "dia TEXT, " +
            "fecha TEXT, " +
            "fecha1 TEXT, " +
            "fecha2 TEXT, " +
            "fecha3 TEXT, " +
            "nombre TEXT, " +
            "descripcion TEXT, " +
            "duracion TEXT, " +
            "horario TEXT, " +
            "ordinario TEXT, " +
            "costo TEXT, " +
            "objetivo TEXT, " +
            "requerimientos TEXT, " +
            "temario TEXT, " +
            "direccion TEXT );";


    public DBHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqliteCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
        db.execSQL("DROP TABLE IF EXIST CURSO");

        db.execSQL(sqliteCreate);

    }
}


