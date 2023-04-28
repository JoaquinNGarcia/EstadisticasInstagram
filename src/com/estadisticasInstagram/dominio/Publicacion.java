package com.estadisticasInstagram.dominio;

import java.time.LocalDate;

public abstract class Publicacion { // NO VA A HABER instancias, va a ser abstracta
    // Hacer fecha con una metodo propio de java https://www.aluracursos.com/blog/como-convertir-string-para-date-en-java
    private String nombre, etiquetasHashtags;
    private LocalDate fechaSubida;
    //private String tipo;
    private int cantidadMG;
   // ArrayList<String> comentarios = new ArrayList<>();


    public Publicacion(String nombre, LocalDate fechaSubida, String etiquetasHashtags, int cantidadMG) {
        this.nombre = nombre;
        this.fechaSubida = fechaSubida;
        this.etiquetasHashtags = etiquetasHashtags;
        this.cantidadMG = cantidadMG;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public String getEtiquetasHashtags() {
        return etiquetasHashtags;
    }

    public void setEtiquetasHashtags(String etiquetasHashtags) {
        this.etiquetasHashtags = etiquetasHashtags;
    }

    public int getCantidadMG() {
        return cantidadMG;
    }

    public void setCantidadMG(int cantidadMG) {
        this.cantidadMG = cantidadMG;
    }
}
