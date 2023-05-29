package com.estadisticasInstagram.dominio;

import java.time.LocalDate;
import java.util.LinkedList;

public class Video extends Publicacion {
    private float duration, resolution;
    private int totalFrames;

    public Video(String name, String Hashtags, String type,
                 LocalDate dateUploaded, int amountLikes, String id, float duration, float resolution, int totalFrames) {
        super(name, Hashtags, type, dateUploaded, amountLikes, id);
        this.duration = duration;
        this.resolution = resolution;
        this.totalFrames = totalFrames;
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
}
