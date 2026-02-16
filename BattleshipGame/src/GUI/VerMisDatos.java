/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import battleshipgame.Battleship;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author nasry
 */
public class VerMisDatos extends JFrame{
    
    private JPanel jPanel1;
    private JButton jButton2;
    private JLabel jLabel1, jLabel3, jLabel4, jLabel5;
    private JTextField jTextField1, jTextField2;
    private Battleship game;

    public VerMisDatos(Battleship game) {
        this.game = game;

        setupFrame();
        setVisible(true);
        initComponents();

        cargarDatos();

        this.revalidate();
        this.repaint();
    }

    private void setupFrame() {
        setTitle("Battleship - Mis Datos");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(780, 590);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void cargarDatos() {
        if (game != null && game.getCurrentUser() != null) {
            String user = game.getCurrentUser().getUsername();
            String pass = game.getCurrentUser().getPassword();

            jTextField1.setText(user);
            jTextField2.setText(pass);

        } else {
            jTextField1.setText("ERROR: No User");
            jTextField2.setText("ERROR: No Pass");
  
        }
    }

    private void initComponents() {
   
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0, 200)); 
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 51), new Color(0, 255, 0)));
        jPanel1.setLayout(null);
        jPanel1.setBounds(120, 60, 520, 430);


        jLabel4 = new JLabel("VER MIS DATOS", SwingConstants.CENTER);
        jLabel4.setFont(new Font("Inlanders", Font.PLAIN, 45));
        jLabel4.setForeground(new Color(0, 255, 0));
        jLabel4.setBounds(50, 30, 420, 60);
        jPanel1.add(jLabel4);

 
        jLabel5 = new JLabel("USERNAME:");
        jLabel5.setFont(new Font("Inlanders", Font.PLAIN, 28));
        jLabel5.setForeground(new Color(0, 255, 0));
        jLabel5.setBounds(60, 110, 400, 40);
        jPanel1.add(jLabel5);

        jTextField1 = createStyledTextField();
        jTextField1.setBounds(60, 155, 400, 35);
        jPanel1.add(jTextField1);

    
        jLabel3 = new JLabel("PASSWORD:");
        jLabel3.setFont(new Font("Inlanders", Font.PLAIN, 28));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setBounds(60, 210, 400, 40);
        jPanel1.add(jLabel3);

        jTextField2 = createStyledTextField();
        jTextField2.setBounds(60, 255, 400, 35);
        jPanel1.add(jTextField2);

        jButton2 = new JButton("VOLVER");
        jButton2.setBackground(new Color(20, 20, 20));
        jButton2.setFont(new Font("Inlanders", Font.PLAIN, 20));
        jButton2.setForeground(new Color(0, 255, 0));
        jButton2.setFocusPainted(false);
        jButton2.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 2));
        jButton2.setBounds(160, 350, 200, 45);
        jButton2.addActionListener(e -> {
            new MiPerfil(this.game).setVisible(true);
            this.dispose();
        });
        jPanel1.add(jButton2);

 
        add(jPanel1);


        jLabel1 = new JLabel();
        try {
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        } catch (Exception e) {
            System.out.println("Imagen no encontrada, usando color sólido.");
            jLabel1.setOpaque(true);
            jLabel1.setBackground(Color.DARK_GRAY);
        }
        jLabel1.setBounds(0, 0, 780, 590);
        add(jLabel1);
    }

    private JTextField createStyledTextField() {
        JTextField txt = new JTextField();
        txt.setEditable(false); 
        txt.setBackground(new Color(30, 30, 30));
        txt.setFont(new Font("Monospaced", Font.BOLD, 22));
        txt.setForeground(new Color(0, 255, 51));
        txt.setCaretColor(new Color(0, 255, 51));
        txt.setHorizontalAlignment(JTextField.CENTER);
        txt.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 0), 1));
        return txt;
    }
}
