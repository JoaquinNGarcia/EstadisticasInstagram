package com.estadisticasInstagram.menu;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.Audio;
import com.estadisticasInstagram.dominio.Imagen;
import com.estadisticasInstagram.dominio.Publicacion;
import com.estadisticasInstagram.dominio.Video;
import com.estadisticasInstagram.serializacion.SerFiltros;

import java.io.Serializable;
import java.util.*;

import static com.estadisticasInstagram.ColorsConsole.*;

/** menú que gestiona la reproducción y filtros de las publicaciones*/
public class MenuReproduccionMultimedia implements Serializable {
    private static Scanner render = new Scanner(System.in);

    private String seePublications, applyFilters;
    private boolean itsEmpty;
    LinkedList<Publicacion> publicationsToReproduce = new LinkedList<>();
    Set<String> idsSelected = new HashSet<>();

    private LinkedList<Audio> listAudio = new LinkedList<>();
    private LinkedList<Imagen> listImage = new LinkedList<>();
    private LinkedList<Video> listVideo = new LinkedList<>();
    

    enum Filters {
        TOKIO,
        NEWYORK,
        BUENOSAIRES
    }
    public void serialize(LinkedList<Publicacion> publi) {
        SerFiltros.serializeFilters(publi);
    }

    public void startReproductionMultimedia(PerfilInstagram profile) {
        double fullDuration = 0;
        String optionReproductionSelected = "", optionOrderReproduccion = "";
        System.out.println(BOLD + "Desea aplicar filtros a las imagenes o los videos?" + RESET);
        System.out.println(BOLD + GREEN + "1.Si" + RESET);
        System.out.println(BOLD + RED + "2.No" + RESET);
        applyFilters = render.nextLine();
        while(applyFilters.equals("1")) {
            applyFilter(profile);
            System.out.println(BOLD + "Desea aplicar filtros a las imagenes o los videos?" + RESET);
            System.out.println(BOLD + GREEN + "1.Si" + RESET);
            System.out.println(BOLD + RED + "2.No" + RESET);
            applyFilters = render.nextLine();
        }
        AddPublicationsToPlay(profile);
        itsEmpty = publicationsToReproduce.isEmpty();
        if(!itsEmpty) {
            System.out.println(BOLD + "\nDesea reproducir las publicaciones seleccionadas? " + RESET);
            System.out.println(BOLD + GREEN + "1.Si" + RESET);
            System.out.println(BOLD + RED + "2.No" + RESET);
            optionReproductionSelected = render.nextLine().trim();
            try {
                if(optionReproductionSelected.equals("1") && !itsEmpty) {
                    System.out.println(BOLD + "\nPuede elegir 3 maneras de reproducir el contenido: \n" + RESET);
                    System.out.println(BOLD + "1.Ordenado por Tipo (Ascendente)." + RESET);
                    System.out.println(BOLD + "2.Ordenado por Tipo (Descendente)." + RESET);
                    System.out.println(BOLD + "3.Ordenado por Nombre (Ordenados alfabeticamente)." + RESET);
                    System.out.println(BOLD + "4.Ordenado por cantidad de 'me gustas' (Ascendente)." + RESET);
                    System.out.println(BOLD + "5.Ordenado por cantidad de 'me gustas' (Descendente)." + RESET);
                    System.out.println(BOLD + "0.Salir.\n" + RESET);
                    optionOrderReproduccion = render.nextLine().trim();
                    switch (optionOrderReproduccion){
                        case "1" -> {
                            publicationsToReproduce.sort(Comparator.comparing(Publicacion::getType));
                        }
                        case "2" -> {
                            publicationsToReproduce.sort(Comparator.comparing(Publicacion::getType).reversed());
                        }
                        case "3" -> {
                            publicationsToReproduce.sort(Comparator.comparing(Publicacion::getName));
                        }
                        case "4" -> {
                            publicationsToReproduce.sort(Comparator.comparing(Publicacion::getAmountLikes));

                        }
                        case "5" -> {
                            publicationsToReproduce.sort(Comparator.comparing(Publicacion::getAmountLikes).reversed());
                        }
                        case "0" -> {
                            System.out.println(BOLD + "Se aplicara el valor por defecto.\n" + RESET);
                            publicationsToReproduce.sort(Comparator.comparing(Publicacion::getId));
                        }
                    }
                    for(Publicacion publicacion : publicationsToReproduce) {
                        if (publicacion instanceof Audio audio) {
                            fullDuration += audio.getDuration();
                        } else {
                            if (publicacion instanceof Video video) {
                                fullDuration += video.getDuration();
                            } else {
                                fullDuration += 100;
                            }
                        }
                    }
                    System.out.println(BOLD + "El tiempo total de reproducción es de: " + Math.round(fullDuration / 10) + " segundos. " + RESET);
                    for(Publicacion publicacion : publicationsToReproduce) {
                        publicacion.reproducirContenido(publicacion);
                    }

                } else {
                    System.out.println(BOLD + BLUE + "Ha salido de la reproduccion de multimedia" + RESET);
                }
            } catch (InputMismatchException error) {
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
                render.nextLine();
            }
        } else {
            System.out.println(BOLD + RED + "No se agrego ninguna publicacion." + RESET);
        }
        publicationsToReproduce.sort(Comparator.comparing(Publicacion::getId));
    }

