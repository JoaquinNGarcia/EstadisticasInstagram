package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Publicacion { // NO VA A HABER instancias
    private String nombre, etiquetasHashtags, tipo;
    private LocalDate fechaSubida;
    private int cantidadMG;
   // ArrayList<String> comentarios = new ArrayList<>();

   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Publicacion(String nombre, String etiquetasHashtags, String tipo, LocalDate fechaSubida, int cantidadMG) {
        this.nombre = nombre;
        this.etiquetasHashtags = etiquetasHashtags;
        this.tipo = tipo;
        this.fechaSubida = fechaSubida;
        this.cantidadMG = cantidadMG;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public int getCantidadMG() {
        return cantidadMG;
    }

    public void setCantidadMG(int cantidadMG) {
        this.cantidadMG = cantidadMG;
    }

    public void aplicarFiltro(){};
}
