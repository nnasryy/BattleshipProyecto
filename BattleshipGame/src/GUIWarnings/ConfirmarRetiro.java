/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIWarnings;

import GUI.BattleshipBoard;
import GUI.Winner;
import battleshipgame.Battleship;
import battleshipgame.Player;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Ventana de confirmación personalizada para retirarse del juego.
 * @author nasry
 */
public class ConfirmarRetiro extends JFrame {
    
    private Battleship game;
    private Player jugadorRetirado;
    private Player jugadorGanador;
    private BattleshipBoard boardReference; // Referencia para cerrar el tablero

    public ConfirmarRetiro(Battleship game, Player retirado, Player ganador, BattleshipBoard board) {
        this.game = game;
        this.jugadorRetirado = retirado;
        this.jugadorGanador = ganador;
        this.boardReference = board;
        
        initComponents();
        this.setLocationRelativeTo(null);
        // Hacemos que esta ventana esté siempre arriba para que el usuario no pierda el foco
        this.setAlwaysOnTop(true); 
    }

    private void initComponents() {
        setSize(626, 319);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // No cerrar con la X, usar botones

        JLabel txt = new JLabel("RETIRARSE DEL JUEGO?");
        txt.setFont(new Font("Cold Warm", Font.PLAIN, 36)); // Ajusté un poco el tamaño
        txt.setForeground(Color.GREEN); 
        txt.setBounds(100, 120, 450, 40);
        add(txt);

        // BOTÓN SÍ
        JButton btnSi = createNeonBtn("SI", 110);
        btnSi.addActionListener(e -> ejecutarRetiro());
        add(btnSi);

        // BOTÓN NO
        JButton btnNo = createNeonBtn("NO", 310);
        btnNo.addActionListener(e -> {
            // Simplemente cerramos esta ventana y el juego continúa
            this.dispose();
        });
        add(btnNo);

        // Fondo
        try {
            JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/Images/joprep.png")));
            bg.setBounds(0, 0, 610, 280);
            add(bg);
        } catch (Exception e) {
            getContentPane().setBackground(Color.BLACK);
        }
    }

    private void ejecutarRetiro() {
        // 1. Registrar en el historial
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String registro = fecha + " - " + jugadorRetirado.getUsername() + " se retiró del juego dejando como ganador a " + jugadorGanador.getUsername() + ".";
        
        // Guardamos en el historial de ambos (según la lógica anterior)
        jugadorRetirado.agregarAlHistorial(registro);
        jugadorGanador.agregarAlHistorial(registro);
        
        // 2. Asignar puntos al ganador
        jugadorGanador.agregarPuntos(3);
        
        // 3. Abrir ventana Winner
        new Winner(jugadorGanador.getUsername(), 3, game).setVisible(true);
        
        // 4. Cerrar el tablero de juego
        boardReference.dispose();
        
        // 5. Cerrar esta ventana de confirmación
        this.dispose();
    }

    private JButton createNeonBtn(String t, int x) {
        JButton b = new JButton(t);
        b.setBackground(new Color(34, 33, 33));
        b.setFont(new Font("Cold Warm", Font.PLAIN, 24));
        b.setForeground(new Color(0, 255, 0));
        b.setBorder(BorderFactory.createBevelBorder(0, null, null, Color.GREEN, Color.GREEN));
        b.setBounds(x, 210, 170, 50);
        b.setFocusPainted(false);
        return b;
    }
}