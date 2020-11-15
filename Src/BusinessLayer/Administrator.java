package BusinessLayer;

import java.util.ArrayList;

public class Administrator {

    private ArrayList<Meniul> meniu=new ArrayList<>();

    /***
     * este vorba despre aduagarea unui produsComplex(adica un produs facut din mai multe produse simple)
     * @param produsComplex produsul complex care urmeza sa fie adaugat
     */
    public void AdaugareProdus(ProdusComplex produsComplex) {
        meniu.add(produsComplex);
    }

    /***
     * se adauga un produs simplu
     * @param produsSimplu produsul care se doreste a fii adaugat
     */
    public void AdaugareProdusS(ProdusSimplu produsSimplu) {
        meniu.add(produsSimplu);
    }

    /***
     * pentru stabilirea meniului
     * @param meniul totalitatea de produse adaugate fie ele simple sau complexe
     */
    public void setMeniu(ArrayList<Meniul> meniul) { this.meniu =meniul ; }

    /***
     * obtinerea meniului
     * @return meniul restaurantului
     */
    public ArrayList<Meniul> getMeniu() { return meniu; }

    /***
     * stergerea unui anumit produs din meniu
     * @param produs produsul care urmeaza sa fie sters din meniu
     */
    public void delete(String produs) {
        int i=0;
      while(i< meniu.size()) {
            if(meniu.get(i).getName().equals(produs)) {
                meniu.remove(i);}
            i++;
        }

    }






}