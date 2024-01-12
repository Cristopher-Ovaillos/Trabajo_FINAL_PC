package TP_PC.ECO.Horario;

import TP_PC.ECO.PSC.Hora;

public class Reloj extends Thread{
    
private Hora hora;

    public Reloj(Hora h){
        this.hora=h;
    }
    public void run() {
        while (true) {
            try {
                hora.avanzarTiempo();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
             this.tiempo();
        }
     
    }

    private void tiempo() {
        try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
    }


}
