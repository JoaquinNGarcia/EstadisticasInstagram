package com.estadisticasInstagram.dominio;

public class Imagen extends Publicacion {
    private float resolucion;
    private int ancho, alto;

    // Aplicar filtros


    public Imagen(String nombre, String fechaSubida, String etiquetasHashtags, String comentarios, int cantidadMG, float resolucion, int ancho, int alto) {
        super(nombre, fechaSubida, etiquetasHashtags, comentarios, cantidadMG);
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
