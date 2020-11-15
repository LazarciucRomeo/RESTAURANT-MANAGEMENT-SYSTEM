package PresentationLayer;
import BusinessLayer.Restaurant;
import DataLayer.RestaurantSerializator;

import java.io.IOException;


public class Start {


    public static void main(String[] args) throws IOException {

        Restaurant restaurant=new Restaurant();
        RestaurantSerializator.Deserializator(restaurant);

         GuiAdministrator administrator= new GuiAdministrator(restaurant);
            administrator.Administrator();
            GuiOspatar ospatar= new GuiOspatar(restaurant);
            ospatar.Ospatar();
            ospatar.Bucatar();

    }

}
