package BusinessLayer;

import java.util.Observable;
import java.util.Observer;

public class Bucatar implements Observer{
    private String comandaNoua;

    /***
     *anuntarea bucatarului ca a fost creata o noua comnda
     */
    @Override
    public void update(Observable o, Object gatire) { this.comandaNoua= (String) gatire; }

    /***
     * obtinerea informatiei pt comanda noua
     * @return comanda noua care a fost efectuata
     */
    public String getComandaNoua() { return comandaNoua; }

}
