package com.estadisticasInstagram.serializacion;

import com.estadisticasInstagram.dominio.Album;

import java.io.*;
import java.util.LinkedList;

public class SerAlbum {

    public static void serializeAlbum(LinkedList<Album> album) {
        try {
            File f = new File("album.ser");
            if (!f.exists())
                f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(album);
            oos.close();
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("Error de IO en la serializacion");
        }
    }

    @SuppressWarnings("unchecked")
    public static LinkedList<Album> deserializeAlbum() {
        LinkedList<Album> albumesPerfil = new LinkedList<>();
        try {
            File f = new File("album.ser");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object stream = ois.readObject();
            albumesPerfil = (LinkedList<Album>)stream;
            ois.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("Error de IO en la deserializacion");
        }
        catch(ClassNotFoundException e){
            System.out.println("No se encuentra la clase");
        }
        return albumesPerfil;
    }
}
