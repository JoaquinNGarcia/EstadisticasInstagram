package com.estadisticasInstagram.dominio;

import java.time.LocalDate;

public class Video extends Publicacion {
    private float duracion, resolucion;
    private int cantidadDeCuadros;


    // Avanzar, detener, aplicar filtro


    public Video(String nombre, String etiquetasHashtags, String tipo, LocalDate fechaSubida, int cantidadMG, float duracion, float resolucion, int cantidadDeCuadros) {
        super(nombre, etiquetasHashtags, tipo, fechaSubida, cantidadMG);
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
