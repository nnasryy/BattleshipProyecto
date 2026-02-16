package GUI;

import battleshipgame.Battleship;
import battleshipgame.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
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

public class Ultimos10Juegos extends JFrame {

    private JPanel jPanel1;
    private JButton jButton2;
    private JLabel jLabel1, jLabel3;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;

    private Battleship game;

    // Constructor modificado para recibir el objeto Battleship
    public Ultimos10Juegos(Battleship game) {
        this.game = game;
        initComponents();
        cargarDatos();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(780, 590);
        setLayout(null);
        setResizable(false);

        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(0, 0, 0));
        jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 255, 51), new Color(0, 255, 0)));
        jPanel1.setLayout(null);
        jPanel1.setBounds(60, 90, 650, 430);

        jLabel3 = new JLabel("ULTIMOS 10 JUEGOS");
        jLabel3.setFont(new Font("Inlanders", Font.PLAIN, 48));
        jLabel3.setForeground(new Color(0, 255, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBounds(90, 10, 480, 70);
        jPanel1.add(jLabel3);

        jTextArea1 = new JTextArea();
        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new Color(23, 24, 24));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new Font("OCR A Extended", Font.BOLD, 18)); // Un poco más pequeño para que quepan las fechas
        jTextArea1.setForeground(new Color(0, 255, 0));
        jTextArea1.setRows(5);
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                null, null, new Color(0, 204, 0), new Color(0, 204, 0)));
        jTextArea1.setDisabledTextColor(new Color(0, 255, 0));

        // Hacemos el textArea transparente al scroll para que se vea bien
        jTextArea1.setOpaque(true);

        jScrollPane1 = new JScrollPane(jTextArea1);
        jScrollPane1.setBounds(20, 70, 600, 310);
        jScrollPane1.setBorder(null);
        jPanel1.add(jScrollPane1);

        jButton2 = new JButton("SALIR");
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

        jLabel1 = new JLabel();
        try {
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/UserScreen.png")));
        } catch (Exception e) {
            // Manejo de error
        }
        jLabel1.setBounds(0, 0, 780, 590);
        add(jLabel1);
    }

    private void cargarDatos() {
        Player currentUser = game.getCurrentUser();
        StringBuilder sb = new StringBuilder();

        if (currentUser != null && currentUser.getHistorialPartidas() != null) {
            int contador = 1;

            for (int i = currentUser.getHistorialPartidas().size() - 1; i >= 0; i--) {
                sb.append(contador).append("- ").append(currentUser.getHistorialPartidas().get(i)).append("\n");
                contador++;
            }

            if (currentUser.getHistorialPartidas().isEmpty()) {
                sb.append("No hay partidas registradas aún.");
            }
        } else {
            sb.append("Error al cargar historial.");
        }

        jTextArea1.setText(sb.toString());
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        this.dispose();
        new Reportes(game);
    }
}
