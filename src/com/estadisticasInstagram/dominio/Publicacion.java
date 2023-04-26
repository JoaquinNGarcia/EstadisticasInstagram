package com.estadisticasInstagram.dominio;

import java.util.ArrayList;

public class Publicacion {
    private String nombre, fechaSubida, etiquetasHashtags;
    ArrayList<String> comentarios = new ArrayList<>();
    private int cantidadMG;


    public Publicacion(String nombre, String fechaSubida, String etiquetasHashtags, String comentarios, int cantidadMG) {
        this.nombre = nombre;
        this.fechaSubida = fechaSubida;
        this.etiquetasHashtags = etiquetasHashtags;
        this.comentarios = comentarios;
        this.cantidadMG = cantidadMG;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(String fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public String getEtiquetasHashtags() {
        return etiquetasHashtags;
    }

    public void setEtiquetasHashtags(String etiquetasHashtags) {
        this.etiquetasHashtags = etiquetasHashtags;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getCantidadMG() {
        return cantidadMG;
    }

    public void setCantidadMG(int cantidadMG) {
        this.cantidadMG = cantidadMG;
    }
}


