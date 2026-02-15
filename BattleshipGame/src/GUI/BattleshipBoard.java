package GUI;

import ENUM.TipoBarco;
import battleshipgame.Battleship;
import battleshipgame.Player;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
import org.w3c.dom.events.EventException;

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

        // --- PANEL INFO ---
        int infoPanelW = 310;
        jPanelInfo = new JPanel();
        jPanelInfo.setBackground(new Color(200, 191, 208, 180));
        jPanelInfo.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        jPanelInfo.setLayout(null);
        jPanelInfo.setBounds(effectiveWidth - infoPanelW - 20, 20, infoPanelW, 350);

        JLabel lblTipoTitulo = new JLabel("MODO DE JUEGO:");
        lblTipoTitulo.setFont(new Font("Capture it", 1, 18));
        lblTipoTitulo.setForeground(new Color(0, 29, 49));
        lblTipoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoTitulo.setBounds(0, 15, 310, 25);
        jPanelInfo.add(lblTipoTitulo);

        String modoTexto = tutorial ? "TUTORIAL" : "NORMAL";
        JLabel lblModoValor = new JLabel(modoTexto);
        lblModoValor.setFont(new Font("Capture it", 0, 20));
        lblModoValor.setForeground(Color.WHITE);
        lblModoValor.setBackground(new Color(0, 29, 49));
        lblModoValor.setOpaque(true);
        lblModoValor.setHorizontalAlignment(SwingConstants.CENTER);
        lblModoValor.setBounds(40, 45, 230, 35);
        jPanelInfo.add(lblModoValor);

        JLabel lblNavesTexto1 = new JLabel("NAVES DEL RIVAL");
        lblNavesTexto1.setFont(new Font("Capture it", 1, 18));
        lblNavesTexto1.setForeground(new Color(0, 29, 49));
        lblNavesTexto1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNavesTexto1.setBounds(0, 100, 310, 25);
        jPanelInfo.add(lblNavesTexto1);

        JLabel lblNavesTexto2 = new JLabel("RESTANTES:");
        lblNavesTexto2.setFont(new Font("Capture it", 1, 18));
        lblNavesTexto2.setForeground(new Color(0, 29, 49));
        lblNavesTexto2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNavesTexto2.setBounds(0, 125, 310, 25);
        jPanelInfo.add(lblNavesTexto2);

        txtCant = new JTextField();
        txtCant.setBackground(new Color(0, 29, 49));
        txtCant.setForeground(Color.WHITE);
        txtCant.setFont(new Font("Capture it", 0, 90));
        txtCant.setHorizontalAlignment(JTextField.CENTER);
        txtCant.setBounds(45, 160, 220, 160);
        txtCant.setEditable(false);
        txtCant.setBorder(null);
        jPanelInfo.add(txtCant);

        add(jPanelInfo);

        // --- TABLERO JUGADOR ---
        JLabel lblJ = new JLabel("TU FLOTA");
        lblJ.setFont(new Font("Capture it", 1, 30));
        lblJ.setForeground(Color.CYAN);
        lblJ.setBounds(startX, startY - 50, 300, 40);
        add(lblJ);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton();
                btn.setBorder(BorderFactory.createLineBorder(new Color(168, 232, 255), 2));
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setBounds(startX + (j * (btnWidth + gap)), startY + (i * (btnHeight + gap)), btnWidth, btnHeight);
                playerBoard[i][j] = btn;
                add(btn);
            }
        }

        // --- TABLERO RIVAL ---
        int rivalStartX = startX + boardWidth + 70;
        JLabel lblR = new JLabel("FLOTA RIVAL");
        lblR.setFont(new Font("Capture it", 1, 30));
        lblR.setForeground(new Color(201, 2, 35));
        lblR.setBounds(rivalStartX, startY - 50, 300, 40);
        add(lblR);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton();
                btn.setBorder(BorderFactory.createLineBorder(new Color(201, 2, 35), 2));
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setBounds(rivalStartX + (j * (btnWidth + gap)), startY + (i * (btnHeight + gap)), btnWidth, btnHeight);
                rivalBoard[i][j] = btn;

                final int fila = i;
                final int col = j;
                btn.addActionListener(e -> disparar(fila, col, btn));

                add(btn);
            }
        }

        // --- LABEL NOMBRE Y RETIRARSE ---
        JLabel lblNombrePlayer = new JLabel(jugadorActual.getUsername());
        lblNombrePlayer.setFont(new Font("Capture it", 1, 70));
        lblNombrePlayer.setForeground(Color.WHITE);
        lblNombrePlayer.setBounds(510, 90, 970, 60);
        lblNombrePlayer.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblNombrePlayer);

        JButton btnRetirar = new JButton("RETIRARSE");
        int btnRetW = 200;
        int btnRetH = 50;
        btnRetirar.setFont(new Font("Capture it", 1, 32));
        btnRetirar.setForeground(Color.WHITE);
        btnRetirar.setBackground(new Color(103, 7, 7));
        btnRetirar.setBorder(BorderFactory.createLineBorder(Color.RED));
        int btnRetX = startX + boardWidth - (btnRetW / 2) + 35;
        int btnRetY = startY + boardHeight + 20;
        btnRetirar.setBounds(btnRetX, btnRetY, btnRetW, btnRetH);
        btnRetirar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Retirarse?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                rivalActual.agregarPuntos(3);
                new Winner(rivalActual.getUsername(), 3).setVisible(true);
                dispose();
            }
        });
        add(btnRetirar);

        // --- FONDO ---
        JLabel backgroundLabel = new JLabel();
        try {
            ImageIcon bgIcon = new ImageIcon(getClass().getResource("/Images/BattleShipbackground.png"));
            Image img = bgIcon.getImage().getScaledInstance(effectiveWidth, effectiveHeight, Image.SCALE_SMOOTH);
            backgroundLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.err.println("Fondo no encontrado.");
        }
        backgroundLabel.setBounds(0, 0, effectiveWidth, effectiveHeight);
        add(backgroundLabel);
    }

    // Método auxiliar imágenes
    private void establecerIcono(JButton btn, String codigo) {
        String ruta = "";
        switch (codigo) {
            case "PA":
                ruta = "/Images/portaavionesbrd.png";
                break;
            case "AZ":
                ruta = "/Images/acorazadobrd.png";
                break;
            case "SM":
                ruta = "/Images/submarinobrd.png";
                break;
            case "DT":
                ruta = "/Images/destructorbrd.png";
                break;
            case "F":
                ruta = "/Images/failedbrd.png";
                break;
            case "X":
                btn.setText("X");
                btn.setForeground(Color.RED);
                btn.setFont(new Font("OCR A Extended", Font.BOLD, 40));
                btn.setIcon(null); // Limpiar icono previo
                return;
            case "~":
                btn.setIcon(null);
                btn.setText("");
                return;
            default:
                btn.setText(codigo);
                return;
        }
        try {
            ImageIcon iconOriginal = new ImageIcon(getClass().getResource(ruta));
            Image imgEscalada = iconOriginal.getImage().getScaledInstance(110, 65, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(imgEscalada));
            btn.setText("");
        } catch (Exception e) {
            btn.setText(codigo);
        }
    }

    private void cargarTableros() {
        String[][] miTablero = (jugadorActual == game.getPlayer1()) ? game.getTableroP1() : game.getTableroP2();
        String[][] tableroEnemigo = (jugadorActual == game.getPlayer1()) ? game.getTableroP2() : game.getTableroP1();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                String valMio = miTablero[i][j];
                if (valMio.equals("F")) {

                    establecerIcono(playerBoard[i][j], "~");
                } else if (valMio.equals("X")) {
                    establecerIcono(playerBoard[i][j], "X");
                } else if (!valMio.equals("~")) {
                    establecerIcono(playerBoard[i][j], valMio);
                } else {
                    establecerIcono(playerBoard[i][j], "~");
                }

                String valRival = tableroEnemigo[i][j];
                if (valRival.equals("F")) {

                    establecerIcono(rivalBoard[i][j], "~");
                } else if (valRival.equals("X")) {
                    establecerIcono(rivalBoard[i][j], "X");
                } else if (tutorial && !valRival.equals("~")) {
                    establecerIcono(rivalBoard[i][j], valRival);
                } else {
                    establecerIcono(rivalBoard[i][j], "~");
                }
            }
        }
    }

    private TipoBarco obtenerBarcoPorCodigo(String codigo) {
        for (TipoBarco barco : TipoBarco.values()) {
            if (barco.getCodigo().equals(codigo)) {
                return barco;
            }
        }
        return null;
    }

    private void disparar(int fila, int col, JButton btn) {

        if (btn.getText() != null && !btn.getText().isEmpty()) {
            return;
        }

        String[][] tableroEnemigo = (jugadorActual == game.getPlayer1()) ? game.getTableroP2() : game.getTableroP1();
        String codigoBarcoGolpeado = tableroEnemigo[fila][col];

        int turnoActual = (jugadorActual == game.getPlayer1()) ? 1 : 2;
        String resultado = game.bombardear(turnoActual, fila, col);

        switch (resultado) {
            case "F":
                establecerIcono(btn, "F");
                Timer timer = new Timer(1000, e -> {
                    establecerIcono(btn, "~");
                });
                timer.setRepeats(false);
                timer.start();
                break;

            case "X":
                establecerIcono(btn, "X");

                TipoBarco barco = obtenerBarcoPorCodigo(codigoBarcoGolpeado);

                if (barco != null) {
                    int[] vidasRival = (rivalActual == game.getPlayer1()) ? game.getVidasP1() : game.getVidasP2();
                    int indiceBarco = barco.ordinal();
                    int vidasRestantes = vidasRival[indiceBarco];

                    JOptionPane.showMessageDialog(this,
                            "¡Bombardeaste un " + barco.getNombreCompleto() + "!\n"
                            + "Faltan " + vidasRestantes + " intentos para hundirlo.",
                            "Impacto Confirmado",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                break;

            case "N":
                return;

            default:
                establecerIcono(btn, resultado);
                TipoBarco barcoHundido = obtenerBarcoPorCodigo(resultado);

                if (barcoHundido != null) {
                    JOptionPane.showMessageDialog(this,
                            "¡Felicidades! Has hundido el " + barcoHundido.getNombreCompleto() + ".",
                            "Nave Destruida",
                            JOptionPane.WARNING_MESSAGE);
                }

                cargarTableros();
                break;
        }

        actualizarCantNaves();

        if (game.hayGanador()) {
            Player ganador = game.getGanador();
            ganador.agregarPuntos(3);
            new Winner(ganador.getUsername(), 3).setVisible(true);
            dispose();
        } else if (!resultado.equals("N")) {
            JOptionPane.showMessageDialog(this, "Turno de " + rivalActual.getUsername(), "Cambiar Jugador", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new BattleshipBoard(game, rivalActual, jugadorActual, tutorial).setVisible(true);
        }
    }

    private void actualizarCantNaves() {
        int restantes = contarNavesRivalRestantes();
        txtCant.setText(String.valueOf(restantes));
    }

    private int contarNavesRivalRestantes() {
        int count = 0;
        int[] vidas = (rivalActual == game.getPlayer1()) ? game.getVidasP1() : game.getVidasP2();
        for (int v : vidas) {
            if (v > 0) {
                count++;
            }
        }
        return count;
    }
}
