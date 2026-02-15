/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class Dificultad extends JFrame{
    
    private JPanel jPanel1;
    private JButton jButton1, jButton2, jButton3, jButton4, jButton5;
    private JLabel jLabel1, jLabel3;
    private Battleship game;

    public Dificultad(Battleship game) {
        this.game=game;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Configuración del JFrame
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(780, 590);
        setLayout(null);
        setResizable(false);

        // --- PANEL NEGRO (jPanel1) ---
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 51), new Color(0, 255, 0)));
        jPanel1.setLayout(null);
        jPanel1.setBounds(120, 90, 520, 430);

        // Título "Dificultad"
        jLabel3 = new JLabel("Dificultad");
        jLabel3.setFont(new Font("Inlanders", Font.PLAIN, 52));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setBounds(120, 30, 270, 70);
        jPanel1.add(jLabel3);

        // Botón: Easy - cinco barcos (jButton3)
        jButton3 = createNeonButton("Easy - cinco barcos");
        jButton3.setBounds(100, 110, 300, 40);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel1.add(jButton3);

        // Botón: normal - cuatro barcos (jButton4)
        jButton4 = createNeonButton("normal - cuatro barcos");
        jButton4.setBounds(100, 170, 300, 40);
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel1.add(jButton4);

        // Botón: expert - dos barcos (jButton5)
        // En tu swing original el alto era -1 (automático), aquí ponemos 40 por consistencia
        jButton5 = createNeonButton("expert - dos barcos");
        jButton5.setBounds(100, 237, 300, 40);
        jButton5.addActionListener(this::jButton5ActionPerformed);
        jPanel1.add(jButton5);

        // Botón: genius - un barco (jButton1)
        jButton1 = createNeonButton("genius - un barco");
        jButton1.setBounds(100, 290, 300, 40);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel1.add(jButton1);

        // Botón: salir (jButton2)
        jButton2 = createNeonButton("salir");
        jButton2.setBounds(20, 370, 160, 50);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel1.add(jButton2);

        // Agregar panel al frame
        add(jPanel1);

        // --- FONDO (jLabel1) ---
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        jLabel1.setBounds(0, 0, 780, 590);
        add(jLabel1);
    }

    // Método para mantener el estilo neón uniforme
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

    // --- MANEJADORES DE EVENTOS ---
    private void jButton1ActionPerformed(ActionEvent evt) {
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        new MenuPrincipal(game);
        this.dispose();
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
    }

    private void jButton5ActionPerformed(ActionEvent evt) {
    }

    
}
