package com.estadisticasInstagram;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.Publicacion;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import static com.estadisticasInstagram.archivos.readPublicaciones.cargaListaPublicacion;

public class Menu {

    // private com.estadisticasInstagram.controlador.PerfilInstagram perfilInstagram;
    // public com.estadisticasInstagram.Main() {
    //    this.perfilInstagram = new com.estadisticasInstagram.controlador.PerfilInstagram();
    // }

    public void menuPrincipal() {
        LinkedList<Publicacion> listaP = null;
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
            System.out.println("\033[0;1m" + "1" +  " - Cargar archivos de publicaciones.\n" +
                    "\033[0;1m" + "2" + " - Mostrar las publicaciones.\n" +
                    "\033[0;1m" + "3" + " - Consultar cantidad de publicaciones por tipo y total.\n" +
                    "\033[0;1m" + "4" +  " - Cantidad de Me Gusta por publicacion.\n" +
                    "\033[0;1m" + "5" +  " - Generar reportes.\n" +
                    "\033[0;1m" + "6" +  " - ABM de las publicaciones y albumes del perfil.\n" +
                    "\033[0;1m" + "7" +  " - Generar estadisticas.\n" +
                    "\033[0;1m" + "0" +  " - Salir\n");
            option = render.nextLine().trim();
            switch(option) {
                case "1":
                    System.out.println("Eligio Cargar archivos de publicaciones.\n");
                    listaP = cargaListaPublicacion();
                    perfilDePublicaciones.setListaPublicacion(listaP);
                    break;
                case "2":
                    System.out.println("Eligio Mostrar las publicaciones.\n");
                    cargaArchivoPublicaciones(perfilDePublicaciones);
                    // hacer la excepcion try cath
                    break;
                case "3":
                    System.out.println("Eligio Consultar cantidad de publicaciones por tipo y total.\n");
                    cantidadDePublicaciones(perfilDePublicaciones);
                    break;
                case "4":
                    System.out.println("Eligio Cantidad de Me Gusta por publicacion.\n");
                    cantDeMeGustaPublicacion(perfilDePublicaciones);
                    break;
                case "5":
                    System.out.println("Eligio Generar reportes.\n");
                    break;
                case "6":
                    System.out.println("Eligio el ABM de las publicaciones y albumes del perfil.\n");
                    break;
                case "7":
                    System.out.println("Eligio Generar estadisticas.\n");
                    break;
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
        System.out.println("El total de publicaciones es de: " + listaPublicacionPerfil.cantDeMeGusta());
        System.out.println();
        System.out.println();
    }
}
