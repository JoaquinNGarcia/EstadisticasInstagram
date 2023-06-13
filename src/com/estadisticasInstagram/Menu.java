package com.estadisticasInstagram;

import com.estadisticasInstagram.Graficos.GraficoTortas;
import com.estadisticasInstagram.Graficos.Histograma;
import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static com.estadisticasInstagram.ColorsConsole.*;
import static com.estadisticasInstagram.archivos.readPublicaciones.uploadPublicationList;

public class Menu {
    public void menuPrincipal() {
        LinkedList<Publicacion> listP = null;
        Album root = new Album("ALBUMES:");
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
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(BOLD + " ===================== © JOAQUÍN GARCIA & MARTÍN ERREA ==========================" + RESET);
        System.out.println(BOLD + " =================== UNIVERSIDAD CAECE - TRABAJO PRÁCTICO JAVA ==================" + RESET);
        System.out.println(BOLD + " ================================================================================" + RESET);
        try {
            Thread.sleep(0);
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
                    "3" + " - Generar estadisticas.\n" +
                    "4" + " - Generar reportes.\n" +
                    "5" + " - Gestor de reproducion de contenido.\n" +
                    "6" + " - Gestor de Albumes.\n" +
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
                        int[] data = arrayOfPublicationsByType(profilePublications);
                        GraficoTortas.createAndShowGUIPieGraphic(data);

                        Map<String, Integer> mapaInformation = profilePublications.getPeopleWithAmountLikes();
                        Histograma.createAndShowGUIHistogramLikes(mapaInformation);
                        mapaInformation = profilePublications.getPeopleWithAmountPublications();
                        Histograma.createAndShowGUIHistogramPublication(mapaInformation);
                    }
                    else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "4":
                    if (updateFile) {
                        System.out.println(BOLD + "============================" + " Generar reportes " + "============================" + RESET);
                        amountPublications(profilePublications);
                        showPublicationsByType(profilePublications);
                        amountLikesPublication(profilePublications);
                        averageLikesPerType(profilePublications);
                        showAlbumsAlphabetically(profilePublications);
                    } else {
                        System.out.println(BOLD + RED + "\t\t\t\t\tPrimero debe cargar el archivo.\n" + RESET);
                    }
                    break;
                case "5":
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
                case "6":
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
        listaPublicacionPerfil.showListSortByName();
        System.out.println();
        System.out.println();
    }

    public int[] arrayOfPublicationsByType(PerfilInstagram profile) {
        int[] data = {0,0,0};
        for (Publicacion publication : profile.getPublicationList()) {
            if (publication instanceof Video) {data[0] += 1;}
            else if (publication instanceof Imagen) {data[1] += 1;}
            else if (publication instanceof Audio) {data[2] += 1;}
        }
        return data;
    }

    /*public int[] amountOfPublicationsDate (int date) {

    }*/

    public void amountPublications(PerfilInstagram listaPublicacionPerfil) {
        System.out.println( BOLD + UNDERLINED + "\nPublicaciones por tipo y total de todas las publicaciones.\n" + RESET);
        Map<Class<Publicacion>, Integer> mapa;
        mapa = listaPublicacionPerfil.amountPublicationsType();
        int totalPublicaciones = 0;
        for (Map.Entry<Class<Publicacion>, Integer> entry : mapa.entrySet()) {
            System.out.println( BOLD + "\t" + entry.getKey().getSimpleName() + ": " + RESET + entry.getValue());
            totalPublicaciones += entry.getValue();
        }
        System.out.println( BOLD + "\t\nEl total de publicaciones es de: " + RESET + totalPublicaciones);
    }

