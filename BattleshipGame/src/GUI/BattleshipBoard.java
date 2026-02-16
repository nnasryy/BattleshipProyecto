package GUI;

import ENUM.TipoBarco;
import battleshipgame.Battleship;
import battleshipgame.Player;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class BattleshipBoard extends JFrame {

    private JButton[][] playerBoard = new JButton[8][8];
    private JButton[][] rivalBoard = new JButton[8][8];
    private JPanel jPanelInfo;
    private JTextField txtCant;
    private Battleship game;
    private Player jugadorActual;
    private Player rivalActual;
    private boolean tutorial;

    private JLabel lblIzq;
    private JLabel lblDer;
    private JLabel lblNombrePlayer;

    private final Color COLOR_CYAN = Color.CYAN;
    private final Color COLOR_ROJO = Color.RED;

    public BattleshipBoard(Battleship game, Player jugadorActual, Player rivalActual, boolean tutorial) {
        this.game = game;
        this.jugadorActual = jugadorActual;
        this.rivalActual = rivalActual;
        this.tutorial = tutorial;

        initComponentsManual();
        actualizarInterfazCompleta();
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

        String modoTexto = tutorial ? "TUTORIAL" : "ARCADE";
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

        // --- TABLERO IZQUIERDA (JUGADOR ACTUAL) ---
        lblIzq = new JLabel();
        lblIzq.setFont(new Font("Capture it", 1, 30));
        lblIzq.setForeground(COLOR_CYAN);
        lblIzq.setBounds(startX, startY - 50, 400, 40);
        add(lblIzq);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton();
                btn.setBorder(new LineBorder(COLOR_CYAN, 2));
                btn.setContentAreaFilled(false);
                btn.setFocusPainted(false);
                btn.setBounds(startX + (j * (btnWidth + gap)), startY + (i * (btnHeight + gap)), btnWidth, btnHeight);
                playerBoard[i][j] = btn;
                add(btn);
            }
        }

        // --- TABLERO DERECHA (RIVAL) ---
        int rivalStartX = startX + boardWidth + 70;
        lblDer = new JLabel();
        lblDer.setFont(new Font("Capture it", 1, 30));
        lblDer.setForeground(COLOR_ROJO);
        lblDer.setBounds(rivalStartX, startY - 50, 400, 40);
        add(lblDer);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton();
                btn.setBorder(new LineBorder(COLOR_ROJO, 2));
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

        // --- NOMBRE Y RETIRARSE ---
        lblNombrePlayer = new JLabel();
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
            new GUIWarnings.ConfirmarRetiro(game, jugadorActual, rivalActual, this).setVisible(true);
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

    private void actualizarInterfazCompleta() {
        lblIzq.setText("FLOTA " + jugadorActual.getUsername());
        lblDer.setText("FLOTA " + rivalActual.getUsername());
        lblNombrePlayer.setText(jugadorActual.getUsername());
        actualizarCantNaves();
        cargarTableros();
    }

    private void establecerIcono(JButton btn, String codigo) {
        String ruta = "";
        switch (codigo) {
            case "PA": ruta = "/Images/portaavionesbrd.png"; break;
            case "AZ": ruta = "/Images/acorazadobrd.png"; break;
            case "SM": ruta = "/Images/submarinobrd.png"; break;
            case "DT": ruta = "/Images/destructorbrd.png"; break;
            case "F": ruta = "/Images/failedbrd.png"; break; // Imagen temporal de fallo
            case "X":
                btn.setText("X");
                btn.setForeground(Color.RED);
                btn.setFont(new Font("OCR A Extended", Font.BOLD, 40));
                btn.setIcon(null);
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
                establecerIcono(playerBoard[i][j], valMio);

                String valRival = tableroEnemigo[i][j];
                // La matriz solo tiene "~" o Codigos de barco vivo.
                if ("~".equals(valRival)) {
                    establecerIcono(rivalBoard[i][j], "~");
                } else {
                    if (tutorial) {
                        establecerIcono(rivalBoard[i][j], valRival);
                    } else {
                        establecerIcono(rivalBoard[i][j], "~"); // Ocultar barco intacto
                    }
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
        int turnoActual = (jugadorActual == game.getPlayer1()) ? 1 : 2;
        String resultado = game.bombardear(turnoActual, fila, col);

        switch (resultado) {
            case "F":
                establecerIcono(btn, "F");
                new GUIWarnings.MensajeImpacto(this, "FALLO", "Agua... No habia nada.").setVisible(true);
                break;

            case "X":
                String nombre = game.getLastHitShipName();
                int restantes = game.getLastHitRemainingLives();
                new GUIWarnings.MensajeImpacto(this, "IMPACTO", "Has bombardeado un " + nombre + ". Faltan " + restantes + " bombardeos.").setVisible(true);
                cargarTableros();
                break;

            case "N":
                return;

            default: // HUNDIDO
                TipoBarco barcoHundido = obtenerBarcoPorCodigo(resultado);
                String nombreHundido = (barcoHundido != null) ? barcoHundido.getNombreCompleto() : resultado;
                new GUIWarnings.MensajeImpacto(this, "HUNDIDO", "Has destruido el " + nombreHundido + ".").setVisible(true);
                cargarTableros();
                break;
        }

        actualizarCantNaves();

        if (game.hayGanador()) {
            Player ganador = game.getGanador();
            Player perdedor = (ganador == game.getPlayer1()) ? game.getPlayer2() : game.getPlayer1();

            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            String modo = game.getDificultad().toString();

            String registro = fecha + " - " + ganador.getUsername() + " hundio todos los barcos de " + perdedor.getUsername() + " en modo " + modo + ".";

            ganador.agregarAlHistorial(registro);
            perdedor.agregarAlHistorial(registro);

            ganador.agregarPuntos(3);
            // Aquí el ganador se obtiene directamente del objeto game, así que es correcto
            new Winner(ganador.getUsername(), 3, game).setVisible(true);
            dispose();
        } else if (!resultado.equals("N")) {
            // CAMBIO DE TURNO
            Player temp = jugadorActual;
            jugadorActual = rivalActual;
            rivalActual = temp;

            // CORRECCIÓN: Se pasa 'jugadorActual' porque es el turno del NUEVO jugador
            new GUIWarnings.CambioTurno(this, jugadorActual.getUsername());
            
            actualizarInterfazCompleta();
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
            if (v > 0) count++;
        }
        return count;
    }
}