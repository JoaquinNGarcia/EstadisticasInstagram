package com.estadisticasInstagram.serializacion;

import com.estadisticasInstagram.dominio.Publicacion;

import java.io.*;
import java.util.LinkedList;

public class SerFiltros {
    public static void serializarFiltros( LinkedList<Publicacion> publi) {
        try {
            File f = new File("publicaciones.ser");
            if (!f.exists())
                f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(publi);
            oos.close();
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Error de IO en la serializacion");
        }
    }

    @SuppressWarnings("unchecked")
    public static LinkedList<Publicacion> deserializarFiltros() {
        LinkedList<Publicacion> publi = new LinkedList<>();
        try {
            File f = new File("publicaciones.ser");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object stream = ois.readObject();
            publi = (LinkedList<Publicacion>)stream;
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
        return publi;
    }
}
