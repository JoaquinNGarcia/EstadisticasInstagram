package com.estadisticasInstagram;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.*;

import javax.swing.*;
import java.util.*;

import static com.estadisticasInstagram.ColorsConsole.*;

public class MenuAlbumes {
    private static Scanner scanner = new Scanner(System.in);
    private String optionSubMenu;
    private int albumIndex;
    private int publicationIndex;
    private boolean itsEmpty;

    public void startMenuAlbumes(Album root, PerfilInstagram profile) { // HACER EL MENU MAS LINDO
        String option = "";
        do {
            itsEmpty = root.getAlbumList().isEmpty();

            System.out.println(BOLD + "\t\t\t\t1. Agregar álbum" + RESET);
            System.out.println(BOLD + "\t\t\t\t2. Mostrar álbumes" + RESET);
            System.out.println(BOLD + "\t\t\t\t3. Cambiar nombre a un álbum" + RESET);
            System.out.println(BOLD + "\t\t\t\t4. Agregar publicación a un álbum " + RESET);
            System.out.println(BOLD + "\t\t\t\t5. Mostrar publicaciones de un álbum " + RESET);
            System.out.println(BOLD + "\t\t\t\t6. Eliminar publicación de un álbum especifico" + RESET);
            System.out.println(BOLD + "\t\t\t\t7. Eliminar publicación de todos los álbumes" + RESET);
            System.out.println(BOLD + "\t\t\t\t8. Eliminar album" + RESET);
            System.out.println(BOLD + "\t\t\t\t9. Cantidad de me gustas acumulados de un álbum" + RESET);
            System.out.println(BOLD + "\t\t\t\t10. Cantidad de publicaciones total y por tipo" + RESET);
            System.out.println(BOLD + "\t\t\t\t0. Salir" + RESET);

            option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> {
                    System.out.println(BOLD + "Ingrese el nombre del álbum:" + RESET);
                    String name = scanner.nextLine();
                    Album albumCreated = new Album(name);
                    addAlbum(albumCreated, root);
                }
                case "2" -> System.out.println(root);
                case "3" -> renameAlbum(root, profile);
                case "4" -> addPublication(root, profile);
                case "5" -> showPublicationsAlbum(root,profile);
                case "6" -> removePublication(root, profile);
                case "7" -> removePublicationAllAlbums(root, profile);
                case "8" -> removeAlbum(root, profile);
                case "9" -> totalAcummulatedLikesAlbum(root);
                case "10" -> totalPublicationsAlbum(root);
                case "0" -> System.out.println(BOLD + BLUE + "Ha salido del gestor de álbumes" + RESET);
            }
        } while (!option.equals("0"));
    }

    private void addAlbum(Album albumCreated, Album father) {
        String option;
        System.out.println(BOLD + GREEN + "1. Confirmar" + RESET);
        System.out.println(BOLD + "2. Agregar como sub-álbum" + RESET);
        System.out.println(BOLD + RED + "3. Cancelar" + RESET);

        option = scanner.nextLine().trim();

        switch (option) {
            case "1" -> {
                father.addAlbum(albumCreated);
                System.out.println(BOLD + GREEN + "El álbum ha sido agregado" + RESET);
            }
            case "2" -> {
                if (father.getAlbumList().isEmpty()) {
                    System.out.println(BOLD + RED + "No hay álbumes para crear un sub-álbum\n" + RESET);
                } else {
                    System.out.println(BOLD + "Seleccione un sub-álbum:");
                    int i = 1;
                    for (Album album : father.getAlbumList()) {
                        System.out.println(i + ". " + album.getName());
                        i++;
                    }
                    try {
                        albumIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (albumIndex >= 0 && albumIndex < father.getAlbumList().size())
                            addAlbum(albumCreated, father.getAlbumList().get(albumIndex));
                        else
                            System.out.println(BOLD + RED + "Opción inválida" + RESET);
                    } catch (InputMismatchException error) {
                        System.out.println(BOLD + RED + "Opción inválida" + RESET);
                    }
                }
            }
            case "3" -> {}
            default -> System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void subMenuAlbum(Album albumSelected) {
        if (albumSelected.getAlbumList().isEmpty())
            System.out.println(BOLD + RED + "No hay álbumes existentes" + RESET);
        else {
            System.out.println(BOLD + "Seleccione un álbum:");
            int i = 1;
            for (Album album : albumSelected.getAlbumList()) {
                System.out.println(i + ". " + album.getName());
                i++;
            }
            try {
                albumIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                System.out.println(BOLD + "1.Confirmar" + RESET);
                System.out.println(BOLD + "2.Buscar sub-álbum" + RESET);
                if (albumIndex >= 0 && albumIndex < albumSelected.getAlbumList().size())
                    optionSubMenu = scanner.nextLine();
                else
                    optionSubMenu = "-1";
            } catch (InputMismatchException error) {
                optionSubMenu = "-1";
                scanner.nextLine();
            }
        }
    }

    public void renameAlbum(Album albumChanged, PerfilInstagram profile) {
        subMenuAlbum(albumChanged);
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                System.out.println(BOLD + "Ingrese el nuevo nombre para el álbum" + RESET);
                String newName = scanner.nextLine();
                if (albumChanged.getAlbumList().get(albumIndex).getName().equals(newName))
                    System.out.println(BOLD + RED + "El álbum ya tiene ese nombre" + RESET);
                else {
                    int j;
                    List<Publicacion> listaPublicaciones = albumChanged.getAlbumList().get(albumIndex).getPublications();
                    if (!listaPublicaciones.isEmpty()) {
                        for (int i = 0; i < listaPublicaciones.size(); i++) {
                            j = 0;
                            while (j < listaPublicaciones.get(i).getAlbumList().size()) {
                                if (listaPublicaciones.get(i).getAlbumList().get(j).equals(albumChanged.getAlbumList().get(albumIndex).getName())) {
                                    listaPublicaciones.get(i).getAlbumList().set(j, newName);
                                    profile.updatePublicationList(i, j, listaPublicaciones.get(i), newName);
                                }
                                j++;
                            }
                        }
                    }
                    System.out.println(BOLD + GREEN + "El álbum ha sido cambiado de nombre" + RESET);
                    albumChanged.getAlbumList().get(albumIndex).setName(newName);
                }
            } else if (optionSubMenu.equals("2"))
                renameAlbum((albumChanged.getAlbumList().get(albumIndex)), profile);
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void showPublicationsAlbum(Album albumSelected,PerfilInstagram profile) {
        subMenuAlbum(albumSelected);
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                List<Publicacion> publicacionesList = albumSelected.getAlbumList().get(albumIndex).getPublications();
                if (publicacionesList.isEmpty())
                    System.out.println(BOLD + RED + "No hay publicaciones en el álbum para mostrar" + RESET);
                else {
                    LinkedList<Publicacion> publicacionesLinkedList = new LinkedList<>(publicacionesList);
                    publicacionesLinkedList.forEach(profile::showPublication);
                }
            } else if (optionSubMenu.equals("2"))
                showPublicationsAlbum((albumSelected.getAlbumList().get((albumIndex))),profile);
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void removeAlbum(Album albumRemove, PerfilInstagram profile) {
        subMenuAlbum((albumRemove));
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                System.out.println(BOLD + "Desea eliminar este álbum?");
                System.out.println(BOLD + GREEN + "1.Confirmar" + RESET);
                System.out.println(BOLD + RED + "2.Cancelar" + RESET);
                String option = scanner.nextLine();
                if (option.equals("1")) {
                    profile.removeAlbumProfile(albumRemove.getAlbumList().get(albumIndex));
                    profile.removeAlbumFromPublication(albumRemove.getAlbumList().get(albumIndex).getName());
                    albumRemove.removePublications();
                    albumRemove.removeAlbum(albumIndex);
                    System.out.println(BOLD + GREEN + "El álbum ha sido eliminado" + RESET);
                } else if (option.equals("2"))
                    System.out.println(BOLD + RED + "Ha cancelado la acción" + RESET);
                else
                    System.out.println(BOLD + RED + "Opción inválida" + RESET);
            } else if (optionSubMenu.equals("2"))
                removeAlbum((albumRemove.getAlbumList().get(albumIndex)), profile);
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void removePublication(Album albumSelected, PerfilInstagram profile) {
        subMenuAlbum(albumSelected);
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                if (albumSelected.getAlbumList().get(albumIndex).getPublications().isEmpty())
                    System.out.println(BOLD + RED + "No hay publicaciones para eliminar" + RESET);
                else {
                    int k = 1;
                    System.out.println(BOLD + "Elija la publicación a eliminar" + RESET);
                    for (Publicacion publicacion : albumSelected.getAlbumList().get(albumIndex).getPublications()) {
                        System.out.println(k + ". " + publicacion.getId());
                        k++;
                    }
                    try {
                        publicationIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (publicationIndex >= 0 && publicationIndex < albumSelected.getAlbumList().get(albumIndex)
                                .getPublications().size()) {
                            int publicationIndexProfile = 0;
                            for (int i = 0; i < profile.getPublicationList().size(); i++) {
                                if (profile.getPublicationList().get(i).getId().equals(albumSelected.getAlbumList().get(albumIndex).getPublications().get(publicationIndex).getId()))
                                    publicationIndexProfile = i;
                            }
                            profile.showPublication(profile.getPublicationList().get(publicationIndexProfile));
                            System.out.println(BOLD + "Desea eliminar esta publicación del album?" + RESET);
                            System.out.println(BOLD + GREEN + "1.Confirmar" + RESET);
                            System.out.println(BOLD + RED + "2.Cancelar" + RESET);
                            String option = scanner.nextLine();
                            scanner.nextLine();
                            if (option.equals("1")) {
                                if (albumSelected.getAlbumList().get(albumIndex).getPublications().isEmpty())
                                    System.out.println(BOLD + RED + "El álbum no tiene publicaciones" + RESET);
                                else {
                                    albumSelected.getAlbumList().get(albumIndex).getPublications()
                                            .remove(publicationIndex);
                                    profile.getPublicationList().get(publicationIndexProfile).getAlbumList()
                                            .remove(albumSelected.getAlbumList().get(albumIndex).getName());
                                    System.out.println(
                                            BOLD + GREEN + "La publicación ha sido eliminada del álbum" + RESET);
                                }
                            } else if (option.equals("2"))
                                System.out.println(BOLD + RED + "Ha cancelado la acción" + RESET);
                            else
                                System.out.println(BOLD + RED + "Opción inválida" + RESET);
                        } else
                            System.out.println(BOLD + RED + "Opción inválida" + RESET);
                    } catch (InputMismatchException error) {
                        System.out.println(BOLD + RED + "Opción inválida" + RESET);
                        scanner.nextLine();
                    }
                }
            } else if (optionSubMenu.equals("2"))
                removePublication((albumSelected.getAlbumList().get(albumIndex)), profile);
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void removePublicationAllAlbums(Album root, PerfilInstagram profile) {
        if (!itsEmpty) {
            System.out.println(BOLD + "Elija la publicación a eliminar" + RESET);
            int k = 1;
            for (Publicacion publicacion : profile.getPublicationList()) {
                System.out.println(k + ". " + publicacion.getId());
                k++;
            }
            try {
                publicationIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (publicationIndex >= 0 && publicationIndex < profile.getPublicationList().size()) {
                    profile.showPublication(profile.getPublicationList().get(publicationIndex));
                    System.out.println(BOLD + "Desea eliminar esta publicación de todos los albumes?" + RESET);
                    System.out.println(BOLD + GREEN + "1.Confirmar" + RESET);
                    System.out.println(BOLD + RED + "2.Cancelar" + RESET);
                    optionSubMenu = scanner.nextLine();
                    if (optionSubMenu.equals("1")) {
                        int j = 0;
                        int publicacionesEliminadas = 0;
                        String nombrePublicacionEliminar = profile.getPublicationList().get(publicationIndex).getId();
                        for (int i = 0; i < root.getAlbumList().size(); i++) {
                            while (j < root.getAlbumList().get(i).getPublications().size()) {
                                if (root.getAlbumList().get(i).getPublications().get(j).getId()
                                        .equals(nombrePublicacionEliminar)) {
                                    root.getAlbumList().get(i).getPublications().remove(j);
                                    profile.getPublicationList().get(publicationIndex).getAlbumList().remove(root.getAlbumList().get(i).getName());
                                    publicacionesEliminadas++;
                                }
                                j++;
                            }
                            j = 0;
                        }
                        if (publicacionesEliminadas == 0)
                            System.out.println(BOLD + RED + "La publicación no estaba en ningun álbum" + RESET);
                        else
                            System.out.println(
                                    BOLD + GREEN + "La publicación ha sido eliminada de todos los albumes" + RESET);
                    } else if (optionSubMenu.equals("2"))
                        System.out.println(BOLD + RED + "Ha cancelado la acción" + RESET);
                    else
                        System.out.println(BOLD + RED + "Opción inválida" + RESET);
                } else
                    System.out.println(BOLD + RED + "Opción inválida" + RESET);
            } catch (InputMismatchException error) {
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
                scanner.nextLine();
            }
        } else
            System.out.println(BOLD + RED + "No hay albumes existentes" + RESET);
    }

    public void insertPublication(Album albumSelected, Publicacion publication) {
        subMenuAlbum(albumSelected);
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                int i = 0;
                boolean found = false;
                while (i < albumSelected.getAlbumList().get(albumIndex).getPublications().size() && !found) {
                    found = albumSelected.getAlbumList().get(albumIndex).getPublications().get(i).getId()
                            .equals(publication.getId());
                    i++;
                }
                if (found)
                    System.out.println(BOLD + RED + "Ya se encuentra la publicación en el álbum" + RESET);
                else {
                    publication.addAlbum(albumSelected.getAlbumList().get(albumIndex));
                    albumSelected.getAlbumList().get(albumIndex).addPublication(publication);
                }
            } else if (optionSubMenu.equals("2"))
                insertPublication((albumSelected.getAlbumList().get(albumIndex)), publication);
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void addPublication(Album albumSelected, PerfilInstagram profile) {
        if (!itsEmpty) {
            int i = 1;
            System.out.println(BOLD + "Elija la publicación que quiere insertar en un álbum" + RESET);
            for (Publicacion publicacion : profile.getPublicationList()) {
                System.out.println(i + ". " + publicacion.getId());
                i++;
            }
            try {
                publicationIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (publicationIndex >= 0 && publicationIndex < profile.getPublicationList().size()) {
                    profile.showPublication(profile.getPublicationList().get(publicationIndex));
                    System.out.println(BOLD + "Desea agregar esta publicacion al album?" + RESET);
                    System.out.println(BOLD + GREEN + "1.Confirmar" + RESET);
                    System.out.println(BOLD + RED + "2.Cancelar" + RESET);

                    optionSubMenu = scanner.nextLine();
                    if (optionSubMenu.equals("1")) {
                        System.out.println(BOLD + "A que álbum desea agregar la publicación?" + RESET);
                        if (albumSelected.getAlbumList().isEmpty())
                            System.out.println(BOLD + RED + "No hay álbumes existentes" + RESET);
                        else
                            insertPublication(albumSelected, profile.getPublicationList().get(publicationIndex));
                    } else if (optionSubMenu.equals("2"))
                        System.out.println(BOLD + RED + "Ha cancelado la acción" + RESET);
                    else
                        System.out.println(BOLD + RED + "Opción inválida" + RESET);
                } else
                    System.out.println(BOLD + RED + "Opción inválida" + RESET);
            } catch (InputMismatchException error) {
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
                scanner.nextLine();
            }
        } else
            System.out.println(BOLD + RED + "No hay albumes existentes" + RESET);
    }

    public void totalAcummulatedLikesAlbum(Album albumSelected) {
        subMenuAlbum((albumSelected));
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                System.out.println(BOLD + "Cantidad de me gustas acumulados: " + RESET);
                System.out.println(albumSelected.getAlbumList().get(albumIndex).totalLikes());
            } else if (optionSubMenu.equals("2"))
                totalAcummulatedLikesAlbum(albumSelected.getAlbumList().get(albumIndex));
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void totalPublicationsAlbum(Album albumSelected) {
        subMenuAlbum((albumSelected));
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                System.out.println(BOLD + "Cantidad de publicaciones total: "
                        + albumSelected.getAlbumList().get(albumIndex).totalPublications() + RESET);
                System.out.print(BOLD + "Cantidad de publicaciones por tipo: " + RESET);
                Map<Class<Publicacion>, Integer> mapa = albumSelected.getAlbumList().get(albumIndex)
                        .amountPublicationsType();
                if (mapa.isEmpty())
                    System.out.println("0");
                else
                    for (Map.Entry<Class<Publicacion>, Integer> entry : mapa.entrySet()) {
                        System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
                    }
            } else if (optionSubMenu.equals("2"))
                totalPublicationsAlbum(albumSelected.getAlbumList().get(albumIndex));
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }
}