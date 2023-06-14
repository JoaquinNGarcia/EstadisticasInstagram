package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.util.LinkedList;

import static com.estadisticasInstagram.ColorsConsole.BOLD;
import static com.estadisticasInstagram.ColorsConsole.RESET;

public class Audio extends Publicacion {
    private float duration;
    private int velocityBits;
    // avanzar, detener;

    public Audio(String name, String Hashtags, String type,
                 LocalDate dateUploaded, int amountLikes, String id, float duration, int velocityBits,LinkedList<String> listComments) {
        super(name, Hashtags, type, dateUploaded, amountLikes, id,listComments);
        this.duration = duration;
        this.velocityBits = velocityBits;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getVelocityBits() {
        return velocityBits;
    }

    public void setVelocityBits(int velocityBits) {
        this.velocityBits = velocityBits;
    }

    @Override
    public void reproducirContenido(Publicacion publicacion) {
        System.out.println(BOLD + "Reproduciendo: " + publicacion.getType() + "\t" +  publicacion.getId() + "\t" + publicacion.getName() + RESET);
        if (publicacion instanceof Audio audio) {
            simulateProgressBar(100, 50, audio.getDuration());
        }
    }
}
