/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.montduver.laformula1;

/**
 *
 * @author lucconlur
 */
public class Xasis {

    String marca;
    String modelo;
    String color;
    double pes;
    double llarg;
    double ample;

    public Xasis(String marca, String modelo, String color, double pes, double llarg, double ample) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.pes = pes;
        this.llarg = llarg;
        this.ample = ample;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPes() {
        return pes;
    }

    public void setPes(double pes) {
        this.pes = pes;
    }

    public double getLlarg() {
        return llarg;
    }

    public void setLlarg(double llarg) {
        this.llarg = llarg;
    }

    public double getAmple() {
        return ample;
    }

    public void setAmple(double ample) {
        this.ample = ample;
    }

}
