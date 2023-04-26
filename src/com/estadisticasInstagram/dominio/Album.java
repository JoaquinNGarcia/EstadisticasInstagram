package com.estadisticasInstagram.dominio;

import java.util.ArrayList;

public class Album {
    private ArrayList<Publicacion> publicacionList = new ArrayList<>();
    private ArrayList<Album> albumList = new ArrayList<>();

    public int cantMgAcumulada() {
        int sum = 0;
        for (Publicacion publicacion : publicacionList) {
            sum += publicacion.getCantidadMG();
        }
        return sum;
    }
    public int cantPublicacionesTotal() {
        int cant = 0;
        for (Publicacion publicacion : publicacionList) {
            cant++;
        }
        return cant;
    }

    // to do, por cada una de las subclases
    public int cantPublicacionesPorTipo() {

    }


}
