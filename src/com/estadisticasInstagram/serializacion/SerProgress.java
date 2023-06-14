package com.estadisticasInstagram.serializacion;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SerProgress {
    public static void serializarProgress(int proceso,String id) {
        try {
            File f = new File("progress.ser");
            Map <Integer,String> myMap = new HashMap<>();
            myMap.put(proceso,id);
            if (!f.exists())
                f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myMap);
            oos.close();
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Error de IO en la serializacion");
        }
    }

    @SuppressWarnings("unchecked")
    public static int deserializarProgress() {
        int proceso = 0;
        try {
            File f = new File("progress.ser");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object stream = ois.readObject();
            proceso = (int)stream;
            System.out.println("PROCESO" + proceso);
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
        return proceso;

    }
}
