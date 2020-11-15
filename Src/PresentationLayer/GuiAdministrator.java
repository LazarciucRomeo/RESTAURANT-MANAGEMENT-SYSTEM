package PresentationLayer;

import BusinessLayer.*;
import DataLayer.RestaurantSerializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GuiAdministrator {



        private JFrame j1;
        private Restaurant restaurant;
        private Administrator administrator;

    /***
     * Constructor
     * @param restaurant
     */

        public  GuiAdministrator(Restaurant restaurant) {
           administrator=new Administrator();
            this.restaurant=restaurant;
            j1=new JFrame();

            j1.setVisible(true);
        }

    /***
     * Creerea ferestre pentru administrator cu toate butoanele si locurile pentru a scrie text necesare
     */
    public void Administrator() {
            String nume;
             float pret;
            ArrayList<String> s=new ArrayList<String>();
            ArrayList<Meniul> meniu=new ArrayList<Meniul>();
            meniu=restaurant.getMeniu();
            for(Meniul i:meniu) {

                nume=i.getName();
                pret=i.getPret();

                s.add(nume+","+pret+"lei");

            }
            final JComboBox comboBox=new JComboBox(s.toArray());

            j1.setBounds(10,10,700,335);
            j1.setTitle("Administrator");
            FlowLayout ecran = new FlowLayout();

           JPanel p=new JPanel();
            JPanel p2=new JPanel();
            JPanel p3=new JPanel();
            p.setVisible(true);

            p2.setVisible(true);

            p2.setLayout (new BoxLayout ( p2, BoxLayout.PAGE_AXIS));
            JButton x1=new JButton("AdaugareMeniu");

            JButton x2=new JButton("SchimbaMeniu");

            JButton x3=new JButton("StergeMeniu");

            JButton x4=new JButton("VeziMeniu");

            JLabel l1=new JLabel("Nume");

            JLabel l2=new JLabel("Pret");

            JLabel l3=new JLabel("Facut din:");


            final TextField text1=new TextField();
            final TextField text2=new TextField();
            final TextField text3=new TextField();
            p2.add(l1);
            p2.add(text1);
            p2.add(l2);
            p2.add(text2);
            p2.add(l3);
            p2.add(text3);

            p3.setLayout(new BoxLayout ( p3, BoxLayout.PAGE_AXIS));
            JLabel lbl1=new JLabel("NumeNou");
            JLabel lbl2=new JLabel("PretNou");
            JLabel lbl3=new JLabel("Facut din:");

            final TextField txt1=new TextField();
            final TextField txt2=new TextField();
            final TextField txt3=new TextField();
            p3.add(lbl1);
            p3.add(txt1);
            p3.add(lbl2);
            p3.add(txt2);
            p3.add(lbl3);
            p3.add(txt3);


            p.setLayout(ecran);
            p.add(x1);
            p.add(x2);
            p.add(x3);
            p.add(x4);


            JLabel l5=new JLabel("NumeProdus");
            l5.setBounds(350,85,100,30);
            TextField t5=new TextField();
            t5.setBounds(340,120,100,27);

            x1.addActionListener( new ActionListener() {

                /***
                 *Urmatoarea metoda se ocupa de datele introduse atunci cand se apasa butonul raspunzator adaugarii unui produs in meniu.
                 */

                public void actionPerformed(ActionEvent e) {
                    administrator.setMeniu(restaurant.getMeniu());
                    String nume=text1.getText();
                    String pret=text2.getText();
                    String alimente=text3.getText();
                    String[] dateintroduse = alimente.split(" ");

                    comboBox.addItem(nume+" "+pret+" lei");
                    if(dateintroduse.length>1) {
                        ProdusComplex ProdusC=new ProdusComplex(nume,Integer.parseInt(pret));
                        for(String i:dateintroduse) {
                            ProdusSimplu simplu=new ProdusSimplu(0,i);
                            ProdusC.addBaseProdus(simplu);}
                        administrator.AdaugareProdus(ProdusC);

                    }
                    else {
                        ProdusSimplu ProdusS=new ProdusSimplu(0,dateintroduse[0]);
                        administrator.AdaugareProdusS(ProdusS);
                    }

                    restaurant.setMeniu(administrator.getMeniu());
                    try {
                        RestaurantSerializator.Serializator(restaurant);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            x2.addActionListener( new ActionListener() {
                /***
                 *Urmatoarea metoda se ocupa de datele introduse atunci cand se apasa butonul raspunzator editarii unui produs in meniu.
                 */

                public void actionPerformed(ActionEvent e) {
                    administrator.setMeniu(restaurant.getMeniu());
                    String nume=txt1.getText();
                    String pret=txt2.getText();
                    String alimente=txt3.getText();
                    String[] textIntrodus = alimente.split(" ");
                    String []FaraSpatii= comboBox.getSelectedItem().toString().split(" ");
                    if(comboBox.getSelectedIndex()>-1) {

                        comboBox.removeItemAt(comboBox.getSelectedIndex());}
                    else {

                    }
                    comboBox.addItem(nume+" "+pret+" lei");
                    if(textIntrodus.length>1) {
                        ProdusComplex cc=new ProdusComplex(nume,Integer.parseInt(pret));
                        for(String i:textIntrodus) {
                            ProdusSimplu simplu2=new ProdusSimplu(0,i);
                            cc.addBaseProdus(simplu2);

                        }
                        administrator.delete(FaraSpatii[0]);
                        administrator.AdaugareProdus(cc);

                        restaurant.setMeniu(administrator.getMeniu());
                        try {
                            RestaurantSerializator.Serializator(restaurant);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            });

            x3.addActionListener( new ActionListener() {

                /***
                 *Metoda care actioneaza atunci cand dorim sa stergem un produs meniu
                 */

                public void actionPerformed(ActionEvent e) {



                    if(comboBox.getSelectedIndex()>-1) {


                        String value = comboBox.getSelectedItem().toString();
                        comboBox.removeItemAt(comboBox.getSelectedIndex());
                        String[] textIntr=value.split(" ");
                        administrator.delete(textIntr[0]);
                        restaurant.setMeniu(administrator.getMeniu());

                       try {
                            RestaurantSerializator.Serializator(restaurant);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }

                }});
            x4.addActionListener(new ActionListener() {

                /***
                 *aceasta metoda actioneaza atunci cand este apasat butonul pentu vizualizarea meniului
                 */
                public void actionPerformed(ActionEvent e) {

                    String [][]data=new String[ restaurant.getMeniu().size()][2];
                    String []col;
                    col=restaurant.getMeniu().get(0).getColoane();

                    int index=0;
                    for(Meniul i:restaurant.getMeniu()) {

                        data[index]=i.getData();
                        index++;

                    }
                    JFrame frame=new JFrame();
                    JTable tabel=new JTable(data,col);
                    tabel.setBounds(30,40,200,300);
                    JScrollPane scroll=new JScrollPane(tabel);
                    frame.add(scroll);
                    frame.setSize(300,400);
                    frame.setVisible(true);


                }


            });

            p.add(comboBox);
            j1.setLayout(null);
            p.setBounds(10, 10, 600, 80);
            p2.setBounds(10, 90, 90, 200);
            p3.setBounds(170,90,90,200);
            j1.add(p);
            j1.add(p2);
            j1.add(p3);
            j1.add(l5);
            j1.add(t5);
            j1.setVisible(true);
        }
}

