/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcuentabancaria;

/**
 *
 * @author anna
 */
public class Responsable implements Runnable {

    Monitor mon;

    public Responsable(Monitor mon) {
        this.mon = mon;
    }

    @Override
    public void run() {
        while (!mon.isSuperarVecesPadre() && !mon.superarDineroHijo() && !mon.isFinResponsable()) {
            try {
                Thread.sleep(15000);
                mon.preguntarSalirResponsable();
            } catch (InterruptedException ex) {
            }
        }
        if (mon.isFinResponsable()) {
             System.out.println("Salimos porque el responsable cerró la caja");
             mon.visCosis();
        }
    }

}
