package com.miranda.edna.a1mirandachavez;

import android.database.Cursor;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*****************************************************************************************************
 *   Main2Activity que Agrega datos en la Base de Datos CursoAgenda SqLite
 ****************************************************************************************************/

public class Main2Activity extends AppCompatActivity {

        DBController dbController;

        TextView txtContenido;

        EditText edtxmes, edtxdia, edtxfecha, edtxfecha1,edtxfecha2, edtxfecha3, edtxnombre, edtxdescripcion, edtxduracion, edtxhorario, edtxordinario, edtxcosto, edtxobjetivo, edtxrequerimientos, edtxtemario, edtxdireccion;


        Button btnInsertar;
        Button btnRegresar;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

            dbController = new DBController(Main2Activity.this);

            txtContenido = (TextView) findViewById(R.id.txtContenido);

            btnInsertar = (Button) findViewById(R.id.btnInsertar);
            btnRegresar = (Button) findViewById(R.id.btnRegresar);


            edtxmes = (EditText) findViewById(R.id.edtxmes);
            edtxdia = (EditText) findViewById(R.id.edtxdia);
            edtxfecha = (EditText) findViewById(R.id.edtxfecha);
            edtxfecha1 = (EditText) findViewById(R.id.edtxfecha1);
            edtxfecha2 = (EditText) findViewById(R.id.edtxfecha2);
            edtxfecha3 = (EditText) findViewById(R.id.edtxfecha3);
            edtxnombre = (EditText) findViewById(R.id.edtxnombre);
            edtxdescripcion = (EditText) findViewById(R.id.edtxdescripcion);
            edtxduracion = (EditText) findViewById(R.id.edtxduracion);
            edtxhorario = (EditText) findViewById(R.id.edtxhorario);
            edtxordinario = (EditText) findViewById(R.id.edtxordinario);
            edtxcosto = (EditText) findViewById(R.id.edtxcosto);
            edtxobjetivo = (EditText) findViewById(R.id.edtxobjetivo);
            edtxrequerimientos = (EditText) findViewById(R.id.edtxrequerimientos);
            edtxtemario = (EditText) findViewById(R.id.edtxtemario);
            edtxdireccion = (EditText) findViewById(R.id.edtxdireccion);
        }

        @Override
        protected void onResume() {
            super.onResume();

            btnInsertar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbController.open();
                    dbController.insertData(edtxmes.getText().toString(),
                    edtxdia.getText().toString(),
                    edtxfecha.getText().toString(),
                    edtxfecha1.getText().toString(),
                    edtxfecha2.getText().toString(),
                    edtxfecha3.getText().toString(),
                    edtxnombre.getText().toString(),
                    edtxdescripcion.getText().toString(),
                    edtxduracion.getText().toString(),
                    edtxhorario.getText().toString(),
                    edtxordinario.getText().toString(),
                    edtxcosto.getText().toString(),
                    edtxobjetivo.getText().toString(),
                    edtxrequerimientos.getText().toString(),
                    edtxtemario.getText().toString(),
                    edtxdireccion.getText().toString());
                    showData();
                    dbController.close();
                }
            });


            btnRegresar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    btnRegresar_onClick();
                }
            });

        }

        public void showData() {
            txtContenido.setText("");

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

                    txtContenido.append("Mes: " + mes
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
                            + " Direccion: " + direccion + "\n");
                } while (cursor.moveToNext());
            }

        }


   private void btnRegresar_onClick() {
      Intent intent3 = new Intent (this, MainActivity.class);
       startActivity(intent3);
    }
}

        /**************************************************************************************
         *   Muestra todas las claves de los registros cargados en la Base de Datos en un Spinner
         *
         *   Abre la base de datos, busca los registros almacenados y copia la clave del mes
         *      en el Spinner
         ************************************************************************************/
        /**
         cboDatos = (Spinner) findViewById(R.id.cboDatos);


         AuxSqlite cmd = new AuxSqlite(this, "DBCursos5", null, 1);
         SQLiteDatabase db = cmd.getReadableDatabase();

         if (db != null) {
         Cursor c = db.rawQuery("SELECT mes FROM Cursos5", null);

         if (c.moveToFirst()) {
         String [] mes = new String[(c.getCount())];
         int i = 0;
         do {
         mes[i] = c.getString(0);
         i++;
         } while (c.moveToNext());

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mes);
         cboDatos.setAdapter(adapter);
         }
         }
         db.close();

         inicializaComponentes();
         }
         **/
/***************************************************************************************************
 *   Inicializa y llama a la funcion de eliminar
 *
 *   La funcion de agregar y actualizar (cambiar) estan funcionando por eso se dejo el codigo
 *      sin embargo se considero que el Usuario no deberia poder insertar, ni cambiar los datos,
 *      unicamente el poder borrarlos
 *
 * ************************************************************************************************/
/***
 private void inicializaComponentes() {
 //        btnAgregar = (Button) findViewById(R.id.btnAgregar);
 //        txtAgregar = (EditText) findViewById(R.id.txtAgregar);

 //        datePicker = (DatePicker) findViewById(R.id.datepicker);

 editText1 = (EditText) findViewById(R.id.editText1);
 btnEliminar = (Button) findViewById(R.id.btnEliminar);
 btnRegresar = (Button) findViewById(R.id.btnRegresar);
 cboDatos = (Spinner) findViewById(R.id.cboDatos);


 btnEliminar.setOnClickListener(new View.OnClickListener() {
 public void onClick(View view) {
 btnEliminar_onClick();
 }
 });


 btnRegresar.setOnClickListener(new View.OnClickListener() {
 public void onClick(View view) {
 btnRegresar_onClick();
 }
 });
 }

 **/
        /*****************************************************************************************************/
