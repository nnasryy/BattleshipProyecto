package GUIWarnings;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class CambioTurno extends JDialog {

    public CambioTurno(java.awt.Frame parent, String nombreJugador) {
        super(parent, true);

        setTitle("Cambio de Turno");
        setSize(610, 280);
        setLayout(null);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel mensaje1 = new JLabel("CAMBIO DE TURNO");

        mensaje1.setBounds(0, 100, 610, 30);
        mensaje1.setFont(new Font("Cold Warm", Font.BOLD, 24));
        mensaje1.setForeground(Color.GREEN);
        mensaje1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(mensaje1);

        JLabel mensaje2 = new JLabel("Turno de: " + nombreJugador);
        mensaje2.setBounds(0, 140, 610, 30);
        mensaje2.setFont(new Font("Cold Warm", Font.PLAIN, 20));

        mensaje2.setForeground(Color.GREEN);
        mensaje2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(mensaje2);

        JButton btnOkay = new JButton("CONTINUAR");
        btnOkay.setBounds(220, 190, 170, 50);
        btnOkay.setBackground(new Color(34, 33, 33));
        btnOkay.setFont(new Font("Cold Warm", Font.PLAIN, 22));
        btnOkay.setForeground(Color.GREEN);
        btnOkay.setFocusPainted(false);
        btnOkay.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, Color.GREEN, Color.GREEN));

        btnOkay.addActionListener(e -> dispose());
        add(btnOkay);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/Images/joprep.png")));
        bg.setBounds(0, 0, 610, 280);
        add(bg);

        setVisible(true);
    }
}
