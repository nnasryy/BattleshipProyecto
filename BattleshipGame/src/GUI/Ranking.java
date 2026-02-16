/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import battleshipgame.Battleship;
import battleshipgame.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author nasry
 */
public class Ranking extends JFrame {

    private JPanel jPanel1;
    private JButton jButton2;
    private JLabel jLabel1, jLabel3;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private Battleship game;

    public Ranking() {
        initComponents();
        cargarRanking();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Configuración del JFrame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(780, 590);
        setLayout(null);
        setResizable(false);
        this.game = game;

        // --- PANEL CENTRAL (jPanel1) ---
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 51), new Color(0, 255, 0)));
        jPanel1.setLayout(null);
        jPanel1.setBounds(60, 90, 650, 430);

        // Título "ranking de jugadores"
        jLabel3 = new JLabel("ranking de jugadores");
        jLabel3.setFont(new Font("Inlanders", Font.PLAIN, 48));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setBounds(60, 10, 530, 70);
        jPanel1.add(jLabel3);

        // --- ÁREA DE TEXTO / LISTA DE RANKING ---
        jTextArea1 = new JTextArea();
        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new Color(23, 24, 24));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new Font("OCR A Extended", Font.BOLD, 20));
        jTextArea1.setForeground(new Color(0, 255, 0));
        jTextArea1.setRows(5);
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 204, 0), new Color(0, 204, 0)));
        jTextArea1.setDisabledTextColor(new Color(0, 255, 0));
        jTextArea1.setEnabled(false);

        jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setBounds(20, 70, 600, 310);
        jScrollPane1.setBorder(null); // Quitar borde predeterminado del scroll
        jPanel1.add(jScrollPane1);

        // Botón: salir (jButton2)
        jButton2 = new JButton("salir");
        jButton2.setBackground(new Color(34, 33, 33));
        jButton2.setFont(new Font("Inlanders", Font.PLAIN, 24));
        jButton2.setForeground(new Color(0, 204, 0));
        jButton2.setFocusPainted(false);
        jButton2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 0), new Color(0, 255, 0)));
        jButton2.setBounds(260, 390, 140, 30);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jPanel1.add(jButton2);

        add(jPanel1);

        // --- IMAGEN DE FONDO (jLabel1) ---
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        jLabel1.setBounds(0, 0, 780, 590);
        add(jLabel1);
    }

    private void cargarRanking() {
        ArrayList<Player> ranking = new ArrayList<>(Player.getAllPlayers());

        ranking.sort((p1, p2)
                -> Integer.compare(p2.getPuntos(), p1.getPuntos())
        );

        StringBuilder sb = new StringBuilder();
        int pos = 1;

        for (Player p : ranking) {
            sb.append(pos++)
                    .append(". ")
                    .append(p.getUsername())
                    .append(" - ")
                    .append(p.getPuntos())
                    .append(" pts\n");
        }

        jTextArea1.setText(sb.toString());
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        this.dispose();
        new Reportes(game);
    }

    /**
     * Método para actualizar el texto del ranking desde fuera de la clase
     *
     * @param listaRanking String formateado con los nombres y puntos
     */
    public void setRankingText(String listaRanking) {
        jTextArea1.setText(listaRanking);
    }
}
