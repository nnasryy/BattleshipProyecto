package GUIWarnings;

import battleshipgame.Battleship;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author nasry
 */
public class CambiarPassword extends JFrame{
    
    private JButton jButton1, btnSalir;
    private JLabel jLabel1, jLabel2;
    private JTextField jTextField2;
    private Battleship game;

    public CambiarPassword(Battleship game) {
        this.game = game;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(626, 319);
        setLayout(null);
        setResizable(false);

        jLabel2 = new JLabel("Nuevo password:");
        jLabel2.setFont(new Font("Cold Warm", Font.PLAIN, 30));
        jLabel2.setForeground(new Color(0, 255, 0));
        jLabel2.setBounds(40, 110, 260, 90);
        add(jLabel2);

        jTextField2 = new JTextField();
        jTextField2.setBackground(new Color(34, 33, 33));
        jTextField2.setFont(new Font("OCR A Extended", Font.BOLD, 24));
        jTextField2.setForeground(new Color(0, 255, 0));
        jTextField2.setCaretColor(new Color(0, 255, 0));
        jTextField2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(51, 255, 0), new Color(0, 255, 0)));
        jTextField2.setBounds(310, 130, 270, 40);
        add(jTextField2);

        jButton1 = new JButton("CAMBIAR");
        jButton1.setBackground(new Color(34, 33, 33));
        jButton1.setFont(new Font("Cold Warm", Font.PLAIN, 24));
        jButton1.setForeground(new Color(0, 255, 0));
        jButton1.setFocusPainted(false);
        jButton1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));
        jButton1.setBounds(310, 210, 170, 50);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        add(jButton1);
        
        btnSalir = new JButton("SALIR");
        btnSalir.setBackground(new Color(34, 33, 33));
        btnSalir.setFont(new Font("Cold Warm", Font.PLAIN, 24));
        btnSalir.setForeground(new Color(0, 255, 0));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));

        btnSalir.setBounds(110, 210, 170, 50);
        btnSalir.addActionListener(e -> this.dispose());
        add(btnSalir);
        
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/joprep.png")));
        jLabel1.setBounds(0, 0, 610, 280);
        add(jLabel1);
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        String nuevaPass = jTextField2.getText().trim();
        
        if (!battleshipgame.Player.lengthValido(nuevaPass)) {
            new MinimoCaracteres();
            return;
        }
        
        // Si es válido, actualizamos
        game.getCurrentUser().setPassword(nuevaPass);
        this.dispose();
    }
}