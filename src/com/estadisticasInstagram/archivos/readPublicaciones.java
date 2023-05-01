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
    public static LinkedList<Publicacion> cargaListaPublicacion() {
        String nombre, etiquetasHashtags, tipo;
        // ArrayList<String> comentarios = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaSubida;
        int cantidadMG;
        // Video
        float duracion, resolucion;
        int cantidadDeCuadros;
        //Imagen
        int ancho, alto;
        //Audio
        int velocidadBits;

        LinkedList<Publicacion> listaPublicacion = new LinkedList<>();
        
        File f = new File("archivoPublicaciones.txt");
        Scanner s;
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*-\\s*");
                nombre = sl.next();
                fechaSubida = sl.next();
                LocalDate fecha = LocalDate.parse(fechaSubida, formatter);
                etiquetasHashtags = sl.next();
                cantidadMG = Integer.parseInt(sl.next());
                tipo = sl.next();
                switch (tipo){
                    case "Video":{
                        duracion = Float.parseFloat(sl.next());
                        resolucion = Float.parseFloat(sl.next());
                        cantidadDeCuadros = Integer.parseInt(sl.next());
                        Video video = new Video(nombre, etiquetasHashtags, tipo, fecha, cantidadMG, duracion, resolucion, cantidadDeCuadros);
                        listaPublicacion.add(video);
                        break;
                    }
                    case "Imagen":{
                        resolucion = Float.parseFloat(sl.next());
                        ancho = Integer.parseInt(sl.next());
                        alto = Integer.parseInt(sl.next());
                        Imagen imagen = new Imagen(nombre, etiquetasHashtags, tipo, fecha, cantidadMG, resolucion, ancho, alto);
                        listaPublicacion.add(imagen);
                        break;
                    }
                    case "Audio":{
                        duracion = Float.parseFloat(sl.next());
                        velocidadBits = Integer.parseInt(sl.next());
                        Audio audio = new Audio(nombre, etiquetasHashtags, tipo, fecha, cantidadMG, duracion, velocidadBits);
                        listaPublicacion.add(audio);
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
        return listaPublicacion;
    }
}
