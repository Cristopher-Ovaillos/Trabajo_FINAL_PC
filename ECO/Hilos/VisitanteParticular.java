package TP_PC.ECO.Hilos;

import TP_PC.ECO.PSC.Parque;

public class VisitanteParticular implements Runnable {
    private final Parque parque;
    private final int nombre;

    public VisitanteParticular(Parque parque, int nombre) {
        this.parque = parque;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            parque.aperturaParque();
            // Lógica específica para VisitanteTour
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejo básico de la excepción, puedes personalizarlo según tus necesidades
        }
    }
}