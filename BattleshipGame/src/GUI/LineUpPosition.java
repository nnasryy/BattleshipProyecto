/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import ENUM.TipoBarco;
import GUIWarnings.CeldasLlenas;
import GUIWarnings.CompletarFlota;
import battleshipgame.Battleship;
import battleshipgame.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

/**
 *
 * @author nasry
 */
public class LineUpPosition extends javax.swing.JFrame{
     private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabelFondo, jLabel3, jLabel4, user, nave;
    private javax.swing.JButton jButton66, jButton67, jButton68;
    
    private Battleship game;
    private Player jugadorActual;
    private TipoBarco[] lineup;
    private int indiceBarco = 0;
    private JButton[][] grid = new JButton[8][8];

    public LineUpPosition(Battleship game, Player jugador) {
        this.game = game;
        this.jugadorActual = jugador;
        this.lineup = (jugador == game.getPlayer1()) ? game.getLineupP1() : game.getLineupP2();

        initComponents();
        actualizarLabels();
        
        setSize(1140, 819);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void actualizarLabels() {
        user.setText(jugadorActual.getUsername().toUpperCase());
        if (indiceBarco < lineup.length) {
            nave.setText(lineup[indiceBarco].getNombreCompleto());
        } else {
            nave.setText("¡FLOTA LISTA!");
        }
    }

    // Método para centralizar la colocación del barco
    private void intentarColocarBarco(int f, int c, JButton btn) {
        if (indiceBarco >= lineup.length) return;

        TipoBarco barcoActual = lineup[indiceBarco];
        String[][] tablero = (jugadorActual == game.getPlayer1()) ? game.getTableroP1() : game.getTableroP2();

        boolean colocado = game.colocarBarco(tablero, f, c, barcoActual, true);

        if (colocado) {
            btn.setText(barcoActual.getCodigo());
            btn.setEnabled(false);
            indiceBarco++;
            actualizarLabels();
        } else {
            JOptionPane.showMessageDialog(this, "No se puede colocar el barco aquí", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para resetear visual y lógicamente
    private void resetTablero() {
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                grid[f][c].setText("OPEN");
                grid[f][c].setEnabled(true);
            }
        }
        // Limpiamos la matriz en la lógica (puedes crear un resetTablero específico en Battleship)
        game.inicializarTableros(); 
        indiceBarco = 0;
        actualizarLabels();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Line Up Position");
        getContentPane().setLayout(null);

        // --- PANEL PRINCIPAL ---
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(Color.BLACK);
        jPanel1.setBorder(BorderFactory.createBevelBorder(0, null, null, Color.GREEN, Color.GREEN));
        jPanel1.setLayout(null);
        jPanel1.setBounds(90, 100, 960, 660);

        // --- LABELS ---
        jLabel3 = new JLabel("TURNO DE PLAYER:");
        jLabel3.setFont(new Font("Inlanders", 1, 36));
        jLabel3.setForeground(new Color(0, 204, 0));
        jLabel3.setBounds(50, 30, 400, 50);
        jPanel1.add(jLabel3);

        jLabel4 = new JLabel("SELECCIONA UNA CELDA PARA:");
        jLabel4.setFont(new Font("Inlanders", 1, 26));
        jLabel4.setForeground(Color.GREEN);
        jLabel4.setBounds(50, 100, 350, 30);
        jPanel1.add(jLabel4);

        user = new JLabel("Player name");
        estiloLabel(user);
        user.setBounds(365, 25, 340, 60);
        jPanel1.add(user);

        nave = new JLabel("nombre nave");
        estiloLabel(nave);
        nave.setBounds(405, 83, 340, 60);
        jPanel1.add(nave);

        // --- GENERACIÓN DE LA CUADRÍCULA ---
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                final int f = fila; // Variable efectiva para la lambda
                final int c = col;
                
                JButton btn = new JButton("OPEN");
                btn.setBackground(Color.BLACK);
                btn.setFont(new Font("OCR A Extended", 1, 13));
                btn.setForeground(new Color(0, 204, 0));
                btn.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 0), 2));
                btn.setBounds(50 + (col * 70), 160 + (fila * 60), 70, 60);
                
                btn.addActionListener(e -> intentarColocarBarco(f, c, btn));
                
                grid[fila][col] = btn;
                jPanel1.add(btn);
            }
        }

        // --- BOTONES DE CONTROL ---
        jButton66 = new JButton("RANDOM");
        estiloBoton(jButton66);
        jButton66.setBounds(670, 190, 220, 80);
        jButton66.addActionListener(e -> ejecutarRandom());
        jPanel1.add(jButton66);

        jButton67 = new JButton("RESET");
        estiloBoton(jButton67);
        jButton67.setBounds(670, 300, 220, 80);
        jButton67.addActionListener(e -> resetTablero());
        jPanel1.add(jButton67);

        jButton68 = new JButton("LISTO!");
        estiloBoton(jButton68);
        jButton68.setBounds(670, 420, 220, 80);
        jButton68.addActionListener(e -> finalizarPosicionamiento());
        jPanel1.add(jButton68);

        // --- FONDO ---
        jLabelFondo = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Images/CeldaChooser.png"));
            Image img = icon.getImage().getScaledInstance(1140, 820, Image.SCALE_SMOOTH);
            jLabelFondo.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.err.println("Error: Imagen de fondo no encontrada");
        }
        jLabelFondo.setBounds(0, 0, 1140, 820);

        getContentPane().add(jPanel1);
        getContentPane().add(jLabelFondo);
        getContentPane().setComponentZOrder(jPanel1, 0);
        getContentPane().setComponentZOrder(jLabelFondo, 1);
    }

    private void ejecutarRandom() {
        if (indiceBarco >= lineup.length) {
            new CeldasLlenas();
            return;
        }
        while (indiceBarco < lineup.length) {
            int f = (int) (Math.random() * 8);
            int c = (int) (Math.random() * 8);
            if (grid[f][c].isEnabled()) {
                intentarColocarBarco(f, c, grid[f][c]);
            }
        }
    }

    private void finalizarPosicionamiento() {
        if (indiceBarco < lineup.length) {
            new CompletarFlota();
            return;
        }

        dispose();
        if (jugadorActual == game.getPlayer1()) {
            new LineUpPosition(game, game.getPlayer2()).setVisible(true);
        } else {
            new BattleshipBoard(game, game.getPlayer1(), game.getPlayer2(), true).setVisible(true);
        }
    }

    private void estiloBoton(JButton btn) {
        btn.setBackground(new Color(34, 33, 33));
        btn.setFont(new Font("Inlanders", 1, 24));
        btn.setForeground(new Color(0, 204, 0));
        btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, null, Color.GREEN, Color.GREEN));
    }

    private void estiloLabel(JLabel lbl) {
        lbl.setFont(new Font("OCR A Extended", 1, 28));
        lbl.setForeground(Color.GREEN);
}
}
