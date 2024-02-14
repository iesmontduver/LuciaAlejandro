/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.montduver.laformula1;

/**
 *
 * @author alemangar
 */
public class Pilot {
    
    private String nom;
    private Escuderia escuderia;
    private int numero;
    private int punts;
    private double sou;

    public Pilot(String nom, Escuderia escuderia, int numero, int punts, double sou) {
        this.nom = nom;
        this.escuderia = escuderia;
        this.numero = numero;
        this.punts = punts;
        this.sou = sou;
    }

    public String getNom() {
        return nom;
    }

    public Escuderia getEscuderia() {
        return escuderia;
    }

    public int getNumero() {
        return numero;
    }

    public int getPunts() {
        return punts;
    }

    public double getSou() {
        return sou;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEscuderia(Escuderia escuderia) {
        this.escuderia = escuderia;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public void setSou(double sou) {
        this.sou = sou;
    }
    
    
    
}
