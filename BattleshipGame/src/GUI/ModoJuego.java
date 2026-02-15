/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

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
public class ModoJuego extends JFrame{
    
    private JPanel jPanel1;
    private JButton jButton1, jButton2, jButton3;
    private JLabel jLabel1, jLabel3;

    public ModoJuego() {
        initComponents();
        this.setLocationRelativeTo(null); // Centra la ventana
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

        // Título "modo de juego"
        jLabel3 = new JLabel("modo de juego");
        jLabel3.setFont(new Font("Inlanders", Font.PLAIN, 52));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBounds(70, 30, 370, 70);
        jPanel1.add(jLabel3);

        // Botón: arcade (jButton3)
        jButton3 = createNeonButton("arcade");
        jButton3.setBounds(150, 120, 200, 60);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel1.add(jButton3);

        // Botón: tutorial (jButton1)
        jButton1 = createNeonButton("tutorial");
        jButton1.setBounds(150, 210, 200, 60);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel1.add(jButton1);

        // Botón: salir (jButton2)
        jButton2 = createNeonButton("salir");
        jButton2.setBounds(150, 300, 200, 50);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel1.add(jButton2);

        // Agregar panel principal al frame
        add(jPanel1);

        // --- IMAGEN DE FONDO (jLabel1) ---
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        jLabel1.setBounds(0, 0, 760, 590);
        add(jLabel1);
    }

    // Método auxiliar para mantener el estilo neón
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

    // --- MANEJO DE EVENTOS ---
    private void jButton1ActionPerformed(ActionEvent evt) {
        // Lógica para iniciar tutorial
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        // Lógica para cerrar o volver
        this.dispose();
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        // Lógica para modo arcade
    }
}
