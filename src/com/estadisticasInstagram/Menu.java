package com.estadisticasInstagram;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.Album;
import com.estadisticasInstagram.dominio.Publicacion;

import java.util.*;

import static com.estadisticasInstagram.archivos.readPublicaciones.cargaListaPublicacion;

public class Menu {

    // private com.estadisticasInstagram.controlador.PerfilInstagram perfilInstagram;
    // public com.estadisticasInstagram.Main() {
    //    this.perfilInstagram = new com.estadisticasInstagram.controlador.PerfilInstagram();
    // }

    public void menuPrincipal() {
        LinkedList<Publicacion> listaP = null;
        Album raiz = new Album("ALBUMES:");
        boolean cargoArchivo = false;
        PerfilInstagram perfilDePublicaciones = new PerfilInstagram(listaP);
        String option = "";
        Scanner render = new Scanner(System.in); //Acoplamiento con interfaz (lectura!!)
        System.out.println("\n\n   ##     ##   ##   #####   ######     ##       ####   ######     ##     ##   ##" +
                "\n" +
                "   ##     ###  ##  ##   ##  # ## #    ####     ##  ##   ##  ##   ####    ### ###\n" +
                "   ##     #### ##  #          ##     ##  ##   ##        ##  ##  ##  ##   #######\n" +
                "   ##     ## ####   #####     ##     ##  ##   ##        #####   ##  ##   #######\n" +
                "   ##     ##  ###       ##    ##     ######   ##  ###   ## ##   ######   ## # ##\n" +
                "   ##     ##   ##  ##   ##    ##     ##  ##    ##  ##   ##  ##  ##  ##   ##   ##\n" +
                "  ####    ##   ##   #####    ####    ##  ##     #####  #### ##  ##  ##   ##   ##\n" +
                "\n\n");

        do {
            System.out.println("\033[0;1m" + "========================================================================");
            System.out.println("\033[0;1m" + "============================" +  " MENÚ PRINCIPAL " +  "\033[0;1m" + "============================");
            System.out.println("\033[0;1m" + "========================================================================\n");
            System.out.println("" +
                    "\033[0;1m" + "1" + " - Cargar archivos de publicaciones.\n" +
                    "\033[0;1m" + "2" + " - Mostrar las publicaciones.\n" +
                    "\033[0;1m" + "3" + " - Consultar cantidad de publicaciones por tipo y total.\n" +
                    "\033[0;1m" + "4" + " - Cantidad de Me Gusta por publicacion.\n" +
                    "\033[0;1m" + "5" + " - Generar reportes.\n" +
                    "\033[0;1m" + "6" + " - ABM de las publicaciones y albumes del perfil.\n" +
                    "\033[0;1m" + "7" + " - Generar estadisticas.\n" +
                    "\033[0;1m" + "8" + " - Gestor de Albumes.\n" +
                    "\033[0;1m" + "0" + " - Salir\n");
            option = render.nextLine().trim();
            switch(option) {
                case "1":
                    System.out.println("\033[0;1m" + "============================" +  " Cargar archivos de publicaciones " +  "\033[0;1m" + "============================\n\n");
                    cargoArchivo = true;
                    listaP = cargaListaPublicacion();
                    perfilDePublicaciones.setListaPublicacion(listaP);
                    break;
                case "2":
                    if (cargoArchivo) {
                        System.out.println("\033[0;1m" + "============================" +  " Mostrar las publicaciones " +  "\033[0;1m" + "============================");
                        cargaArchivoPublicaciones(perfilDePublicaciones);
                    } else {
                        System.out.println("Primero debe cargar el archivo.\n");
                    }
                    break;
                case "3":
                    if (cargoArchivo) {
                        System.out.println("\033[0;1m" + "============================" +  " Consultar cantidad de publicaciones por tipo y total " +  "\033[0;1m" + "============================");
                        cantidadDePublicaciones(perfilDePublicaciones);
                    } else {
                        System.out.println("Primero debe cargar el archivo.\n");
                    }
                    break;
                case "4":
                    if (cargoArchivo) {
                        System.out.println("\033[0;1m" + "============================" +  " Cantidad de Me Gusta por publicacion " +  "\033[0;1m" + "============================");
                        cantDeMeGustaPublicacion(perfilDePublicaciones);
                    } else {
                        System.out.println("Primero debe cargar el archivo.\n");
                    }
                    break;
                case "5":
                    if (cargoArchivo) {
                        System.out.println("\033[0;1m" + "============================" +  " Generar reportes " +  "\033[0;1m" + "============================");
                    } else {
                        System.out.println("Primero debe cargar el archivo.\n");
                    }
                    break;
                case "6":
                    if (cargoArchivo) {
                        System.out.println("\033[0;1m" + "============================" +  " ABM de las publicaciones y albumes del perfil " +  "\033[0;1m" + "============================");
                    } else {
                        System.out.println("Primero debe cargar el archivo.\n");
                    }
                    break;
                case "7":
                    if (cargoArchivo) {
                        System.out.println("\033[0;1m" + "============================" +  " Generar estadisticas " +  "\033[0;1m" + "============================");
                        perfilDePublicaciones.muestraLista();
                    } else {
                        System.out.println("Primero debe cargar el archivo.\n");
                    }
                    break;
                case "8":
                    if (cargoArchivo) {
                        System.out.println("\033[0;1m" + "============================" + " Gestor de álbumes " + "\033[0;1m" + "============================");
                        MenuAlbumes menuAlbumes = new MenuAlbumes();
                        menuAlbumes.startMenuAlbumes(raiz,perfilDePublicaciones);

                        LinkedList<Album> albumesPerfil = new LinkedList<Album>();
                        albumesPerfil.add(raiz);
                        perfilDePublicaciones.setListaAlbumes(albumesPerfil);
                    }
                    else {
                        System.out.println("Primero debe cargar el archivo.\n");
                    }
            }
        }while (!option.equals("0"));
    }

    public static void cargaArchivoPublicaciones(PerfilInstagram listaPublicacionPerfil) {
        System.out.println();
        listaPublicacionPerfil.muestraLista();
        System.out.println();
        System.out.println();
    }

    public static void cantidadDePublicaciones(PerfilInstagram listaPublicacionPerfil){
        Map<Class<Publicacion>, Integer> mapa;
        mapa = listaPublicacionPerfil.cantPublicacionesTipo();
        int totalPublicaciones = 0;
        for (Map.Entry<Class<Publicacion>, Integer> entry : mapa.entrySet()) {
            System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
            totalPublicaciones += entry.getValue();
        }
        System.out.println("El total de publicaciones es de: " + totalPublicaciones);
        System.out.println();
        System.out.println();
    }

    public static void cantDeMeGustaPublicacion(PerfilInstagram listaPublicacionPerfil){
        System.out.println("El total de 'me gustas' es de: " + listaPublicacionPerfil.cantDeMeGusta());
        System.out.println();
        System.out.println();
    }
}

