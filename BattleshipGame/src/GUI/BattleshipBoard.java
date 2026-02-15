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
    }

    private void initComponentsManual() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        int btnWidth = 110;
        int btnHeight = 65;
        int gap = 1;
        int boardWidth = (btnWidth * 8) + (gap * 7);
        int boardHeight = (btnHeight * 8) + (gap * 7);
        int totalContentW = (boardWidth * 2) + 70;

        GraphicsConfiguration gc = getGraphicsConfiguration();
        Rectangle bounds = gc.getBounds();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

        int effectiveWidth = bounds.width - screenInsets.left - screenInsets.right;
        int effectiveHeight = bounds.height - screenInsets.top - screenInsets.bottom;

        int startX = (effectiveWidth - totalContentW) / 2;
        int startY = ((effectiveHeight - boardHeight) / 2) + 140;

        // --- PANEL DE INFORMACIÓN ---
        int infoPanelW = 310;
        jPanelInfo = new JPanel();
        jPanelInfo.setBackground(new Color(200, 191, 208, 180));
        jPanelInfo.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        jPanelInfo.setLayout(null);
        jPanelInfo.setBounds(effectiveWidth - infoPanelW - 20, 20, infoPanelW, 350);

        JLabel lblTipoTitulo = new JLabel("MODO DE JUEGO:");
        lblTipoTitulo.setFont(new Font("Capture it", 1, 18));
        lblTipoTitulo.setBounds(0, 15, 310, 25);
        lblTipoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jPanelInfo.add(lblTipoTitulo);

        String modoTexto = tutorial ? "TUTORIAL" : "NORMAL";
        JLabel lblModoValor = new JLabel(modoTexto);
        lblModoValor.setFont(new Font("Capture it", 0, 20));
        lblModoValor.setOpaque(true);
        lblModoValor.setHorizontalAlignment(SwingConstants.CENTER);
        lblModoValor.setBounds(40, 45, 230, 35);
        jPanelInfo.add(lblModoValor);

        txtCant = new JTextField();
        txtCant.setBounds(45, 160, 220, 160);
        txtCant.setEditable(false);
        txtCant.setHorizontalAlignment(JTextField.CENTER);
        jPanelInfo.add(txtCant);
        add(jPanelInfo);

        // --- TABLERO JUGADOR ---
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton();
                btn.setBounds(startX + (j * (btnWidth + gap)), startY + (i * (btnHeight + gap)), btnWidth, btnHeight);
                playerBoard[i][j] = btn;
                add(btn);
            }
        }

        // --- TABLERO RIVAL ---
        int rivalStartX = startX + boardWidth + 70;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton();
                btn.setBounds(rivalStartX + (j * (btnWidth + gap)), startY + (i * (btnHeight + gap)), btnWidth, btnHeight);
                rivalBoard[i][j] = btn;
                final int fila = i;
                final int col = j;
                btn.addActionListener(e -> disparar(fila, col, btn));
                add(btn);
            }
        }

        // --- NOMBRE JUGADOR ---
        JLabel lblNombrePlayer = new JLabel(jugadorActual.getUsername());
        lblNombrePlayer.setFont(new Font("Capture it", 1, 70));
        lblNombrePlayer.setForeground(Color.WHITE);
        lblNombrePlayer.setBounds(510, 90, 970, 60);
        add(lblNombrePlayer);

        // --- BOTÓN RETIRAR ---
        JButton btnRetirar = new JButton("RETIRARSE");
        btnRetirar.setBounds(startX + boardWidth - 65, startY + boardHeight + 20, 200, 50);
        btnRetirar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Retirarse?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
        add(btnRetirar);

        // --- FONDO ---
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, effectiveWidth, effectiveHeight);
        add(backgroundLabel);
    } // AQUÍ TERMINA EL MÉTODO INIT

    private void cargarTableros() {
        String[][] miTablero = (jugadorActual == game.getPlayer1()) ? game.getTableroP1() : game.getTableroP2();
        String[][] tableroEnemigo = (jugadorActual == game.getPlayer1()) ? game.getTableroP2() : game.getTableroP1();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!miTablero[i][j].equals("~")) {
                    playerBoard[i][j].setText(miTablero[i][j]);
                }
                if (tutorial && !tableroEnemigo[i][j].equals("~")) {
                    rivalBoard[i][j].setText(tableroEnemigo[i][j]);
                }
            }
        }
    }

   private void disparar(int fila, int col, JButton btn) {
        String resultado = game.bombardear(turnoJugador(), fila, col);
        switch (resultado) {
            case "F": btn.setText("F"); break;
            case "X": btn.setText("X"); break;
            case "H":
                btn.setText("H");
                btn.setEnabled(false);
                JOptionPane.showMessageDialog(this, "¡Hundiste un barco!");
                break;
        }
        actualizarCantNaves();
        if (game.hayGanador()) {
            Player ganador = game.getGanador();
            ganador.agregarPuntos(3);
            new Winner(ganador.getUsername(), 3).setVisible(true);
            dispose();
        }
    }

    private int contarNavesRivalRestantes() {
        int count = 0;
        int[] vidas = (rivalActual == game.getPlayer1()) ? game.getVidasP1() : game.getVidasP2();
        for (int v : vidas) {
            if (v > 0) count++;
        }
        return count;
    }
    
    private int turnoJugador() {
        return (jugadorActual == game.getPlayer1()) ? 1 : 2;
    }
    private void actualizarCantNaves() {
    int restantes = contarNavesRivalRestantes();
    txtCant.setText(String.valueOf(restantes));
}
}