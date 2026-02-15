package GUIWarnings;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog; // CAMBIO IMPORTANTE
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class MensajeImpacto extends JDialog {

    // Recibimos el 'parent' (tu tablero) para centrarlo sobre él
    public MensajeImpacto(java.awt.Frame parent, String linea1, String linea2) {
        // super(parent, true) hace que sea modal (bloquee la ventana de atrás)
        super(parent, true); 
        
        setTitle("Mensaje");
        setSize(610, 280); 
        setLayout(null);
        setLocationRelativeTo(parent); // Se centra sobre el tablero
        setUndecorated(true); 
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // --- PRIMERA LÍNEA ---
        JLabel mensaje1 = new JLabel(linea1);
        mensaje1.setBounds(0, 100, 610, 30);
        mensaje1.setFont(new Font("Cold Warm", Font.PLAIN, 22));
        mensaje1.setForeground(Color.GREEN);
        mensaje1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(mensaje1);

        // --- SEGUNDA LÍNEA ---
        JLabel mensaje2 = new JLabel(linea2);
        mensaje2.setBounds(0, 135, 610, 30);
        mensaje2.setFont(new Font("Cold Warm", Font.PLAIN, 18));
        mensaje2.setForeground(new Color(0, 255, 100));
        mensaje2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(mensaje2);

        // Botón OKAY
        JButton btnOkay = new JButton("OKAY");
        btnOkay.setBounds(220, 190, 170, 50);
        btnOkay.setBackground(new Color(34, 33, 33));
        btnOkay.setFont(new Font("Cold Warm", Font.PLAIN, 24));
        btnOkay.setForeground(Color.GREEN);
        btnOkay.setFocusPainted(false);
        btnOkay.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, 
                null, null, Color.GREEN, Color.GREEN));
        
        btnOkay.addActionListener(e -> dispose()); // dispose cierra el diálogo
        add(btnOkay);

        // Fondo
        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/Images/joprep.png")));
        bg.setBounds(0, 0, 610, 280); 
        add(bg);

        setVisible(true); // Al ser modal, el código se detiene aquí hasta cerrar
    }
}