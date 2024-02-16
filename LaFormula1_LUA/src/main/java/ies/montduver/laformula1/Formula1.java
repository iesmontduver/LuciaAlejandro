/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ies.montduver.laformula1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jasb
 */
public class Formula1 {

    public static final String RESET = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String MORADO = "\033[35m";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

        int opcio;
        Circuit granPremi = null;
        List<Cotxe> cotxes = new ArrayList<Cotxe>();
        List<Box> pitLane = new ArrayList<Box>();

        /* Aquest mètode s'encarrega de crear les escuderies (5), els pilots (10), els cotxes (10) i els boxes de la linea pitLane (5).
           S'assigna un cotxe a un pilot i un box a cada escuderia. */
        crearEscuderies(cotxes, pitLane);

        do {

            opcio = -1;

            System.out.println("\n" + MORADO + "\t\t\t**** F O R M U L A  1  ****\n" + RESET);
            System.out.println("\t\t\t1. Establir un nou Gran Premi");
            System.out.println("\t\t\t2. Començar la carrera");
            System.out.println("\t\t\t3. Consultar qualificació");
            System.out.println("\t\t\t4. Eixir");
            opcio = Leer.leerEntero("\t\tIntrodueix una opcio (1-4): \n");

            switch (opcio) {
                case 1:
                    granPremi = crearGranPremi();
                    break;
                case 2:
                    //Començar la carrera
                    if (granPremi != null) {
                        Cotxe guanyador = GranPremi.correr(granPremi, cotxes, pitLane);
                        
                        int hores = (int) (guanyador.getTempsCarrera() / 3600);
                        int minuts = (int) ((guanyador.getTempsCarrera() - (hores * 3600)) / 60);
                        double segons = ((int) (guanyador.getTempsCarrera() - (hores * 3600) - (minuts * 60)) * 100.0) / 100.0;

                        System.out.println(BLUE + "\n\t\t\t I EL GUANYADOR DEL GRAN PREMI ÉS..." + RESET);
                        Thread.sleep(2000);
                        System.out.println("\n\t\t" + RED + guanyador.getPilot().getNom() + " !!!" + RESET + " amb un temps total de " + RED + hores + "h:" + minuts + "':" + segons + "\"\n\n\n" + RESET);

                        granPremi = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "Encara no s'ha creat el circuit (opció 1).");
                    }
                    break;
                case 3:
                    mostrarQualify(cotxes);
                    Thread.sleep(3000);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opció incorrecta.");
            }

        } while (opcio != 4);

    }

    private static Circuit crearGranPremi() {
        Circuit circuit = new Circuit(Leer.leerTextoConDialogo("Quin és el nom del circuit?"), Leer.leerTextoConDialogo("En quin pais es troba?"), Leer.leerTextoConDialogo("Quina ciutat?"), Integer.parseInt(Leer.leerTextoConDialogo("Indica la distància del circuit en metres")), Integer.parseInt(Leer.leerTextoConDialogo("Quantes voltes han de donar per a completar-lo?")));

        return circuit;
    }

    private static void mostrarQualify(List<Cotxe> cotxes) {
        
        List<Cotxe> listaAux = new ArrayList<Cotxe>(cotxes); //copia de la original 
        List<Cotxe> qualificacio = new ArrayList<Cotxe>();

        while (!listaAux.isEmpty()) {
            Iterator<Cotxe> co = listaAux.iterator();
            Cotxe lider = co.next();

            while (co.hasNext()) {
                Cotxe el_cotxe = co.next();
                
                if (el_cotxe.getPilot().getPunts() > lider.getPilot().getPunts()) {
                    lider = el_cotxe;
                }
            }
            qualificacio.add(lider);
            listaAux.remove(listaAux.indexOf(lider));            
        }
        System.out.println(MORADO + "\n\t\t     QUALIFICACIÓ DEL MUNDIAL DE PILOTS " + RESET);  
        System.out.println("\n\t\t\t*****************************");
        for (int i = 0; i < qualificacio.size(); i++) {
            
            System.out.println("\t\t\t|" + BLUE + qualificacio.get(i).getPilot().getNom() + RESET + "\t | " + GREEN + qualificacio.get(i).getPilot().getPunts() + RESET + " punts |");
            if (i != (qualificacio.size() -1))
                System.out.println("\t\t\t|---------------------------|");
        }
        System.out.println("\t\t\t*****************************");

    }

    private static void crearEscuderies(List<Cotxe> cotxes, List<Box> pitLane) {
        
        /* TO DO: 
           S’ha de completar el mètode per a crear instancies de: 
            • les escuderies (5),
            • els boxes  (5) per a cada escuderia,
            • els pilots (10), dos pilots per cada escuderia
            • els cotxes (10), un conduit per cadascun dels pilots.
        
            He deixat implementat un exemple de cada i com s'afegeix a les llistes de cotxes i el pitLane
            
        */
        
        //Crear 5 escuderies
        Escuderia McLaren = new Escuderia("McLaren", 125000000.0,0);
        Escuderia Ferrari = new Escuderia("Ferrari", 245000000.0,0);
        Escuderia AstonMartin = new Escuderia("Aston Martin", 125000000.0,0);
        Escuderia RedBull = new Escuderia("RedBull", 130000000.0,0);
        Escuderia Mercedes = new Escuderia("Mercedes", 129000000.0,0);
        
        //Crear els boxes de cada escuderia i afegir-los al pitLane
        Box box_ML = new Box(McLaren, 12);
        pitLane.add(box_ML);
        Box box_FE = new Box(Ferrari, 20);
        pitLane.add(box_FE);
        Box box_AM = new Box(AstonMartin, 16);
        pitLane.add(box_AM);
        Box box_RB = new Box(RedBull, 11);
        pitLane.add(box_RB);
        Box box_ME = new Box(Mercedes, 19);
        pitLane.add(box_ME);
        

        //Crear 10 pilots; 2 per escuderia
        Pilot Norris = new Pilot("L. Norris", McLaren, 1,0,1000000.0);
        Pilot Alonso = new Pilot("F. Alonso", AstonMartin, 3, 2, 1000000.0);
        Pilot Stroll = new Pilot("L.Stroll", AstonMartin, 4, 1, 100000.0);
        Pilot Hamilton = new Pilot("L. Hamilton", Mercedes, 5, 3, 10000000.0 );
        Pilot Leclerc = new Pilot("C. Leclerc", Ferrari, 7, 3, 1000000.0);
        Pilot Pérez = new Pilot("S. Pérez", RedBull, 9,5, 1000000.0);
        Pilot Russell = new Pilot("G.Russell", Mercedes, 6, 1, 1000000.0);
        Pilot Sainz = new Pilot("C. Sainz", Ferrari, 8, 4, 1000000.0);
        Pilot Verstappen = new Pilot("M. Verstappen", RedBull, 10, 10, 100000000.0);
        Pilot Piastri = new Pilot("O.Piastri", McLaren, 2, 0, 100000.0);

        //Montar els 10 cotxes i afegir-los a la llista de cotxes
        Cotxe c1 = new Cotxe(McLaren, new Rodes("Michelín"), new Motor(), new Xasis("McLaren", "F1", "Taronja",1300,2,1.5 ));
        cotxes.add(c1);
        Cotxe c2 = new Cotxe(McLaren, new Rodes("Michelín"), new Motor(), new Xasis("McLaren", "F1", "Taronja",1250,2,1.6));
        cotxes.add(c2);
        Cotxe c3 = new Cotxe(AstonMartin, new Rodes("Michelín"), new Motor(), new Xasis("AstonMartin", "F1", "Verd",1150,2,1.4));
        cotxes.add(c3);
        Cotxe c4 = new Cotxe(AstonMartin, new Rodes("Michelín"), new Motor(), new Xasis("AstonMartin", "F1", "Verd",1220,1.9,1.5));
        cotxes.add(c4);
        Cotxe c5 = new Cotxe(Mercedes, new Rodes("Michelín"), new Motor(), new Xasis("Mercedes", "F1", "Negre", 850, 1.8, 1.3));
        cotxes.add(c5);
        Cotxe c6 = new Cotxe(Mercedes, new Rodes("Michelín"), new Motor(), new Xasis( "Mercedes", "F1", "Roig", 975, 2, 1.5));
        cotxes.add(c6);
        Cotxe c7 = new Cotxe(Ferrari, new Rodes("Michelín"), new Motor(), new Xasis("Ferrari", "F1", "Roig", 1120, 2, 1.7 ));
        cotxes.add(c7);
        Cotxe c8 = new Cotxe(Ferrari, new Rodes("Michelín"), new Motor(), new Xasis("Ferrari", "F1", "Roig", 1150,1.9, 1.5 ));
        cotxes.add(c8);
        Cotxe c9 = new Cotxe(RedBull, new Rodes("Michelín"), new Motor(), new Xasis("RedBull", "F1", "Blau", 1500, 2.2, 1.9));
        cotxes.add(c9);
        Cotxe c10 = new Cotxe(RedBull, new Rodes("Michelín"), new Motor(), new Xasis("RedBull", "F1", "Blau", 1300, 1.9, 1.8));
        cotxes.add(c10);
       
        //Assignar un cotxe a cada pilot 
        c4.setPilot(Norris);
        
    }    
}
