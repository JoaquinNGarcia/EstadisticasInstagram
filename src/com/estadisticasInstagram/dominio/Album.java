package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.util.*;

public class Album {
    private String name;
    private List<Album> subAlbums;

    private List<Publicacion> publications;

    public Album(String name) {
        this.name = name;
        this.subAlbums = new ArrayList<>();
        this.publications = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbumList() {
        return subAlbums;
    }

    public void setAlbumList(List<Album> albumList) {
        this.subAlbums = albumList;
    }

    public List<Publicacion> getPublications() {
        return publications;
    }

    public void setPublications(List<Publicacion> publications) {
        this.publications = publications;
    }

    public void addPublication(Publicacion publicacion) {
        publications.add(publicacion);
    }

    public void removePublications() {
        publications.clear();
    }

    public void addAlbum(Album hijo) {
        subAlbums.add(hijo);
    }

    public void removeAlbum(int indiceEliminar) {
        subAlbums.remove(indiceEliminar);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        showAlbums(sb, subAlbums, 0);
        return sb.toString();
    }

    private void showAlbums(StringBuilder sb, List<Album> subAlbums, int nivel) {
        nivel++;
        subAlbums.sort(Comparator.comparing(Album::getName));
        for (Album album : subAlbums) {
            for (int i = 0; i < nivel; i++) {
                sb.append("\t");
            }
            sb.append("- ").append(album.getName()).append("\n");
            showAlbums(sb, album.getAlbumList(), nivel);
        }
    }

    public int totalLikes() {
        int sum = 0;
        for (int i = 0; i < publications.size(); i++)
            sum += publications.get(i).getAmountLikes();
        return sum;
    }

    public Map<Class<Publicacion>, Integer> amountPublicationsType() {
        Map<Class<Publicacion>, Integer> mapa = new HashMap<>();
        for (Publicacion publicacion : publications) {
            Class<Publicacion> clase = (Class<Publicacion>) publicacion.getClass();
            if (mapa.containsKey(clase))
                mapa.put(clase, mapa.get(clase) + 1);
            else
                mapa.put(clase, 1);
        }
        return mapa;
    }

    public int totalPublications() {
        int amount = 0;
        for (Publicacion publicacion : publications) {
            amount++;
        }
        return amount;
    }
}
