package com.estadisticasInstagram;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.*;

import java.util.*;


public class MenuAlbumes {
    private static Scanner scanner = new Scanner(System.in);
    private int opcionSubMenu;
    private int albumindice;
    private int publicacionindice;
    private boolean estaVacio;

    public void startMenuAlbumes(Album raiz, PerfilInstagram perfil) { // HACER EL MENU MAS LINDO
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Agregar álbum");
            System.out.println("2. Mostrar álbumes");
            System.out.println("3. Cambiar nombre a un álbum");
            System.out.println("4. Agregar publicación a un álbum ");
            System.out.println("5. Mostrar publicaciones de un álbum ");
            System.out.println("6. Eliminar publicación de un álbum especifico");
            System.out.println("7. Eliminar publicación de todos los álbumes");
            System.out.println("8. Eliminar album");
            System.out.println("9. Cantidad de me gustas acumulados de un álbum");
            System.out.println("10. Cantidad de me gustas acumulados de un álbum");
            System.out.println("0. Salir");

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
                    EliminarPublicacionAlbum(raiz,perfil);
                    break;
                case 7:
                    EliminarPublicacionTodosAlbumes(raiz,perfil);
                    break;
                case 8:
                    EliminarAlbum(raiz,perfil);
                    break;
                case 9:
                    CantidadMGAcumuladosAlbum(raiz);
                    break;
                case 10:
                    CantidadPublicaciones(raiz);
                    break;
                case 0:
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
    public void EliminarPublicacionAlbum (Album albumSeleccionado,PerfilInstagram publicacion) {
        NavejarPorAlbumes(albumSeleccionado);
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                System.out.println("Elija la publicacion a eliminar");
                for (int i = 0; i < albumSeleccionado.getAlbumList().get(albumindice).getPublicaciones().size(); i++)
                    System.out.println((i + 1) + ". " + albumSeleccionado.getAlbumList().get(albumindice).getPublicaciones().get(i).getId());
                publicacionindice = scanner.nextInt() - 1;
                scanner.nextLine();
                int publicacionindiceperfil = 0;
                for (int i = 0; i < publicacion.getListaPublicacion().size(); i++) {
                    if (publicacion.getListaPublicacion().get(i).getId() == albumSeleccionado.getAlbumList().get(albumindice).getPublicaciones().get(publicacionindice).getId())
                        publicacionindiceperfil = i;
                }
                publicacion.MuestraPublicacion(publicacion.getListaPublicacion().get(publicacionindiceperfil));
                System.out.println("Desea eliminar esta publicacion del album?");
                System.out.println("1.Confirmar");
                System.out.println("2.Cancelar");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion == 1) {
                    if (albumSeleccionado.getAlbumList().get(albumindice).getPublicaciones().isEmpty())
                        System.out.println("El álbum no tiene publicaciones");
                    else {
                        albumSeleccionado.getAlbumList().get(albumindice).getPublicaciones().remove(publicacionindice);
                        publicacion.getListaPublicacion().get(publicacionindiceperfil).getListaAlbumes().remove(albumSeleccionado.getAlbumList().get(albumindice).getNombre());
                        System.out.println("La publicación ha sido eliminada del álbum");
                    }
                }
                else
                    EliminarPublicacionAlbum(albumSeleccionado,publicacion);
            }
            else
                EliminarPublicacionAlbum((albumSeleccionado.getAlbumList().get(albumindice)),publicacion);
        }
    }
    public void EliminarPublicacionTodosAlbumes (Album raiz,PerfilInstagram publicacion) {
        if (!raiz.getAlbumList().isEmpty()) {
            System.out.println("Elija la publicacion a eliminar");
            for (int i = 0; i < publicacion.getListaPublicacion().size(); i++)
                System.out.println((i + 1) + ". " + publicacion.getListaPublicacion().get(i).getId());
            publicacionindice = scanner.nextInt() - 1;
            scanner.nextLine();
            publicacion.MuestraPublicacion(publicacion.getListaPublicacion().get(publicacionindice));
            System.out.println("Desea eliminar esta publicacion de todos los albumes?");
            System.out.println("1.Confirmar");
            System.out.println("2.Cancelar");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 1) {
                int j = 0;
                int publicacionesEliminadas = 0;
                String nombrePublicacionEliminar = publicacion.getListaPublicacion().get(publicacionindice).getId();
                for (int i = 0; i < raiz.getAlbumList().size(); i++) {
                    while (j < raiz.getAlbumList().get(i).getPublicaciones().size()) {
                        if (raiz.getAlbumList().get(i).getPublicaciones().get(j).getId() == nombrePublicacionEliminar) {
                            raiz.getAlbumList().get(i).getPublicaciones().remove(j);
                            publicacion.getListaPublicacion().get(publicacionindice).getListaAlbumes().remove(raiz.getAlbumList().get(i).getNombre());
                            publicacionesEliminadas++;
                        }
                        j++;
                    }
                    j = 0;
                }
                if (publicacionesEliminadas == 0)
                    System.out.println("La publicación no estaba en ningun álbum");
                else
                    System.out.println("La publicación ha sido eliminada de todos los albumes");
            } else
                EliminarPublicacionTodosAlbumes(raiz, publicacion);
        }
        else
            System.out.println("No hay albumes existentes");
    }

    public void Agrega(Album albumSeleccionado, Publicacion publicacion) {
        NavejarPorAlbumes(albumSeleccionado);
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                int i = 0;
                boolean encontre = false;
                while(i < albumSeleccionado.getAlbumList().get(albumindice).getPublicaciones().size() && encontre == false) {
                    if (albumSeleccionado.getAlbumList().get(albumindice).getPublicaciones().get(i).getId() == publicacion.getId())
                        encontre = true;
                    i++;
                }
                System.out.println(i);
                if (encontre == true)
                    System.out.println("Ya se encuentra la publicación en el álbum");
                else {
                    publicacion.agregarAlbum(albumSeleccionado.getAlbumList().get(albumindice));
                    albumSeleccionado.getAlbumList().get(albumindice).agregarPublicacion(publicacion);
                }
            }
            else
                Agrega((albumSeleccionado.getAlbumList().get(albumindice)), publicacion);
        }
    }
    public void AgregarPublicacion(Album albumSeleccionado, PerfilInstagram perfil) {
        System.out.println("Elija la publicación que quiere insertar en un álbum");
        for (int i = 0; i < perfil.getListaPublicacion().size(); i++)
            System.out.println((i + 1) + ". " + perfil.getListaPublicacion().get(i).getId());
        publicacionindice = scanner.nextInt() - 1;
        scanner.nextLine();
        perfil.MuestraPublicacion(perfil.getListaPublicacion().get(publicacionindice));
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
                Agrega(albumSeleccionado, perfil.getListaPublicacion().get(publicacionindice));
        }
        else
            AgregarPublicacion(albumSeleccionado,perfil);
    }

    public void CantidadMGAcumuladosAlbum (Album albumSeleccionado) {
        NavejarPorAlbumes((albumSeleccionado));
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                System.out.println(albumSeleccionado.getAlbumList().get(albumindice).cantMgAcumulada());
            } else
                CantidadMGAcumuladosAlbum(albumSeleccionado.getAlbumList().get(albumindice));
        }
    }
    public void CantidadPublicaciones(Album albumSeleccionado) {
        NavejarPorAlbumes((albumSeleccionado));
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                System.out.println("Cantidad de publicaciones total: ");
                System.out.println(albumSeleccionado.getAlbumList().get(albumindice).cantPublicacionesTotal());
                System.out.println("Cantidad de publicaciones por tipo: ");
                Map<Class<Publicacion>, Integer> mapa = albumSeleccionado.getAlbumList().get(albumindice).cantPublicacionesTipo();
                if (mapa.isEmpty())
                    System.out.println("0");
                else
                    for (Map.Entry<Class<Publicacion>, Integer> entry : mapa.entrySet()) {
                        System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
                    }
            }
            else
                CantidadPublicaciones(albumSeleccionado.getAlbumList().get(albumindice));
        }
    }
}