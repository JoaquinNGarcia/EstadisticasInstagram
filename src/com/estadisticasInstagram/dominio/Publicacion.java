package com.estadisticasInstagram.dominio;

import com.estadisticasInstagram.interfaces.ContenidoMultimedia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

import static com.estadisticasInstagram.ColorsConsole.*;

public abstract class Publicacion implements ContenidoMultimedia, Serializable {
    private String name, Hashtags, type, id;
    private LinkedList<String> listAlbums;

    private LinkedList<String> listComments;
    private LocalDate dateUploaded;
    private int amountLikes;

    public Publicacion(String name, String Hashtags, String type, LocalDate dateUploaded, int amountLikes, String id,LinkedList<String> listComments) {
        this.name = name;
        this.Hashtags = Hashtags;
        this.type = type;
        this.listAlbums = new LinkedList<>();
        this.listComments = listComments;
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

    public LinkedList<String> getListAlbums() {
        return listAlbums;
    }

    public void setListAlbums(LinkedList<String> listAlbums) {
        this.listAlbums = listAlbums;
    }

    public LinkedList<String> getListComments() {
        return listComments;
    }

    public void setListComments(LinkedList<String> listComments) {
        this.listComments = listComments;
    }

    public void addAlbum(Album album) {
        listAlbums.add(album.getName());
    }

    public abstract void reproducirContenido(Publicacion publicacion);

    public void simulateProgressBar(int total, int width, float delayMillis) {
        int progress = 0;
        int prevProgress = -1;

        System.out.println("La duracion de la siguiente publicacion sera de: " + Math.round(delayMillis / 10) + " segundos.");
        System.out.println(BOLD + GREEN + "\t\t\t\tInicio de su reproduccion!" + RESET);
        while (progress <= total) {
            int currentWidth = (int) (((double) progress / total) * width);
            if (progress != prevProgress) {
                String progressBar = buildProgressBar(currentWidth, width, progress);
                System.out.print("\r" + progressBar + " " + progress + "%");
                prevProgress = progress;
            }
            try {
                Thread.sleep((long) delayMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress++;
        }
        System.out.println(BOLD + RED + "\n\t\t\t\tFin de su reproduccion!\n" + RESET);
    }

    private String buildProgressBar(int currentWidth, int totalWidth, int progress) {
        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < currentWidth; i++) {
            if(progress <= 50) {
                progressBar.append(BOLD + GREEN + "=" + RESET);
            } else {
                if (progress <= 80) {
                    progressBar.append(BOLD + YELLOW + "=" + RESET);
                } else {
                    progressBar.append(BOLD + RED + "=" + RESET);
                }
            }
        }
        for (int i = currentWidth; i < totalWidth; i++) {
            progressBar.append(" ");
        }
        progressBar.append("]");
        return progressBar.toString();
    }

}
