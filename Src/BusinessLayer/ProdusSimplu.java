package BusinessLayer;


public   class ProdusSimplu implements Meniul{
    private String nume;
    private int pret;

    /***
     * Constructor
     * @param pret pretul produsului
     * @param nume numele produsului
     */
    public ProdusSimplu (int pret,String nume) {
        this.nume=nume;
       this.pret=pret;
    }

    /***
     *
     * @return numele produsului
     */
    public String getName() { return nume; }

    /***
     *
     * @return pretul produsului
     */
    public int getPret() { return pret; }


    /***
     * Este folosit la crearea tabelului
     * @return vector de stringuri
     */
    public String[] getColoane() {
        String[]coloane=new String[] {"Nume","Pret","Facut din:" };
        return coloane;
    }

    /***
     *
     * @return informatii despre produs
     */
    public String[] getData() {
        int indexData=0;
        String[]data=new String[3];
        data[indexData]=nume;
        data[indexData+1]=String.valueOf(pret);
        data[indexData+2]=nume;
        return data;
    }


}