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

        LinkedList<Publicacion> listaPublicacion = new LinkedList<>();
        Publicacion publi = null;
        File f = new File("archivoPublicaciones.txt");
        Scanner s;
        try {
            s = new Scanner(f);
            while (s.hasNextLine()){
                String linea = s.nextLine();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*-\\s*");
                nombre = sl.next();
                fechaSubida = sl.next();
                etiquetasHashtags = sl.next();
                cantidadMG = Integer.parseInt(sl.next());
                /*tipo = sl.next();
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
                }*/
                resolucion = Float.parseFloat(sl.next());
                ancho = Integer.parseInt(sl.next());
                alto = Integer.parseInt(sl.next());
                publi = new Imagen(nombre, fechaSubida, etiquetasHashtags, cantidadMG, resolucion, ancho, alto);
                System.out.println("readPublicaciones - hola 3");
                listaPublicacion.add(publi);
                System.out.println("readPublicaciones - hola 4");
                sl.close();
            }
            System.out.println("readPublicaciones - hola 1");
            s.close();
        } catch (FileNotFoundException ex) {
            System.out.println("readPublicaciones - aca?");
            throw new RuntimeException(ex);
            // ex.printStackTrace();
        }
        System.out.println("readPublicaciones - hola 2");
        return listaPublicacion;
    }
}
