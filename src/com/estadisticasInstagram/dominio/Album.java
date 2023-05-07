package com.estadisticasInstagram.dominio;

import java.util.ArrayList;
import java.util.List;

public class Album {
//Sumar una lista de albunes
    private String nombre;
    private List<Album> subAlbumes; //hijos del album

    public Album(String nombre) {
        this.nombre = nombre;
        this.subAlbumes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Album> getAlbumList() {
        return subAlbumes;
    }

    public void setAlbumList(List<Album> albumList) {
        this.subAlbumes = albumList;
    }

    public void agregarAlbum (Album hijo) {
        subAlbumes.add(hijo);
    }

    public void eliminarAlbum (Album hijo) {
        subAlbumes.remove(hijo);
    }

    public void eliminarAlbumPadre (Album padre) { // se elimina toda la lista
        subAlbumes.clear();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append("\n");
        imprimirSubAlbumes(sb, subAlbumes, 0);
        return sb.toString();
    }

    private void imprimirSubAlbumes(StringBuilder sb, List<Album> subAlbumes, int nivel) {
        nivel++;
        for (Album album : subAlbumes) {
            for (int i = 0; i < nivel; i++) {
                sb.append("\t");
            }
            sb.append("- ").append(album.getNombre()).append("\n");
            imprimirSubAlbumes(sb, album.getAlbumList(), nivel);
        }
    }

    /*

    public int cantMgAcumulada() {
        int sum = 0;
        for (Publicacion publicacion : publicacionList) {
            sum += publicacion.getCantidadMG();
        }
        return sum;
    }
    public int cantPublicacionesTotal() {
        int cant = 0;
        for (Publicacion publicacion : publicacionList) {
            cant++;
        }
        return cant;
    }

 */
}
