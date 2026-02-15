/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUI.MenuPrincipal;
import GUI.Ranking;
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
public class Reportes extends JFrame{
    
    private JPanel jPanel1;
    private JButton jButton1, jButton2, jButton3;
    private JLabel jLabel1, jLabel3;
private Battleship game;

    public Reportes(Battleship game) {
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

        // --- PANEL CENTRAL (jPanel1) ---
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, 
                null, null, new Color(0, 255, 51), new Color(0, 255, 0)));
        jPanel1.setLayout(null);
        jPanel1.setBounds(120, 90, 520, 430);

        // Título "reportes"
        jLabel3 = new JLabel("reportes");
        jLabel3.setFont(new Font("Inlanders", Font.PLAIN, 52));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setBounds(140, 50, 250, 70);
        jPanel1.add(jLabel3);

        // Botón: descripción de mis últimos 10 juegos (jButton3)
        jButton3 = createNeonButton("descripcion de mis ultimos 10 juegos", "Cold Warm", 24);
        jButton3.setBounds(20, 150, 460, 60);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel1.add(jButton3);

        // Botón: ranking de jugadores (jButton1)
        jButton1 = createNeonButton("ranking de jugadores", "Cold Warm", 24);
        jButton1.setBounds(20, 230, 460, 50);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel1.add(jButton1);

        // Botón: salir (jButton2)
        jButton2 = createNeonButton("salir", "Inlanders", 24);
        jButton2.setBounds(20, 360, 200, 50);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel1.add(jButton2);

        add(jPanel1);

        // --- IMAGEN DE FONDO (jLabel1) ---
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        jLabel1.setBounds(0, 0, 770, 590);
        add(jLabel1);
    }

    private JButton createNeonButton(String text, String fontName, int fontSize) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(34, 33, 33));
        btn.setFont(new Font(fontName, Font.PLAIN, fontSize));
        btn.setForeground(new Color(0, 204, 0));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, 
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));
        return btn;
    }

    // --- ACCIONES DE LOS BOTONES ---
    private void jButton1ActionPerformed(ActionEvent evt) {
      new Ranking().setVisible(true);
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        new MenuPrincipal(game);
        this.dispose(); 
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        // Lógica para ver historial de juegos
    }
}
