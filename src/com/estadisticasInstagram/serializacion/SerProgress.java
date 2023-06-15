package com.estadisticasInstagram.serializacion;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/** clase utilizada para serializar el progreso de la reproducción*/
public class SerProgress {
    public static void serializeProgress(int proceso, String id) {
        try {
            File f = new File("progress.ser");
            Map <String, Object> myMap = new HashMap<>();
            myMap.put("proceso", proceso);
            myMap.put("id", id);
            if (!f.exists())
                f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myMap);
            oos.close();
        }
        catch(IOException e) {
            e.printStackTrace();
            System.out.println("Error de IO en la serializacion");
        }
    }

    @SuppressWarnings("unchecked")
    public static Map <String, Object> deserializeProgress() {
        Map <String, Object> myMap = new HashMap<>();
        try {
            File f = new File("progress.ser");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object stream = ois.readObject();
            myMap = (Map <String, Object>)stream;
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
        return myMap;
    }
}
