package BusinessLayer;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class Restaurant implements Serializable {
    private HashMap<Integer, Comanda> comanda=new HashMap<>();;
    private ArrayList<Meniul> meniu=new ArrayList<>();
    public Restaurant() {}

    /***
     *
     * @return comanda efectuata
     */
    public HashMap<Integer, Comanda> getComanda() { return comanda; }

    /***
     * seteaza o comanda
     * @param comanda comanda care urmeaza sa fie efectuata
     */
    public void setComanda(HashMap<Integer, Comanda> comanda) { this.comanda = comanda; }

    /***
     *
     * @return obtinerea meniului
     */
    public ArrayList<Meniul> getMeniu() { return meniu; }

    /***
     * setarea unui anumit meniu
     * @param meniu " produsul" care intra in meniu
     */
    public void setMeniu(ArrayList<Meniul> meniu) { this.meniu = meniu; }


}