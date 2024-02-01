/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.montduver.laformula1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jasb
 */
public class GranPremi {

    public static final String RESET = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String MORADO = "\033[35m";

    public static Cotxe correr(Circuit circuit, List<Cotxe> cotxes, List<Box> pitLane) throws InterruptedException {

        Cotxe guanyador = null;
        List<Cotxe> cotxesEnCarrera = new ArrayList<Cotxe>(cotxes); //copia de la original 
        
        System.out.println(MORADO + "\n\t\t\tBENVNGUTS AL GRAN PREMI DE " + circuit.getNombre().toUpperCase()+ RESET);
        Thread.sleep(1000);
        System.out.println(BLUE + "\n\t\t\tEngegant els motors..." + RESET);
        Thread.sleep(2000);
        Iterator<Cotxe> c0 = cotxesEnCarrera.iterator();
        while (c0.hasNext()) {
            Cotxe el_cotxe = c0.next();
            el_cotxe.arrancarMotors();
            Thread.sleep(400);
        }

        System.out.println(RED + "\t\t\t O O O O O");
        Thread.sleep(1000);
        System.out.println(RED + "\t\t\t O O O O" + GREEN + " O");
        Thread.sleep(1000);
        System.out.println(RED + "\t\t\t O O O" + GREEN + " O O");
        Thread.sleep(1000);
        System.out.println(RED + "\t\t\t O O" + GREEN + " O O O");
        Thread.sleep(1000);
        System.out.println(RED + "\t\t\t O" + GREEN + " O O O O");
        Thread.sleep(1000);
        System.out.println(GREEN + "\t\t\t O O O O O" + BLUE);
        Thread.sleep(10);
        System.out.println(GREEN + "\n\t\t\t   START" + RESET);
        Thread.sleep(100);

        Iterator<Cotxe> c1 = cotxesEnCarrera.iterator();
        while (c1.hasNext()) {
            Cotxe el_cotxe = c1.next();
            el_cotxe.ferEixida();
            Thread.sleep(500);
        }
        System.out.println("\n");

        int a1 = (int) ((Math.random() * circuit.getNumVoltes())) + 1; //numero aleatori entre 1 i el numero de voltes del circuit
        int a2 = (int) ((Math.random() * circuit.getNumVoltes())) + 1; //numero aleatori entre 1 i el numero de voltes del circuit       

        Cotxe Cotxe_a1 = null;
        Cotxe Cotxe_a2 = null;

        int volta = 1;
        do {
            System.out.println("\n Volta " + volta);
            Thread.sleep(300);

            //Generar un accident en 2 voltes aleatòries
            if ((volta == a1) || (volta == a2)) {
                if (volta == a1) {
                    System.out.println(RED + "Accident !!!" + RESET);
                    Cotxe_a1 = cotxeAleatori(cotxesEnCarrera);
                    Cotxe_a1.accident();
                    cotxesEnCarrera.remove(Cotxe_a1);
                    Thread.sleep(1200);
                } else {
                    System.out.println(RED + "Accident !!!" + RESET);
                    Cotxe_a2 = cotxeAleatori(cotxesEnCarrera);
                    Cotxe_a2.accident();
                    cotxesEnCarrera.remove(Cotxe_a2);
                    Thread.sleep(1200);
                }
            }

            //Pit stop en el primer i útlim terça de la carrera
            int primerPS = circuit.getNumVoltes() / 3;
            int segonPS = circuit.getNumVoltes() - (circuit.getNumVoltes() / 3);

            if (volta == primerPS || volta == segonPS) {
                if (volta == primerPS) {
                    System.out.println(GREEN + "\nPrimer Pit Stop: " + RESET);
                } else {
                    System.out.println(GREEN + "\nSegon Pit Stop: " + RESET);
                }

                Iterator<Cotxe> c3 = cotxesEnCarrera.iterator();
                while (c3.hasNext()) {
                    Cotxe el_cotxe = c3.next();
                    double temps_pit_stop = pitLane.get(0).realitzarPitStop(el_cotxe);
                    el_cotxe.setTempsCarrera(el_cotxe.getTempsCarrera() + temps_pit_stop);
                    Thread.sleep(500);
                }
                Thread.sleep(500);
            }

            Iterator<Cotxe> c4 = cotxesEnCarrera.iterator();
            while (c4.hasNext()) {
                Cotxe el_cotxe = c4.next();
                el_cotxe.setTempsCarrera(el_cotxe.getTempsCarrera() + (Math.random() * 10 + 120));
            }

            volta++;

        } while (volta <= circuit.getNumVoltes());

        /* Acabada la carrera es donen punts als 5 primers pilots segons el seu temps (10, 8, 6, 4 i 2 punts) */
        guanyador = puntuarCotxes(cotxesEnCarrera, cotxes);
        
        return guanyador; 
    }

    private static Cotxe cotxeAleatori(List<Cotxe> cotxesEnCarrera) {
        int numero = (int) ((Math.random() * cotxesEnCarrera.size()));
        return cotxesEnCarrera.get(numero);
    }

    /** Aquest mètode tornara actualitzarà la puntación de la llista de cotxes, sumant 
     * els punts als 5 primers qualificats de la carrera */
    private static Cotxe puntuarCotxes(List<Cotxe> cotxesEnCarrera, List<Cotxe> cotxes) {
        List<Cotxe> listaAux = new ArrayList<Cotxe>(cotxesEnCarrera); //copia de la original 
        List<Cotxe> listaOrdenada = new ArrayList<Cotxe>();

        while (!listaAux.isEmpty()) {
            Iterator<Cotxe> co = listaAux.iterator();
            Cotxe mesRapid = co.next();

            while (co.hasNext()) {
                Cotxe el_cotxe = co.next();
                
                if (el_cotxe.getTempsCarrera() < mesRapid.getTempsCarrera()) {
                    mesRapid = el_cotxe;
                }
            }
            listaOrdenada.add(mesRapid);
            listaAux.remove(listaAux.indexOf(mesRapid));            
        }       
            
        for (int i=0; i<cotxes.size();i++)
        {
            if (listaOrdenada.get(0).getPilot().getNom().equals(cotxes.get(i).getPilot().getNom()))
                cotxes.get(i).getPilot().setPunts(cotxes.get(i).getPilot().getPunts() + 10);
            
            if (listaOrdenada.get(1).getPilot().getNom().equals(cotxes.get(i).getPilot().getNom()))
                cotxes.get(i).getPilot().setPunts(cotxes.get(i).getPilot().getPunts() + 8);
            
            if (listaOrdenada.get(2).getPilot().getNom().equals(cotxes.get(i).getPilot().getNom()))
                cotxes.get(i).getPilot().setPunts(cotxes.get(i).getPilot().getPunts() + 6);
            
            if (listaOrdenada.get(3).getPilot().getNom().equals(cotxes.get(i).getPilot().getNom()))
                cotxes.get(i).getPilot().setPunts(cotxes.get(i).getPilot().getPunts() + 4);
            
            if (listaOrdenada.get(4).getPilot().getNom().equals(cotxes.get(i).getPilot().getNom()))
                cotxes.get(i).getPilot().setPunts(cotxes.get(i).getPilot().getPunts() + 2);
                       
        }

        return listaOrdenada.get(0);
    }
}
