package com.estadisticasInstagram.dominio;

public class Video extends Publicacion {
    private float duracion, resolucion;
    private int cantidadDeCuadros;


    // Avanzar, detener, aplicar filtro


    public Video(String nombre, String fechaSubida, String etiquetasHashtags, int cantidadMG, float duracion, float resolucion, int cantidadDeCuadros) {
        super(nombre, fechaSubida, etiquetasHashtags, cantidadMG);
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
