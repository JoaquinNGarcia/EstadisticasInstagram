package com.estadisticasInstagram;

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

    enum Filters {
        TOKIO,
        NEWYORK,
        BUENOSAIRES
    }

    public void startReproduccionMultimedia(PerfilInstagram profile) {
        String option = "";
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
            System.out.println(BOLD + "\nSeleccione el tipo de contenido que desea reproducir: \n" + RESET);
            do {
                System.out.println(BOLD + "\t\t\t\t1. Audio" + RESET);
                System.out.println(BOLD + "\t\t\t\t2. Imagen" + RESET);
                System.out.println(BOLD + "\t\t\t\t3. Video" + RESET);
                System.out.println(BOLD + "\t\t\t\t0. Salir" + RESET);
                option = render.nextLine().trim();
                switch (option) {
                    case "1":
                        reproducirContenido(publicacionesAReproducir, "Audio");
                        break;
                    case "2":
                        reproducirContenido(publicacionesAReproducir, "Imagen");
                        break;
                    case "3":
                        reproducirContenido(publicacionesAReproducir, "Video");
                        break;
                    case "0":
                        System.out.println(BOLD + BLUE + "Ha salido de la reproduccion de multimedia" + RESET);
                        break;
                }
            } while (!option.equals("0") && !itsEmpty);
        } else {
            System.out.println(BOLD + RED + "No se agrego ninguna publicacion." + RESET);
        }
    }

    public void aplicarFiltros(PerfilInstagram profile) {
        if(!profile.getPublicationList().isEmpty()){
            System.out.println(BOLD + "Elija la publicación a la cual quiere aplicar el filtro." + RESET);
            for (int i = 0; i < profile.getPublicationList().size(); i++)
                System.out.println("\t" + profile.getPublicationList().get(i).getId() + "\t" + profile.getPublicationList().get(i).getName());
            System.out.println(BOLD + "Ingrese el ID de manera exacta... Por ejemplo '10'." + RESET);
            System.out.println(BOLD + "Ingrese 0 para salir." + RESET);
            try{
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
                                if (publicacion instanceof Imagen) {
                                    System.out.println(BOLD + "Se aplico un filtro " + filtroSeleccionado.toUpperCase() + " en la Imagen: " + publicacion.getId() + "\t" + publicacion.getName() + "\n\n" + RESET);
                                } else if (publicacion instanceof Video) {
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
                System.out.println("\t" + profile.getPublicationList().get(i).getId() + "\t" + profile.getPublicationList().get(i).getName());
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
                        System.out.println("Publicaciones seleccionadas:");
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

    public void simulateProgressBar(int total, int width, float delayMillis) {
        int progress = 0;
        int prevProgress = -1;

        System.out.println("La duracion de la siguiente publicacion sera de: " + delayMillis / 10 + " segundos.");
        System.out.println(BOLD + GREEN + "\t\t\t\tInicio de su reproduccion!" + RESET);
        while (progress <= total) {
            int currentWidth = (int) (((double) progress / total) * width);
            if (progress != prevProgress) {
                String progressBar = buildProgressBar(currentWidth, width, progress);
                System.out.print("\r" + progressBar + " " + progress + "%");
                prevProgress = progress;
            }
            try {
                Thread.sleep((long) delayMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress++;
        }
        System.out.println(BOLD + RED + "\n\t\t\t\tFin de su reproduccion!\n" + RESET);
    }

    private String buildProgressBar(int currentWidth, int totalWidth, int progress) {
        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < currentWidth; i++) {
            if(progress <= 50) {
                progressBar.append(BOLD + GREEN + "=" + RESET);
            } else {
                if (progress <= 80) {
                    progressBar.append(BOLD + YELLOW + "=" + RESET);
                } else {
                    progressBar.append(BOLD + RED + "=" + RESET);
                }
            }
        }
        for (int i = currentWidth; i < totalWidth; i++) {
            progressBar.append(" ");
        }
        progressBar.append("]");
        return progressBar.toString();
    }
}
