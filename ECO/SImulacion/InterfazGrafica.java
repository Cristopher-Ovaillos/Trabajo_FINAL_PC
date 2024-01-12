package TP_PC.ECO.SImulacion;

import javax.swing.*;

import TP_PC.ECO.Horario.Reloj;
import TP_PC.ECO.PSC.Hora;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {

    private JLabel horaLabel;
    private JTextField estadoParque;
    private JButton iniciarButton;
    private JButton detenerButton;
    private JTextArea entradaTexto;
    public InterfazGrafica() {
        setTitle("PARQUE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        // Crear el panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Crear el JLabel para mostrar la hora
        horaLabel = new JLabel("HORA 00:00     DIA: 0");
        horaLabel.setHorizontalAlignment(JLabel.CENTER);
        horaLabel.setVerticalAlignment(JLabel.TOP);

        // Crear el JTextField para mostrar el estado del parque
        estadoParque = new JTextField();
        estadoParque.setEditable(true); // Para evitar que el usuario lo edite
        estadoParque.setHorizontalAlignment(JTextField.CENTER);
        

        // Añadir el JLabel y JTextField al panel principal
        panelPrincipal.add(horaLabel, BorderLayout.CENTER);
        panelPrincipal.add(estadoParque, BorderLayout.SOUTH); // Cambiado a SOUTH para que aparezca debajo

        // Botones para iniciar y detener la simulación
        iniciarButton = new JButton("Iniciar Simulación");
        detenerButton = new JButton("Detener Simulación");

        // Añadir botones a un panel
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.add(iniciarButton);
        panelBotones.add(detenerButton);

        // Añadir paneles de botones al panel principal
        panelPrincipal.add(panelBotones, BorderLayout.EAST);

        // Añadir el panel principal al JFrame
        add(panelPrincipal);

        // Añadir acción al botón de iniciar
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSimulacion();
            }
        });

        // Hacer visible la interfaz
        setVisible(true);
    }

    private void iniciarSimulacion() {
        ejecutarHilo();
    }

    private void ejecutarHilo() {
        Hora h = new Hora(horaLabel, estadoParque);
        Reloj r = new Reloj(h);
        r.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica());
    }
}
