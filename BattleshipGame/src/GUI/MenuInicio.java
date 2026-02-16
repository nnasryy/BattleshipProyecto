/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author nasry
 */
public class MenuInicio {

    private JFrame frame;

    public MenuInicio() {
        frame = new JFrame("Main Menu");
        frame.setSize(790, 628);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JButton loginBtn = new JButton("LOG IN");
        loginBtn.setBounds(20, 410, 230, 40);
        loginBtn.setFont(new Font("Capture it", Font.BOLD, 24));
        loginBtn.setBackground(new Color(18, 40, 61));
        loginBtn.setForeground(new Color(186, 215, 241));
        frame.add(loginBtn);

        JButton signUpBtn = new JButton("CREAR PLAYER");
        signUpBtn.setBounds(20, 460, 230, 40);
        signUpBtn.setFont(new Font("Capture it", Font.BOLD, 24));
        signUpBtn.setBackground(new Color(15, 35, 56));
        signUpBtn.setForeground(new Color(186, 215, 241));
        frame.add(signUpBtn);

        JButton exitBtn = new JButton("Salir");
        exitBtn.setBounds(640, 540, 120, 40);
        exitBtn.setFont(new Font("Capture it", Font.PLAIN, 24));
        exitBtn.setBackground(new Color(102, 0, 0));
        exitBtn.setForeground(new Color(255, 204, 204));
        frame.add(exitBtn);

        JLabel bg = new JLabel(new ImageIcon("src/Images/mainMenu.png"));
        bg.setBounds(0, 0, 779, 590);
        frame.add(bg);

        loginBtn.addActionListener(e -> {
            frame.dispose();
            new LogIn();
        });

        signUpBtn.addActionListener(e -> {
            frame.dispose();
            new SignUp();
        });

        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);

    }
}
