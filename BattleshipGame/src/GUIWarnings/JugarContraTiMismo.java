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
public class JugarContraTiMismo {
    
    private JFrame frame;

    public JugarContraTiMismo() {
        frame = new JFrame("Advertencia");
        frame = new JFrame("Advertencia");
    frame.setSize(610, 280); 
    frame.setLayout(null);
    frame.setLocationRelativeTo(null);
    frame.setUndecorated(true); 
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // 1. Texto de Advertencia
    JLabel mensaje = new JLabel("No puedes jugar contra ti mismo!", JLabel.CENTER);
    mensaje.setBounds(0, 130, 610, 40); // Centrado horizontalmente
    mensaje.setFont(new Font("Cold Warm", Font.PLAIN, 30));
    mensaje.setForeground(Color.GREEN);
    frame.add(mensaje);
    
    // 2. Botón OKAY
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

    // 3. Imagen de Fondo (Se añade al final para que esté "detrás")
    try {
        java.net.URL imgURL = getClass().getResource("/Images/joprep.png");
        if (imgURL != null) {
            JLabel bg = new JLabel(new ImageIcon(imgURL));
            bg.setBounds(0, 0, 610, 280); 
            frame.add(bg);
        } else {
            System.err.println("Error: No se encontró la imagen en /Images/joprep.png");
            frame.getContentPane().setBackground(Color.BLACK); // Fondo de respaldo
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    // 4. MOSTRAR al final
    frame.setVisible(true);
    }
}
