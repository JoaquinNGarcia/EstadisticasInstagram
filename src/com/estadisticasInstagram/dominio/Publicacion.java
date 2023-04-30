package com.estadisticasInstagram.dominio;

public abstract class Publicacion { // NO VA A HABER instancias, va a ser abstracta
    // Hacer fecha con una metodo propio de java https://www.aluracursos.com/blog/como-convertir-string-para-date-en-java
    private String nombre, fechaSubida, etiquetasHashtags, tipo;
    private int cantidadMG;
   // ArrayList<String> comentarios = new ArrayList<>();

    public Publicacion(String nombre, String fechaSubida, String etiquetasHashtags, String tipo, int cantidadMG) {
        this.nombre = nombre;
        this.fechaSubida = fechaSubida;
        this.etiquetasHashtags = etiquetasHashtags;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidadMG() {
        return cantidadMG;
    }

    public void setCantidadMG(int cantidadMG) {
        this.cantidadMG = cantidadMG;
    }
}
