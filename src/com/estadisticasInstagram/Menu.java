package com.estadisticasInstagram;

import com.estadisticasInstagram.dominio.Album;
import com.estadisticasInstagram.dominio.Publicacion;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import static com.estadisticasInstagram.archivos.readPublicaciones.cargaListaPublicacion;

public class Menu {

    public static void menuPrincipal() {
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
            System.out.println("\033[0;1m" + "1" +  " - Cargar archivos de publicaciones.\n" +
                    "\033[0;1m" + "2" + " - Consultar cantidad de publicaciones.\n" +
                    "\033[0;1m" + "3" +  " - Generar estadisticas.\n" +
                    "\033[0;1m" + "4" +  " - Generar reportes.\n" +
                    "\033[0;1m" + "5" +  " - ABM de las publicaciones y albumes del perfil.\n" +
                    "\033[0;1m" + "0" +  " - Salir\n");

            option = render.nextLine().trim();
            switch(option) {
                case "1":
                    System.out.println("Eligio Cargar archivos de publicaciones.\n");
                    cargaArchivoPublicaciones();
                    break;
                case "2":
                    System.out.println("Eligio Consultar cantidad de publicaciones.\n");
                    cantidadDePublicaciones();
                    break;
                case "3":
                    System.out.println("Eligio Generar estadisticas.\n");
                    break;
                case "4":
                    System.out.println("Eligio Generar reportes.\n");
                    break;
                case "5":
                    System.out.println("Eligio el ABM de las publicaciones y albumes del perfil.\n");
                    break;
            }
        }while (!option.equals("0"));
    }

    public static void cargaArchivoPublicaciones() {
        LinkedList<Publicacion> listaP;
        listaP = cargaListaPublicacion();
        String verPublicaciones;
        Album albumPublicaciones = new Album(listaP);

        System.out.println("Desea ver la lista de las publicaciones? (si - no)");
        Scanner leerPublicaciones = new Scanner(System.in);
        verPublicaciones = leerPublicaciones.nextLine();
        if(verPublicaciones.equals("si")) {
            System.out.println();
            albumPublicaciones.muestraLista();
        }
        System.out.println();
        System.out.println();
    }

    public static void cantidadDePublicaciones(){
        LinkedList<Publicacion> listaP;
        listaP = cargaListaPublicacion();
        Album albumPublicaciones = new Album(listaP);

        Map<Class<Publicacion>, Integer> mapa;
        mapa = albumPublicaciones.cantPublicacionesTipo();
        for (Map.Entry<Class<Publicacion>, Integer> entry : mapa.entrySet()) {
            System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
        }
        System.out.println();
        System.out.println();
    }
}
