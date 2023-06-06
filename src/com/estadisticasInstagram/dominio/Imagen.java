package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.util.LinkedList;

public class Imagen extends Publicacion {
    private float resolution;
    private int width, heigth;
    // Aplicar filtros

    public Imagen(String name, String Hashtags, String type,
                  LocalDate dateUploaded, int amountLikes, String id, float resolution, int width, int heigth, LinkedList<String> listComments) {
        super(name, Hashtags, type, dateUploaded, amountLikes, id,listComments);
        this.resolution = resolution;
        this.width = width;
        this.heigth = heigth;
    }

    public float getResolution() {
        return resolution;
    }

    public void setResolution(float resolution) {
        this.resolution = resolution;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return heigth;
    }

    public void setHeight(int heigth) {
        this.heigth = heigth;
    }

    public void setFilter() {
        // System.o ut.println("Se aplico filtro en imagen");
    }
}
