package com.estadisticasInstagram;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class MenuAlbumes {
    private static Scanner scanner = new Scanner(System.in);
    private int opcionSubMenu;
    private int albumindice;
    private boolean estaVacio;

    public void startMenuAlbumes(Album raiz, PerfilInstagram perfil) { // HACER EL MENU MAS LINDO
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Agregar álbum");
            System.out.println("2. Mostrar álbumes");
            System.out.println("3. Cambiar nombre a un álbum");
            System.out.println("4. Agregar publicacion a un álbum ");
            System.out.println("5. Mostrar Publicaciones de un álbum ");
            System.out.println("6. Eliminar album");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del álbum:");
                    String nombre = scanner.nextLine();
                    Album albumcreado = new Album(nombre);
                    SubMenuAlbumes(albumcreado, raiz);
                    break;
                case 2:
                    System.out.println(raiz.toString());
                    break;
                case 3:
                    CambiarNombre(raiz,perfil);
                    break;
                case 4:
                    AgregarPublicacion(raiz, perfil);
                    break;
                case 5:
                    MostrarPublicacionesAlbum(raiz);
                    break;
                case 6:
                    EliminarAlbum(raiz,perfil);
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    private static void SubMenuAlbumes(Album albumcreado, Album padre) {
        System.out.println("1. Confirmar");
        System.out.println("2. Agregar como sub-álbum");
        System.out.println("3. Cancelar");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                padre.agregarAlbum(albumcreado);
                break;
            case 2:
                if (padre.getAlbumList().isEmpty()) {
                    System.out.println("No hay albumes para crear un sub-álbum");
                } else {
                    System.out.println("Seleccione un sub-álbum:");
                    for (int i = 0; i < padre.getAlbumList().size(); i++) {
                        System.out.println((i + 1) + ". " + padre.getAlbumList().get(i).getNombre());
                    }

                    int albumindice = scanner.nextInt() - 1;
                    scanner.nextLine();
                    SubMenuAlbumes(albumcreado, padre.getAlbumList().get(albumindice));
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public void NavejarPorAlbumes (Album albumSeleccionado) {
        if (albumSeleccionado.getAlbumList().isEmpty()) {
            estaVacio = true;
            System.out.println("No hay álbumes existentes");
        }
        else {
            System.out.println("Seleccione un álbum:");
            for (int i = 0; i < albumSeleccionado.getAlbumList().size(); i++) {
                System.out.println((i + 1) + ". " + albumSeleccionado.getAlbumList().get(i).getNombre());
            }
            albumindice = scanner.nextInt() - 1;
            scanner.nextLine();
            System.out.println("1.Confirmar");
            System.out.println("2.Seguir seleccionando");
            opcionSubMenu = scanner.nextInt();
            scanner.nextLine();
        }
    }
    public void Agrega(Album albumSeleccionado, Publicacion publicacion) {
        NavejarPorAlbumes(albumSeleccionado);
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                publicacion.agregarAlbum(albumSeleccionado.getAlbumList().get(albumindice));
                albumSeleccionado.getAlbumList().get(albumindice).agregarPublicacion(publicacion);
            } else
                Agrega((albumSeleccionado.getAlbumList().get(albumindice)), publicacion);
        }
    }
    public void CambiarNombre(Album albumcambiado,PerfilInstagram perfil) { // si se quiere se pueden hacer variables locales para no tener tantos .get.get.get.......
        NavejarPorAlbumes(albumcambiado);
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                System.out.println("Ingrese el nuevo nombre para el álbum");
                String nombreNuevo = scanner.nextLine();
                int j;
                List<Publicacion> listaPublicaciones = albumcambiado.getAlbumList().get(albumindice).getPublicaciones();
                if (!listaPublicaciones.isEmpty()) {
                    for (int i = 0; i < listaPublicaciones.size(); i++) {
                        j = 0;
                        while (j < listaPublicaciones.get(i).getListaAlbumes().size()) {
                            if (listaPublicaciones.get(i).getListaAlbumes().get(j) == albumcambiado.getAlbumList().get(albumindice).getNombre()) {
                                listaPublicaciones.get(i).getListaAlbumes().set(j, nombreNuevo);
                                perfil.actualizarListaPublicacion(i, j, listaPublicaciones.get(i), nombreNuevo);
                            }
                            j++;
                        }
                    }
                }
                albumcambiado.getAlbumList().get(albumindice).setNombre(nombreNuevo);
            } else
                CambiarNombre((albumcambiado.getAlbumList().get(albumindice)), perfil);
        }
    }

    public void MostrarPublicacionesAlbum (Album albumSeleccionado) {
        NavejarPorAlbumes(albumSeleccionado);
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                List<Publicacion> publicacionesList = albumSeleccionado.getAlbumList().get(albumindice).getPublicaciones();
                if (publicacionesList.isEmpty())
                    System.out.println("No hay publicaciones en el álbum para mostrar");
                else {
                    LinkedList<Publicacion> publicacionesLinkedList = new LinkedList<>(publicacionesList);
                    PerfilInstagram listaPublicaciones = new PerfilInstagram(publicacionesLinkedList);
                    listaPublicaciones.muestraLista();
                }
            } else
                MostrarPublicacionesAlbum((albumSeleccionado.getAlbumList().get((albumindice))));
        }
    }
    public void EliminarAlbum(Album albumeliminar,PerfilInstagram perfil) {
        NavejarPorAlbumes((albumeliminar));
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                perfil.eliminarAlbum(albumeliminar.getAlbumList().get(albumindice));
                perfil.eliminarAlbumDePublicacion(albumeliminar.getAlbumList().get(albumindice).getNombre());
                albumeliminar.eliminarPublicaciones();
                albumeliminar.eliminarAlbum(albumindice);

            } else
                EliminarAlbum((albumeliminar.getAlbumList().get(albumindice)), perfil);
        }
    }

    public void AgregarPublicacion(Album albumSeleccionado, PerfilInstagram publicacion) {
        System.out.println("Elija la publicación que quiere insertar en un álbum");
        for (int i = 0; i < publicacion.getListaPublicacion().size(); i++)
            System.out.println((i + 1) + ". " + publicacion.getListaPublicacion().get(i).getId());
        int publicacionindice = scanner.nextInt() - 1;
        scanner.nextLine();
        publicacion.MuestraPublicacion(publicacion.getListaPublicacion().get(publicacionindice));
        System.out.println("Desea agregar esta publicacion al album?");
        System.out.println("1.Confirmar");
        System.out.println("2.Cancelar");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 1) {
            System.out.println("A que álbum desea agregar la publicación?");
            if (albumSeleccionado.getAlbumList().isEmpty())
                System.out.println("No hay álbumes existentes");
            else
                Agrega(albumSeleccionado, publicacion.getListaPublicacion().get(publicacionindice));
        }
        else
            AgregarPublicacion(albumSeleccionado,publicacion);
    }
}

