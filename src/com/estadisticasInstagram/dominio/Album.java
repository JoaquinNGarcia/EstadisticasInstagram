package com.estadisticasInstagram.dominio;

import java.util.Iterator;
import java.util.LinkedList;

public class Album {
    LinkedList<Publicacion> listaPublicacion;

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

        Iterator<Publicacion> it = listaPublicacion.iterator();
        Publicacion pub = it.next();
        // while(it.hasNext()) {
            // System.out.println(it.next().toString() + "\n");
        // }
        // LinkedList<Publicacion> listaP;
        // listaP = alb.getListaPublicacion();


        for (int i = 0; i < listaPublicacion.size(); i++) {
            System.out.println("\033[0;1m" + "============================" +  " PUBLICACION " +  "\033[0;1m" + "============================");
            System.out.println("Nombre : " + listaPublicacion.get(i).getNombre());
            System.out.println("Fecha de subida: " + listaPublicacion.get(i).getFechaSubida());
            System.out.println("Etiquetas - Hashtags: " + listaPublicacion.get(i).getEtiquetasHashtags());
            System.out.println("Cantidad de me gustas: " + listaPublicacion.get(i).getCantidadMG());
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

    public Map<Class<?>,Integer> cantPublicacionesTipo() { // POSIBLE FUNCION
        Map<Class<?>, Integer> mapa = new HashMap<Class<?>,Integer>(); // creo el mapa que va a guardar la cantidad
        for (Publicacion publicacion : publicacionList) {
            Class<?> clase = publicacion.getClass(); // creo el objeto "clase" que puede ser de cualquier tipo y obtengo de que clase es
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

 */
}