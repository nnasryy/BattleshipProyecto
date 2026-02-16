/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIWarnings;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author nasry
 */
public class BarcoBombardeado {

    private JFrame frame;

    public BarcoBombardeado() {
        frame = new JFrame("Advertencia");

        frame.setSize(610, 280);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel mensaje = new JLabel("Nave bombardeada!");
        mensaje.setBounds(90, 120, 600, 30);
        mensaje.setFont(new Font("Cold Warm", Font.PLAIN, 20));
        mensaje.setForeground(Color.GREEN);
        frame.add(mensaje);

        JButton btnOkay = new JButton("OKAY");
        btnOkay.setBounds(220, 190, 170, 50);
        btnOkay.setBackground(new Color(34, 33, 33));
        btnOkay.setFont(new Font("Cold Warm", Font.PLAIN, 24));
        btnOkay.setForeground(Color.GREEN);
        btnOkay.setFocusPainted(false);
        btnOkay.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, Color.GREEN, Color.GREEN));

        btnOkay.addActionListener(e -> frame.dispose());
        frame.add(btnOkay);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/Images/joprep.png")));

        bg.setBounds(0, 0, 610, 280);
        frame.add(bg);

        frame.setVisible(true);
    }
}
