package TP_PC.ECO.Hilos;

import TP_PC.ECO.PSC.Parque;

public class VisitanteTour implements Runnable {
    private final Parque parque;
    private final String nombre;

    public VisitanteTour(Parque parque, String nombre) {
        this.parque = parque;
        this.nombre = nombre;
    }

    @Override
    public void run() {
       
    }
}