    /** aplica filtros a las publicaciones*/
    public void applyFilter(PerfilInstagram profile) {
        if(!profile.getPublicationList().isEmpty()){
            System.out.println(BOLD + "Elija la publicación a la cual quiere aplicar el filtro." + RESET);
            for (int i = 0; i < profile.getPublicationList().size(); i++)
                System.out.println("\t" + "#" + profile.getPublicationList().get(i).getId() + "\t" + profile.getPublicationList().get(i).getName());
            System.out.println(BOLD + "Ingrese el ID de manera exacta... Por ejemplo '10'." + RESET);
            System.out.println(BOLD + "Ingrese 0 para salir." + RESET);
            try {
                String idSelected;
                if(!(idSelected = render.nextLine()).equals("0")) {
                    boolean found = false;
                    for (Publicacion publicacion : profile.getPublicationList()) {
                        if (publicacion.getId().equals(idSelected)) {
                            found = true;
                            System.out.println(BOLD + "Puede elegir entre estos 3 filtros para colocar \n\t" + Filters.BUENOSAIRES + " " + Filters.NEWYORK + " " + Filters.TOKIO + RESET);
                            System.out.println("Ingrese uno manera exacta... Por ejemplo 'BUENOSAIRES'.");
                            System.out.println(BOLD + "Ingrese 0 para salir." + RESET);
                            String filterSelected;
                            if (!(filterSelected = render.nextLine()).equals("0")) {
                                if (publicacion instanceof Imagen image) {
                                    image.setFiltro(filterSelected.toUpperCase());
                                    System.out.println(BOLD + "Se aplico un filtro " + filterSelected.toUpperCase() + " en la Imagen: " + publicacion.getId() + "\t" + publicacion.getName() + "\n\n" + RESET);
                                } else if (publicacion instanceof Video video) {
                                    video.setFiltro(filterSelected.toUpperCase());
                                    System.out.println(BOLD + "Se aplico un filtro " + filterSelected.toUpperCase() + " en el Video: " + publicacion.getId() + "\t" + publicacion.getName() + "\n\n" + RESET);
                                } else {
                                    System.out.println(BOLD + RED + "No se puede aplicar un filtro porque es un audio" + "\n\n" + RESET);

                                }
                            }
                            break;
                        }

                    }
                    if (!found) {
                        System.out.println("El ID ingresado no existe en la lista de publicaciones. Inténtelo nuevamente.");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            serialize(profile.getPublicationList());
        }
    }

    /** reproduce las publicaciones elegidas */
    public void AddPublicationsToPlay(PerfilInstagram profile) {
        if (!itsEmpty) {
            System.out.println(BOLD + "Elija la/s publicación/es que quiere reproducir." + RESET);
            for (int i = 0; i < profile.getPublicationList().size(); i++)
                System.out.println("\t" + profile.getPublicationList().get(i).getId() + "\t" + profile.getPublicationList().get(i).getName() + " - " + profile.getPublicationList().get(i).getType());
            System.out.println(BOLD + "Ingrese el ID de manera exacta... Por ejemplo '10'." + RESET);
            System.out.println(BOLD + "Ingrese 0 para salir." + RESET);
            try {
                String idSelected;
                while (!(idSelected = render.nextLine()).equals("0")) {
                    boolean found = false;
                    for (Publicacion publicacion : profile.getPublicationList()) {
                        if (publicacion.getId().equals(idSelected)) {
                            if(!(idsSelected.contains(idSelected))) {
                                publicationsToReproduce.add(publicacion);
                                idsSelected.add(idSelected);
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("El ID ingresado no existe en la lista de publicaciones. Inténtelo nuevamente.");
                    }
                }
                System.out.println(BOLD + "Desea ver en detalle las publicaciones seleccionadas?" + RESET);
                System.out.println(BOLD + GREEN + "1.Si" + RESET);
                System.out.println(BOLD + RED + "2.No" + RESET);
                seePublications = render.nextLine();
                if (seePublications.equals("1")) {
                    if (publicationsToReproduce.isEmpty()) {
                        System.out.println(BOLD + RED + "No hay publicaciones seleccionadas." + RESET);
                    } else {
                        System.out.println("Publicaciones seleccionadas ordenadas por nombre:");
                        publicationsToReproduce.sort(Comparator.comparing(Publicacion::getName));
                        publicationsToReproduce.forEach(profile::showPublication);
                    }
                }
            } catch (InputMismatchException error) {
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
                render.nextLine();
            }
        } else
            System.out.println(BOLD + RED + "No hay publicaciones seleccionadas." + RESET);
    }
}
