package com.estadisticasInstagram.dominio;

import java.util.*;

public class Album {
    // Sumar una lista de albunes
    private String nombre;
    private List<Album> subAlbumes; // hijos del album

    private List<Publicacion> publicaciones;
    // private static LinkedList<Publicacion> publicaciones; no deberia ser linkedList?

    public Album(String nombre) {
        this.nombre = nombre;
        this.subAlbumes = new ArrayList<>();
        this.publicaciones = new ArrayList<>();
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

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public void agregarPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
    }

    public void eliminarPublicaciones() {
        publicaciones.clear();
    }

    public void agregarAlbum(Album hijo) {
        subAlbumes.add(hijo);
    }

    public void eliminarAlbum(int indiceEliminar) {
        subAlbumes.remove(indiceEliminar);
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

    public int cantMgAcumulada() {
        int sum = 0;
        for (int i = 0; i < publicaciones.size(); i++)
            sum += publicaciones.get(i).getCantidadMG();
        return sum;
    }

    public Map<Class<Publicacion>, Integer> cantPublicacionesTipo() {
        Map<Class<Publicacion>, Integer> mapa = new HashMap<>(); // creo el mapa que va a guardar la cantidad
        for (Publicacion publicacion : publicaciones) {
            Class<Publicacion> clase = (Class<Publicacion>) publicacion.getClass(); // creo el objeto "clase" que puede
                                                                                    // ser de cualquier tipo y obtengo
                                                                                    // de que clase es
            if (mapa.containsKey(clase)) // se fija si ya esta la clase en el mapa
                mapa.put(clase, mapa.get(clase) + 1); // si esta incrementa {.put asigna clave/valor} {.get recupera el
                                                      // valor asignado a una clave}
            else
                mapa.put(clase, 1); // si no esta inicia el contador en 1;
        }
        return mapa;
    }

    public int cantPublicacionesTotal() {
        int cant = 0;
        for (Publicacion publicacion : publicaciones) {
            cant++;
        }
        return cant;
    }
}
