package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import battleshipgame.Battleship;
import battleshipgame.Player;

public class BattleshipBoard extends JFrame {

    private JButton[][] playerBoard = new JButton[8][8];
    private JButton[][] rivalBoard = new JButton[8][8];
    private JPanel jPanelInfo;
    private JTextField txtCant;
    private Battleship game;
    private Player jugadorActual;
    private Player rivalActual;
    private boolean tutorial;

    public BattleshipBoard(Battleship game, Player jugadorActual, Player rivalActual, boolean tutorial) {
        this.game = game;
        this.jugadorActual = jugadorActual;
        this.rivalActual = rivalActual;
        this.tutorial = tutorial;

        initComponentsManual();
        actualizarCantNaves();
        cargarTableros();
        
        setTitle("Battleship - Batalla: " + jugadorActual.getUsername());
        setVisible(true);
    }

    private void initComponentsManual() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(10, 10, 25)); // Fondo oscuro naval

        // --- CÁLCULO DE POSICIONES ---
        int btnWidth = 80; // Ajustado para que quepa mejor en pantallas estándar
        int btnHeight = 60;
        int gap = 2;
        int boardSize = (btnWidth * 8) + (gap * 7);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int startX = (screenSize.width - (boardSize * 2 + 100)) / 2;
        int startY = 200;

        // --- PANEL DE INFORMACIÓN ---
        jPanelInfo = new JPanel();
        jPanelInfo.setBackground(new Color(30, 40, 60, 200));
        jPanelInfo.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        jPanelInfo.setLayout(null);
        jPanelInfo.setBounds(screenSize.width - 350, 50, 310, 250);

        JLabel lblTipoTitulo = new JLabel("BARCOS RIVAL:");
        lblTipoTitulo.setFont(new Font("OCR A Extended", Font.BOLD, 18));
        lblTipoTitulo.setForeground(Color.WHITE);
        lblTipoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoTitulo.setBounds(0, 20, 310, 25);
        jPanelInfo.add(lblTipoTitulo);

        txtCant = new JTextField();
        txtCant.setBounds(55, 60, 200, 100);
        txtCant.setEditable(false);
        txtCant.setBackground(Color.BLACK);
        txtCant.setForeground(Color.GREEN);
        txtCant.setFont(new Font("Digital-7", Font.BOLD, 80)); // Estilo radar
        txtCant.setHorizontalAlignment(JTextField.CENTER);
        jPanelInfo.add(txtCant);
        add(jPanelInfo);

        // --- TABLERO PROPIO (IZQUIERDA) ---
        JLabel lblTu = new JLabel("TU FLOTA", SwingConstants.CENTER);
        lblTu.setForeground(Color.CYAN);
        lblTu.setFont(new Font("OCR A Extended", Font.BOLD, 25));
        lblTu.setBounds(startX, startY - 40, boardSize, 30);
        add(lblTu);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton();
                btn.setBounds(startX + (j * (btnWidth + gap)), startY + (i * (btnHeight + gap)), btnWidth, btnHeight);
                btn.setBackground(new Color(20, 20, 40));
                btn.setEnabled(false); // Tu tablero no es clicable
                playerBoard[i][j] = btn;
                add(btn);
            }
        }

        // --- TABLERO RIVAL (DERECHA) ---
        int rivalStartX = startX + boardSize + 100;
        JLabel lblRival = new JLabel("RADAR ENEMIGO", SwingConstants.CENTER);
        lblRival.setForeground(Color.RED);
        lblRival.setFont(new Font("OCR A Extended", Font.BOLD, 25));
        lblRival.setBounds(rivalStartX, startY - 40, boardSize, 30);
        add(lblRival);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton("?");
                btn.setBounds(rivalStartX + (j * (btnWidth + gap)), startY + (i * (btnHeight + gap)), btnWidth, btnHeight);
                btn.setBackground(new Color(0, 0, 0));
                btn.setForeground(Color.WHITE);
                final int fila = i;
                final int col = j;
                btn.addActionListener(e -> disparar(fila, col, btn));
                rivalBoard[i][j] = btn;
                add(btn);
            }
        }

        // --- NOMBRE JUGADOR ---
        JLabel lblNombrePlayer = new JLabel(jugadorActual.getUsername().toUpperCase());
        lblNombrePlayer.setFont(new Font("Capture it", Font.BOLD, 60));
        lblNombrePlayer.setForeground(Color.GREEN);
        lblNombrePlayer.setBounds(startX, 50, 800, 70);
        add(lblNombrePlayer);
    }

    private void cargarTableros() {
        String[][] miTablero = (jugadorActual == game.getPlayer1()) ? game.getTableroP1() : game.getTableroP2();
        String[][] tableroEnemigo = (jugadorActual == game.getPlayer1()) ? game.getTableroP2() : game.getTableroP1();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Dibujar mis barcos
                if (!miTablero[i][j].equals("~")) {
                    playerBoard[i][j].setText(miTablero[i][j]);
                    playerBoard[i][j].setBackground(new Color(0, 80, 0));
                }
                
                // Si ya ataqué al rival anteriormente (X o F), mostrarlo
                if (tableroEnemigo[i][j].equals("X")) {
                    rivalBoard[i][j].setText("X");
                    rivalBoard[i][j].setBackground(Color.RED);
                    rivalBoard[i][j].setEnabled(false);
                } else if (tableroEnemigo[i][j].equals("F")) {
                    rivalBoard[i][j].setText("F");
                    rivalBoard[i][j].setBackground(Color.GRAY);
                    rivalBoard[i][j].setEnabled(false);
                }
                
                // Modo Tutorial: Ver barcos enemigos ocultos
                if (tutorial && !tableroEnemigo[i][j].equals("~") && !tableroEnemigo[i][j].equals("X")) {
                    rivalBoard[i][j].setText("(" + tableroEnemigo[i][j] + ")");
                }
            }
        }
    }

    private void disparar(int fila, int col, JButton btn) {
        String resultado = game.bombardear(turnoJugador(), fila, col);
        
        if (resultado.equals("N")) return; // Celda ya atacada

        switch (resultado) {
            case "F": 
                btn.setText("AGUA");
                btn.setBackground(Color.GRAY);
                JOptionPane.showMessageDialog(this, "¡Fallaste! Cambio de turno.");
                cambiarTurnoVisual();
                break;
            case "X": 
                btn.setText("IMPACTO");
                btn.setBackground(Color.RED);
                // Si impactas, usualmente sigues disparando (según reglas estándar)
                break;
            case "H":
                btn.setText("HUNDIDO");
                btn.setBackground(new Color(100, 0, 0));
                JOptionPane.showMessageDialog(this, "¡ESTRATEGIA COMPLETADA! Has hundido una nave enemiga.");
                break;
        }

        btn.setEnabled(false);
        actualizarCantNaves();
        
        if (game.hayGanador()) {
            Player ganador = game.getGanador();
            ganador.agregarPuntos(3);
            JOptionPane.showMessageDialog(this, "¡VICTORIA PARA " + ganador.getUsername() + "!");
            new Winner(ganador.getUsername(), 3).setVisible(true);
            this.dispose();
        }
    }

    private void cambiarTurnoVisual() {
        this.dispose(); // Cerramos la ventana actual
        // Abrimos la del siguiente jugador
        new BattleshipBoard(game, rivalActual, jugadorActual, tutorial);
    }

    private int turnoJugador() {
        return (jugadorActual == game.getPlayer1()) ? 1 : 2;
    }

    private void actualizarCantNaves() {
        int restantes = (rivalActual == game.getPlayer1()) ? game.getBarcosVivosP1() : game.getBarcosVivosP2();
        txtCant.setText(String.valueOf(restantes));
    }
}