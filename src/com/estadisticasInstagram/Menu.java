package com.estadisticasInstagram;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.Album;
import com.estadisticasInstagram.dominio.Publicacion;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import static com.estadisticasInstagram.ColoresConsola.*;
import static com.estadisticasInstagram.archivos.readPublicaciones.cargaListaPublicacion;

public class Menu {

    // private com.estadisticasInstagram.controlador.PerfilInstagram
    // perfilInstagram;
    // public com.estadisticasInstagram.Main() {
    // this.perfilInstagram = new
    // com.estadisticasInstagram.controlador.PerfilInstagram();
    // }

    public void menuPrincipal() {
        LinkedList<Publicacion> listaP = null;
        Album raiz = new Album("ALBUMES:");
        //LinkedList<Publicacion> publicacionesAReproducir = new LinkedList<>();
        boolean cargoArchivo = false;
        PerfilInstagram perfilDePublicaciones = new PerfilInstagram(listaP);
        String option = "";
        Scanner render = new Scanner(System.in); // Acoplamiento con interfaz (lectura!!)
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
            System.out
                    .println("\033[0;1m" + "========================================================================");
            System.out.println("\033[0;1m" + "============================" + " MENÚ PRINCIPAL " + "\033[0;1m"
                    + "============================");
            System.out.println(
                    "\033[0;1m" + "========================================================================\n");
            System.out.println("" +
                    "\033[0;1m" + "1" + " - Cargar archivos de publicaciones.\n" +
                    "\033[0;1m" + "2" + " - Mostrar las publicaciones.\n" +
                    "\033[0;1m" + "3" + " - Generar reportes.\n" +
                    "\033[0;1m" + "4" + " - Gestor de reproducion de contenido.\n" +
                    "\033[0;1m" + "5" + " - Gestor de Albumes.\n" +
                    "\033[0;1m" + "0" + " - Salir\n");
            option = render.nextLine().trim();
            switch (option) {
                case "1":
                    if (cargoArchivo) {
                        System.out.println(BOLD + RED + "\t\t\t\t\tEl archivo ya fue cargado.\n" + RESET);
                    } else {
                        System.out.println("\033[0;1m" + BOLD + GREEN + "\t\t\t\t\tEl archivo fue cargado con exito. \n\n" + RESET);
                        cargoArchivo = true;
                        listaP = cargaListaPublicacion();
                        perfilDePublicaciones.setListaPublicacion(listaP);
                    }
                    break;
                case "2":
                    if (cargoArchivo) {
                        cargaArchivoPublicaciones(perfilDePublicaciones);
                    } else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "3":
                    if (cargoArchivo) {
                        System.out.println("\033[0;1m" + "============================" + " Generar reportes "
                                + "\033[0;1m" + "============================");
                        cantidadDePublicaciones(perfilDePublicaciones);
                        cantDeMeGustaPublicacion(perfilDePublicaciones);
                    } else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "4":
                    if (cargoArchivo) {
                        MenuReproduccionMultimedia menuReproduccionMultimedia = new MenuReproduccionMultimedia();
                        menuReproduccionMultimedia.startReproduccionMultimedia(perfilDePublicaciones);

                        //LinkedList<Publicacion> listaPublicacionesAReproducir = new LinkedList<>();

                        //LinkedList<Album> albumesPerfil = new LinkedList<Album>();
                        //listaPublicacionesAReproducir.add(publicacionesAReproducir);
                        //perfilDePublicaciones.setListaAlbumes(albumesPerfil);
                    } else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "5":
                    if (cargoArchivo) {
                        System.out.println("\033[0;1m" + "============================" + " Gestor de álbumes "
                                + "\033[0;1m" + "============================");
                        MenuAlbumes menuAlbumes = new MenuAlbumes();
                        menuAlbumes.startMenuAlbumes(raiz, perfilDePublicaciones);

                        LinkedList<Album> albumesPerfil = new LinkedList<>();
                        albumesPerfil.add(raiz);
                        perfilDePublicaciones.setListaAlbumes(albumesPerfil);
                    } else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "0": System.out.println(BOLD + BLUE + "Ha salido del menu" + RESET);
            }
        } while (!option.equals("0"));
    }

    public void cargaArchivoPublicaciones(PerfilInstagram listaPublicacionPerfil) {
        System.out.println();
        listaPublicacionPerfil.muestraLista();
        System.out.println();
        System.out.println();
    }

    public void cantidadDePublicaciones(PerfilInstagram listaPublicacionPerfil) {
        System.out.println("\033[0;1m" + BOLD + "\nPublicaciones por tipo y total de todas las publicaciones. \n" + RESET);
        Map<Class<Publicacion>, Integer> mapa;
        mapa = listaPublicacionPerfil.cantPublicacionesTipo();
        int totalPublicaciones = 0;
        for (Map.Entry<Class<Publicacion>, Integer> entry : mapa.entrySet()) {
            System.out.println( BOLD + "\t" + entry.getKey().getSimpleName() + ": " + entry.getValue() + RESET);
            totalPublicaciones += entry.getValue();
        }
        System.out.println( BOLD + "\tEl total de publicaciones es de: " + totalPublicaciones + RESET);
        System.out.println();
        System.out.println();
    }

    public void cantDeMeGustaPublicacion(PerfilInstagram listaPublicacionPerfil) {
        System.out.println("\033[0;1m" + BOLD + "\nCantidad de Me Gusta por publicacion. \n" + RESET);
        System.out.println("El total de 'me gustas' es de: " + listaPublicacionPerfil.cantDeMeGusta());
        System.out.println();
        System.out.println();
    }
}
