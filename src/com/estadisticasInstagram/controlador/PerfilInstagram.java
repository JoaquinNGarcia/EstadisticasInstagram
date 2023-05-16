package com.estadisticasInstagram.controlador;

import com.estadisticasInstagram.dominio.*;

import java.util.*;

public class PerfilInstagram { // NO VA A HABER instancias
    private LinkedList<Publicacion> listaPublicacion;
    private LinkedList<Album> listaAlbumes = new LinkedList<>(); // podemos hacer tree o el list

    public PerfilInstagram(LinkedList<Publicacion> listaPublicacion) {
        this.listaPublicacion = listaPublicacion;
    }

    public LinkedList<Publicacion> getListaPublicacion() {
        return listaPublicacion;
    }

    public void setListaPublicacion(LinkedList<Publicacion> listaPublicacion) {
        this.listaPublicacion = listaPublicacion;
    }

    public LinkedList<Album> getListaAlbumes() {
        return listaAlbumes;
    }

    public void setListaAlbumes(LinkedList<Album> listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
    }

    /*public void actualizarListaPublicacion (int indicePub,int indiceAlb,Publicacion publicacion,String nombreNuevo) {
        this.listaPublicacion.get(indicePub).getListaAlbumes().set(indiceAlb,nombreNuevo);
    }*/

    public void muestraLista() {
        System.out.println("\033[0;1m" + "============================" +  " PUBLICACION " +  "\033[0;1m" + "============================");
        for (Publicacion publicacion : listaPublicacion) {
            System.out.println("ID: " +  publicacion.getId());
            System.out.println("Nombre : " +  publicacion.getNombre());
            System.out.println("Fecha de subida: " + publicacion.getFechaSubida());
            System.out.println("Etiquetas - Hashtags: " + publicacion.getEtiquetasHashtags());
            System.out.println("Cantidad de me gustas: " + publicacion.getCantidadMG());
            // Imprimir cada elemento de la lista de albumes
            System.out.print("Pertenece a los siguiente albumes: ");
            for (String elem : publicacion.getListaAlbumes()) {
                System.out.print(elem + " ");
            }
            System.out.println();
            if (publicacion instanceof Video) {
                Video video = (Video) publicacion;
                System.out.println("Duración del video: " + video.getDuracion());
                System.out.println("Resolución del video: " + video.getResolucion());
                System.out.println("Cantidad de cuadros: " + video.getCantidadDeCuadros());
            } else if (publicacion instanceof Imagen) {
                Imagen imagen = (Imagen) publicacion;
                System.out.println("Resolución de la imagen: " + imagen.getResolucion());
                System.out.println("Ancho de la imagen: " + imagen.getAncho());
                System.out.println("Alto de la imagen: " + imagen.getAlto());
            } else if (publicacion instanceof Audio) {
                Audio audio = (Audio) publicacion;
                System.out.println("Duración del audio: " + audio.getDuracion());
                System.out.println("Velocidad de bits: " + audio.getVelocidadBits());
            }
            System.out.println("\033[0;1m" + "=====================================================================");
        }
    }

    public void MuestraPublicacion (Publicacion publicacion) {
        System.out.println("ID: " +  publicacion.getId());
        System.out.println("Nombre: " +  publicacion.getNombre());
        System.out.println("Fecha de subida: " + publicacion.getFechaSubida());
        System.out.println("Etiquetas - Hashtags: " + publicacion.getEtiquetasHashtags());
        System.out.println("Cantidad de me gustas: " + publicacion.getCantidadMG());
        // Imprimir cada elemento de la lista de albumes
        System.out.print("Pertenece a los siguiente albumes: ");
        for (String elem : publicacion.getListaAlbumes()) {
            System.out.print(elem + " ");
        }
        System.out.println();
        if (publicacion instanceof Video) {
            Video video = (Video) publicacion;
            System.out.println("Duración del video: " + video.getDuracion());
            System.out.println("Resolución del video: " + video.getResolucion());
            System.out.println("Cantidad de cuadros: " + video.getCantidadDeCuadros());
        } else if (publicacion instanceof Imagen) {
            Imagen imagen = (Imagen) publicacion;
            System.out.println("Resolución de la imagen: " + imagen.getResolucion());
            System.out.println("Ancho de la imagen: " + imagen.getAncho());
            System.out.println("Alto de la imagen: " + imagen.getAlto());
        } else if (publicacion instanceof Audio) {
            Audio audio = (Audio) publicacion;
            System.out.println("Duración del audio: " + audio.getDuracion());
            System.out.println("Velocidad de bits: " + audio.getVelocidadBits());
        }
        System.out.println("\033[0;1m" + "=====================================================================");
    }

    public Map<Class<Publicacion>,Integer> cantPublicacionesTipo() {
        Map<Class<Publicacion>, Integer> mapa = new HashMap<Class<Publicacion>,Integer>(); // creo el mapa que va a guardar la cantidad
        for (Publicacion publicacion : listaPublicacion) {
            Class<Publicacion> clase = (Class<Publicacion>) publicacion.getClass(); // creo el objeto "clase" que puede ser de cualquier tipo y obtengo de que clase es
            if (mapa.containsKey(clase)) // se fija si ya esta la clase en el mapa
                mapa.put(clase,mapa.get(clase) + 1); // si esta incrementa {.put asigna clave/valor} {.get recupera el valor asignado a una clave}
            else
                mapa.put(clase, 1); // si no esta inicia el contador en 1;
        }
        return mapa;

        // for (Map.Entry<Class<?>, Integer> entry : mapa.entrySet()) {
        //            System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
        //  }
        // para imprimir el mapa en otra funcion y mostrar la cantidad de cada tipo
    }

    public int cantDeMeGusta() {
        int totalMeGusta = 0;
        for (Publicacion publicacion : listaPublicacion) {
            totalMeGusta += publicacion.getCantidadMG();
        }
        return totalMeGusta;
    }
}
