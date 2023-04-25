/**
 * @author Joaquin Garcia, Gabriel Corvalan, Martin Errea.
 */
package com.estadisticasInstagram;

import com.estadisticasInstagram.controladora.PerfilInstagram;
import java.util.*;

public class Main {
    private PerfilInstagram perfilInstagram;

    public main() {
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
    }
}
