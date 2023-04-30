package com.estadisticasInstagram.dominio;

public class Audio extends Publicacion {
    private float duracion;
    private int velocidadBits;
    //avanzar, detener;


    public Audio(String nombre, String fechaSubida, String etiquetasHashtags, String tipo, int cantidadMG, float duracion, int velocidadBits) {
        super(nombre, fechaSubida, etiquetasHashtags, tipo, cantidadMG);
        this.duracion = duracion;
        this.velocidadBits = velocidadBits;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public int getVelocidadBits() {
        return velocidadBits;
    }

    public void setVelocidadBits(int velocidadBits) {
        this.velocidadBits = velocidadBits;
    }
}
