package TP_PC.ECO.Hilos;

import java.util.Random;

import TP_PC.ECO.PSC.Parque;
public class GeneradorP extends Thread {

    private Parque parque;
    private int id;

    public GeneradorP(Parque p) {
        this.parque = p;
        this.id = 0;
    }

    public void run() {
        Random random = new Random();

        while (true) {
            int tipoVisitante = random.nextInt(2); // Genera 0 o 1
            Runnable visitante;

            if (tipoVisitante == 0) {
                visitante =  new VisitanteParticular(parque, id);
            } else {
                visitante = new VisitanteTour(parque, id);
            }

            id++;
            Thread thread = new Thread(visitante);
            thread.start();
            System.out.println(id);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


