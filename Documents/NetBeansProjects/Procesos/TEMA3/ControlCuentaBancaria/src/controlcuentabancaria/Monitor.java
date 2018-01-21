/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcuentabancaria;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anna
 */
public class Monitor {

    private int numHijos, numVecesPadre;
    private Padre papa;
    private Responsable responsable;
    private ArrayList<Hijos> nenes = new ArrayList();
    private boolean turnoPadre = true, superarVecesPadre, finResponsable;
    private float ingreso;
    private String nombHijo;

    public Monitor() {
        pedirDatosInicio();
        prepararHilos();
    }

    private void pedirDatosInicio() {
        Scanner pedir = new Scanner(System.in);
        System.out.print("Introduzca el numero de hijos: ");
        numHijos = Integer.parseInt(pedir.nextLine());

        System.out.print("Indique la cantidad de veces que irá el padre al banco: ");
        numVecesPadre = Integer.parseInt(pedir.nextLine());

    }

    private void prepararHilos() {
        papa = new Padre(this, numVecesPadre);
        for (int i = 0; i < numHijos; i++) {
            nenes.add(new Hijos(this));
        }
        responsable = new Responsable(this);

        Thread padre = new Thread(papa);
        padre.start();
        for (Hijos nene : nenes) {
            Thread hijo = new Thread(nene);
            hijo.start();
        }
        Thread resp = new Thread(responsable);
        resp.start();

    }

    public synchronized void ingresar() {
        if (turnoPadre && !superarDineroHijo()) {
            Scanner pedir = new Scanner(System.in);
            System.out.print("Indique la cantidad a ingresar: ");
            ingreso = Float.parseFloat(pedir.nextLine());

            System.out.println("Indique para que hijo es el ingreso: ");
            for (Hijos nene : nenes) {
                //comprobamos que el hijo NO supero el dinero, para mostrar su nombre
                if (!nene.isSuperaDinero()) {
                    System.out.println("-" + nene.getNombre());
                }

            }
            nombHijo = pedir.nextLine();

            turnoPadre = false;
            notifyAll();
        }
    }

    public synchronized void sacarDinero(Hijos hijoActual) {
        if (turnoPadre) {
            try {
                System.out.println("No es tu turno, " + hijoActual.getNombre());
                wait();
            } catch (InterruptedException ex) {
            }
        } else {
            if (hijoActual.getNombre().equalsIgnoreCase(nombHijo) && !finResponsable) {
                hijoActual.setDineroHijo(hijoActual.getDineroHijo() + ingreso);
                System.out.println("El hijo " + hijoActual.getNombre()
                        + " saca la cantidad de: " + ingreso + "\n y lleva un total de: "
                        + hijoActual.getDineroHijo());
                //acumulamos al padre ya que se hizo efectivo el ingreso
                papa.setDineroIngresadoTotal(papa.getDineroIngresadoTotal() + ingreso);
                papa.setVeces(papa.getVeces() - 1);
                if (papa.getVeces() == 0) {
                    //motivo de salida
                    superarVecesPadre = true;

                }
                System.out.println("El padre ingresó un total de: " + papa.getDineroIngresadoTotal() + " "
                        + "y le quedan por ir: " + papa.getVeces());
                hijoActual.setVecesHijo(hijoActual.getVecesHijo() + 1);

                notifyAll();
                //comprobamos para poner la boolean a true y seguir pidiendo datos
                if (papa.getVeces() > 0) {
                    turnoPadre = true;
                }
            }
        }
    }

    public boolean isSuperarVecesPadre() {
        return superarVecesPadre;
    }

    public boolean superarDineroHijo() {
        int cont = 0;
        for (Hijos nene : nenes) {
            if (nene.isSuperaDinero()) {
                //para saber si es igual al size
                cont++;
            }
        }
        if (cont == nenes.size()) {
            return true;
        } else {
            return false;
        }
    }

    public synchronized void preguntarSalirResponsable() {
        if (!superarVecesPadre && !superarDineroHijo()) {
            Scanner pedir = new Scanner(System.in);
            System.out.println("Quiere salir? (s/n)");
            String respuesta = pedir.nextLine();

            if (respuesta.equalsIgnoreCase("s")) {
                finResponsable = true;
            }
        }

    }

    public boolean isFinResponsable() {
        return finResponsable;
    }

    public void visCosis() {
        System.out.println("\tEl padre sale porque el responsable cerró la caja, y le quedaban por ir " + papa.getVeces()
                + " veces,e ingresó " + papa.getDineroIngresadoTotal() + "€ en total");
        for (Hijos nene : nenes) {
            System.out.println("\t" + nene.getNombre() + " sale porque el responsable cerró la caja."
                    + "Sacó un total de " + nene.getDineroHijo() + "€"
                    + "y fue al baco un total de: " + nene.getVecesHijo());
        }
    }

}
