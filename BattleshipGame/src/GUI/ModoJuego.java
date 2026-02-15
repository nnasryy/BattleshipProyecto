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

public class ModoJuego extends JFrame {

    private JPanel jPanel1;
    private JButton jButton1, jButton2, jButton3;
    private JLabel jLabel1, jLabel3;
    private Battleship game;

    public ModoJuego(Battleship game) {
        this.game = game;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(780, 590);
        setLayout(null);
        setResizable(false);

        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 51), new Color(0, 255, 0)));
        jPanel1.setLayout(null);
        jPanel1.setBounds(120, 90, 520, 430);

        jLabel3 = new JLabel("modo de juego");
        jLabel3.setFont(new Font("Inlanders", Font.PLAIN, 52));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBounds(70, 30, 370, 70);
        jPanel1.add(jLabel3);

        // Botón: ARCADE
        jButton3 = createNeonButton("arcade");
        jButton3.setBounds(150, 120, 200, 60);
        jButton3.addActionListener(e -> seleccionarModo(false));
        jPanel1.add(jButton3);

        // Botón: TUTORIAL
        jButton1 = createNeonButton("tutorial");
        jButton1.setBounds(150, 210, 200, 60);
        jButton1.addActionListener(e -> seleccionarModo(true));
        jPanel1.add(jButton1);

        // Botón: SALIR
        jButton2 = createNeonButton("salir");
        jButton2.setBounds(150, 300, 200, 50);
        jButton2.addActionListener(e -> regresar());
        jPanel1.add(jButton2);

        add(jPanel1);

        jLabel1 = new JLabel();
        try {
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        } catch (Exception e) {
            jLabel1.setOpaque(true);
            jLabel1.setBackground(Color.DARK_GRAY);
        }
        jLabel1.setBounds(0, 0, 760, 590);
        add(jLabel1);
    }

    private JButton createNeonButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(34, 33, 33));
        btn.setFont(new Font("Inlanders", Font.PLAIN, 24));
        btn.setForeground(new Color(0, 204, 0));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));
        return btn;
    }

    private void seleccionarModo(boolean esTutorial) {
        // Asegúrate de tener este método en tu clase Battleship
        game.setModoTutorial(esTutorial);
        regresar();
    }

    private void regresar() {
        new Configuracion(game).setVisible(true);
        this.dispose();
    }
}