package PresentationLayer;

import BusinessLayer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class GuiOspatar {

    private JFrame j1;
    private JFrame j2;


    private Restaurant restaurant;
    private Bucatar bucatar;
    TextField anunt=new TextField();

    /***
     * Constructor
     * @param restaurant
     */
    public  GuiOspatar(Restaurant restaurant) {
        this.restaurant=restaurant;
        bucatar=new Bucatar();
        j1=new JFrame();
        j2=new JFrame();
        j1.setVisible(true);
    }

    /***
     * Creez fereastra pentru Ospatar unde o sa am butoane specifice fiecarei actiuni
     */
    public void Ospatar() {
        j1.setBounds(10,10,600,250);
        j1.setTitle("Ospatar");
        JPanel p=new JPanel();
        p.setVisible(true);
        p.setLayout(null);
        JButton x1=new JButton("AdaugaComanda");
        x1.setBounds(10,10,200,20);
        JButton x2=new JButton("VeziComenzile");
        x2.setBounds(10,160,200,20);
        JLabel l1=new JLabel("IdOspatar");
        l1.setBounds(10,50,80,30);
        JLabel l2=new JLabel("NumarMasa");
        l2.setBounds(170,50,100,30);
        JLabel l3=new JLabel("Data");
        l3.setBounds(340,50,50,30);
        JLabel l4=new JLabel("Produse");
        l4.setBounds(100,100,60,30);

        final TextField scriere1=new TextField();
        scriere1.setBounds(90,50,50,30);
        final TextField scriere2=new TextField();
        scriere2.setBounds(270,50,50,30);
        final TextField scriere3=new TextField();
        scriere3.setBounds(390,50,100,30);
        final TextField scriere4=new TextField();
        scriere4.setBounds(160,100,150,30);
        p.add(x1);
        p.add(x2);
        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(scriere1);
        p.add(scriere2);
        p.add(scriere3);
        p.add(scriere4);
        x1.addActionListener(new ActionListener() {
            /***
             * Daca este apasat butonul care se ocupa cu adaugarea comenzii intervine metoda de mai jos in care chitanta se genereaza intr-un fiser.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int Id=Integer.parseInt(scriere1.getText());

                int numarMasa=Integer.parseInt(scriere2.getText());
                String Data=scriere3.getText();
                ArrayList<Meniul> meniu=new ArrayList<Meniul>();
                String produse=scriere4.getText();
                String ceeaCeAmIntrodus[]=produse.split(" ");
                for(Meniul i:restaurant.getMeniu()) {
                    int j=0;
                    while(j< ceeaCeAmIntrodus.length) {
                        if(i.getName().equals( ceeaCeAmIntrodus[j])) {
                           meniu.add(i);
                        }
                        j++;
                    }}
                Comanda o=new Comanda(meniu,Data,Id,numarMasa);
                try(FileWriter fw = new FileWriter("chitanta.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println(o.Chitanta()+"\n");

                } catch (IOException exceptie) {

                }
                HashMap<Integer, Comanda> ord;
                ord=restaurant.getComanda();
                ord.put(new Integer(o.getIdComanda()), o);
                o.addObserver(bucatar);
                o.setAnunt("Inca o comanda a fost efectuata\n");
                anuntbucatar();
                restaurant.setComanda(ord);

            }



        });
/***
 * Daca este apasat butonul care se ocupa de afisarea informatiilor despre o comnda (intr-un tabel ) intervine metoda de mai jos.
 */
        x2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String []col=new String[]{"IdComanda","NumarMasa","Data","ProduseCumparate","Pret"};
                int indexColoana=0;
                float pret;
                String [][]data=new String[restaurant.getComanda().size()][5];
                int index=0;
                String k="";
                for(Comanda i:restaurant.getComanda().values()) {
                    pret=0;

                    data[index][indexColoana]= String.valueOf(i.getIdComanda());
                    data[index][indexColoana+1]= String.valueOf(i.getNumarmasa());
                    data[index][indexColoana+2]=i.getData();
                    StringBuilder stringBuilder = new StringBuilder(k);
                    for(Meniul j:i.getMeniul()) {
                        stringBuilder.append(j.getName()).append(" ");
                        pret+=j.getPret();
                    }
                    k = stringBuilder.toString();
                    data[index][indexColoana+3]=k;
                    data[index][indexColoana+4]= String.valueOf(pret);
                    index++;}
                     JFrame f=new JFrame();
                    JTable jt=new JTable(data,col);
                    jt.setBounds(30,40,200,300);
                      JScrollPane afisareTabel=new JScrollPane(jt);
                    f.add(afisareTabel);
                    f.setSize(300,400);
                    f.setVisible(true);




            }
        });
        j1.setLayout(null);
        j1.setContentPane(p);
        j1.setVisible(true);
    }

    /***
     * Creerea ferestrei pentru anuntul catre bucatar.
     */
    public void Bucatar() {
        j2.setTitle("Anunt catre bucatar");
        j2.setLayout(null);
        j2.setBounds(20, 20, 250, 100);


        anunt.setBounds(10,10,200,30);
        String s;
        j2.add(anunt);
        j2.setVisible(true);


    }

    /***
     * Aici este facut anuntul catre bucatar,atunci cand avem o noua comnda.
     */
    public  void anuntbucatar() {
       anunt.setText(anunt.getText()+"\n"+bucatar.getComandaNoua()+"\n");
    }

}
