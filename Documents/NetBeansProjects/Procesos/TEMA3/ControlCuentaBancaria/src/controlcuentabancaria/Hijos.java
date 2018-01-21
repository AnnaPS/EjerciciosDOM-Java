/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcuentabancaria;

import java.util.Scanner;

/**
 *
 * @author anna
 */
public class Hijos implements Runnable {

    private Monitor mon;

    private String nombre;
    private float dineroHijo;
    private boolean superaDinero;
    private int vecesHijo;

    public Hijos(Monitor mon) {
        this.mon = mon;
        pedirNombre();
    }

    @Override
    public void run() {

        while (!mon.isSuperarVecesPadre() && !superaDinero && !mon.isFinResponsable()) {
            try {
                mon.sacarDinero(this);
                if (dineroHijo >= 1000) {
                    System.out.println(nombre + ", sale por superar el limite de 1000€");
                    superaDinero = true;
                }
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        if (mon.isSuperarVecesPadre()) {
            System.out.println(nombre + ", sale por que el padre superó las veces");
        }

    }

    private void pedirNombre() {
        Scanner pedir = new Scanner(System.in);
        System.out.print("Indique el nombre: ");
        nombre = pedir.nextLine();
    }

    public String getNombre() {
        return nombre;
    }

    public float getDineroHijo() {
        return dineroHijo;
    }

    public void setDineroHijo(float dineroHijo) {
        this.dineroHijo = dineroHijo;
    }

    public boolean isSuperaDinero() {
        return superaDinero;
    }

    public void setSuperaDinero(boolean superaDinero) {
        this.superaDinero = superaDinero;
    }

    public int getVecesHijo() {
        return vecesHijo;
    }

    public void setVecesHijo(int vecesHijo) {
        this.vecesHijo = vecesHijo;
    }

}
