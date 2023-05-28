package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.util.LinkedList;

public abstract class Publicacion { // NO VA A HABER instancias
    private String name, Hashtags, type, id;
    private LinkedList<String> listAlbums;

    private LocalDate dateUploaded;
    private int amountLikes;

    public Publicacion(String name, String Hashtags, String type, LocalDate dateUploaded, int amountLikes, String id) {
        this.name = name;
        this.Hashtags = Hashtags;
        this.type = type;
        this.listAlbums = new LinkedList<>();
        this.dateUploaded = dateUploaded;
        this.amountLikes = amountLikes;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashtags() {
        return Hashtags;
    }

    public void setHashtags(String Hashtags) {
        this.Hashtags = Hashtags;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public LinkedList<String> getAlbumList() {
        return listAlbums;
    }

    public void setAlbumList(LinkedList<String> listAlbums) {
        this.listAlbums = listAlbums;
    }

    public LocalDate getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(LocalDate dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public int getAmountLikes() {
        return amountLikes;
    }

    public void setAmountLikes(int amountLikes) {
        this.amountLikes = amountLikes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addAlbum(Album album) {
        listAlbums.add(album.getName());
    }

    public void setFilter() {
    };
}
