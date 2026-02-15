package GUI;

import ENUM.TipoBarco;
import GUIWarnings.CompletarFlota;
import battleshipgame.Battleship;
import battleshipgame.Player;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;

public class LineUpPosition extends javax.swing.JFrame {

    private JPanel jPanel1;
    private JLabel jLabelFondo, jLabel3, user, nave;
    private JButton jButton66, jButton67, jButton68;

    private Battleship game;
    private Player jugadorActual;
    private TipoBarco[] lineup;
    private JButton[][] grid = new JButton[8][8];

    public LineUpPosition(Battleship game, Player jugador) {
        this.game = game;
        this.jugadorActual = jugador;
        // Sincronizamos el lineup actual
        this.lineup = (jugador == game.getPlayer1()) ? game.getLineupP1() : game.getLineupP2();

        initComponents();
        actualizarLabels();

        setSize(1140, 819);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private String nombreCompleto(String cod) {
        if (cod == null) return "";
        switch (cod) {
            case "PA": return "PORTAAVIONES";
            case "AZ": return "ACORAZADO";
            case "SM": return "SUBMARINO";
            case "DT": return "DESTRUCTOR";
            default: return "";
        }
    }

    private void actualizarLabels() {
        user.setText(jugadorActual.getUsername().toUpperCase());
        
        int indice = game.getIndiceActual(jugadorActual);
        
        // Si el índice es válido y menor al total, mostramos la nave
        if (indice < lineup.length && lineup[indice] != null) {
            nave.setText(nombreCompleto(lineup[indice].getCodigo()));
        } else {
            nave.setText("FLOTA COMPLETA");
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Line Up Position");
        getContentPane().setLayout(null);

        jPanel1 = new JPanel();
        jPanel1.setBackground(Color.BLACK);
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, null, new Color(0, 204, 0), new Color(0, 204, 0)));
        jPanel1.setLayout(null);
        jPanel1.setBounds(90, 100, 960, 660);

        jLabel3 = new JLabel("TURNO DE PLAYER:");
        jLabel3.setFont(new Font("Inlanders", 1, 36));
        jLabel3.setForeground(new Color(0, 204, 0));
        jLabel3.setBounds(50, 30, 400, 50);
        jPanel1.add(jLabel3);

        user = new JLabel("Player name");
        estiloLabel(user);
        user.setBounds(365, 25, 340, 60);
        jPanel1.add(user);

        nave = new JLabel("nombre nave");
        estiloLabel(nave);
        nave.setBounds(405, 83, 340, 60);
        jPanel1.add(nave);

        // --- MATRIZ DE BOTONES ---
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                final int f = fila;
                final int c = col;
                JButton btn = new JButton("OPEN");
                btn.setBackground(Color.BLACK);
                btn.setFont(new Font("OCR A Extended", 1, 13));
                btn.setForeground(new Color(0, 204, 0));
                btn.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 0), 2));
                btn.setBounds(50 + (col * 70), 160 + (fila * 60), 70, 60);

                btn.addActionListener(e -> {
                    // REGLA: Si NO dice OPEN -> NO hace nada
                    if (!btn.getText().equals("OPEN")) {
                        return;
                    }

                    // REGLA: Si dice OPEN -> Coloca nave actual
                    int indiceActual = game.getIndiceActual(jugadorActual);
                    if (indiceActual < lineup.length) {
                        TipoBarco barcoActual = lineup[indiceActual];

                        // 1. Colocar en lógica
                        game.colocarBarcoSimple(jugadorActual, f, c);

                        // 2. Actualizar visual
                        btn.setText(barcoActual.getCodigo());
                        btn.setEnabled(false); // Bloquear celda

                        // 3. Pasar a la siguiente nave (actualizar label)
                        actualizarLabels();
                    }
                });

                grid[fila][col] = btn;
                jPanel1.add(btn);
            }
        }

        // --- BOTONES DE ACCIÓN ---
        
        // RANDOM
        jButton66 = new JButton("RANDOM");
        estiloBoton(jButton66);
        jButton66.setBounds(670, 190, 220, 80);
        jPanel1.add(jButton66);

        // RESET
        jButton67 = new JButton("RESET");
        estiloBoton(jButton67);
        jButton67.setBounds(670, 300, 220, 80);
        jPanel1.add(jButton67);

        // LISTO
        jButton68 = new JButton("LISTO!");
        estiloBoton(jButton68);
        jButton68.setBounds(670, 420, 220, 80);
        jPanel1.add(jButton68);

        // --- FONDO ---
        jLabelFondo = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/Images/CeldaChooser.png"));
            Image img = icon.getImage().getScaledInstance(1140, 820, Image.SCALE_SMOOTH);
            jLabelFondo.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("Error cargando fondo: " + e.getMessage());
        }
        jLabelFondo.setBounds(0, 0, 1140, 820);

        getContentPane().add(jPanel1);
        getContentPane().add(jLabelFondo);
        getContentPane().setComponentZOrder(jPanel1, 0);
        getContentPane().setComponentZOrder(jLabelFondo, 1);

        // --- LISTENERS DE ACCIÓN ---

        // RANDOM: Coloca solo la nave actual en una celda OPEN aleatoria
        jButton66.addActionListener(e -> {
            int indiceActual = game.getIndiceActual(jugadorActual);
            if (indiceActual >= lineup.length) return; // Flota completa

            // Buscar celda OPEN aleatoria
            // Intentamos máximo 100 veces encontrar una celda libre
            for (int i = 0; i < 100; i++) {
                int f = (int) (Math.random() * 8);
                int c = (int) (Math.random() * 8);

                if (grid[f][c].getText().equals("OPEN")) {
                    // Simulamos clic en esa celda para reutilizar la lógica
                    // O invocamos directamente:
                    TipoBarco barcoActual = lineup[indiceActual];
                    game.colocarBarcoSimple(jugadorActual, f, c);
                    grid[f][c].setText(barcoActual.getCodigo());
                    grid[f][c].setEnabled(false);
                    actualizarLabels();
                    return; // Salimos después de colocar uno
                }
            }
            // Si no encuentra espacio después de 100 intentos, no hace nada (no muestra warning)
        });

        // RESET: Limpia TODO
               // RESET
        jButton67.addActionListener(e -> {
            // Usamos resetColocacion para limpiar el tablero pero mantener los barcos elegidos
            game.resetColocacion(jugadorActual);
            
            // Limpiar visual
            for (int f = 0; f < 8; f++) {
                for (int c = 0; c < 8; c++) {
                    grid[f][c].setText("OPEN");
                    grid[f][c].setEnabled(true);
                }
            }
            actualizarLabels();
        });

        // LISTO: Valida y avanza
        jButton68.addActionListener(e -> {
            if (!game.flotaCompleta(jugadorActual)) {
                new CompletarFlota();
                return;
            }
            
            dispose();
            if (jugadorActual == game.getPlayer1()) {
                new LineUpPosition(game, game.getPlayer2()).setVisible(true);
            } else {
                new BattleshipBoard(game, game.getPlayer1(), game.getPlayer2(), game.isModoTutorial()).setVisible(true);
            }
        });
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