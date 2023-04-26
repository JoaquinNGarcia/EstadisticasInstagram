package com.estadisticasInstagram;

import com.estadisticasInstagram.controladora.PerfilInstagram;
import java.util.*;
import java.lang.String;


public class Main {
    private PerfilInstagram perfilInstagram;

    public Main() {
        this.perfilInstagram = new PerfilInstagram();
    }

    /**
     * Método que invoca al Menu
     */

    public void iniciarMenu() {
        menuPrincipal();
    }

    /**
     * Método que muestra el menu de opciones para el perfil de Instagram.
     */
    public void menuPrincipal() {
        System.out.println("   ##     ##   ##   #####   ######     ##       ####   ######     ##     ##   ##" +
                "\n" +
                "          ###  ##  ##   ##  # ## #    ####     ##  ##   ##  ##   ####    ### ###\n" +
                "  ###     #### ##  #          ##     ##  ##   ##        ##  ##  ##  ##   #######\n" +
                "   ##     ## ####   #####     ##     ##  ##   ##        #####   ##  ##   #######\n" +
                "   ##     ##  ###       ##    ##     ######   ##  ###   ## ##   ######   ## # ##\n" +
                "   ##     ##   ##  ##   ##    ##     ##  ##    ##  ##   ##  ##  ##  ##   ##   ##\n" +
                "  ####    ##   ##   #####    ####    ##  ##     #####  #### ##  ##  ##   ##   ##\n" +
                "\n\n");
        System.out.println("\033[0;1m" + "\n========================================================================" );
        System.out.println("\033[0;1m" + "============================" +  " MENÚ PRINCIPAL " +  "\033[0;1m" + "============================" );
        System.out.println("\033[0;1m" + "========================================================================\n" );
        System.out.println("\033[0;1m" +  "1" +  " - ABM de las publicaciones y albumes del perfil.\n" +
                "\033[0;1m" + "2" + " - Consultar publicaciones.\n" +
                "\033[0;1m" + "3" +  " - Generar estadisticas.\n" +
                "\033[0;1m" + "4" +  " - Generar reportes.\n");
    }
    public static void main(String[] args) {
        Main menu = new Main();
        menu.iniciarMenu();
    }
}
