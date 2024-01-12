package TP_PC.ECO.PSC;

import javax.swing.*;
import java.awt.Color;


public class Hora {
    private int hora;
    private int minuto;
    private int dia;
    private final int APERTURA = 7;
    private final int CIERRE = 18;
    private final int ACTIVIDADES=17;
    private boolean estadoParque;
    private boolean FIN;
    private Object bloqueoA= new Object();
    
    private JLabel sout;
    private JTextField text;

    public Hora(JLabel sout, JTextField text) {
        hora = 6;
        minuto = 0;
        dia = 0;
        estadoParque=false;
        this.sout = sout;
        this.text = text;
        FIN=false;
        text.setText("CERRADO");
        text.setBackground(Color.RED);
    }

    public synchronized void avanzarTiempo() throws InterruptedException {
        minuto = (minuto + 5) % 60;
        if (minuto == 0) {
            hora = (hora + 1) % 24;

            if (hora == 0) {
                dia++;
            }
        }
        this.mostrarHora();
        
    }


    private void mostrarHora() throws InterruptedException {
        SwingUtilities.invokeLater(() -> {
            sout.setText(String.format("%02d:%02d     DIA: %d", hora, minuto, dia));});

            switch (hora) {
                case APERTURA:estadoParque=true;this.notifyAll();
                text.setText("ABIERTO");
                text.setBackground(Color.GREEN);
                    break;
                case ACTIVIDADES:FIN=false; 
                text.setText("FIN ACTIVIDADES");
                text.setBackground(Color.YELLOW);
                    break;
                case CIERRE:estadoParque=false;FIN=true;
                text.setText("CERRADO");
                text.setBackground(Color.RED);
                break;
                
            }

            
    }
    
    public synchronized boolean entrarParque() throws InterruptedException{
        while (!estadoParque) {
            this.wait();
        }
        return estadoParque;
    }
    

    public boolean finalizarActividades() throws InterruptedException{
        synchronized(bloqueoA){
        }
       
        return estadoParque;

    }

}
