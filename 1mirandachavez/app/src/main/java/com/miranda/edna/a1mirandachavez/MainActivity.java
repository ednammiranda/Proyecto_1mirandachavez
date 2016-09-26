package com.miranda.edna.a1mirandachavez;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


/***********************************************************************************************
 * Diseño de la Aplicacion
 *    -- Despliega la ventana principal y muestra el mes que se tienen programados cursos de certificacion
 *        . La carga de la base de datos es automatica para que se puedan consultar los datos
 *        . En esta ventana con un Spinner se muestran todos los meses que hay cursos definidos para certificarse
 *        . El Boton CLIC PARA SABER SI HAY CURSOS DE CERTIFICACIÓN  verificar si hay cursos en este mes, y envia un
 *             toast, el cual muestra al usuario el mensaje que hay cusos y cambia el boton de  PROXIMANETE a INSCRIBIRSE
 *        . El Boton PROXIMAMENTE/INSCRIBIRSE  envia a la pagina de www.it-okcenter.com/calendario
 *        . El Boton VER DETALLE CURSO SELECCIOADO despliega el detalle del curso seleccionado
 *        . El Boton BORRAR REGISTROS DE LA BD despliega una ventana en donde el usuario puede borrar los registros predefinidos
 *
 *************************************************************************************************/

    DBController dbController;
    /*************************************************************************************************
     *   Se relaciona el XML con JAVA
     *       el diseño con la programación
     ************************************************************************************************/
    String mesS= "9";
    String diaS = "20";
    String fechaS = "20160920";
    TextView textView;
    TextView textView2;
    EditText editMesPic;
    Button btnDetalle;
    Button btnAgregar;
    Button btnproximamente;
    Button btnActualizar;
    Button btnEliminar;
    ImageView imageView;
    Spinner cboDatos;
    DatePicker datePicker;
    Toast toast;


    /***********************************************************************************************************
     *   Se ejecuta inicia la aplicación
     *          Se crea la ventana (activity_main)
     *          Se activan e inicializan los botones, editText, TextViewe, DatePicker, Spinner
     *********************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbController = new DBController(MainActivity.this);

        btnDetalle = (Button) findViewById(R.id.btnDetalle);
        btnDetalle.setOnClickListener(this);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(this);
        btnproximamente = (Button) findViewById(R.id.btnproximamente);
        btnproximamente.setOnClickListener(this);
        editMesPic = (EditText) findViewById(R.id.editMesPic);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        imageView = (ImageView) findViewById(R.id.imageView);
        cboDatos = (Spinner) findViewById(R.id.cboDatos);

        cargaDatos();

        int b = 0;

        dbController.open();
        Cursor cursor = dbController.getData();


        if (cursor.moveToFirst()) {
            String[] meses = new String[0];
            meses = new String[(cursor.getCount())];
            int i = 0;
            do {
                meses[i] = cursor.getString(1);
                    if (meses[i] == editMesPic.getText().toString()) {
                        b=1;
                    }
                i++;
            } while (cursor.moveToNext());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meses);
            cboDatos.setAdapter(adapter);
        }
        dbController.close();
        inicializaComponentes();
    }



/**************************************************************************************************************
 * Se habre la base de datos y se muestran en el Spinner los meses que hay cursos de certificaion
 *    una vez que los muestra se cierra la base de datos
 *
 * Se coloco este codigo porque cuando regresaba el usuario a esta ventana, el Spinner no se mostraba
 *
 * En caso de coincidir el mes actual con el curso programado envia un Toast (mensaje al usuario)
 *
 *  Se inicia el recorrido de la base de datros en el primer registro
 *            if (c.moveToFirst())
 *                 despues al siguiente
 *           (c.moveToNext());
 *                 hasta que no existan mas registros
 *           if (db != null)
 *
 *  Mientras tanto va copiando la clave en el Spinner   cboDatos
 *                 mes[i] = c.getString(0);
 ************************************************************************************************************/


        /******************************************************************************************************************
         *  Inicializa los componentes para escribir la base de datos y le da el control a la funcion btnActualizar_onCLick
         *******************************************************************************************************************/

    private void inicializaComponentes() {

        editMesPic.setText(String.valueOf(datePicker.getMonth()));
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btnActualizar_onClick();
            }
        });
    }

    /**********************************************************************************************************************
     *   Crea la base de datos con datos predefinidos, se considero que el usuario necesitaba ver la informacion y por lo
     *   tanto deberia aparecer cuando iniciara la aplicación
     *
     *     Se abre la base de datos de forma de escritura
     *
     *                   AuxSqlite cmd = new AuxSqlite(this, "DBCursos5", null, 1);
     *                   SQLiteDatabase db = cmd.getWritableDatabase();
     *******************************************************************************************************************/


     private void btnActualizar_onClick() {

     /***************************************************************************************************
     * Carga el mes de imparticion del curso en el Spinner, muestra los meses en los cuales hay cursos decapacitacion
     *     con certificación
     *
     * Compara si el mes actual coincide con alguno de los meses que se imparten cursos envia un Toast (mensaje al usuario)
     *
     * Cierra la base de datos
     ******************************************************************************************************/

     Toast.makeText(getApplicationContext(),"Este mes puedes Certificarte, vee el detalle de los cursos que se imparten, da clic en el boton INSCRIPCION",Toast.LENGTH_LONG).show();

     btnproximamente.setText(R.string.inscripcion);
     //          btnproximamente.setBackgroundColor(R.color.rojo);

     }

     /***********************************************************************************************************************
     *  Si el usuario desea borrar registros de la base de datos envia a la pantalla Main2Activity
     *
     *  Si el usuario desea saber el detalle del curso selecccionado envia el mes (clave) a la actividad 3
     *     para que en esta actividad se consulte la base de datos y se despliegue el registro que el
     *     usuario selecciono.  El mes se guarda en FECHA
     *
     *  Si el usuario quiere inscribirse en un curso o saber detalle de otros cursos que no estan programados para
     *     en este momento el obtener la certificación el sistema envia a la pagina de la empresa It-OkCenter
     *         "http://www.it-okcenter.com/calendario
     *
     **************************************************************************************************************************/

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnAgregar:
                Intent intent6 = new Intent(this, Main2Activity.class);
                startActivity(intent6);
                break;

            case R.id.btnEliminar:
                Intent intent3 = new Intent(this, Main4Activity.class);
                startActivity(intent3);
                break;

            case R.id.btnDetalle:
                Intent intent2 = new Intent(this, Main3Activity.class);
                String fecha = editMesPic.getText().toString();
                intent2.putExtra("FECHA",fecha);
                startActivity(intent2);
                break;

            case R.id.btnproximamente:
                Uri uriUrl = Uri.parse("http://www.it-okcenter.com/calendario/");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(intent1);
                break;

            default:
                break;
        }
    }

    /************************************************************************************************************
     * Se cargan datos de los cursos que tienen certificación
     ************************************************************************************************************/

    public void cargaDatos() {
        dbController.open();

        dbController.insertData("9", "20", "20092016", "23092016", " ", " ", "Fundamentos del Diseño Web", "Navegabilidad, interactividad, usabilidad, arquitectura de la información", "20 horas", "14:00 a 18:00 hrs ", "De lunes a viernes", "$3,500.00", "El participante demostrará conocimientos suficientes sobre:  - El manejo y operación del editor de texto con sistema EMMET  - HTML Y CSS a nivel intermedio - Maquetación y prototipado Web - Sistema Adaptativos para Web dirigidas a moviles", "Nociones básicas de Ofimática", "- Historia de la web - Funcionamiento de una página web  - HTML5 - CSS3  - JavaScript - Publicaciones de una página web", "Av. Tecamachalco 54B Colónia Lomas de Chapultepec  Delegación Miguel Hidalgo C.P. 11000");

        dbController.insertData("9", "20", "20092016", "23092016", " ", " ", "Fundamentos del Diseño Web", "Navegabilidad, interactividad, usabilidad, arquitectura de la información", "20 horas", "14:00 a 18:00 hrs ", "De lunes a viernes", "$3,500.00", "El participante demostrará conocimientos suficientes sobre:  - El manejo y operación del editor de texto con sistema EMMET  - HTML Y CSS a nivel intermedio - Maquetación y prototipado Web - Sistema Adaptativos para Web dirigidas a moviles", "Nociones básicas de Ofimática", "- Historia de la web - Funcionamiento de una página web  - HTML5 - CSS3  - JavaScript - Publicaciones de una página web", "Av. Tecamachalco 54B Colónia Lomas de Chapultepec  Delegación Miguel Hidalgo C.P. 11000");

        dbController.insertData("9", "24", "24092016", "01102016", "08102016", " ", "Programador de aplicaciones moviles jr. utilizando Android", "En los niveles de Introducción, Programador Jr y Programador Senior", "24 horas", "9:00 a 18:00 hrs", "Sabados", "$3,600.00", "El participante demostrará conocimientos suficientes sobre: La operación de los Servicios Android El manejo adecuado de las Herramientas de desarrollo en Android StudioEl uso e implementación de Frameworks El uso e implementación de Fragments El manejo Procesos e hilos en Android La asignación adecuada de los Recursos de una aplicación desarrollada en Android Studio Manejar el almacenamiento de datos en un dispositivo móvil La implantación de la Seguridad y permisos en el sistema operativo Android", "Conocimiento del lenguaje de programación orientada a objetos en Java Nociones básicas de Android Studio", "Introducción a Android Fundamentos de una aplicación Android Servicios  Interfaz de usuario Seguridad y permisos", "Av. Tecamachalco 54B  Colonia Lomas de Chapultepec   Delegación Miguel Hidalgo  C.P. 11000");

        dbController.insertData("9", "26", "30092016", "30092016", "  ", " ", "Linux Administrador", "Diseñado y programado por miles de desarrolladores al rededor del mundo", "30 horas", "9:00 a 16:00 hrs.", "De lunes a viernes", "$3,800.00", "El participante demostrará conocimientos suficientes sobre: -La instalación del sistema operativo Linux Centos -La configuración de entorno Grafico Linux -La configuración de impresión en Linux -Configuracion y manejo de las redes TCP/IP -Programar tareas en el sistema operativo Linux -La configuración y manejo del acceso remoto -Gestion y administracion del sistema operativo mediante la terminal -El manjeo de SSH -El manejo de gestion de permisos y seguridad", " ", "Unidad 1.- Instalación del Sistema Operativo y Ajustes posteriores Unidad 2.- Administración del Sistema de Archivos  Unidad 3.- Administración de Software y servicios Unidad 4.- Gestión de usuarios, grupos y privilegios Unidad 5. Permisos y Atributos del Sistema de Archivos Unidad 6.- Herramientas básicas del Sistema Operativo  Unidad 7.- Redes", "Av. Tecamachalco 54B Colonia Lomas de Chapultepec  Delegación Miguel Hidalgo C.P. 11000");

        dbController.insertData("10", "03", "03102016", "07102016", " ", " ", "Programador de aplicaciones moviles jr. utilizando Android", "En los niveles de Introducción, Programador Jr y Programador Senior ", "24 horas", "De lunes a viernes", "13:00 a 18:00 hrs.", "$3,600.00", "El participante demostrará conocimientos suficientes sobre:La operación de los Servicios Android El manejo adecuado de las Herramientas de desarrollo en Android Studio  El uso e implementación de Frameworks El uso e implementación de Fragments El manejo Procesos e hilos en Android La asignación adecuada de los Recursos de una aplicación desarrollada en Android Studio Manejar el almacenamiento de datos en un dispositivo móvil La implantación de la Seguridad y permisos en el sistema operativo Android", "Conocimiento del lenguaje de programación orientada a objetos en Java Nociones básicas de Android Studio", "Introducción a Android. Fundamentos de una aplicación Android Servicios Interfaz de usuario Seguridad y permisos", "Av. Tecamachalco 54B  Colonia Lomas de Chapultepec   Delegación Miguel Hidalgo C.P. 11000");

       dbController.insertData("10", "15", "15102016", "22102016", "29102016", "  ", "Fundamentos del Diseño Web", "Navegabilidad, interactividad, usabilidad, arquitectura de la información", "20 horas", "9:00 a 16:00 hrs.", "Sabados", "$3,500.00", "El participante demostrará conocimientos suficientes sobre:  - El manejo y operación del editor de texto con sistema EMMET  - HTML Y CSS a nivel intermedio - Maquetación y prototipado Web - Sistema Adaptativos para Web dirigidas a moviles", "Nociones básicas de Ofimática", "- Historia de la web - Funcionamiento de una página web - HTML5  - CSS3  - JavaScript  - Publicaciones de una página web", "Av. Tecamachalco 54B Colónia Lomas de Chapultepec  Delegación Miguel Hidalgo C.P. 11000");

      dbController.insertData("11", "05", "05112016", "12112016", "19112016", " ", "Linux Administrador", "Diseñado y programado por miles de desarrolladores al rededor del mundo", "30 horas", "9:00 a 17:00 hrs.", "Sabados", "$3,800.00", "El participante demostrará conocimientos suficientes sobre: -La instalación del sistema operativo Linux Centos -La configuración de entorno Grafico Linux -La configuración de impresión en Linux -Configuracion y manejo de las redes TCP/IP -Programar tareas en el sistema operativo Linux -La configuración y manejo del acceso remoto -Gestion y administracion del sistema operativo mediante la terminal -El manjeo de SSH -El manejo de gestion de permisos y seguridad", " ", "Unidad 1.- Instalación del Sistema Operativo y Ajustes posteriores Unidad 2.- Administración del Sistema de Archivos  Unidad 3.- Administración de Software y servicios Unidad 4.- Gestión de usuarios, grupos y privilegios Unidad 5. Permisos y Atributos del Sistema de Archivos Unidad 6.- Herramientas básicas del Sistema Operativo Unidad 7.- Redes", "Av. Tecamachalco 54B Colonia Lomas de Chapultepec  Delegación Miguel Hidalgo C.P. 11000");
     }

}

