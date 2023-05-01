package com.estadisticasInstagram.dominio;

import java.time.LocalDate;

public class Imagen extends Publicacion {
    private float resolucion;
    private int ancho, alto;
    // Aplicar filtros


    public Imagen(String nombre, String etiquetasHashtags, String tipo, LocalDate fechaSubida, int cantidadMG, float resolucion, int ancho, int alto) {
        super(nombre, etiquetasHashtags, tipo, fechaSubida, cantidadMG);
        this.resolucion = resolucion;
        this.ancho = ancho;
        this.alto = alto;
    }

    public float getResolucion() {
        return resolucion;
    }

    public void setResolucion(float resolucion) {
        this.resolucion = resolucion;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}
