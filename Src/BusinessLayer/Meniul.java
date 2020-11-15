package BusinessLayer;

import java.io.Serializable;


public interface Meniul extends Serializable {
    /***
     *
     * @return numele unui anumit produs
     */
    String getName();

    /***
     *
     * @return un vector de stringuri
     */
    String[] getColoane();

    /***
     *
     * @return informatii despre produs
     */
    String[] getData();

    /***
     *
     * @return pretul produsului
     */
    int getPret();

}
