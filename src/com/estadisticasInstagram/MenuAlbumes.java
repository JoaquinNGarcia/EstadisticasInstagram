package com.estadisticasInstagram;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.*;

import javax.swing.*;
import java.util.*;

import static com.estadisticasInstagram.ColoresConsola.*;

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

            System.out.println(BOLD + "1. Agregar álbum");
            System.out.println(BOLD + "2. Mostrar álbumes");
            System.out.println(BOLD + "3. Cambiar nombre a un álbum" + RESET);
            System.out.println(BOLD + "4. Agregar publicación a un álbum " + RESET);
            System.out.println(BOLD + "5. Mostrar publicaciones de un álbum " + RESET);
            System.out.println(BOLD + "6. Eliminar publicación de un álbum especifico" + RESET);
            System.out.println(BOLD + "7. Eliminar publicación de todos los álbumes" + RESET);
            System.out.println(BOLD + "8. Eliminar album" + RESET);
            System.out.println(BOLD + "9. Cantidad de me gustas acumulados de un álbum" + RESET);
            System.out.println(BOLD + "10. Cantidad de publicaciones total y por tipo" + RESET);
            System.out.println(BOLD + "0. Salir" + RESET);

            option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> {
                    System.out.println(BOLD + "Ingrese el nombre del álbum:" + RESET);
                    String name = scanner.nextLine();
                    Album albumCreated = new Album(name);
                    addAlbum(albumCreated, root);
                }
                case "2" -> System.out.println(root);
                case "3" -> Rename(root, profile);
                case "4" -> addPublication(root, profile);
                case "5" -> showPublications(root);
                case "6" -> RemovePublication(root, profile);
                case "7" -> RemovePublicationAllAlbums(root, profile);
                case "8" -> Remove(root, profile);
                case "9" -> TotalAcummulatedLikes(root);
                case "10" -> TotalPublications(root);
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
                father.agregarAlbum(albumCreated);
                System.out.println(BOLD + GREEN + "El álbum ha sido agregado" + RESET);
            }
            case "2" -> {
                if (father.getAlbumList().isEmpty()) {
                    System.out.println(BOLD + RED + "No hay álbumes para crear un sub-álbum\n" + RESET);
                } else {
                    System.out.println(BOLD + "Seleccione un sub-álbum:");
                    for (int i = 0; i < father.getAlbumList().size(); i++) {
                        System.out.println((i + 1) + ". " + father.getAlbumList().get(i).getNombre());
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
            case "3" -> {
            }
            default -> System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void SubMenuAlbum(Album albumSelected) {
        if (albumSelected.getAlbumList().isEmpty())
            System.out.println(BOLD + RED + "No hay álbumes existentes" + RESET);
        else {
            System.out.println(BOLD + "Seleccione un álbum:");
            for (int i = 0; i < albumSelected.getAlbumList().size(); i++) {
                System.out.println((i + 1) + ". " + albumSelected.getAlbumList().get(i).getNombre());
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

    public void Rename(Album albumChanged, PerfilInstagram profile) {
        SubMenuAlbum(albumChanged);
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                System.out.println(BOLD + "Ingrese el nuevo nombre para el álbum" + RESET);
                String newName = scanner.nextLine();
                if (albumChanged.getAlbumList().get(albumIndex).getNombre().equals(newName))
                    System.out.println(BOLD + RED + "El álbum ya tiene ese nombre" + RESET);
                else {
                    int j;
                    List<Publicacion> listaPublicaciones = albumChanged.getAlbumList().get(albumIndex)
                            .getPublicaciones();
                    if (!listaPublicaciones.isEmpty()) {
                        for (int i = 0; i < listaPublicaciones.size(); i++) {
                            j = 0;
                            while (j < listaPublicaciones.get(i).getListaAlbumes().size()) {
                                if (listaPublicaciones.get(i).getListaAlbumes().get(j)
                                        .equals(albumChanged.getAlbumList().get(albumIndex).getNombre())) {
                                    listaPublicaciones.get(i).getListaAlbumes().set(j, newName);
                                    profile.actualizarListaPublicacion(i, j, listaPublicaciones.get(i), newName);
                                }
                                j++;
                            }
                        }
                    }
                    System.out.println(BOLD + GREEN + "El álbum ha sido cambiado de nombre" + RESET);
                    albumChanged.getAlbumList().get(albumIndex).setNombre(newName);
                }
            } else if (optionSubMenu.equals("2"))
                Rename((albumChanged.getAlbumList().get(albumIndex)), profile);
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void showPublications(Album albumSelected) {
        SubMenuAlbum(albumSelected);
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                List<Publicacion> publicacionesList = albumSelected.getAlbumList().get(albumIndex).getPublicaciones();
                if (publicacionesList.isEmpty())
                    System.out.println(BOLD + RED + "No hay publicaciones en el álbum para mostrar" + RESET);
                else {
                    LinkedList<Publicacion> publicacionesLinkedList = new LinkedList<>(publicacionesList);
                    PerfilInstagram listaPublicaciones = new PerfilInstagram(publicacionesLinkedList);
                    listaPublicaciones.muestraLista();
                }
            } else if (optionSubMenu.equals("2"))
                showPublications((albumSelected.getAlbumList().get((albumIndex))));
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void Remove(Album albumRemove, PerfilInstagram profile) {
        SubMenuAlbum((albumRemove));
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                System.out.println(BOLD + "Desea eliminar esta publicación de todos los albumes?");
                System.out.println(BOLD + GREEN + "1.Confirmar" + RESET);
                System.out.println(BOLD + RED + "2.Cancelar" + RESET);
                optionSubMenu = scanner.nextLine();
                if (optionSubMenu.equals("1")) {
                    profile.eliminarAlbum(albumRemove.getAlbumList().get(albumIndex));
                    profile.eliminarAlbumDePublicacion(albumRemove.getAlbumList().get(albumIndex).getNombre());
                    albumRemove.eliminarPublicaciones();
                    albumRemove.eliminarAlbum(albumIndex);
                    System.out.println(BOLD + GREEN + "El álbum ha sido eliminado" + RESET);
                } else if (optionSubMenu.equals("2"))
                    Remove(albumRemove, profile);
                else
                    System.out.println(BOLD + RED + "Opción inválida" + RESET);
            } else if (optionSubMenu.equals("2"))
                Remove((albumRemove.getAlbumList().get(albumIndex)), profile);
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void RemovePublication(Album albumSelected, PerfilInstagram profile) {
        SubMenuAlbum(albumSelected);
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                if (albumSelected.getAlbumList().get(albumIndex).getPublicaciones().isEmpty())
                    System.out.println(BOLD + RED + "No hay publicaciones para eliminar" + RESET);
                else {
                    System.out.println(BOLD + "Elija la publicación a eliminar" + RESET);
                    for (int i = 0; i < albumSelected.getAlbumList().get(albumIndex).getPublicaciones().size(); i++)
                        System.out.println((i + 1) + ". "
                                + albumSelected.getAlbumList().get(albumIndex).getPublicaciones().get(i).getId());
                    try {
                        publicationIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (publicationIndex >= 0 && publicationIndex < albumSelected.getAlbumList().get(albumIndex)
                                .getPublicaciones().size()) {
                            int publicationIndexProfile = 0;
                            for (int i = 0; i < profile.getListaPublicacion().size(); i++) {
                                if (profile.getListaPublicacion().get(i).getId().equals(albumSelected.getAlbumList()
                                        .get(albumIndex).getPublicaciones().get(publicationIndex).getId()))
                                    publicationIndexProfile = i;
                            }
                            profile.MuestraPublicacion(profile.getListaPublicacion().get(publicationIndexProfile));
                            System.out.println(BOLD + "Desea eliminar esta publicación del album?" + RESET);
                            System.out.println(BOLD + GREEN + "1.Confirmar" + RESET);
                            System.out.println(BOLD + RED + "2.Cancelar" + RESET);
                            String option = scanner.nextLine();
                            scanner.nextLine();
                            if (option.equals("1")) {
                                if (albumSelected.getAlbumList().get(albumIndex).getPublicaciones().isEmpty())
                                    System.out.println(BOLD + RED + "El álbum no tiene publicaciones" + RESET);
                                else {
                                    albumSelected.getAlbumList().get(albumIndex).getPublicaciones()
                                            .remove(publicationIndex);
                                    profile.getListaPublicacion().get(publicationIndexProfile).getListaAlbumes()
                                            .remove(albumSelected.getAlbumList().get(albumIndex).getNombre());
                                    System.out.println(
                                            BOLD + GREEN + "La publicación ha sido eliminada del álbum" + RESET);
                                }
                            } else if (option.equals("2"))
                                RemovePublication(albumSelected, profile);
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
                RemovePublication((albumSelected.getAlbumList().get(albumIndex)), profile);
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void RemovePublicationAllAlbums(Album root, PerfilInstagram profile) {
        if (!itsEmpty) {
            System.out.println(BOLD + "Elija la publicación a eliminar" + RESET);
            for (int i = 0; i < profile.getListaPublicacion().size(); i++)
                System.out.println((i + 1) + ". " + profile.getListaPublicacion().get(i).getId());
            try {
                publicationIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (publicationIndex >= 0 && publicationIndex < profile.getListaPublicacion().size()) {
                    profile.MuestraPublicacion(profile.getListaPublicacion().get(publicationIndex));
                    System.out.println(BOLD + "Desea eliminar esta publicación de todos los albumes?" + RESET);
                    System.out.println(BOLD + GREEN + "1.Confirmar" + RESET);
                    System.out.println(BOLD + RED + "2.Cancelar" + RESET);
                    optionSubMenu = scanner.nextLine();
                    if (optionSubMenu.equals("1")) {
                        int j = 0;
                        int publicacionesEliminadas = 0;
                        String nombrePublicacionEliminar = profile.getListaPublicacion().get(publicationIndex).getId();
                        for (int i = 0; i < root.getAlbumList().size(); i++) {
                            while (j < root.getAlbumList().get(i).getPublicaciones().size()) {
                                if (root.getAlbumList().get(i).getPublicaciones().get(j).getId()
                                        .equals(nombrePublicacionEliminar)) {
                                    root.getAlbumList().get(i).getPublicaciones().remove(j);
                                    profile.getListaPublicacion().get(publicationIndex).getListaAlbumes()
                                            .remove(root.getAlbumList().get(i).getNombre());
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
                        RemovePublicationAllAlbums(root, profile);
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

    public void add(Album albumSelected, Publicacion publication) {
        SubMenuAlbum(albumSelected);
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                int i = 0;
                boolean encontre = false;
                while (i < albumSelected.getAlbumList().get(albumIndex).getPublicaciones().size() && !encontre) {
                    encontre = albumSelected.getAlbumList().get(albumIndex).getPublicaciones().get(i).getId()
                            .equals(publication.getId());
                    i++;
                }
                if (encontre)
                    System.out.println(BOLD + RED + "Ya se encuentra la publicación en el álbum" + RESET);
                else {
                    publication.agregarAlbum(albumSelected.getAlbumList().get(albumIndex));
                    albumSelected.getAlbumList().get(albumIndex).agregarPublicacion(publication);
                }
            } else if (optionSubMenu.equals("2"))
                add((albumSelected.getAlbumList().get(albumIndex)), publication);
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void addPublication(Album albumSelected, PerfilInstagram profile) {
        if (!itsEmpty) {
            System.out.println(BOLD + "Elija la publicación que quiere insertar en un álbum" + RESET);
            for (int i = 0; i < profile.getListaPublicacion().size(); i++)
                System.out.println((i + 1) + ". " + profile.getListaPublicacion().get(i).getId());
            try {
                publicationIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (publicationIndex >= 0 && publicationIndex < profile.getListaPublicacion().size()) {
                    profile.MuestraPublicacion(profile.getListaPublicacion().get(publicationIndex));
                    System.out.println(BOLD + "Desea agregar esta publicacion al album?" + RESET);
                    System.out.println(BOLD + GREEN + "1.Confirmar" + RESET);
                    System.out.println(BOLD + RED + "2.Cancelar" + RESET);

                    optionSubMenu = scanner.nextLine();
                    if (optionSubMenu.equals("1")) {
                        System.out.println(BOLD + "A que álbum desea agregar la publicación?" + RESET);
                        if (albumSelected.getAlbumList().isEmpty())
                            System.out.println(BOLD + RED + "No hay álbumes existentes" + RESET);
                        else
                            add(albumSelected, profile.getListaPublicacion().get(publicationIndex));
                    } else if (optionSubMenu.equals("2"))
                        addPublication(albumSelected, profile);
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

    public void TotalAcummulatedLikes(Album albumSelected) {
        SubMenuAlbum((albumSelected));
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                System.out.println(BOLD + "Cantidad de me gustas acumulados: " + RESET);
                System.out.println(albumSelected.getAlbumList().get(albumIndex).cantMgAcumulada());
            } else if (optionSubMenu.equals("2"))
                TotalAcummulatedLikes(albumSelected.getAlbumList().get(albumIndex));
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }

    public void TotalPublications(Album albumSelected) {
        SubMenuAlbum((albumSelected));
        if (!itsEmpty) {
            if (optionSubMenu.equals("1")) {
                System.out.println(BOLD + "Cantidad de publicaciones total: "
                        + albumSelected.getAlbumList().get(albumIndex).cantPublicacionesTotal() + RESET);
                System.out.print(BOLD + "Cantidad de publicaciones por tipo: " + RESET);
                Map<Class<Publicacion>, Integer> mapa = albumSelected.getAlbumList().get(albumIndex)
                        .cantPublicacionesTipo();
                if (mapa.isEmpty())
                    System.out.println("0");
                else
                    for (Map.Entry<Class<Publicacion>, Integer> entry : mapa.entrySet()) {
                        System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
                    }
            } else if (optionSubMenu.equals("2"))
                TotalPublications(albumSelected.getAlbumList().get(albumIndex));
            else
                System.out.println(BOLD + RED + "Opción inválida" + RESET);
        }
    }
}