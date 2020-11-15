package BusinessLayer;


import java.util.ArrayList;

public class ProdusComplex implements Meniul {

    private String nume;
    private int pret;
    private ArrayList<ProdusSimplu>produs;

    /***
     * Constructor
     * @param nume denumirea produsului
     * @param pret pretul produsului
     */
    public ProdusComplex(String nume,int pret) {
        this.nume=nume;
        this.pret=pret;
        produs=new ArrayList<ProdusSimplu>();


    }

    /***
     * Adauga produse simple, pentru a putea alcatuii produse complexe
     * @param x un produs simplu
     */
    public void addBaseProdus(ProdusSimplu x) { produs.add(x); }

    /***
     *
     * @return numele produsului
     */
    public String getName() { return nume; }

    /***
     *
     * @return pretul produsului
     */
    @Override
    public int getPret() { return pret; }

    /***
     *
     * @return un vector de stringuri folosit la tabel
     */
    @Override
    public String[] getColoane() {
        String[]coloane=new String[] {"Nume","Pret","Facut din:"};
        return coloane;
    }

    /***
     *
     * @return informatii despre produsul complex
     */
    @Override
    public String[] getData() {
        int indexData=0;
        String[]data=new String[3];
        data[indexData]=nume;
        data[indexData+1]= String.valueOf(pret);
        StringBuilder string= new StringBuilder();
        for(Meniul i:produs) {
            string.append(i.getName()).append(" ");
        }
        data[indexData+2]= string.toString();
        return data;
    }

}
