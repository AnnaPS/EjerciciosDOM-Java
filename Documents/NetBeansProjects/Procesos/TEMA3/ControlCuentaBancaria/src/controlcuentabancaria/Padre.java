/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcuentabancaria;

import static java.lang.Thread.sleep;

/**
 *
 * @author anna
 */
public class Padre implements Runnable {

    Monitor mon;
    private int veces;
    private float dineroIngresadoTotal;

    public Padre(Monitor mon, int v) {
        this.mon = mon;
        veces = v;
    }

    @Override
    public void run() {
        while (!mon.isSuperarVecesPadre()&&!mon.superarDineroHijo()&&!mon.isFinResponsable()) {
            try {
                mon.ingresar();
                sleep(10);
            } catch (InterruptedException ex) {
            }
        }
        if (mon.isSuperarVecesPadre()) {
            System.out.println("Salimos porque el padre fue todas las veces");
        }
        if (mon.superarDineroHijo()) {
            System.out.println("El padre sale porque los hijos superaron el limite");
        }
    }

    public float getDineroIngresadoTotal() {
        return dineroIngresadoTotal;
    }

    public void setDineroIngresadoTotal(float dineroIngresadoTotal) {
        this.dineroIngresadoTotal = dineroIngresadoTotal;
    }

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }

}
