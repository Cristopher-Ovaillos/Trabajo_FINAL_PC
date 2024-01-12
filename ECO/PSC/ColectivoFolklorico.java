package TP_PC.ECO.PSC;

import javax.swing.JTextArea;
import java.util.concurrent.Semaphore;

public class ColectivoFolklorico {
    private int CAPACIDAD;
    private Semaphore subirseSemaphore;
    private Semaphore bajarseSemaphore;
    private JTextArea txt;

    public ColectivoFolklorico(JTextArea txt) {
        CAPACIDAD = 5;
        subirseSemaphore = new Semaphore(CAPACIDAD);
        bajarseSemaphore = new Semaphore(0); // Inicialmente bloqueado
        this.txt = txt;
    }

    public void subirColectivo(int id) {
        String dato = "PASAJERO TOUR ID:" + id + " \n";
        txt.append(dato);
        subirseSemaphore.release(); // Libera un permiso
    }

    public void entrarEstacionamiento(String nombre) throws InterruptedException {
        txt.append("El colectivo " + nombre + " está esperando a los visitantes. \n");
        // El colectivo espera a que todos los pasajeros suban
        subirseSemaphore.acquire(CAPACIDAD);
        txt.append("¡Todos los pasajeros están a bordo! El Colectivo comienza su recorrido. \n");
    }

    public void salirColectivo(int id) throws InterruptedException {
        String dato = "    P.TOUR ID: " + id + " bajo \n";
        txt.append(dato);
        // Libera un permiso para permitir que otros pasajeros bajen
        bajarseSemaphore.release();
    }

    public void salirEstacionamiento(String nombre) throws InterruptedException {
        txt.append("El colectivo " + nombre + " llegó al Estacionamiento \n");
        // Bloquea hasta que todos los pasajeros hayan bajado
        bajarseSemaphore.acquire(CAPACIDAD);
        txt.append("¡Todos los pasajeros bajaron!\n");
        // Reinicia el semáforo para la siguiente ronda de bajadas
        bajarseSemaphore = new Semaphore(0);
    }
}
