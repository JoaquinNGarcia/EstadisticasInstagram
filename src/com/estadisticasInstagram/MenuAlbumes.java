package com.estadisticasInstagram;

import com.estadisticasInstagram.controlador.PerfilInstagram;
import com.estadisticasInstagram.dominio.*;

import java.util.*;


public class MenuAlbumes {
    private static Scanner scanner = new Scanner(System.in);
    private int opcionSubMenu;
    private int albumIndice;
    private int publicacionIndice;
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
            System.out.println("10. Cantidad de publicaciones total y por tipo de un álbum");
            System.out.println("0. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del álbum:");
                    String nombre = scanner.nextLine();
                    Album albumCreado = new Album(nombre);
                    SubMenuAlbumes(albumCreado, raiz);
                    break;
                case 2:
                    System.out.println(raiz.toString());
                    break;
                case 3:
                    CambiarNombre(raiz, perfil);
                    break;
                case 4:
                    AgregarPublicacion(raiz, perfil);
                    break;
                case 5:
                    MostrarPublicacionesAlbum(raiz);
                    break;
                case 6:
                    EliminarPublicacionAlbum(raiz, perfil);
                    break;
                case 7:
                    EliminarPublicacionTodosAlbumes(raiz, perfil);
                    break;
                case 8:
                    EliminarAlbum(raiz, perfil);
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
    private static void SubMenuAlbumes(Album albumCreado, Album padre) {
        System.out.println("1. Confirmar");
        System.out.println("2. Agregar como sub-álbum");
        System.out.println("3. Cancelar");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                padre.agregarAlbum(albumCreado);
                break;
            case 2:
                if (padre.getAlbumList().isEmpty()) {
                    System.out.println("No hay álbumes para crear un sub-álbum");
                } else {
                    System.out.println("Seleccione un sub-álbum:");
                    for (int i = 0; i < padre.getAlbumList().size(); i++) {
                        System.out.println((i + 1) + ". " + padre.getAlbumList().get(i).getNombre());
                    }

                    int albumIndice = scanner.nextInt() - 1;
                    scanner.nextLine();
                    SubMenuAlbumes(albumCreado, padre.getAlbumList().get(albumIndice));
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public void NavegarPorAlbumes (Album albumSeleccionado) {
        if (albumSeleccionado.getAlbumList().isEmpty()) {
            estaVacio = true;
            System.out.println("No hay álbumes existentes");
        }
        else {
            System.out.println("Seleccione un álbum:");
            for (int i = 0; i < albumSeleccionado.getAlbumList().size(); i++) {
                System.out.println((i + 1) + ". " + albumSeleccionado.getAlbumList().get(i).getNombre());
            }
            albumIndice = scanner.nextInt() - 1;
            scanner.nextLine();
            System.out.println("1.Confirmar");
            System.out.println("2.Seguir seleccionando");
            opcionSubMenu = scanner.nextInt();
            scanner.nextLine();
        }
    }
    public void CambiarNombre(Album albumCambiado, PerfilInstagram perfil) { // si se quiere se pueden hacer variables locales para no tener tantos .get.get.get.......
        NavegarPorAlbumes(albumCambiado);
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                System.out.println("Ingrese el nuevo nombre para el álbum");
                String nombreNuevo = scanner.nextLine();
                if (albumCambiado.getAlbumList().get(albumIndice).getNombre().equals(nombreNuevo))
                    System.out.println("El álbum ya tiene ese nombre");
                else {
                    int j;
                    List<Publicacion> listaPublicaciones = albumCambiado.getAlbumList().get(albumIndice).getPublicaciones();
                    if (!listaPublicaciones.isEmpty()) {
                        for (int i = 0; i < listaPublicaciones.size(); i++) {
                            j = 0;
                            while (j < listaPublicaciones.get(i).getListaAlbumes().size()) {
                                if (listaPublicaciones.get(i).getListaAlbumes().get(j).equals(albumCambiado.getAlbumList().get(albumIndice).getNombre())) {
                                    listaPublicaciones.get(i).getListaAlbumes().set(j, nombreNuevo);
                                    perfil.actualizarListaPublicacion(i, j, listaPublicaciones.get(i), nombreNuevo);
                                }
                                j++;
                            }
                        }
                    }
                    albumCambiado.getAlbumList().get(albumIndice).setNombre(nombreNuevo);
                }
            }
            else
                CambiarNombre((albumCambiado.getAlbumList().get(albumIndice)), perfil);
        }
    }
    public void MostrarPublicacionesAlbum (Album albumSeleccionado) {
        NavegarPorAlbumes(albumSeleccionado);
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                List<Publicacion> publicacionesList = albumSeleccionado.getAlbumList().get(albumIndice).getPublicaciones();
                if (publicacionesList.isEmpty())
                    System.out.println("No hay publicaciones en el álbum para mostrar");
                else {
                    LinkedList<Publicacion> publicacionesLinkedList = new LinkedList<>(publicacionesList);
                    PerfilInstagram listaPublicaciones = new PerfilInstagram(publicacionesLinkedList);
                    listaPublicaciones.muestraLista();
                }
            } else
                MostrarPublicacionesAlbum((albumSeleccionado.getAlbumList().get((albumIndice))));
        }
    }
    public void EliminarAlbum(Album albumEliminar,PerfilInstagram perfil) {
        NavegarPorAlbumes((albumEliminar));
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                perfil.eliminarAlbum(albumEliminar.getAlbumList().get(albumIndice));
                perfil.eliminarAlbumDePublicacion(albumEliminar.getAlbumList().get(albumIndice).getNombre());
                albumEliminar.eliminarPublicaciones();
                albumEliminar.eliminarAlbum(albumIndice);

            } else
                EliminarAlbum((albumEliminar.getAlbumList().get(albumIndice)), perfil);
        }
    }
    public void EliminarPublicacionAlbum (Album albumSeleccionado, PerfilInstagram publicacion) {
        NavegarPorAlbumes(albumSeleccionado);
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                System.out.println("Elija la publicación a eliminar");
                for (int i = 0; i < albumSeleccionado.getAlbumList().get(albumIndice).getPublicaciones().size(); i++)
                    System.out.println((i + 1) + ". " + albumSeleccionado.getAlbumList().get(albumIndice).getPublicaciones().get(i).getId());
                publicacionIndice = scanner.nextInt() - 1;
                scanner.nextLine();
                int publicacionIndiceperfil = 0;
                for (int i = 0; i < publicacion.getListaPublicacion().size(); i++) {
                    if (publicacion.getListaPublicacion().get(i).getId() == albumSeleccionado.getAlbumList().get(albumIndice).getPublicaciones().get(publicacionIndice).getId())
                        publicacionIndiceperfil = i;
                }
                publicacion.MuestraPublicacion(publicacion.getListaPublicacion().get(publicacionIndiceperfil));
                System.out.println("Desea eliminar esta publicación del album?");
                System.out.println("1.Confirmar");
                System.out.println("2.Cancelar");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion == 1) {
                    if (albumSeleccionado.getAlbumList().get(albumIndice).getPublicaciones().isEmpty())
                        System.out.println("El álbum no tiene publicaciones");
                    else {
                        albumSeleccionado.getAlbumList().get(albumIndice).getPublicaciones().remove(publicacionIndice);
                        publicacion.getListaPublicacion().get(publicacionIndiceperfil).getListaAlbumes().remove(albumSeleccionado.getAlbumList().get(albumIndice).getNombre());
                        System.out.println("La publicación ha sido eliminada del álbum");
                    }
                }
                else
                    EliminarPublicacionAlbum(albumSeleccionado, publicacion);
            }
            else
                EliminarPublicacionAlbum((albumSeleccionado.getAlbumList().get(albumIndice)), publicacion);
        }
    }
    public void EliminarPublicacionTodosAlbumes (Album raiz, PerfilInstagram publicacion) {
        if (!raiz.getAlbumList().isEmpty()) {
            System.out.println("Elija la publicación a eliminar");
            for (int i = 0; i < publicacion.getListaPublicacion().size(); i++)
                System.out.println((i + 1) + ". " + publicacion.getListaPublicacion().get(i).getId());
            publicacionIndice = scanner.nextInt() - 1;
            scanner.nextLine();
            publicacion.MuestraPublicacion(publicacion.getListaPublicacion().get(publicacionIndice));
            System.out.println("Desea eliminar esta publicación de todos los albumes?");
            System.out.println("1.Confirmar");
            System.out.println("2.Cancelar");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 1) {
                int j = 0;
                int publicacionesEliminadas = 0;
                String nombrePublicacionEliminar = publicacion.getListaPublicacion().get(publicacionIndice).getId();
                for (int i = 0; i < raiz.getAlbumList().size(); i++) {
                    while (j < raiz.getAlbumList().get(i).getPublicaciones().size()) {
                        if (raiz.getAlbumList().get(i).getPublicaciones().get(j).getId() == nombrePublicacionEliminar) {
                            raiz.getAlbumList().get(i).getPublicaciones().remove(j);
                            publicacion.getListaPublicacion().get(publicacionIndice).getListaAlbumes().remove(raiz.getAlbumList().get(i).getNombre());
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
        NavegarPorAlbumes(albumSeleccionado);
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                int i = 0;
                boolean encontre = false;
                while(i < albumSeleccionado.getAlbumList().get(albumIndice).getPublicaciones().size() && encontre == false) {
                    if (albumSeleccionado.getAlbumList().get(albumIndice).getPublicaciones().get(i).getId() == publicacion.getId())
                        encontre = true;
                    i++;
                }
                System.out.println(i);
                if (encontre == true)
                    System.out.println("Ya se encuentra la publicación en el álbum");
                else {
                    publicacion.agregarAlbum(albumSeleccionado.getAlbumList().get(albumIndice));
                    albumSeleccionado.getAlbumList().get(albumIndice).agregarPublicacion(publicacion);
                }
            }
            else
                Agrega((albumSeleccionado.getAlbumList().get(albumIndice)), publicacion);
        }
    }
    public void AgregarPublicacion(Album albumSeleccionado, PerfilInstagram perfil) {
        System.out.println("Elija la publicación que quiere insertar en un álbum");
        for (int i = 0; i < perfil.getListaPublicacion().size(); i++)
            System.out.println((i + 1) + ". " + perfil.getListaPublicacion().get(i).getId());
        publicacionIndice = scanner.nextInt() - 1;
        scanner.nextLine();
        perfil.MuestraPublicacion(perfil.getListaPublicacion().get(publicacionIndice));
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
                Agrega(albumSeleccionado, perfil.getListaPublicacion().get(publicacionIndice));
        }
        else
            AgregarPublicacion(albumSeleccionado,perfil);
    }

    public void CantidadMGAcumuladosAlbum (Album albumSeleccionado) {
        NavegarPorAlbumes((albumSeleccionado));
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                System.out.println("Cantidad de me gustas acumulados: ");
                System.out.println(albumSeleccionado.getAlbumList().get(albumIndice).cantMgAcumulada());
            } else
                CantidadMGAcumuladosAlbum(albumSeleccionado.getAlbumList().get(albumIndice));
        }
    }
    public void CantidadPublicaciones(Album albumSeleccionado) {
        NavegarPorAlbumes((albumSeleccionado));
        if (estaVacio == false) {
            if (opcionSubMenu == 1) {
                System.out.println("Cantidad de publicaciones total: " + albumSeleccionado.getAlbumList().get(albumIndice).cantPublicacionesTotal());
                System.out.print("Cantidad de publicaciones por tipo: ");
                Map<Class<Publicacion>, Integer> mapa = albumSeleccionado.getAlbumList().get(albumIndice).cantPublicacionesTipo();
                if (mapa.isEmpty()) {
                    System.out.println("0");
                    //System.out.println(" ");
                }
                else
                    for (Map.Entry<Class<Publicacion>, Integer> entry : mapa.entrySet()) {
                        System.out.println(entry.getKey().getSimpleName() + ": " + entry.getValue());
                    }
            }
            else
                CantidadPublicaciones(albumSeleccionado.getAlbumList().get(albumIndice));
        }
    }

}