/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIWarnings;

import GUI.MiPerfil;
import battleshipgame.Battleship;
import battleshipgame.Player;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author nasry
 */
public class ConfirmarEliminar extends JFrame{
       private Battleship game;

    public ConfirmarEliminar(Battleship game) {
        this.game = game;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setSize(626, 319);
        setLayout(null);
        setResizable(false);

        JLabel txt = new JLabel("ELIMINAR TU CUENTA?");
        txt.setFont(new Font("Cold Warm", Font.PLAIN, 40));
        txt.setForeground(Color.GREEN); 
        txt.setBounds(120, 120, 400, 40);
        add(txt);

        // BOTÓN SÍ (A la izquierda)
        JButton btnSi = createNeonBtn("SI", 110);
        btnSi.addActionListener(e -> {
            Player.eliminarPlayer(game.getCurrentUser()); 
            new CuentaEliminada();     
            this.dispose();
        });
        add(btnSi);

        // BOTÓN NO (A la derecha)
        JButton btnNo = createNeonBtn("NO", 310);
        btnNo.addActionListener(e -> new MiPerfil(this.game));
        add(btnNo);

        // Fondo al final
        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/Images/joprep.png")));
        bg.setBounds(0, 0, 610, 280);
        add(bg);
    }

    private JButton createNeonBtn(String t, int x) {
        JButton b = new JButton(t);
        b.setBackground(new Color(34, 33, 33));
        b.setFont(new Font("Cold Warm", Font.PLAIN, 24));
        b.setForeground(new Color(0, 255, 0));
        b.setBorder(BorderFactory.createBevelBorder(0, null, null, Color.GREEN, Color.GREEN));
        b.setBounds(x, 210, 170, 50);
        return b;
    }
}