/**  El codigo para agregar un registro en la base de datos esta comentarizado porque se agregaron los
 *   registros y sus atributos de forma predefinida, este codigo funciona correctamente
 *
 *   Y se considero que el usuario no deberia poder agregar registros
 *
 *    --- Se abre la base de datos de forma escritura
 *
 *                      AuxSqlite cmd = new AuxSqlite(this, "DBCursos5", null, 1);
 *                      SQLiteDatabase db = cmd.getWritableDatabase();
 *    --- Acepta hasta 100 registros
 *
 *    --- Se copia el contenido de un EditText en el campo de la base de datos
 *
 *    --- Con el comando INSERT se graba la informacion en la base de datos
 ****************************************************************************************************/
        /**
         private void btnAgregar_onClick() {
         AuxSqlite cmd = new AuxSqlite(this, "DBCursos5", null, 1);
         SQLiteDatabase db = cmd.getWritableDatabase();

         if (db != null) {
         int clave = new Random().nextInt(100);

         String mes  = Integer.parseInt(this.txtmes.getText().toString());
         String dia = Integer.parseInt(this.txtdia.getText().toString());
         String fecha = this.txtfecha.getText().toString();
         String fecha1 = this.txtfecha1.getText().toString();
         String fecha2 = this.txtFecha2.getText().toString();
         String fecha3 = this.txtFecha3.getText().toString();
         String nombre = this.txtNombre.getText().toString();
         String descripcion = this.txtdescripcion.getText().toString();
         String duracion = this.txtduracion.getText().toString();
         String horario = this.txthorario.getText().toString();
         String ordinario = this.txtordinario.getText().toString();
         String costo = this.txtcosto.getText().toString();
         String objetivo = this.txtobjetivo.getText().toString();
         String requerimientos = this.txtrequerimientos.getText().toString();
         String temario = this.txttemario.getText().toString();
         String direccion = this.txtdireccion.getText().toString();

         db.execSQL("INSERT INTO Cursos (clave, mes, dia, nombre, descripcion, duracion, horario, ordinario, costo, objetivo, requerimientos, temario, descripcion) " +
         "VALUES (" + clave + ",  '" + mes + "', '" + dia + "', '" + fecha + "', '" + fecha1 + "', '" + fecha2 + "',  '" + fecha3 + "', '" + nombre + "', '" + descripcion + "',  '" + duracion + "', '" + horario + "', '" + ordinario + "',  '" + costo + "' , '" + objetivo + "', '" + requerimientos + "', '" + temario + "', '" + direccion + "')");
         }
         db.close();
         }
         **/


        /************************************************************************************************
         * Funcion para eliminar registros de la base de datos
         *
         *         .Se Abre la base de datos de forma escritura DBCursos5
         *                 AuxSqlite cmd = new AuxSqlite(this, "DBCursos5", null, 1);
         *                 SQLiteDatabase db = cmd.getWritableDatabase();
         *         .Se toma la clave seleccionada del Spinner
         *                 String mes = cboDatos.getSelectedItem().toString();
         *         .Se da delete al registro con la clave seleccionada en el Spinner
         *                 db.execSQL("DELETE FROM Cursos5 WHERE mes = '" + mes + "'");
         *         .Se refresca el Spinner para observar que no existe el registro
         *         .Se cierra la base de datos
         ***********************************************************************************************/
        /**
         private void btnEliminar_onClick() {


         AuxSqlite cmd = new AuxSqlite(this, "DBCursos5", null, 1);
         SQLiteDatabase db = cmd.getWritableDatabase();

         if (db != null && cboDatos.getSelectedItem() != null) {

         String mes = cboDatos.getSelectedItem().toString();

         db.execSQL("DELETE FROM Cursos5 WHERE mes = '" + mes + "'");
         }
         **/
        /******************************************************************************************
         *   se refresca el contenido del Spinner, iniciando en el primer registro
         *            if (c.moveToFirst())
         *   despues al siguiente
         *           (c.moveToNext());
         *   hasta que no existan mas registros
         *           if (db != null)
         ******************************************************************************************/
        /**
         cboDatos.setAdapter(null);

         if (db != null) {
         Cursor c = db.rawQuery("SELECT mes FROM Cursos5", null);

         if (c.moveToFirst()) {
         //                  String[] nombres = new String[c.getCount()];
         String [] mes = new String[(c.getCount())];
         int i = 0;
         do {
         mes[i] = c.getString(0);
         i++;
         } while (c.moveToNext());


         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mes);
         cboDatos.setAdapter(adapter);
         }
         }
         db.close();

         }
         **/
        /****************************************************************************************
         * Cuando se da clic al boton Regresa a la ventana principal MainActivity
         *            .Indica que debe ir en este caso a la actividad principal (MainActivity)
         *                    - Intent intent3 = new Intent (this, MainActivity.class)
         *            .Inicia la actividad -  startActivity(intent3);
         ****************************************************************************************/




