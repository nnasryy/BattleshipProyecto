package GUI;

import ENUM.TipoBarco;
import GUIWarnings.CeldasLlenas;
import GUIWarnings.CompletarFlota;
import battleshipgame.Battleship;
import battleshipgame.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class LineUpPosition extends javax.swing.JFrame {

    // --- Componentes de la ventana ---
    private JPanel jPanel1;
    private JLabel jLabelFondo, jLabel3, user, nave;
    private JButton jButton66, jButton67, jButton68;
    
    private Battleship game;
    private Player jugadorActual;
    private TipoBarco[] lineup;
    private int indiceBarco = 0;
    private JButton[][] grid = new JButton[8][8];
    private Random random = new Random();

    // --- Constructor ---
    public LineUpPosition(Battleship game, Player jugador) {
        this.game = game;
        this.jugadorActual = jugador;
        this.lineup = (jugador == game.getPlayer1()) ? game.getLineupP1() : game.getLineupP2();

        initComponents();
        actualizarLabels();

        setSize(1140, 819);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // --- Actualiza labels de player y nave ---
    private void actualizarLabels() {
        user.setText(jugadorActual.getUsername().toUpperCase());
        if (indiceBarco < lineup.length) {
            nave.setText("Seleccione una celda para: " + lineup[indiceBarco].getNombreCompleto());
        } else {
            nave.setText("FLOTA COMPLETA");
        }
    }

    // --- Intentar colocar la nave en la celda ---
    private void intentarColocarBarco(int f, int c, JButton btn) {
        if (indiceBarco >= lineup.length) return; // Si ya terminó, nada que hacer

        TipoBarco barcoActual = lineup[indiceBarco];
        String[][] tablero = (jugadorActual == game.getPlayer1()) ? game.getTableroP1() : game.getTableroP2();

        if (!tablero[f][c].equals("~")) {
            new CeldasLlenas(); // Opcional: aviso de celda ocupada
            return;
        }

        boolean colocado = game.colocarBarco(tablero, f, c, barcoActual, true);

        if (colocado) {
            btn.setText(barcoActual.getCodigo());
            btn.setEnabled(false);
            btn.setBackground(new Color(0, 50, 0));
            btn.setOpaque(true);

            indiceBarco++;
            actualizarLabels();
        }
    }

    // --- Limpiar tablero ---
    private void resetTablero() {
        String[][] tablero = (jugadorActual == game.getPlayer1()) ? game.getTableroP1() : game.getTableroP2();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = "~";
                grid[i][j].setText("OPEN");
                grid[i][j].setEnabled(true);
                grid[i][j].setBackground(Color.BLACK);
                grid[i][j].setForeground(new Color(0, 204, 0));
            }
        }
        indiceBarco = 0;
        actualizarLabels();
    }

    // --- Colocación aleatoria ---
    private void ejecutarRandom() {
        if (indiceBarco >= lineup.length) {
            new CeldasLlenas();
            return;
        }

        String[][] tablero = (jugadorActual == game.getPlayer1()) ? game.getTableroP1() : game.getTableroP2();
        boolean posicionado = false;

        while (!posicionado) {
            int f = random.nextInt(8);
            int c = random.nextInt(8);

            if (tablero[f][c].equals("~")) {
                intentarColocarBarco(f, c, grid[f][c]);
                posicionado = true;
            }
        }
    }

    // --- Finalizar posicionamiento ---
    private void finalizarPosicionamiento() {
        if (indiceBarco < lineup.length) {
            new CompletarFlota();
            return;
        }

        this.dispose();
        if (jugadorActual == game.getPlayer1()) {
            new LineUpPosition(game, game.getPlayer2()).setVisible(true);
        } else {
            new BattleshipBoard(game, game.getPlayer1(), game.getPlayer2(), game.isModoTutorial()).setVisible(true);
        }
    }

    // --- Inicialización de componentes ---
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Line Up Position");
        getContentPane().setLayout(null);

        // --- Fondo ---
        jLabelFondo = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Images/CeldaChooser.png"));
            Image img = icon.getImage().getScaledInstance(1140, 820, Image.SCALE_SMOOTH);
            jLabelFondo.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.err.println("Imagen de fondo no encontrada");
        }
        jLabelFondo.setBounds(0, 0, 1140, 820);
        getContentPane().add(jLabelFondo);

        // --- Panel principal encima del fondo ---
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0, 180)); // semi-transparente
        jPanel1.setLayout(null);
        jPanel1.setBounds(90, 100, 960, 660);
        getContentPane().add(jPanel1);

        // --- Labels ---
        jLabel3 = new JLabel("TURNO DE PLAYER:");
        jLabel3.setFont(new Font("Inlanders", Font.BOLD, 36));
        jLabel3.setForeground(new Color(0, 204, 0));
        jLabel3.setBounds(50, 30, 400, 50);
        jPanel1.add(jLabel3);

        user = new JLabel("Player name");
        estiloLabel(user);
        user.setBounds(365, 25, 340, 60);
        jPanel1.add(user);

        nave = new JLabel("Seleccione una celda para:");
        estiloLabel(nave);
        nave.setBounds(405, 83, 400, 60);
        jPanel1.add(nave);

        // --- Grid de botones ---
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                final int f = fila;
                final int c = col;

                JButton btn = new JButton("OPEN");
                btn.setBackground(Color.BLACK);
                btn.setFont(new Font("OCR A Extended", Font.BOLD, 13));
                btn.setForeground(new Color(0, 204, 0));
                btn.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 0), 2));
                btn.setBounds(50 + (col * 70), 160 + (fila * 60), 70, 60);

                btn.addActionListener(e -> intentarColocarBarco(f, c, btn));

                grid[fila][col] = btn;
                jPanel1.add(btn);
            }
        }

        // --- Botones RANDOM, RESET, LISTO ---
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
    }

    // --- Estilo de botones ---
    private void estiloBoton(JButton btn) {
        btn.setBackground(new Color(34, 33, 33));
        btn.setFont(new Font("Inlanders", Font.BOLD, 24));
        btn.setForeground(new Color(0, 204, 0));
        btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, null, Color.GREEN, Color.GREEN));
        btn.setFocusPainted(false);
    }

    // --- Estilo de labels ---
    private void estiloLabel(JLabel lbl) {
        lbl.setFont(new Font("OCR A Extended", Font.BOLD, 28));
        lbl.setForeground(Color.GREEN);
    }
}
