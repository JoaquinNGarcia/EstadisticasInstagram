package com.estadisticasInstagram.menu;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.Audio;
import com.estadisticasInstagram.dominio.Imagen;
import com.estadisticasInstagram.dominio.Publicacion;
import com.estadisticasInstagram.dominio.Video;

import java.util.*;

import static com.estadisticasInstagram.ColorsConsole.*;

public class MenuReproduccionMultimedia {
    private static Scanner render = new Scanner(System.in);

    private String seePublications, applyFilters;
    private boolean itsEmpty;
    LinkedList<Publicacion> publicacionesAReproducir = new LinkedList<>();
    Set<String> idsSeleccionados = new HashSet<>();

    private LinkedList<Audio> listAudio = new LinkedList<>();
    private LinkedList<Imagen> listImagen = new LinkedList<>();
    private LinkedList<Video> listVideo = new LinkedList<>();
    

    enum Filters {
        TOKIO,
        NEWYORK,
        BUENOSAIRES
    }

    public void startReproduccionMultimedia(PerfilInstagram profile) {
        double fullDuration = 0;
        String optionReproduccionSeleccionada = "", optionOrdenReproduccion = "";
        System.out.println(BOLD + "Desea aplicar filtros a las imagenes o los videos?" + RESET);
        System.out.println(BOLD + GREEN + "1.Si" + RESET);
        System.out.println(BOLD + RED + "2.No" + RESET);
        applyFilters = render.nextLine();
        while(applyFilters.equals("1")) {
            aplicarFiltros(profile);
            System.out.println(BOLD + "Desea aplicar filtros a las imagenes o los videos?" + RESET);
            System.out.println(BOLD + GREEN + "1.Si" + RESET);
            System.out.println(BOLD + RED + "2.No" + RESET);
            applyFilters = render.nextLine();
        }
        AddPublicationsToPlay(profile);
        itsEmpty = publicacionesAReproducir.isEmpty();
        if(!itsEmpty) {
            System.out.println(BOLD + "\nDesea reproducir las publicaciones seleccionadas? " + RESET);
            System.out.println(BOLD + GREEN + "1.Si" + RESET);
            System.out.println(BOLD + RED + "2.No" + RESET);
            optionReproduccionSeleccionada = render.nextLine().trim();
            try {
                if(optionReproduccionSeleccionada.equals("1") && !itsEmpty) {
                    System.out.println(BOLD + "\nPuede elegir 3 maneras de reproducir el contenido: \n" + RESET);
                    System.out.println(BOLD + "1.Ordenado por Tipo (Ascendente)." + RESET);
                    System.out.println(BOLD + "2.Ordenado por Tipo (Descendente)." + RESET);
                    System.out.println(BOLD + "3.Ordenado por Nombre (Ordenados alfabeticamente)." + RESET);
                    System.out.println(BOLD + "4.Ordenado por cantidad de 'me gustas' (Ascendente)." + RESET);
                    System.out.println(BOLD + "5.Ordenado por cantidad de 'me gustas' (Descendente)." + RESET);
                    System.out.println(BOLD + "0.Salir.\n" + RESET);
                    optionOrdenReproduccion = render.nextLine().trim();
                    switch (optionOrdenReproduccion){
                        case "1" -> {
                            publicacionesAReproducir.sort(Comparator.comparing(Publicacion::getType));
                        }
                        case "2" -> {
                            publicacionesAReproducir.sort(Comparator.comparing(Publicacion::getType).reversed());
                        }
                        case "3" -> {
                            publicacionesAReproducir.sort(Comparator.comparing(Publicacion::getName));
                        }
                        case "4" -> {
                            publicacionesAReproducir.sort(Comparator.comparing(Publicacion::getAmountLikes));

                        }
                        case "5" -> {
                            publicacionesAReproducir.sort(Comparator.comparing(Publicacion::getAmountLikes).reversed());
                        }
                        case "0" -> {
                            System.out.println(BOLD + "Se aplicara el valor por defecto.\n" + RESET);
                            publicacionesAReproducir.sort(Comparator.comparing(Publicacion::getId));
                        }
                    }
                    for(Publicacion publicacion : publicacionesAReproducir) {
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
                    for(Publicacion publicacion : publicacionesAReproducir) {
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
        publicacionesAReproducir.sort(Comparator.comparing(Publicacion::getId));
    }

    public void aplicarFiltros(PerfilInstagram profile) {
        if(!profile.getPublicationList().isEmpty()){
            System.out.println(BOLD + "Elija la publicación a la cual quiere aplicar el filtro." + RESET);
            for (int i = 0; i < profile.getPublicationList().size(); i++)
                System.out.println("\t" + profile.getPublicationList().get(i).getId() + "\t" + profile.getPublicationList().get(i).getName());
            System.out.println(BOLD + "Ingrese el ID de manera exacta... Por ejemplo '10'." + RESET);
            System.out.println(BOLD + "Ingrese 0 para salir." + RESET);
            try {
                String idSeleccionado;
                if(!(idSeleccionado = render.nextLine()).equals("0")) {
                    boolean encontrado = false;
                    for (Publicacion publicacion : profile.getPublicationList()) {
                        if (publicacion.getId().equals(idSeleccionado)) {
                            encontrado = true;
                            System.out.println(BOLD + "Puede elegir entre estos 3 filtros para colocar \n\t" + Filters.BUENOSAIRES + " " + Filters.NEWYORK + " " + Filters.TOKIO + RESET);
                            System.out.println("Ingrese uno manera exacta... Por ejemplo 'BUENOSAIRES'.");
                            System.out.println(BOLD + "Ingrese 0 para salir." + RESET);
                            String filtroSeleccionado;
                            if(!(filtroSeleccionado = render.nextLine()).equals("0")) {
                                if (publicacion instanceof Imagen imagen) {
                                    imagen.setFiltro(filtroSeleccionado.toUpperCase());
                                    System.out.println(BOLD + "Se aplico un filtro " + filtroSeleccionado.toUpperCase() + " en la Imagen: " + publicacion.getId() + "\t" + publicacion.getName() + "\n\n" + RESET);
                                } else if (publicacion instanceof Video video) {
                                    video.setFiltro(filtroSeleccionado.toUpperCase());
                                    System.out.println(BOLD + "Se aplico un filtro " + filtroSeleccionado.toUpperCase() + " en el Video: " + publicacion.getId() + "\t" + publicacion.getName() + "\n\n" + RESET);
                                } else {
                                    System.out.println(BOLD + RED + "No se puede aplicar un filtro porque es un audio" + "\n\n" + RESET);

                                }
                            }
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("El ID ingresado no existe en la lista de publicaciones. Inténtelo nuevamente.");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void AddPublicationsToPlay(PerfilInstagram profile) {
        if (!itsEmpty) {
            System.out.println(BOLD + "Elija la/s publicación/es que quiere reproducir." + RESET);
            for (int i = 0; i < profile.getPublicationList().size(); i++)
                System.out.println("\t" + profile.getPublicationList().get(i).getId() + "\t" + profile.getPublicationList().get(i).getName() + " - " + profile.getPublicationList().get(i).getType());
            System.out.println(BOLD + "Ingrese el ID de manera exacta... Por ejemplo '10'." + RESET);
            System.out.println(BOLD + "Ingrese 0 para salir." + RESET);
            try {
                String idSeleccionado;
                while (!(idSeleccionado = render.nextLine()).equals("0")) {
                    boolean encontrado = false;
                    for (Publicacion publicacion : profile.getPublicationList()) {
                        if (publicacion.getId().equals(idSeleccionado)) {
                            if(!(idsSeleccionados.contains(idSeleccionado))) {
                                publicacionesAReproducir.add(publicacion);
                                idsSeleccionados.add(idSeleccionado);
                            }
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("El ID ingresado no existe en la lista de publicaciones. Inténtelo nuevamente.");
                    }
                }
                System.out.println(BOLD + "Desea ver en detalle las publicaciones seleccionadas?" + RESET);
                System.out.println(BOLD + GREEN + "1.Si" + RESET);
                System.out.println(BOLD + RED + "2.No" + RESET);
                seePublications = render.nextLine();
                if (seePublications.equals("1")) {
                    if (publicacionesAReproducir.isEmpty()) {
                        System.out.println(BOLD + RED + "No hay publicaciones seleccionadas." + RESET);
                    } else {
                        System.out.println("Publicaciones seleccionadas ordenadas por nombre:");
                        publicacionesAReproducir.sort(Comparator.comparing(Publicacion::getName));
                        publicacionesAReproducir.forEach(profile::showPublication);
                    }
                }
            } catch (InputMismatchException error) {
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
                render.nextLine();
            }
        } else
            System.out.println(BOLD + RED + "No hay publicaciones seleccionadas." + RESET);
    }

    /*
    public void reproducirContenido(LinkedList<Publicacion> publicacionesSeleccionadas, String tipoDeClase) {
        for (Publicacion publicacion : publicacionesSeleccionadas) {
            Class<Publicacion> clase = (Class<Publicacion>) publicacion.getClass();
            if (clase.getSimpleName().equals(tipoDeClase)) {
                if (publicacion instanceof Audio audio) {

                    System.out.println(BOLD + "Reproduciendo: " + publicacion.getId() + "\t" + publicacion.getName() + RESET);
                    simulateProgressBar(100, 50, audio.getDuration());
                } else if (publicacion instanceof Imagen) {
                    System.out.println(BOLD + "Reproduciendo: " + publicacion.getId() + "\t" + publicacion.getName()  + RESET);
                    simulateProgressBar(100, 50, 100); // 10 segundos por defecto.
                } else if (publicacion instanceof Video video) {
                    System.out.println(BOLD + "Reproduciendo: " + publicacion.getId() + "\t" + publicacion.getName() + RESET);
                    simulateProgressBar(100, 50, video.getDuration());
                }
            }
        }
    }
    */

}
