package DataLayer;
import BusinessLayer.Restaurant;

import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.io.Serializable;



public class RestaurantSerializator implements Serializable {
    /***
     *Transform starea obiectului intr un flux de octeti
     * @param x obiectul care urmeaza a fii serializat
     */

    public static void Serializator(Restaurant x) throws IOException {

            FileOutputStream file = new FileOutputStream("restaurnat.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(x);
            out.close();
            file.close();


    }

    /***
     *Procesul opus celui de mai sus. Pentru ca fluxul de octeti este independent de o platforma
     *  deserializarea se poate face pe alta platforma.
     */

    public static void Deserializator(Restaurant x) {

        try
        {
            FileInputStream file = new FileInputStream  ("restaurant.ser");
            ObjectInputStream in = new ObjectInputStream  (file);
            Restaurant p =(Restaurant)in.readObject();
            x.setMeniu(p.getMeniu());
            in.close();
            file.close();

        }

        catch(IOException | ClassNotFoundException exceptie)
        {
            System.out.println("IOException or ClassNotFoundException is caught");
        }


    }


}
