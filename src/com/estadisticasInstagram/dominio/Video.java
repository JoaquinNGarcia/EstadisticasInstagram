package com.estadisticasInstagram.dominio;

import com.estadisticasInstagram.interfaces.ContenidoMultimedia;

import java.time.LocalDate;
import java.util.LinkedList;

import static com.estadisticasInstagram.ColorsConsole.BOLD;
import static com.estadisticasInstagram.ColorsConsole.RESET;

public class Video extends Publicacion implements ContenidoMultimedia {
    private float duration, resolution;
    private int totalFrames;
    private String filtro;

    public Video(String name, String Hashtags, String type, LocalDate dateUploaded, int amountLikes, String id, LinkedList<String> listComments, float duration, float resolution, int totalFrames, String filtro) {
        super(name, Hashtags, type, dateUploaded, amountLikes, id, listComments);
        this.duration = duration;
        this.resolution = resolution;
        this.totalFrames = totalFrames;
        this.filtro = filtro;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getResolution() {
        return resolution;
    }

    public void setResolution(float resolution) {
        this.resolution = resolution;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
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
        System.out.println(BOLD + "Reproduciendo: " + publicacion.getType() + "\t" +  publicacion.getId() + "\t" + publicacion.getName() + RESET);
        if (publicacion instanceof Video video) {
            System.out.println(BOLD + "Filtro: " + video.getFiltro());
            simulateProgressBar(100, 50, video.getDuration());
        }
    }
}
