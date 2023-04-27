package com.estadisticasInstagram.archivos;

import com.estadisticasInstagram.dominio.Imagen;
import com.estadisticasInstagram.dominio.Publicacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class readPublicaciones {
    public static LinkedList<Publicacion> cargaListaPublicacion() {
        String nombre, fechaSubida, etiquetasHashtags, tipo;
        // ArrayList<String> comentarios = new ArrayList<>();
        int cantidadMG;
        // Video
        float duracion, resolucion;
        int cantidadDeCuadros;
        //Imagen
        int ancho, alto;
        //Audio
        int velocidadBits;

        //Sumar al archivoPublicaciones los tipos y los voy leyendo con GetClass


        LinkedList<Publicacion> listaPublicacion = new LinkedList<>();
        Publicacion publi = null;
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
                etiquetasHashtags = sl.next();
                cantidadMG = Integer.parseInt(sl.next());
                /*
                tipo = sl.next();
                switch (tipo){
                    case "Video":{
                        duracion = Float.parseFloat(sl.next());
                        resolucion = Float.parseFloat(sl.next());
                        cantidadDeCuadros = Integer.parseInt(sl.next());
                        publi = new Video(nombre, fechaSubida, etiquetasHashtags, cantidadMG, duracion, resolucion, cantidadDeCuadros);
                        break;
                    }
                    case "Imagen":{
                        resolucion = Float.parseFloat(sl.next());
                        ancho = Integer.parseInt(sl.next());
                        alto = Integer.parseInt(sl.next());
                        publi = new Imagen(nombre, fechaSubida, etiquetasHashtags, cantidadMG, resolucion, ancho, alto);
                        break;
                    }
                    case "Audio":{
                        duracion = Float.parseFloat(sl.next());
                        velocidadBits = Integer.parseInt(sl.next());
                        publi = new Audio(nombre, fechaSubida, etiquetasHashtags, cantidadMG, duracion, velocidadBits);
                        break;
                    }
                }
                */
                resolucion = Float.parseFloat(sl.next());
                ancho = Integer.parseInt(sl.next());
                alto = Integer.parseInt(sl.next());
                publi = new Imagen(nombre, fechaSubida, etiquetasHashtags, cantidadMG, resolucion, ancho, alto);
                listaPublicacion.add(publi);
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
