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
public class ModificarMisDatos extends JFrame{
    
    private Battleship game;
    private JPanel jPanel3;
    private JButton jButton3, jButton4, jButton5;
    private JLabel jLabel1, jLabel5;

    public ModificarMisDatos(Battleship game) {
        this.game = game;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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
        jLabel5 = new JLabel("modificar mis datos");
        jLabel5.setFont(new Font("Inlanders", Font.PLAIN, 36));
        jLabel5.setForeground(new Color(0, 255, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setBounds(80, 30, 370, 70);
        jPanel3.add(jLabel5);

        // Botones
        jButton5 = createNeonButton("cambiar username");
        jButton5.setBounds(120, 120, 280, 60);
        jButton5.addActionListener(this::jButton5ActionPerformed);
        jPanel3.add(jButton5);

        jButton3 = createNeonButton("cambiar password");
        jButton3.setBounds(116, 210, 280, 60);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel3.add(jButton3);

        jButton4 = createNeonButton("salir");
        jButton4.setBounds(150, 320, 200, 50);
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel3.add(jButton4);
        add(jPanel3);
        
         // --- IMAGEN DE FONDO ---
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        jLabel1.setBounds(0, 0, 780, 590);
        add(jLabel1);
    }

    // Estilo de botón consistente
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
    private void jButton3ActionPerformed(ActionEvent evt) {
        new CambiarPassword(this.game).setVisible(true);
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private void jButton5ActionPerformed(ActionEvent evt) {
        new CambiarUsername(this.game).setVisible(true);
    }
    
}
