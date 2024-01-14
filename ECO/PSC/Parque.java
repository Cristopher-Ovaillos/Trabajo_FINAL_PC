package TP_PC.ECO.PSC;
import javax.swing.JTextArea;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import TP_PC.ECO.PSC.Parque;
public class Parque {
private Hora horario;
private JTextArea txtEntrada;
private CountDownLatch molinete;
private final int molinetes=3;
private Lock visitantes;
    public Parque(JTextArea entrada,Hora hora){
        this.txtEntrada=entrada;
        this.horario=hora;
        this.molinete= new CountDownLatch(molinetes);
    }

    public void aperturaParque() throws InterruptedException{
        horario.entrarParque(txtEntrada);
        
    }

    public boolean seguirActividades(){
       return horario.seguirActividades();
    }
    
    public void pasarMolinete(int id){
        
        molinete.countDown();
    }

    
}
