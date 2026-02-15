/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import ENUM.DificultadJuego; // Asegúrate de que el Enum se llame así
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

public class Dificultad extends JFrame {

    private JPanel jPanel1;
    private JButton jButton1, jButton2, jButton3, jButton4, jButton5;
    private JLabel jLabel1, jLabel3;
    private Battleship game;

    public Dificultad(Battleship game) {
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

        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 51), new Color(0, 255, 0)));
        jPanel1.setLayout(null);
        jPanel1.setBounds(120, 90, 520, 430);

        jLabel3 = new JLabel("Dificultad");
        jLabel3.setFont(new Font("Inlanders", Font.PLAIN, 52));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setBounds(120, 30, 270, 70);
        jPanel1.add(jLabel3);

        // EASY - 5 Barcos
        jButton3 = createNeonButton("Easy - cinco barcos");
        jButton3.setBounds(100, 110, 300, 40);
        jButton3.addActionListener(e -> seleccionarDificultad(DificultadJuego.EASY));
        jPanel1.add(jButton3);

        // NORMAL - 4 Barcos
        jButton4 = createNeonButton("normal - cuatro barcos");
        jButton4.setBounds(100, 170, 300, 40);
        jButton4.addActionListener(e -> seleccionarDificultad(DificultadJuego.NORMAL));
        jPanel1.add(jButton4);

        // EXPERT - 2 Barcos
        jButton5 = createNeonButton("expert - dos barcos");
        jButton5.setBounds(100, 237, 300, 40);
        jButton5.addActionListener(e -> seleccionarDificultad(DificultadJuego.EXPERT));
        jPanel1.add(jButton5);

        // GENIUS - 1 Barco
        jButton1 = createNeonButton("genius - un barco");
        jButton1.setBounds(100, 290, 300, 40);
        jButton1.addActionListener(e -> seleccionarDificultad(DificultadJuego.GENIUS));
        jPanel1.add(jButton1);

        // SALIR
        jButton2 = createNeonButton("salir");
        jButton2.setBounds(20, 370, 160, 50);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel1.add(jButton2);

        add(jPanel1);

        jLabel1 = new JLabel();
        try {
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        } catch (Exception e) {
            jLabel1.setOpaque(true);
            jLabel1.setBackground(Color.DARK_GRAY);
        }
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

    // Método unificado para cambiar dificultad y salir
    private void seleccionarDificultad(DificultadJuego dif) {
        // Asumiendo que Battleship tiene un método para recibir el Enum
        // Si no, puedes pasar directamente el entero: game.setDificultad(dif.getCantidadBarcos());
        game.setDificultad(dif); 
        regresarAlMenu();
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        regresarAlMenu();
    }
    
    private void regresarAlMenu() {
        // Según tu instrucción, vuelve al SUB MENU DE CONFIGURACION
        new Configuracion(game).setVisible(true); 
        this.dispose();
    }
}