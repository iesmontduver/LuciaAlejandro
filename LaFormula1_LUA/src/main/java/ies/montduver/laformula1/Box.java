/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.montduver.laformula1;

/**
 *
 * @author jasb
 */
public class Box {
    
    //TO DO: crear el constructor, getters i setters que s’observa al diagrama de classe.
    
    /* ATENCIO: Una vegada faces els canvis, descomenta el mètode següent per a que el projecte funcione */
    private Escuderia escuderia;
    private int numMecanics;

    public Box(Escuderia escuderia, int numMecanics) {
        this.escuderia = escuderia;
        this.numMecanics = numMecanics;
    }

    public Escuderia getEscuderia() {
        return escuderia;
    }

    public int getNumMecanics() {
        return numMecanics;
    }

    public void setEscuderia(Escuderia escuderia) {
        this.escuderia = escuderia;
    }

    public void setNumMecanics(int numMecanics) {
        this.numMecanics = numMecanics;
    }

    public double realitzarPitStop(Cotxe c){
        double ps = ((Math.random()*3 + 2 ) * 100.0) / 100.0;
        c.getRodes().setTipo_neumatic((int) (Math.random() * 4) + 1);
        System.out.println("El " + c.getEscuderia().getNom() + " conduït per " + c.getPilot().getNom() + " ha fet el pit stop en " + ((int) (ps * 100.0))/100.0 + " segons. Ha montat rodes "+ c.getRodes().getTipo_neumatic());
        return ps; // tornarà entre 2 i 5
    }
     
}
