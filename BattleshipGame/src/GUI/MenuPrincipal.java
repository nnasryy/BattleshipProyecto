/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUIWarnings.JugarContraTiMismo;
import GUIWarnings.Menosde2Players;
import GUIWarnings.PedirPlayer2;
import GUIWarnings.PlayerNotFound;
import battleshipgame.Battleship;
import battleshipgame.Player;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author nasry
 */
public class MenuPrincipal extends JFrame {

    // Ahora usamos el objeto game como motor principal
    private battleshipgame.Battleship game;

    // Cambiamos el constructor para recibir el objeto game
    public MenuPrincipal(Battleship game) {
        this.game = game;

        setTitle("Menú Principal - Battleship");
        setSize(1330, 780);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        initComponents();

        setVisible(true);
    }

    private JButton btnCerrarSesion;
    private JButton btnBattleship;
    private JButton btnConfiguracion;
    private JButton btnReportes;
    private JButton btnPerfil;
    private JLabel fondo;

    private void initComponents() {
        Font fontBotones = new Font("Capture it", Font.BOLD, 36);
        Color fondoBtn = new Color(18, 40, 61);
        Color textoBtn = new Color(186, 215, 241);

        // 1. Botón BATTLESHIP
        btnBattleship = crearBoton("BATTLESHIP", fontBotones, fondoBtn, textoBtn, 450, 280, 410, 60);
        btnBattleship.addActionListener(e -> {
            if (Player.getTotalPlayers() < 2) {
                new Menosde2Players();
                return;
            }

            PedirPlayer2 dialog = new PedirPlayer2(this);
            dialog.setVisible(true);

            String usernameRival = dialog.getUsernameIngresado();
            if (usernameRival == null || usernameRival.trim().isEmpty()) {
                return;
            }

            Player rival = Player.getPlayerByUsername(usernameRival.trim());

            if (rival == null) {
                new PlayerNotFound();
                return;
            }

            // Usamos game.getCurrentUser() para validar
            if (rival.getUsername().equals(game.getCurrentUser().getUsername())) {
                new JugarContraTiMismo();
                return;
            }

            // Iniciamos partida usando el objeto game que ya tenemos
            game.iniciarPartida(game.getCurrentUser(), rival);
            new PickerNave(this.game, this.game.getPlayer1());
            this.dispose();
        });

        // 2. Botón CONFIGURACIÓN
        btnConfiguracion = crearBoton("CONFIGURACIÓN", fontBotones, fondoBtn, textoBtn, 450, 360, 410, 60);
        btnConfiguracion.addActionListener(e -> {
            // Aquí también deberías pasar 'game' si la configuración lo requiere
            new Configuracion(this.game).setVisible(true);
            this.dispose();
        });

        // 3. Botón REPORTES
        btnReportes = crearBoton("REPORTES", fontBotones, fondoBtn, textoBtn, 450, 440, 410, 60);
        btnReportes.addActionListener(e -> {
            new Reportes(this.game).setVisible(true);
            this.dispose();
        });

        // 4. Botón MI PERFIL (¡AQUÍ ESTÁ EL CAMBIO!)
        // Dentro de MenuPrincipal.java
        btnPerfil = crearBoton("MI PERFIL", fontBotones, fondoBtn, textoBtn, 450, 520, 410, 60);
        btnPerfil.addActionListener(e -> {
            // CAMBIO: Ahora llama a MiPerfil, no a VerMisDatos directamente
            new MiPerfil(this.game);
            this.dispose();
        });

        // 5. Botón CERRAR SESIÓN
        btnCerrarSesion = crearBoton("CERRAR SESIÓN", fontBotones, fondoBtn, textoBtn, 450, 600, 410, 60);
        btnCerrarSesion.addActionListener(e -> {
            new MenuInicio();
            this.dispose();
        });

        try {
            fondo = new JLabel(new ImageIcon(getClass().getResource("/Images/MenuPrincipal.png")));
            fondo.setBounds(0, 0, 1330, 780);
            add(fondo);
        } catch (Exception e) {
            getContentPane().setBackground(new Color(10, 20, 30));
        }
    }

    private JButton crearBoton(String texto, Font font, Color bg, Color fg, int x, int y, int w, int h) {
        JButton btn = new JButton(texto);
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createBevelBorder(1));
        btn.setBounds(x, y, w, h);
        add(btn);
        return btn;
    }
}
