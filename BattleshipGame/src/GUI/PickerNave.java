/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import ENUM.TipoBarco;
import GUIWarnings.CompletarFlota;
import GUIWarnings.MaximoDeNaves;

import battleshipgame.Battleship;
import battleshipgame.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 *
 * @author nasry
 */
public class PickerNave extends JFrame {

    private Battleship game;
    private Player jugadorActual;

    private JButton btnAcorazado, btnPortaaviones, btnSubmarino, btnDestructor;
    private JButton btnReset, btnListo;
    private JTextArea areaSeleccion;
    private JLabel labelNombrePlayer;

    public PickerNave(Battleship game, Player jugadorActual) {
        this.game = game;
        this.jugadorActual = jugadorActual;

        setTitle("Battleship - Selección de Flota");
        setSize(1330, 780);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        Font fontLabels = new Font("Capture it", Font.ITALIC, 36);
        Color colorTexto = Color.WHITE;

        JLabel lblAco = crearLabel("ACORAZADO", 40, 260, 220, 40, fontLabels);
        JLabel lblPorta = crearLabel("PORTAAVIONES", 480, 260, 270, 40, fontLabels);
        JLabel lblDest = crearLabel("DESTRUCTOR", 40, 470, 220, 40, fontLabels);
        JLabel lblSub = crearLabel("SUBMARINO", 480, 470, 220, 40, fontLabels);

        btnAcorazado = crearBotonImagen("/Images/AcorazadoBtn.png", 40, 300, 420, 160);
        btnPortaaviones = crearBotonImagen("/Images/PortaavionesBtn.png", 480, 300, 420, 160);
        btnDestructor = crearBotonImagen("/Images/DestructorBtn.png", 40, 510, 420, 160);
        btnSubmarino = crearBotonImagen("/Images/SubmarinoBtn.png", 480, 510, 420, 160);

        JPanel panelControl = new JPanel();
        panelControl.setBackground(new Color(200, 191, 208));
        panelControl.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
        panelControl.setLayout(null);
        panelControl.setBounds(920, 300, 370, 360);

        JLabel lblTitulo = new JLabel("LINEUP PARA:");
        lblTitulo.setFont(new Font("Capture it", Font.BOLD, 30));
        lblTitulo.setBounds(20, 20, 250, 40);
        panelControl.add(lblTitulo);

        //
        labelNombrePlayer = new JLabel(jugadorActual.getUsername().toUpperCase());
        labelNombrePlayer.setFont(new Font("Capture it", Font.BOLD, 30));
        labelNombrePlayer.setForeground(new Color(9, 9, 71));
        labelNombrePlayer.setBounds(20, 60, 330, 40);
        panelControl.add(labelNombrePlayer);

        areaSeleccion = new JTextArea();
        areaSeleccion.setEditable(false);
        areaSeleccion.setBackground(new Color(14, 13, 42));
        areaSeleccion.setForeground(new Color(229, 229, 242));
        areaSeleccion.setFont(new Font("OCR A Extended", Font.BOLD, 20));

        JScrollPane scroll = new JScrollPane(areaSeleccion);
        scroll.setBounds(10, 110, 350, 150);
        panelControl.add(scroll);

        btnReset = new JButton("RESET");
        btnReset.setBackground(new Color(102, 0, 0));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Capture it", Font.PLAIN, 24));
        btnReset.setBounds(20, 280, 130, 50);
        btnReset.addActionListener(e -> {
            areaSeleccion.setText("");
            game.resetLineup(jugadorActual);
        });
        panelControl.add(btnReset);

        btnListo = new JButton("LISTO!");
        btnListo.setBackground(new Color(0, 0, 51));
        btnListo.setForeground(Color.WHITE);
        btnListo.setFont(new Font("Capture it", Font.PLAIN, 26));
        btnListo.setBounds(170, 280, 180, 50);
        btnListo.addActionListener(this::accionListo);
        panelControl.add(btnListo);

        add(panelControl);

        btnAcorazado.addActionListener(e -> seleccionar(TipoBarco.ACORAZADO));
        btnPortaaviones.addActionListener(e -> seleccionar(TipoBarco.PORTAAVIONES));
        btnSubmarino.addActionListener(e -> seleccionar(TipoBarco.SUBMARINO));
        btnDestructor.addActionListener(e -> seleccionar(TipoBarco.DESTRUCTOR));

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/Images/SelectingBackground.png")));
        fondo.setBounds(0, 0, 1330, 780);
        add(fondo);
    }

    private void seleccionar(TipoBarco barco) {
        if (game.seleccionarBarco(jugadorActual, barco)) {
            areaSeleccion.append("- " + barco.getCodigo() + "\n");
        } else {
            new MaximoDeNaves();
        }
    }

    private void accionListo(ActionEvent e) {
        if (!game.lineupCompleto(jugadorActual)) {
            new CompletarFlota();
            return;
        }

        this.dispose();
        if (jugadorActual == game.getPlayer1()) {

            new PickerNave(game, game.getPlayer2());
        } else {

            new LineUpPosition(game, game.getPlayer1());
        }
    }

    private JButton crearBotonImagen(String ruta, int x, int y, int w, int h) {
        JButton btn = new JButton();
        try {
            btn.setIcon(new ImageIcon(getClass().getResource(ruta)));
        } catch (Exception e) {
            btn.setText("IMG NOT FOUND");
            btn.setBackground(Color.DARK_GRAY);
        }
        btn.setBounds(x, y, w, h);
        btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(153, 204, 255), new Color(153, 204, 255)));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        add(btn);
        return btn;
    }

    private JLabel crearLabel(String texto, int x, int y, int w, int h, Font f) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(f);
        lbl.setForeground(Color.WHITE);
        lbl.setBounds(x, y, w, h);
        add(lbl);
        return lbl;
    }
}
