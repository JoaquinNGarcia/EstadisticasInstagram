package com.estadisticasInstagram.controlador;

import com.estadisticasInstagram.dominio.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.estadisticasInstagram.ColorsConsole.*;

public class PerfilInstagram {
    private static LinkedList<Publicacion> listPublication;
    private LinkedList<Album> listAlbums = new LinkedList<>();

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

    public LinkedList<Publicacion> getListPublicationByType (String type) {
        LinkedList<Publicacion> publicationByType = new LinkedList<>();
        for (Publicacion publication : listPublication){
            if (publication.getType().equals(type))
                publicationByType.add(publication);
        }
        return publicationByType;
    }
    public static void showListByType(LinkedList<Publicacion> publicationsByType) {
        System.out.println(BOLD + "=============================== " + RESET + BOLD +  UNDERLINED + BLUE + publicationsByType.get(0).getType().toUpperCase() + RESET + BOLD + " ===============================" + RESET);
        for (Publicacion publication : publicationsByType) {
            System.out.println(BOLD + "ID: " + RESET + publication.getId());
            System.out.println(BOLD + "Nombre : " + RESET + publication.getName());
            System.out.println(BOLD + "Fecha de subida: " + RESET + publication.getDateUploaded());
            System.out.println(BOLD + "Etiquetas - Hashtags: " + RESET + publication.getHashtags());
            System.out.println(BOLD + "Cantidad de me gustas: " + RESET + RED + "❤" + publication.getAmountLikes() + RESET);
            System.out.println("\n" + BOLD + UNDERLINED + "Comentarios:" + RESET);
            int i = 1;
            for (String comment : publication.getListComments()) {
                System.out.print("\n" + BOLD + "Comentario " + RESET + i + ": " + comment);
                i++;
            }
            System.out.println("\n");
            if (publication instanceof Video) {
                Video video = (Video) publication;
                System.out.println(BOLD + "Filtro: " + RESET + video.getFiltro() + "\n");
                System.out.println(BOLD + "Duración del video: " + RESET + video.getDuration());
                System.out.println(BOLD + "Resolución del video: " + RESET + video.getResolution());
                System.out.println(BOLD + "Cantidad de cuadros: " + RESET + video.getTotalFrames());
            } else if (publication instanceof Imagen) {
                Imagen imagen = (Imagen) publication;
                System.out.println(BOLD + "Filtro: " + RESET + imagen.getFiltro() + "\n");
                System.out.println(BOLD + "Resolución de la imagen: " + RESET + imagen.getResolution());
                System.out.println(BOLD + "Ancho de la imagen: " + RESET + imagen.getWidth());
                System.out.println(BOLD + "Alto de la imagen: " + RESET + imagen.getHeight());
            } else if (publication instanceof Audio) {
                Audio audio = (Audio) publication;
                System.out.println(BOLD + "Duración del audio: " + RESET + audio.getDuration());
                System.out.println(BOLD + "Velocidad de bits: " + RESET + audio.getVelocityBits());
            }
            System.out.println(BOLD + "=====================================================================" + RESET);
        }
    }
    public void showListSortByName() {
        System.out.println("\033[0;1m" + "============================" + " PUBLICACION " + "\033[0;1m" + "============================");
        listPublication.sort(Comparator.comparing(Publicacion::getName));
        for (Publicacion publication : listPublication) {
            System.out.println(BOLD + "ID: " + RESET + publication.getId());
            System.out.println(BOLD + "Nombre : " + RESET + publication.getName());
            System.out.println(BOLD + "Fecha de subida: " + RESET + publication.getDateUploaded());
            System.out.println(BOLD + "Etiquetas - Hashtags: " + RESET + publication.getHashtags());
            System.out.println(BOLD + "Cantidad de me gustas: " + RESET + RED + "❤" + publication.getAmountLikes() + RESET);
            System.out.println("\n" + BOLD + UNDERLINED + "Comentarios:" + RESET);
            int i = 1;
            for (String comment : publication.getListComments()) {
                System.out.print("\n" + BOLD + "Comentario " + RESET + i + ": " + comment);
                i++;
            }
            System.out.println("\n");
            if (publication instanceof Video) {
                Video video = (Video) publication;
                System.out.println(BOLD + "Filtro: " + RESET + video.getFiltro() + "\n");
                System.out.println(BOLD + "Duración del video: " + RESET + video.getDuration());
                System.out.println(BOLD + "Resolución del video: " + RESET + video.getResolution());
                System.out.println(BOLD + "Cantidad de cuadros: " + RESET + video.getTotalFrames());
            } else if (publication instanceof Imagen) {
                Imagen imagen = (Imagen) publication;
                System.out.println(BOLD + "Filtro: " + RESET + imagen.getFiltro() + "\n");
                System.out.println(BOLD + "Resolución de la imagen: " + RESET + imagen.getResolution());
                System.out.println(BOLD + "Ancho de la imagen: " + RESET + imagen.getWidth());
                System.out.println(BOLD + "Alto de la imagen: " + RESET + imagen.getHeight());
            } else if (publication instanceof Audio) {
                Audio audio = (Audio) publication;
                System.out.println(BOLD + "Duración del audio: " + RESET + audio.getDuration());
                System.out.println(BOLD + "Velocidad de bits: " + RESET + audio.getVelocityBits());
            }
            System.out.println("\033[0;1m" + "=====================================================================");
        }
        listPublication.sort(Comparator.comparing(Publicacion::getId));
    }

