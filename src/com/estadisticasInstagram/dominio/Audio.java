package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.util.LinkedList;

public class Audio extends Publicacion {
    private float duracion;
    private int velocidadBits;
    //avanzar, detener;


    public Audio(String nombre, String etiquetasHashtags, String tipo, LinkedList<String> listaAlbumes, LocalDate fechaSubida, int cantidadMG,String id, float duracion, int velocidadBits) {
        super(nombre, etiquetasHashtags, tipo, listaAlbumes, fechaSubida, cantidadMG,id);
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

