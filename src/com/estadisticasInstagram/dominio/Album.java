package com.estadisticasInstagram.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Album {
    LinkedList<Publicacion> listaPublicacion;
//Sumar una lista de albunes
    public Album(LinkedList<Publicacion> listaPublicacion) {
        this.listaPublicacion = listaPublicacion;
    }

    public LinkedList<Publicacion> getListaPublicacion() {
        return listaPublicacion;
    }

    public void setListaPublicacion(LinkedList<Publicacion> listaPublicacion) {
        this.listaPublicacion = listaPublicacion;
    }

    public void muestraLista() {
        System.out.println("\033[0;1m" + "============================" +  " PUBLICACION " +  "\033[0;1m" + "============================");
        for (Publicacion publicacion : listaPublicacion) {
            System.out.println("Nombre : " +  publicacion.getNombre());
            System.out.println("Fecha de subida: " + publicacion.getFechaSubida());
            System.out.println("Etiquetas - Hashtags: " + publicacion.getEtiquetasHashtags());
            System.out.println("Cantidad de me gustas: " + publicacion.getCantidadMG());
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

    /*
    private ArrayList<Album> albumList = new ArrayList<>();

    public int cantMgAcumulada() {
        int sum = 0;
        for (Publicacion publicacion : publicacionList) {
            sum += publicacion.getCantidadMG();
        }
        return sum;
    }
    public int cantPublicacionesTotal() {
        int cant = 0;
        for (Publicacion publicacion : publicacionList) {
            cant++;
        }
        return cant;
    }

 */
    public Map<Class<Publicacion>,Integer> cantPublicacionesTipo() { // POSIBLE FUNCION
        Map<Class<Publicacion>, Integer> mapa = new HashMap<Class<Publicacion>,Integer>(); // creo el mapa que va a guardar la cantidad
        for (Publicacion publicacion : listaPublicacion) {
            Class<Publicacion> clase = (Class<Publicacion>) publicacion.getClass(); // creo el objeto "clase" que puede ser de cualquier tipo y obtengo de que clase es
            if (mapa.containsKey(clase)) // se fija si ya esta la clase en el mapa
                mapa.put(clase,mapa.get(clase) + 1); // si esta incrementa {.put asigna clave/valor} {.get recupera el valor asignado a una clave}
            else
                mapa.put(clase,1); // si no esta inicia el contador en 1;
        }
        return mapa;

        // for (Map.Entry<Class<?>, Integer> entry : mapa.entrySet()) {
        //            System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
        //  }
        // para imprimir el mapa en otra funcion y mostrar la cantidad de cada tipo
    }

}
