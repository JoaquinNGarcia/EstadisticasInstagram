package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.util.LinkedList;

public class Video extends Publicacion {
    private float duracion, resolucion;
    private int cantidadDeCuadros;

    // Avanzar, detener, aplicar filtro


    public Video(String nombre, String etiquetasHashtags, String tipo, LinkedList<String> listaAlbumes, LocalDate fechaSubida, int cantidadMG,String id, float duracion, float resolucion, int cantidadDeCuadros) {
        super(nombre, etiquetasHashtags, tipo, listaAlbumes, fechaSubida, cantidadMG,id);
        this.duracion = duracion;
        this.resolucion = resolucion;
        this.cantidadDeCuadros = cantidadDeCuadros;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public float getResolucion() {
        return resolucion;
    }

    public void setResolucion(float resolucion) {
        this.resolucion = resolucion;
    }

    public int getCantidadDeCuadros() {
        return cantidadDeCuadros;
    }

    public void setCantidadDeCuadros(int cantidadDeCuadros) {
        this.cantidadDeCuadros = cantidadDeCuadros;
    }
}

