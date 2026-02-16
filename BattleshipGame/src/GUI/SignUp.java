/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUIWarnings.MinimoCaracteres;
import GUIWarnings.SameUsername;
import battleshipgame.Player;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

/**
 *
 * @author nasry
 */
public class SignUp {

    JFrame frame;
    JPasswordField passField;
    char defaultEcho;

    public SignUp() {
        frame = new JFrame("Sign Up");

        frame.setSize(776, 590);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 51), new Color(0, 255, 0)));
        mainPanel.setLayout(null);

        mainPanel.setBounds(120, 90, 520, 430);

        JLabel title = new JLabel("crear Player");
        title.setBounds(90, 10, 350, 90);
        title.setFont(new Font("Inlanders", Font.PLAIN, 52));
        title.setForeground(Color.GREEN);
        mainPanel.add(title);

        JLabel userLbl = new JLabel("username:");
        userLbl.setBounds(60, 130, 200, 40);
        userLbl.setFont(new Font("Inlanders", Font.PLAIN, 24));
        userLbl.setForeground(new Color(22, 188, 63));
        mainPanel.add(userLbl);

        JTextField userField = new JTextField();
        userField.setBounds(30, 170, 250, 40);
        userField.setFont(new Font("OCR A Extended", Font.BOLD, 20));
        userField.setBackground(new Color(34, 33, 33));
        userField.setForeground(Color.GREEN);
        userField.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(51, 255, 0), new Color(0, 255, 0)));
        mainPanel.add(userField);

        JLabel passLbl = new JLabel("password:");
        passLbl.setBounds(50, 230, 210, 40);
        passLbl.setFont(new Font("Inlanders", Font.PLAIN, 24));
        passLbl.setForeground(new Color(22, 188, 63));
        mainPanel.add(passLbl);

        passField = new JPasswordField();
        passField.setBounds(30, 270, 250, 40);
        passField.setFont(new Font("OCR A Extended", Font.BOLD, 20));
        passField.setBackground(new Color(34, 33, 33));
        passField.setForeground(Color.GREEN);
        passField.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));
        mainPanel.add(passField);

        defaultEcho = passField.getEchoChar();

        JToggleButton showPass = new JToggleButton();
        showPass.setIcon(new ImageIcon(getClass().getResource("/Images/eyeClosed.png")));
        showPass.setBounds(300, 270, 50, 40);
        showPass.setBackground(new Color(34, 34, 34));
        showPass.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));
        mainPanel.add(showPass);

        JButton exitBtn = new JButton("salir");
        exitBtn.setBounds(30, 360, 170, 40);
        exitBtn.setBackground(new Color(34, 33, 33));
        exitBtn.setForeground(new Color(0, 204, 0));
        exitBtn.setFont(new Font("Inlanders", Font.PLAIN, 24));
        exitBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));
        mainPanel.add(exitBtn);

        JButton nextBtn = new JButton("siguiente");
        nextBtn.setBounds(320, 360, 170, 40);
        nextBtn.setBackground(new Color(34, 33, 33));
        nextBtn.setForeground(new Color(0, 204, 0));
        nextBtn.setFont(new Font("Inlanders", Font.PLAIN, 24));
        nextBtn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));
        mainPanel.add(nextBtn);

        frame.add(mainPanel);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        bg.setBounds(0, 0, 776, 590);
        frame.add(bg);

        showPass.addActionListener(e -> {
            passField.setEchoChar(showPass.isSelected() ? (char) 0 : defaultEcho);
        });

        nextBtn.addActionListener(e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword()).trim();

            if (!Player.lengthValido(user) || !Player.lengthValido(pass)) {
                new MinimoCaracteres();
                return;
            }

            if (Player.usernameExists(user)) {
                new SameUsername();
                return;
            }

            Player.registrar(user, pass);

            Player nuevoJugador = Player.login(user, pass);

            battleshipgame.Battleship game = new battleshipgame.Battleship();
            if (nuevoJugador != null) {
                game.setCurrentUser(nuevoJugador);
            }

            frame.dispose();
            new MenuPrincipal(game);
        });

        exitBtn.addActionListener(e -> {
            frame.dispose();
            new MenuInicio();
        });

        frame.setVisible(true);
    }
}
