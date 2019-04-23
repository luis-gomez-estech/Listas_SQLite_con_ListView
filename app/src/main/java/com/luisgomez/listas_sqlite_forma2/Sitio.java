package com.luisgomez.listas_sqlite_forma2;

public class Sitio {

    String nombreSitio, nombreCiudad, nombrePais;

    // Constructor
    public Sitio(String nombre, String email, String direccion) {
        this.nombreSitio = nombre;
        this.nombreCiudad = email;
        this.nombrePais = direccion;
    }

    // obtengo los Getter de los campos
    public String getNombreSitio() {
        return nombreSitio;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public String getNombrePais() {
        return nombrePais;
    }

}
