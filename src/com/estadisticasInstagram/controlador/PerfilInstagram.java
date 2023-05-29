package com.estadisticasInstagram.controlador;

import com.estadisticasInstagram.dominio.*;

import java.util.*;

public class PerfilInstagram {
    private static LinkedList<Publicacion> listPublication;
    private LinkedList<Album> listAlbums = new LinkedList<>(); // podemos hacer tree o el list

    public PerfilInstagram(LinkedList<Publicacion> listPublication) {
        this.listPublication = listPublication;
    }

    public LinkedList<Publicacion> getPublicationList() {
        return listPublication;
    }

    public void setPublicationList(LinkedList<Publicacion> listPublication) {
        this.listPublication = listPublication;
    }

    public LinkedList<Album> getAlbumsList() {
        return listAlbums;
    }

    public void setAlbumsList(LinkedList<Album> listAlbums) {
        this.listAlbums = listAlbums;
    }

    public void updatePublicationList(int indexPub, int indexAlb, Publicacion publication, String newName) {
        this.listPublication.get(indexPub).getAlbumList().set(indexAlb, newName);
    }

    public void showList() {
        System.out.println("\033[0;1m" + "============================" + " PUBLICACION " + "\033[0;1m" + "============================");
        for (Publicacion publication : listPublication) {
            // Collections.sort(publication, new sortByName());
            System.out.println("ID: " + publication.getId());
            System.out.println("Nombre : " + publication.getName());
            System.out.println("Fecha de subida: " + publication.getDateUploaded());
            System.out.println("Etiquetas - Hashtags: " + publication.getHashtags());
            System.out.println("Cantidad de me gustas: " + publication.getAmountLikes());
            // Imprimir cada elemento de la lista de albumes
            System.out.print("Pertenece a los siguiente albumes: ");
            for (String elem : publication.getAlbumList()) {
                System.out.print(elem + " ");
            }
            System.out.println();
            if (publication instanceof Video) {
                Video video = (Video) publication;
                System.out.println("Duración del video: " + video.getDuration());
                System.out.println("Resolución del video: " + video.getResolution());
                System.out.println("Cantidad de cuadros: " + video.getTotalFrames());
            } else if (publication instanceof Imagen) {
                Imagen imagen = (Imagen) publication;
                System.out.println("Resolución de la imagen: " + imagen.getResolution());
                System.out.println("Ancho de la imagen: " + imagen.getWidth());
                System.out.println("Alto de la imagen: " + imagen.getHeight());
            } else if (publication instanceof Audio) {
                Audio audio = (Audio) publication;
                System.out.println("Duración del audio: " + audio.getDuration());
                System.out.println("Velocidad de bits: " + audio.getVelocityBits());
            }
            System.out.println("\033[0;1m" + "=====================================================================");
        }
    }
    public void showListSortByName() {
        System.out.println("\033[0;1m" + "============================" + " PUBLICACION " + "\033[0;1m" + "============================");
        listPublication.sort(Comparator.comparing(Publicacion::getName));
        for (Publicacion publication : listPublication) {
            System.out.println("ID: " + publication.getId());
            System.out.println("Nombre : " + publication.getName());
            System.out.println("Fecha de subida: " + publication.getDateUploaded());
            System.out.println("Etiquetas - Hashtags: " + publication.getHashtags());
            System.out.println("Cantidad de me gustas: " + publication.getAmountLikes());
            System.out.print("Pertenece a los siguiente albumes: ");
            for (String elem : publication.getAlbumList()) {
                System.out.print(elem + " ");
            }
            System.out.println();
            if (publication instanceof Video) {
                Video video = (Video) publication;
                System.out.println("Duración del video: " + video.getDuration());
                System.out.println("Resolución del video: " + video.getResolution());
                System.out.println("Cantidad de cuadros: " + video.getTotalFrames());
            } else if (publication instanceof Imagen) {
                Imagen imagen = (Imagen) publication;
                System.out.println("Resolución de la imagen: " + imagen.getResolution());
                System.out.println("Ancho de la imagen: " + imagen.getWidth());
                System.out.println("Alto de la imagen: " + imagen.getHeight());
            } else if (publication instanceof Audio) {
                Audio audio = (Audio) publication;
                System.out.println("Duración del audio: " + audio.getDuration());
                System.out.println("Velocidad de bits: " + audio.getVelocityBits());
            }
            System.out.println("\033[0;1m" + "=====================================================================");
        }
        listPublication.sort(Comparator.comparing(Publicacion::getId));

    }

    public void showPublication(Publicacion publication) {
        System.out.println("ID: " + publication.getId());
        System.out.println("Nombre: " + publication.getName());
        System.out.println("Fecha de subida: " + publication.getDateUploaded());
        System.out.println("Etiquetas - Hashtags: " + publication.getHashtags());
        System.out.println("Cantidad de me gustas: " + publication.getAmountLikes());
        System.out.print("Pertenece a los siguiente albumes: ");
        for (String elem : publication.getAlbumList()) {
            System.out.print(elem + " ");
        }
        System.out.println();
        if (publication instanceof Video) {
            Video video = (Video) publication;
            System.out.println("Duración del video: " + video.getDuration());
            System.out.println("Resolución del video: " + video.getResolution());
            System.out.println("Cantidad de cuadros: " + video.getTotalFrames());
        } else if (publication instanceof Imagen) {
            Imagen imagen = (Imagen) publication;
            System.out.println("Resolución de la imagen: " + imagen.getResolution());
            System.out.println("Ancho de la imagen: " + imagen.getWidth());
            System.out.println("Alto de la imagen: " + imagen.getHeight());
        } else if (publication instanceof Audio) {
            Audio audio = (Audio) publication;
            System.out.println("Duración del audio: " + audio.getDuration());
            System.out.println("Velocidad de bits: " + audio.getVelocityBits());
        }
        System.out.println("\033[0;1m" + "=====================================================================");
    }

    public void removeAlbumProfile(Album album) {
        this.listAlbums.remove(album);
    }

    public void removeAlbumFromPublication(String nombreEliminar) {
        for (Publicacion publicacion : listPublication)
            publicacion.getAlbumList().remove(nombreEliminar);
    }

    public Map<Class<Publicacion>, Integer> amountPublicationsType() {
        Map<Class<Publicacion>, Integer> mapa = new HashMap<Class<Publicacion>, Integer>();
        for (Publicacion publication : listPublication) {
            Class<Publicacion> clase = (Class<Publicacion>) publication.getClass();
            if (mapa.containsKey(clase))
                mapa.put(clase, mapa.get(clase) + 1);
            else
                mapa.put(clase, 1);
        }
        return mapa;
    }

    public int totalLikes() {
        int amount = 0;
        for (Publicacion publication : listPublication) {
            amount += publication.getAmountLikes();
        }
        return amount;
    }
}
