package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.util.LinkedList;

public abstract class Publicacion { // NO VA A HABER instancias
    private String nombre, etiquetasHashtags, tipo;
    private LinkedList<String> listaAlbumes;

    private LocalDate fechaSubida;
    private int cantidadMG;


    // private LinkedList<Album> listaAlbum;
    // ArrayList<String> comentarios = new ArrayList<>();


    public Publicacion(String nombre, String etiquetasHashtags, String tipo, LinkedList<String> listaAlbumes, LocalDate fechaSubida, int cantidadMG) {
        this.nombre = nombre;
        this.etiquetasHashtags = etiquetasHashtags;
        this.tipo = tipo;
        this.listaAlbumes = listaAlbumes;
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

    public LinkedList<String> getListaAlbumes() {
        return listaAlbumes;
    }

    public void setListaAlbumes(LinkedList<String> listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
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
