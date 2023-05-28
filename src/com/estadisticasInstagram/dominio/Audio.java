package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.util.LinkedList;

public class Audio extends Publicacion {
    private float duration;
    private int velocityBits;
    // avanzar, detener;

    public Audio(String name, String Hashtags, String type,
                 LocalDate dateUploaded, int amountLikes, String id, float duration, int velocityBits) {
        super(name, Hashtags, type, dateUploaded, amountLikes, id);
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
}
