package BusinessLayer;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class Comanda extends Observable implements Serializable{
    private String anunt;
    private int idComanda;
    private final ArrayList<Meniul> meniul;
    private int numarMasa;
    private String data;

    /***
     * Constructor
     * @param meniul meniul din care se poate alege
     * @param data   data la care a fost efectuata comnda
     * @param idComanda cheia primara a comenzii(id-ul ospatarului )
     * @param numarMasa masa la care trebuie facuta servirea
     */
    public Comanda(ArrayList<Meniul> meniul,String data, int idComanda,int numarMasa) {
        this.idComanda=idComanda;
        this.numarMasa=numarMasa;
        this.data=data;
        this.meniul=meniul;
    }




    public void setAnunt(String anunt) {
        this.anunt = anunt;
        setChanged();
        notifyObservers(anunt);
    }

    /***
     * Aici este creata chitanta
     * @return un string care ofer informatii despre comnda efectuata si costul comenzii(factura)
     */
    public String Chitanta() {
        StringBuilder k = new StringBuilder();
     float nota=0;
        for(Meniul i:meniul) {
            k.append(i.getName()+", ");
            nota+=i.getPret();
        }
        String chitanta="Comanda cu numarul "+idComanda+ " "+"a fost facuta la masa "+numarMasa+" "+"in data de "+data+". La masa a fost adus produsul "+k +", iar valoarea comenzii este de "+nota+" lei.";
        return chitanta;
    }

    /***
     *
     * @return id-ul comenzii
     */
    public int getIdComanda() {
        return idComanda;
    }

    /***
     *
     * @return numarul mesei la care se face servirea
     */
    public int getNumarmasa() {
        return numarMasa;
    }

    /***
     *
     * @return data la care a fost facuta servirea
     */
    public String getData() { return data; }

    /***
     *
     * @return meniul la momentul actual
     */
    public ArrayList<Meniul> getMeniul() {
        return meniul;
    }


}
