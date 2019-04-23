package com.luisgomez.listas_sqlite_forma2;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText nombreSitio, nombreCiudad, nombrePais;
    Button guardar, mostrar, borrar;
    ListView listaSitios;

    SitiosSQLiteHelper database;

    // Variable para el tamaño de los campos de la tabla de base de datos
    int tamanoCamposBD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreSitio = (EditText) findViewById(R.id.nombreSitio);
        nombreCiudad = (EditText) findViewById(R.id.nombreCiudad);
        nombrePais = (EditText) findViewById(R.id.nombrePais);

        guardar = (Button) findViewById(R.id.guardar);
        mostrar = (Button) findViewById(R.id.mostrar);
        borrar = (Button) findViewById(R.id.borrar);

        listaSitios = (ListView) findViewById(R.id.mostrar_datos_guardados);

        // Inicio la base de datos
        database = new SitiosSQLiteHelper(MainActivity.this);

        // Le digo a los botones que al hacer click van a hacer algo (esa funcion que van a hacer esta dentro del metodo onClick
        guardar.setOnClickListener(this);
        mostrar.setOnClickListener(this);
        borrar.setOnClickListener(this);

        // Lista que va a mostrar los datos almacenados en la base de datos
        List<Sitio> list = database.getAllData();

        // Arraylist que va a guardar los sitios
        ArrayList<Sitio> SitiosArrayList = new ArrayList<Sitio>();

        // obteniendo el tamaño de la lista y lo guarda en tamanoCamposBD
        tamanoCamposBD = list.size();

        // Bucle que recorre la base de datos
        for (Sitio data : list) {

            // El bucle va  a recoger los atributos de cada sitio (objeto) de la base de datos
            String nombreSitioGuardado = data.getNombreSitio();
            String nombreCiudadGuardada = data.getNombreCiudad();
            String nombrePaisGuardado = data.getNombrePais();

            // Añado los datos al arraylist
            SitiosArrayList.add(new Sitio(nombreSitioGuardado, nombreCiudadGuardada,
                    nombrePaisGuardado));

        }

        // Adaptador actualiza el arraylist
        SitiosAdapter adapter = new SitiosAdapter(MainActivity.this,
                SitiosArrayList);

        // Adaptador actualiza la lista
        listaSitios.setAdapter(adapter);

        // Notifying adapter
        //adapter.notifyDataSetChanged();

        // por ultimo, mostramos la lista (listview)
        listaSitios.setVisibility(View.VISIBLE);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guardar:

                // Guardo el valor de los campos
                String nombreSitio = this.nombreSitio.getText().toString();
                String nombreCiudad = this.nombreCiudad.getText().toString();
                String nombrePais = this.nombrePais.getText().toString();

                // Validacion para comprobar que todos los campos estan rellenos (lo he metido como opcional)
                if (TextUtils.isEmpty(nombreSitio) || TextUtils.isEmpty(nombreCiudad)
                        || TextUtils.isEmpty(nombrePais)) {

                    // Muestra mensaje para que se rellenen todos los datos
                    Toast.makeText(MainActivity.this, "Rellene todos los campos.",
                            Toast.LENGTH_SHORT).show();
                } else {

                    // Insertamos datos añadidos a la base de datos
                    database.insertData(new Sitio(nombreSitio, nombreCiudad,
                            nombrePais));

                    // Muestro mensaje para verificar que se ha añadido el sitio correctamente
                    Toast.makeText(MainActivity.this, "Sitio guardado correctamente.",
                            Toast.LENGTH_SHORT).show();


                    // Para poner losdatos almacenados de la base de datos en la lista
                    List<Sitio> list = database.getAllData();

                    // Arraylist que va a guardar los sitios
                    ArrayList<Sitio> SitiosArrayList = new ArrayList<Sitio>();

                    // obteniendo el tamaño de la lista y lo guarda en tamanoCamposBD
                    tamanoCamposBD = list.size();


                        // Bucle que recorre la base de datos
                        for (Sitio data : list) {

                            // El bucle va  a recoger los atributos de cada sitio (objeto) de la base de datos
                            String nombreGuardado = data.getNombreSitio();
                            String emailGuardado = data.getNombreCiudad();
                            String direccionGuardada = data.getNombrePais();

                            // Añado los datos al arraylist
                            SitiosArrayList.add(new Sitio(nombreGuardado, emailGuardado,
                                    direccionGuardada));

                        }

                        // Adaptador actualiza el arraylist
                        SitiosAdapter adapter = new SitiosAdapter(MainActivity.this,
                                SitiosArrayList);

                        // Adaptador actualiza la lista
                        listaSitios.setAdapter(adapter);

                        // Notifying adapter
                        //adapter.notifyDataSetChanged();

                        // por ultimo, mostramos la lista (listview)
                        listaSitios.setVisibility(View.VISIBLE);


                }

                break;

            case R.id.mostrar:

                // Para poner losdatos almacenados de la base de datos en la lista
                List<Sitio> list = database.getAllData();

                // Arraylist que va a guardar los sitios
                ArrayList<Sitio> SitiosArrayList = new ArrayList<Sitio>();

                // obteniendo el tamaño de la lista y lo guarda en tamanoCamposBD
                tamanoCamposBD = list.size();

                // Validacion para Comprobar si hay datos en la base de datos (Si pulsamos el boton Mostrar y no hay datos en la tabla, entonces
                // un Toasts nos va a alertar con el mensaje No hay datos en la tabla
                if (tamanoCamposBD == 0) {

                    // Si no hay datos , muestro mensaje
                    Toast.makeText(MainActivity.this, "No hay datos en la tabla.",
                            Toast.LENGTH_SHORT).show();


                } else {

                    // Bucle que recorre la base de datos
                    for (Sitio data : list) {

                        // El bucle va  a recoger los atributos de cada sitio (objeto) de la base de datos
                        String nombreGuardado = data.getNombreSitio();
                        String emailGuardado = data.getNombreCiudad();
                        String direccionGuardada = data.getNombrePais();

                        // Añado los datos al arraylist
                        SitiosArrayList.add(new Sitio(nombreGuardado, emailGuardado,
                                direccionGuardada));

                    }

                    // Adaptador actualiza el arraylist
                    SitiosAdapter adapter = new SitiosAdapter(MainActivity.this,
                            SitiosArrayList);

                    // Adaptador actualiza la lista
                    listaSitios.setAdapter(adapter);

                    // Notifying adapter
                    //adapter.notifyDataSetChanged();

                    // por ultimo, mostramos la lista (listview)
                    listaSitios.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.borrar:

                // Validacion para comprobar si hay datos que borrar o no
                if (tamanoCamposBD == 0) {

                    // Si no hay datos que borrar, mostramos mensaje
                    Toast.makeText(MainActivity.this,"No existen datos para eliminar", Toast.LENGTH_SHORT).show();

                } else {

                    // Pero si, si existen datos, entonces se borran los datos y se oculta la vista de lista
                    Toast.makeText(MainActivity.this, "Datos borrados correctamente.",
                            Toast.LENGTH_SHORT).show();
                    database.deleteTable();
                    listaSitios.setVisibility(View.GONE);
                }

                break;
        }

    }

}