    public void showPublication(Publicacion publication) {
        System.out.println(BOLD + "ID: " + RESET + publication.getId());
        System.out.println(BOLD + "Nombre : " + RESET + publication.getName());
        System.out.println(BOLD + "Fecha de subida: " + RESET + publication.getDateUploaded());
        System.out.println(BOLD + "Etiquetas - Hashtags: " + RESET + publication.getHashtags());
        System.out.println(BOLD + "Cantidad de me gustas: " + RESET + RED + "❤" + publication.getAmountLikes() + RESET);
        System.out.println("\n" + BOLD + UNDERLINED + "Comentarios:" + RESET);
        int i = 1;
        for (String comment : publication.getListComments()) {
            System.out.print("\n" + BOLD + "Comentario " + RESET + i + ": " + comment);
            i++;
        }
        System.out.println("\n");
        if (publication instanceof Video) {
            Video video = (Video) publication;
            System.out.println(BOLD + "Filtro: " + RESET + video.getFiltro() + "\n");
            System.out.println(BOLD + "Duración del video: " + RESET + video.getDuration());
            System.out.println(BOLD + "Resolución del video: " + RESET + video.getResolution());
            System.out.println(BOLD + "Cantidad de cuadros: " + RESET + video.getTotalFrames());
        } else if (publication instanceof Imagen) {
            Imagen imagen = (Imagen) publication;
            System.out.println(BOLD + "Filtro: " + RESET + imagen.getFiltro() + "\n");
            System.out.println(BOLD + "Resolución de la imagen: " + RESET + imagen.getResolution());
            System.out.println(BOLD + "Ancho de la imagen: " + RESET + imagen.getWidth());
            System.out.println(BOLD + "Alto de la imagen: " + RESET + imagen.getHeight());
        } else if (publication instanceof Audio) {
            Audio audio = (Audio) publication;
            System.out.println(BOLD + "Duración del audio: " + RESET + audio.getDuration());
            System.out.println(BOLD + "Velocidad de bits: " + RESET + audio.getVelocityBits());
        }
        System.out.println(BOLD + "=====================================================================" + RESET);
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

    public Map<String, Integer> getPeopleWithAmountLikes() {
        Map<String, Integer> mapa = new HashMap<String, Integer>();
        for (Publicacion publication : listPublication) {
            String name = publication.getName();
            int likes = publication.getAmountLikes();
            if (mapa.containsKey(name))
                mapa.put(name, mapa.get(name) + likes);
            else
                mapa.put(name, likes);
        }
        return mapa;
    }

    public Map<String, Integer> getPeopleWithAmountPublications() {
        Map<String, Integer> mapa = new HashMap<String, Integer>();
        for (Publicacion publication : listPublication) {
            String name = publication.getName();
            if (mapa.containsKey(name))
                mapa.put(name, mapa.get(name) + 1);
            else
                mapa.put(name, 1);
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
