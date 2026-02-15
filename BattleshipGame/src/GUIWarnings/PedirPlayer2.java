/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIWarnings;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author nasry
 */
public class PedirPlayer2 extends Dialog{
    
    private JTextField PedirUsername;
    private String usernameIngresado = null;

    public PedirPlayer2(JFrame parent) {
        super(parent, true);
        setTitle("Advertencia");
        setSize(610, 280);
        setLayout(null);
        setLocationRelativeTo(parent);
        setUndecorated(true);
  

        // Texto
        JLabel mensaje = new JLabel("Ingresa el username del Player 2:");
        mensaje.setBounds(50, 120, 600, 40);
        mensaje.setFont(new Font("Cold Warm", Font.PLAIN, 30));
        mensaje.setForeground(Color.GREEN);
        add(mensaje);

        // TextField
        PedirUsername = new JTextField();
        PedirUsername.setBackground(new Color(34, 33, 33));
        PedirUsername.setFont(new Font("OCR A Extended", Font.BOLD, 24));
        PedirUsername.setForeground(Color.GREEN);
        PedirUsername.setBorder(BorderFactory.createBevelBorder(
                BevelBorder.RAISED, null, null, Color.GREEN, Color.GREEN));
        PedirUsername.setBounds(160, 160, 270, 40);
        add(PedirUsername);

        // Botón OKAY
        JButton btnOkay = new JButton("OKAY");
        btnOkay.setBounds(210, 210, 170, 50);
        btnOkay.setBackground(new Color(34, 33, 33));
        btnOkay.setFont(new Font("Cold Warm", Font.PLAIN, 24));
        btnOkay.setForeground(Color.GREEN);
        btnOkay.setFocusPainted(false);
        btnOkay.setBorder(BorderFactory.createBevelBorder(
                BevelBorder.RAISED, null, null, Color.GREEN, Color.GREEN));

        btnOkay.addActionListener(e -> {
            usernameIngresado = PedirUsername.getText().trim();
            dispose();
        });

        add(btnOkay);

        // Fondo
        JLabel bg = new JLabel(new ImageIcon(
                getClass().getResource("/Images/joprep.png")));
        bg.setBounds(0, 0, 610, 280);
        add(bg);
    }

    // metodo clave
    public String getUsernameIngresado() {
        return usernameIngresado;
    }
}
