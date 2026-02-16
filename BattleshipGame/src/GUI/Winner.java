package GUI;

import battleshipgame.Battleship;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class Winner extends JFrame {

    private JLabel lblFelicidades, lblMensaje, backgroundLabel;
    private JPanel panelMensaje;
    private Battleship game;

    public Winner(String winnerName, int puntos, Battleship game) {
        this.game = game;
        initComponents();
        setWinnerData(winnerName, puntos); 
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 650);
        setLayout(null);
        setResizable(false);

        // --- TÍTULO "FELICIDADES" ---
        lblFelicidades = new JLabel();
        try {
            lblFelicidades.setIcon(new ImageIcon(getClass().getResource("/Images/¡FELICIDADES!.png")));
        } catch (Exception e) {
            lblFelicidades.setText("¡FELICIDADES!");
            lblFelicidades.setFont(new Font("Capture it", Font.BOLD, 48));
            lblFelicidades.setForeground(Color.WHITE);
        }
        lblFelicidades.setBounds(90, 180, 620, 110);
        lblFelicidades.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblFelicidades);

        // --- PANEL DE MENSAJE ---
        panelMensaje = new JPanel();
        panelMensaje.setBackground(new Color(17, 25, 48));
        panelMensaje.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, null, new Color(0, 0, 51), new Color(0, 0, 51)));
        panelMensaje.setLayout(null);
        panelMensaje.setBounds(80, 300, 640, 70);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Capture it", Font.PLAIN, 32));
        lblMensaje.setForeground(Color.WHITE);
        lblMensaje.setBounds(30, 15, 580, 40);
        panelMensaje.add(lblMensaje);

        add(panelMensaje);

        // --- BOTÓN ATRÁS ---
        JButton btnAtras = new JButton("ATRÁS");
        btnAtras.setBackground(new Color(102, 0, 0));
        btnAtras.setFont(new Font("Capture it", Font.PLAIN, 36));
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setFocusPainted(false);
        btnAtras.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(153, 0, 0), new Color(153, 0, 0)));
        btnAtras.setBounds(250, 390, 300, 80);
        btnAtras.addActionListener(this::volverMenuPrincipal);
        add(btnAtras);

        // --- FONDO ---
        backgroundLabel = new JLabel();
        try {
            ImageIcon bgIcon = new ImageIcon(getClass().getResource("/Images/image.png"));
            Image img = bgIcon.getImage().getScaledInstance(800, 650, Image.SCALE_SMOOTH);
            backgroundLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            backgroundLabel.setBackground(Color.DARK_GRAY);
            backgroundLabel.setOpaque(true);
        }
        backgroundLabel.setBounds(0, 0, 800, 650);
        
   
        add(backgroundLabel);
        
    
    }

    public void setWinnerData(String name, int puntos) {
        lblMensaje.setText(name + " HA GANADO " + puntos + " PUNTOS");
    }

    private void volverMenuPrincipal(ActionEvent evt) {
        this.dispose();
        new MenuPrincipal(this.game).setVisible(true);
    }
}