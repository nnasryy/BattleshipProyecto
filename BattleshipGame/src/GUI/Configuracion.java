package GUI;

import battleshipgame.Battleship;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author nasry
 */
public class Configuracion extends JFrame {

    private JPanel jPanel1;
    private JButton jButton1; // Modo de juego
    private JButton jButton2; // Salir
    private JButton jButton3; // Dificultad
    private JLabel jLabel1;   // Fondo
    private JLabel jLabel3;   // Título

    private Battleship game;

    public Configuracion(Battleship game) {
        this.game = game;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(780, 590);
        setLayout(null);
        setResizable(false);

        // --- PANEL CENTRAL ---
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 51), new Color(0, 255, 0)));
        jPanel1.setLayout(null);
        jPanel1.setBounds(120, 90, 520, 430);

        jLabel3 = new JLabel("configuracion");
        jLabel3.setFont(new Font("Cold Warm", Font.PLAIN, 52));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setBounds(70, 30, 370, 70);
        jPanel1.add(jLabel3);

        // Botón: Dificultad
        jButton3 = createStyledButton("dificultad");
        jButton3.setBounds(150, 120, 200, 60);
        jButton3.addActionListener(e -> jButton3ActionPerformed(e));
        jPanel1.add(jButton3);

        // Botón: Modo de juego
        jButton1 = createStyledButton("modo de juego");
        jButton1.setBounds(150, 210, 200, 60);
        jButton1.addActionListener(e -> jButton1ActionPerformed(e));
        jPanel1.add(jButton1);

        // Botón: Salir (Volver al Menú)
        jButton2 = createStyledButton("salir");
        jButton2.setBounds(150, 300, 200, 50);
        jButton2.addActionListener(e -> jButton2ActionPerformed(e));
        jPanel1.add(jButton2);

        add(jPanel1);

        // --- FONDO ---
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        jLabel1.setBounds(0, 0, 780, 590);
        add(jLabel1);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(34, 33, 33));
        btn.setFont(new Font("Inlanders", Font.PLAIN, 24));
        btn.setForeground(new Color(0, 204, 0));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));
        return btn;
    }

    // --- EVENTOS ---
    private void jButton1ActionPerformed(ActionEvent evt) {
        // CORREGIDO: Se pasa 'game' y se cierra la ventana actual
        new ModoJuego(game).setVisible(true);
        this.dispose();
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        new MenuPrincipal(game).setVisible(true);
        this.dispose();
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        // CORREGIDO: Se pasa 'game'
        new Dificultad(game).setVisible(true);
        this.dispose();
    }
}