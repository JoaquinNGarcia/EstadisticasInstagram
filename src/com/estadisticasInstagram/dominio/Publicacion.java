package com.estadisticasInstagram.dominio;

public class Publicacion {
    private String nombre, fechaSubida, etiquetasHashtags;
    private int cantidadMG;
   // ArrayList<String> comentarios = new ArrayList<>();


    public Publicacion(String nombre, String fechaSubida, String etiquetasHashtags, int cantidadMG) {
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


    public int getCantidadMG() {
        return cantidadMG;
    }

    public void setCantidadMG(int cantidadMG) {
        this.cantidadMG = cantidadMG;
    }
}
