package com.estadisticasInstagram.dominio;

import com.estadisticasInstagram.interfaces.ContenidoMultimedia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

import static com.estadisticasInstagram.ColorsConsole.BOLD;
import static com.estadisticasInstagram.ColorsConsole.RESET;

public class Imagen extends Publicacion implements ContenidoMultimedia, Serializable {
    private float resolution;
    private int width, height;
    private String filtro;

    public Imagen(String name, String Hashtags, String type, LocalDate dateUploaded, int amountLikes, String id, LinkedList<String> listComments,int progress, float resolution, int width, int heigth, String filtro) {
        super(name, Hashtags, type, dateUploaded, amountLikes, id, listComments,progress);
        this.resolution = resolution;
        this.width = width;
        this.height = heigth;
        this.filtro = filtro;
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
        return height;
    }

    public void setHeight(int heigth) {
        this.height = heigth;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public void setFilter() {
        // System.o ut.println("Se aplico filtro en imagen");
    }

    @Override
    public void reproducirContenido(Publicacion publicacion) {
        System.out.println(BOLD + "Reproduciendo: " + publicacion.getType() + "\t" + publicacion.getId() + "\t" + publicacion.getName() + RESET);
        if (publicacion instanceof Imagen imagen) {
            System.out.println(BOLD + "Filtro: " + imagen.getFiltro());
            simulateProgressBar(100, 50, 100, imagen.getProgress(),imagen.getId()); // 10 segundos por defecto.
        }
    }
}
