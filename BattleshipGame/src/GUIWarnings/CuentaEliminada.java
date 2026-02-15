/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIWarnings;

import GUI.MenuInicio;
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
public class CuentaEliminada {
    
    private JFrame frame;

    public CuentaEliminada() {
        frame = new JFrame("Advertencia");
        // Ajustamos el tamaño según la imagen joprep.png
        // (Suele ser de unos 600x300 o similar, ajústalo si ves espacios)
        frame.setSize(610, 280);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true); // Opcional: Quita la barra de arriba para que parezca un popup real
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Texto de Advertencia
        JLabel mensaje = new JLabel("Cuenta Eliminada!");
        mensaje.setBounds(80, 100, 600, 70);
        mensaje.setFont(new Font("Cold Warm", Font.PLAIN, 52));
        mensaje.setForeground(Color.GREEN);
        frame.add(mensaje);

        // Botón OKAY
        JButton btnOkay = new JButton("OKAY");
        btnOkay.setBounds(80, 180, 460, 70);
        btnOkay.setBackground(new Color(34, 33, 33));
        btnOkay.setFont(new Font("Cold Warm", Font.PLAIN, 24));
        btnOkay.setForeground(Color.GREEN);
        btnOkay.setFocusPainted(false);
        btnOkay.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, Color.GREEN, Color.GREEN));

        btnOkay.addActionListener(e -> {
            new MenuInicio();
                frame.dispose();
        });
        frame.add(btnOkay);

    
        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/Images/joprep.png")));

        bg.setBounds(0, 0, 610, 280);
        frame.add(bg);

        frame.setVisible(true);
    }
}
