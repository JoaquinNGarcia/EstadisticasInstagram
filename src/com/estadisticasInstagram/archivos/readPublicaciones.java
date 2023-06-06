package com.estadisticasInstagram.archivos;

import com.estadisticasInstagram.dominio.Audio;
import com.estadisticasInstagram.dominio.Imagen;
import com.estadisticasInstagram.dominio.Publicacion;
import com.estadisticasInstagram.dominio.Video;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

public class readPublicaciones {
    public static LinkedList<Publicacion> uploadPublicationList() {
        String name, Hashtags, type,id, comments;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateUploaded;
        int amountLikes;
        // Video
        float duration, resolution;
        int totalFrames;
        // Imagen
        int width, heigth;
        // Audio
        int velocityBits;

        LinkedList<Publicacion> listPublication = new LinkedList<>();
        File f = new File("archivoPublicaciones.txt");
        Scanner s;
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*-\\s*");
                id = sl.next();
                name = sl.next();
                dateUploaded = sl.next();
                LocalDate fecha = LocalDate.parse(dateUploaded, formatter);
                Hashtags = sl.next();
                amountLikes = Integer.parseInt(sl.next());
                type = sl.next();
                comments = sl.next();
                LinkedList<String> listComments = new LinkedList<>();
                String[] arrayComments = comments.split("_");
                for (String comment : arrayComments) {
                    listComments.add(comment);
                }
                switch (type) {
                    case "Video": {
                        duration = Float.parseFloat(sl.next());
                        resolution = Float.parseFloat(sl.next());
                        totalFrames = Integer.parseInt(sl.next());
                        Video video = new Video(name, Hashtags, type, fecha, amountLikes, id, duration, resolution, totalFrames, listComments);
                        listPublication.add(video);
                        break;
                    }
                    case "Imagen": {
                        resolution = Float.parseFloat(sl.next());
                        width = Integer.parseInt(sl.next());
                        heigth = Integer.parseInt(sl.next());
                        Imagen imagen = new Imagen(name, Hashtags, type, fecha, amountLikes, id, resolution, width, heigth, listComments);
                        listPublication.add(imagen);
                        break;
                    }
                    case "Audio": {
                        duration = Float.parseFloat(sl.next());
                        velocityBits = Integer.parseInt(sl.next());
                        Audio audio = new Audio(name, Hashtags, type, fecha, amountLikes, id, duration, velocityBits, listComments);
                        listPublication.add(audio);
                        break;
                    }
                    default:
                        System.out.println("Tipo de publicación no reconocido.");
                }
                sl.close();
            }
            s.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
            // ex.printStackTrace();
        }
        return listPublication;
    }
}
