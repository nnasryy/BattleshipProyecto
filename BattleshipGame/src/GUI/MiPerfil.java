/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUIWarnings.ConfirmarEliminar;
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
public class MiPerfil extends JFrame {

    private JPanel jPanel1;
    private JButton jButton1, jButton2, jButton3, jButton4;
    private JLabel jLabel1, jLabel3;
    private Battleship game;

    public MiPerfil(Battleship game) {
        this.game = game;
        initComponents();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(780, 590);
        setLayout(null);
        setResizable(false);

        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        jLabel1.setBounds(0, 0, 780, 590);
        add(jLabel1);

        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setBorder(BorderFactory.createBevelBorder(
                BevelBorder.RAISED,
                null, null,
                new Color(0, 255, 51),
                new Color(0, 255, 0)
        ));
        jPanel1.setLayout(null);
        jPanel1.setBounds(120, 90, 520, 430);

        jLabel3 = new JLabel("mi perfil");
        jLabel3.setFont(new Font("Inlanders", Font.PLAIN, 52));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setBounds(140, 40, 230, 70);
        jPanel1.add(jLabel3);

        jButton3 = createNeonButton("ver mis datos");
        jButton3.setBounds(120, 140, 260, 40);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jPanel1.add(jButton3);

        jButton1 = createNeonButton("modificar mis datos");
        jButton1.setBounds(120, 200, 260, 40);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanel1.add(jButton1);

        jButton4 = createNeonButton("eliminar cuenta");
        jButton4.setBounds(120, 260, 260, 40);
        jButton4.addActionListener(this::jButton4ActionPerformed);
        jPanel1.add(jButton4);

        jButton2 = createNeonButton("salir");
        jButton2.setBounds(120, 320, 260, 40);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel1.add(jButton2);

        add(jPanel1);

        getContentPane().setComponentZOrder(jLabel1, 1);
        getContentPane().setComponentZOrder(jPanel1, 0);
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

    private void jButton1ActionPerformed(ActionEvent evt) {
        new ModificarMisDatos(game).setVisible(true);
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        new MenuPrincipal(game);
        this.dispose();
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        new VerMisDatos(this.game).setVisible(true);
        this.dispose();
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        new ConfirmarEliminar(this.game).setVisible(true);
        this.dispose();
    }
}
