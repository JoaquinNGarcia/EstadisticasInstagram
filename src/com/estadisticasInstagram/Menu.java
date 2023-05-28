package com.estadisticasInstagram;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.Album;
import com.estadisticasInstagram.dominio.Publicacion;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import static com.estadisticasInstagram.ColorsConsole.*;
import static com.estadisticasInstagram.archivos.readPublicaciones.uploadPublicationList;

public class Menu {

    // private com.estadisticasInstagram.controlador.PerfilInstagram
    // perfilInstagram;
    // public com.estadisticasInstagram.Main() {
    // this.perfilInstagram = new
    // com.estadisticasInstagram.controlador.PerfilInstagram();
    // }

    public void menuPrincipal() {
        LinkedList<Publicacion> listP = null;
        Album root = new Album("ALBUMES:");
        //LinkedList<Publicacion> publicacionesAReproducir = new LinkedList<>();
        boolean updateFile = false;
        PerfilInstagram profilePublications = new PerfilInstagram(listP);
        String option = "";
        Scanner render = new Scanner(System.in); // Acoplamiento con interfaz (lectura!!)
        System.out.println(BOLD + "\n ================================================================================" + RESET);
        System.out.println(BOLD + " ================================================================================" + RESET + BLUE +
                "\n  ####    ##   ##   #####   ######     ##       ####   ######     ##     ##   ##" + "\n" + CYAN +
                "   ##     ###  ##  ##   ##  # ## #    ####     ##  ##   ##  ##   ####    ### ###\n" + PURPLE + BOLD +
                "   ##     #### ##  #          ##     ##  ##   ##        ##  ##  ##  ##   #######\n" + BOLD + RESET +
                "   ##     ## ####   #####     ##     ##  ##   ##        #####   ##  ##   #######\n" + RED +
                "   ##     ##  ###       ##    ##     ######   ##  ###   ## ##   ######   ## # ##\n" + YELLOW +
                "   ##     ##   ##  ##   ##    ##     ##  ##    ##  ##   ##  ##  ##  ##   ##   ##\n" + BLUE + RESET +
                "  ####    ##   ##   #####    ####    ##  ##     #####  #### ##  ##  ##   ##   ##\n" + BOLD +
                " ================================================================================" + RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(BOLD + " ===================== © JOAQUÍN GARCIA & MARTÍN ERREA ==========================" + RESET);
        System.out.println(BOLD + " =================== UNIVERSIDAD CAECE - TRABAJO PRÁCTICO JAVA ==================" + RESET);
        System.out.println(BOLD + " ================================================================================" + RESET);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n\n\n");
        do {
            System.out.println(BOLD + "========================================================================" + RESET);
            System.out.println(BOLD + "============================" + " MENÚ PRINCIPAL " + "============================" + RESET);
            System.out.println(BOLD + "========================================================================\n" + RESET);
            System.out.println("" + BOLD +
                    "1" + " - Cargar archivos de publicaciones.\n" +
                    "2" + " - Mostrar las publicaciones.\n" +
                    "3" + " - Generar reportes.\n" +
                    "4" + " - Gestor de reproducion de contenido.\n" +
                    "5" + " - Gestor de Albumes.\n" +
                    "0" + " - Salir\n" + RESET);
            option = render.nextLine().trim();
            switch (option) {
                case "1":
                    if (updateFile) {
                        System.out.println(BOLD + RED + "\t\t\t\t\tEl archivo ya fue cargado.\n" + RESET);
                    } else {
                        System.out.println(BOLD + GREEN + "\t\t\t\t\tEl archivo fue cargado con exito. \n\n" + RESET);
                        updateFile = true;
                        listP = uploadPublicationList();
                        profilePublications.setPublicationList(listP);
                    }
                    break;
                case "2":
                    if (updateFile) {
                        uploadFilePublications(profilePublications);
                    } else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "3":
                    if (updateFile) {
                        System.out.println(BOLD + "============================" + " Generar reportes " + "============================" + RESET);
                        amountPublications(profilePublications);
                        amountLikesPublication(profilePublications);

                    } else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "4":
                    if (updateFile) {
                        MenuReproduccionMultimedia menuReproduccionMultimedia = new MenuReproduccionMultimedia();
                        menuReproduccionMultimedia.startReproduccionMultimedia(profilePublications);

                        //LinkedList<Publicacion> listaPublicacionesAReproducir = new LinkedList<>();

                        //LinkedList<Album> albumesPerfil = new LinkedList<Album>();
                        //listaPublicacionesAReproducir.add(publicacionesAReproducir);
                        //profilePublications.setListaAlbumes(albumesPerfil);
                    } else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "5":
                    if (updateFile) {
                        System.out.println(BOLD + "============================" + " Gestor de álbumes " + "============================" + RESET);
                        MenuAlbumes menuAlbumes = new MenuAlbumes();
                        menuAlbumes.startMenuAlbumes(root, profilePublications);

                        LinkedList<Album> albumesPerfil = new LinkedList<>();
                        albumesPerfil.add(root);
                        profilePublications.setAlbumsList(albumesPerfil);
                    } else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "0": System.out.println(BOLD + BLUE +"H" + RESET + BLUE + "A" + RESET + BOLD + CYAN + " S" + RESET + CYAN + "A" + RESET +
                        BOLD + PURPLE + "L" + RESET + PURPLE + "I" + RESET + BOLD + RED + "D" + RESET + RED + "O " + RESET +
                        BOLD + YELLOW + "D" + RESET + YELLOW + "E" + RESET + BOLD + GREEN + "L " + RESET + GREEN + "P" + RESET + BOLD + BLUE +"R" + RESET +
                        BLUE + "O" + RESET + BOLD + CYAN + "G" + RESET + CYAN + "R" + RESET + BOLD + PURPLE + "A" + RESET + PURPLE + "M" + RESET + BOLD + RED + "A " + RESET);
            }
        } while (!option.equals("0"));
    }

    public static void uploadFilePublications(PerfilInstagram listaPublicacionPerfil) {
        System.out.println();
        listaPublicacionPerfil.showList();
        System.out.println();
        System.out.println();
    }

    public void amountPublications(PerfilInstagram listaPublicacionPerfil) {
        System.out.println( BOLD + "\nPublicaciones por tipo y total de todas las publicaciones. \n" + RESET);
        Map<Class<Publicacion>, Integer> mapa;
        mapa = listaPublicacionPerfil.amountPublicationsType();
        int totalPublicaciones = 0;
        for (Map.Entry<Class<Publicacion>, Integer> entry : mapa.entrySet()) {
            System.out.println( BOLD + "\t" + entry.getKey().getSimpleName() + ": " + entry.getValue() + RESET);
            totalPublicaciones += entry.getValue();
        }
        System.out.println( BOLD + "\tEl total de publicaciones es de: " + totalPublicaciones + RESET);
        System.out.println();
        System.out.println();
    }

    public void amountLikesPublication(PerfilInstagram listaPublicacionPerfil) {
        System.out.println( BOLD + "\nCantidad de Me Gusta por publicacion. \n" + RESET);
        System.out.println( BOLD + "El total de 'me gustas' es de: " + listaPublicacionPerfil.totalLikes() + RESET);
        // hacer instance of ...
        System.out.println();
        System.out.println();
    }
}
