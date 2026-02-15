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
public class CeldasLlenas {
    
    private JFrame frame;

    public CeldasLlenas() {
        frame = new JFrame("Advertencia");
        // Ajustamos el tamaño según la imagen joprep.png
        // (Suele ser de unos 600x300 o similar, ajústalo si ves espacios)
        frame.setSize(610, 280);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true); // Opcional: Quita la barra de arriba para que parezca un popup real
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Texto de Advertencia
        JLabel mensaje = new JLabel("Ya colocaste todas tus naves");
        mensaje.setBounds(65, 120, 600, 40);
        mensaje.setFont(new Font("Cold Warm", Font.PLAIN, 23));
        mensaje.setForeground(Color.GREEN);
        frame.add(mensaje);

        // Botón OKAY
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

        // Imagen de Fondo (joprep.png)
        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/Images/joprep.png")));
        // El size de la imagen debe dictar el size del frame
        bg.setBounds(0, 0, 610, 280);
        frame.add(bg);

        frame.setVisible(true);
    }
}
