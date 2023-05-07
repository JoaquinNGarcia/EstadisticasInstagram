package com.estadisticasInstagram;

import com.estadisticasInstagram.dominio.Album;

import java.util.Scanner;


public class MenuAlbumes {
    private static Scanner scanner = new Scanner(System.in);


    public void startMenuAlbumes (Album raiz) { // HACER EL MENU MAS LINDO
        boolean salir = false;
        while (!salir) {
        System.out.println("1. Agregar álbum");
        System.out.println("2. Mostrar álbumes");
        System.out.println("3. Cambiar nombre a un álbum");
        System.out.println("4. Agregar publicacion a un álbum ");
        System.out.println("5. Eliminar album");
        System.out.println("6. Salir");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nombre del álbum:");
                String nombre = scanner.nextLine();
                Album albumcreado = new Album(nombre);
                SubMenuAlbumes(albumcreado,raiz);
                break;
            case 2:
                System.out.println(raiz.toString());
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.println("Ingrese el nombre del álbum a eliminar:");
                String nombreEliminar = scanner.nextLine();
                break;
            case 6:
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
                }
                else {
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
}
