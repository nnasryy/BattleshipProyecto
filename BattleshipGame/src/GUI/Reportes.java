/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUIWarnings.CambiarPassword;
import GUIWarnings.CambiarUsername;
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
public class Reportes extends JFrame {
    
    private Battleship game;
    private JPanel jPanel3;
    private JButton jButton1, jButton2, jButton3;
    private JLabel jLabel1, jLabel5;

    public Reportes(Battleship game) {
        this.game = game;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(780, 590);
        setLayout(null);
        setResizable(false);

        // --- PANEL DE OPCIONES ---
        jPanel3 = new JPanel();
        jPanel3.setBackground(new Color(0, 0, 0));
        jPanel3.setBorder(BorderFactory.createBevelBorder(
                BevelBorder.RAISED, null, null,
                new Color(0, 255, 51), new Color(0, 255, 0)));
        jPanel3.setLayout(null);
        jPanel3.setBounds(120, 90, 520, 430);

        // Título
        jLabel5 = new JLabel("reportes");
        jLabel5.setFont(new Font("Inlanders", Font.PLAIN, 36));
        jLabel5.setForeground(new Color(0, 255, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setBounds(80, 30, 370, 70);
        jPanel3.add(jLabel5);

        // Botones
        jButton3 = createNeonButton("descripcion de mis ultimos diez juegos");
        jButton3.setBounds(20, 120, 460, 60);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel3.add(jButton3);

        jButton1 = createNeonButton("ranking de jugadores");
        jButton1.setBounds(20, 210, 460, 50);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel3.add(jButton1);

        jButton2 = createNeonButton("salir");
        jButton2.setBounds(20, 360, 200, 50);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel3.add(jButton2);
        add(jPanel3);
        
         // --- IMAGEN DE FONDO ---
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        jLabel1.setBounds(0, 0, 780, 590);
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

    // --- ACCIONES DE BOTONES ---
    
    // Ranking
    private void jButton1ActionPerformed(ActionEvent evt) {
        // CORRECCIÓN: Pasar 'game' para no perder la sesión
        new Ranking(this.game).setVisible(true); 
        this.dispose();
    }

    // Salir (vuelve al menú)
    private void jButton2ActionPerformed(ActionEvent evt) {
        new MenuPrincipal(this.game);
        this.dispose();
    }

    // Últimos 10 juegos
    private void jButton3ActionPerformed(ActionEvent evt) {
        // CORRECCIÓN: Asegurarse de pasar 'this.game'
        new Ultimos10Juegos(this.game).setVisible(true);
        this.dispose();
    }
    
}