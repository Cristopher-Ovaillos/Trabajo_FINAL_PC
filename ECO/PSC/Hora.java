package TP_PC.ECO.PSC;

import javax.sql.rowset.spi.SyncFactory;
import javax.swing.*;
import java.awt.Color;

import TP_PC.ECO.PSC.Parque;
public class Hora {
    private int hora;
    private int minuto;
    private int dia;
    private final int APERTURA = 7;
    private final int CIERRE = 18;
    private final int ACTIVIDADES=17;
    private boolean estadoParque;
    private boolean estadoActividad;
    private Object bloqueoA= new Object();
    
    private JLabel sout;
    private JTextField text;

    public Hora(JLabel sout, JTextField text) {
        hora = 0;
        minuto = 0;
        dia = 0;
        estadoParque=false;
        estadoActividad=false;
        this.sout = sout;
        this.text = text;
       
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
                synchronized(bloqueoA){
                    estadoActividad=true;
                }
                    break;
                case ACTIVIDADES:
                synchronized(bloqueoA){
                    estadoActividad=false;
                    bloqueoA.notifyAll();
                }
                text.setText("FIN ACTIVIDADES");
                text.setBackground(Color.YELLOW);
                    break;
                case CIERRE:estadoParque=false;
               
                text.setText("CERRADO");
                text.setBackground(Color.RED);
                break;
                
            }

            
    }
    
    public synchronized boolean entrarParque(JTextArea txt) throws InterruptedException{
        while (!estadoParque) {
            this.wait();
        }
        txt.append(	"intento entrar \n");
        return estadoParque;
    }
    

    public boolean seguirActividades() throws InterruptedException{
       synchronized(bloqueoA){}
        return estadoActividad;
    }
}

    
    