    public void averageLikesPerType (PerfilInstagram listaPublicacionPerfil) {
        int sumVideo = 0;
        int sumAudio = 0;
        int sumImage = 0;
        int countVideo = 0;
        int countImage = 0;
        int countAudio = 0;
        for (Publicacion publication : listaPublicacionPerfil.getPublicationList()) {
            if (publication instanceof Video) {
                Video video = (Video) publication;
                sumVideo += publication.getAmountLikes();
                countVideo++;
            } else if (publication instanceof Imagen) {
                Imagen imagen = (Imagen) publication;
                sumImage += publication.getAmountLikes();
                countImage++;
            } else if (publication instanceof Audio) {
                Audio audio = (Audio) publication;
                sumAudio += publication.getAmountLikes();
                countAudio++;
            }
        }
        System.out.println(BOLD + "Cantidad de" + RED + " 'me gustas' " + RESET + BOLD + "promedio por tipo de publicación.\n" + RESET);
        if (countVideo > 0 )
            System.out.println(BOLD + "\tCantidad de" + RED + " 'me gustas' " + RESET + BOLD + "promedio de video: " + RESET + RED + "❤" + sumVideo/countVideo + RESET);
        if (countImage > 0)
            System.out.println(BOLD + "\tCantidad de" + RED + " 'me gustas' " + RESET + BOLD + "promedio de imagen: " + RESET + RED + "❤" + sumImage/countImage + RESET);
        if (countAudio > 0)
            System.out.println(BOLD + "\tCantidad de" + RED + " 'me gustas' " + RESET + BOLD + "promedio de audio: " + RESET + RED + "❤" + sumAudio/countAudio + RESET + "\n");
    }
    public void showPublicationsByType(PerfilInstagram listaPublicacionPerfil) {
        System.out.println("\n" + BOLD + "Lista ordenada" + RED + " 'me gustas' " + RESET + BOLD + "de manera descendente por tipos: \n" + RESET);
        LinkedList<Publicacion> publications = listaPublicacionPerfil.getListPublicationByType("Video");
        publications.sort(Comparator.comparing(Publicacion::getAmountLikes).reversed());
        PerfilInstagram.showListByType(publications);
        System.out.println();

        publications = listaPublicacionPerfil.getListPublicationByType("Imagen");
        publications.sort(Comparator.comparing(Publicacion::getAmountLikes).reversed());
        PerfilInstagram.showListByType(publications);
        System.out.println();

        publications = listaPublicacionPerfil.getListPublicationByType("Audio");
        publications.sort(Comparator.comparing(Publicacion::getAmountLikes).reversed());
        PerfilInstagram.showListByType(publications);
        System.out.println();

    }
    public void amountLikesPublication(PerfilInstagram listaPublicacionPerfil) {
        System.out.println( BOLD + "El total de " + RED + "'me gustas' " + RESET + BOLD + "es de: " + RESET + RED + "❤" + listaPublicacionPerfil.totalLikes() + RESET);
        System.out.println();
        listaPublicacionPerfil.getPublicationList().sort(Comparator.comparing(Publicacion::getId));
    }
    public void showPublicationsBetweenDates(List<Album> listAlbum, LocalDate date1, LocalDate date2) {
        for (Album album : listAlbum) {
            int countPublications = 0;
            System.out.print(BOLD + "Publicaciones del álbum " + BOLD + BLUE + album.getName() + RESET + " dentro de las fechas ingresadas: " + RESET);
            for (Publicacion publication : album.getPublications()) {
                LocalDate publicationDate = publication.getDateUploaded();
                if (publicationDate.isAfter(date1) && publicationDate.isBefore(date2)) {
                    countPublications++;
                }
            }
            System.out.print(countPublications + "\n");
            showPublicationsBetweenDates(album.getAlbumList(), date1, date2);
        }
    }
    public void showAlbumsAlphabetically (PerfilInstagram listaAlbumes) {
        Scanner render = new Scanner(System.in);
        System.out.println(BOLD + "Ingrese una fecha minima y otra maxima para mostrar (formato dd/MM/yyyy)" + RESET);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date1Str = render.nextLine();
        String date2Str = render.nextLine();
        LocalDate date1 = null;
        LocalDate date2 = null;
        try {
            date1 = LocalDate.parse(date1Str, formatter);
            date2 = LocalDate.parse(date2Str, formatter);
        }
        catch (DateTimeParseException e) {
            System.out.println(BOLD + "Error al analizar las fechas ingresadas. Asegúrese de ingresarlas en el formato correcto." + RESET);
            showAlbumsAlphabetically(listaAlbumes);
        }
        System.out.println(BOLD + UNDERLINED + "\nListado alfabetico de álbumes" + RESET);
        for (Album album : listaAlbumes.getAlbumsList()) {
            System.out.println(album);
            showPublicationsBetweenDates(album.getAlbumList(), date1,date2);
        }
    }
}
