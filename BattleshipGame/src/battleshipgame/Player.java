/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipgame;

import java.util.ArrayList;

/**
 *
 * @author nasry
 */
public class Player {

    private static ArrayList<Player> players = new ArrayList<>();
    private ArrayList<String> historialPartidas = new ArrayList<>();

    private String username;
    private String password;
    private int puntos;
    private String[] logs;
    private int logIndex;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
        this.logs = new String[10];
        this.logIndex = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ====== PUNTOS ======
    public void agregarPuntos(int pts) {
        puntos += pts;
    }

    // ====== LOGS ======
    public void registrarLog(String descripcion) {
        logs[logIndex] = descripcion;
        logIndex = (logIndex + 1) % 10;
    }

    public String[] getLogs() {
        String[] resultado = new String[10];
        int k = 0;
        for (int i = logIndex; i < 10; i++) {
            resultado[k++] = logs[i];
        }
        for (int i = 0; i < logIndex; i++) {
            resultado[k++] = logs[i];
        }
        return resultado;
    }

    public static int getTotalPlayers() {
        return players.size();
    }

    public static Player getPlayerByUsername(String username) {
        for (Player p : players) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public static boolean lengthValido(String text) {
        return text.length() >= 3 && text.length() <= 20;
    }

    public static boolean usernameExists(String username) {
        for (Player p : players) {
            if (p.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean registrar(String username, String password) {
        if (!lengthValido(username) || !lengthValido(password)) {
            return false;
        }
        if (usernameExists(username)) {
            return false;
        }

        players.add(new Player(username, password));
        return true;
    }

    public static Player login(String username, String password) {
        for (Player p : players) {
            if (p.username.equals(username) && p.password.equals(password)) {
                return p;
            }
        }
        return null;
    }

    public static ArrayList<Player> getAllPlayers() {
        return players;
    }

    public static void eliminarPlayer(Player p) {
        players.remove(p);
    }

    public void agregarAlHistorial(String registro) {
        if (historialPartidas.size() >= 10) {
            historialPartidas.remove(0);
        }
        historialPartidas.add(registro);
    }

    public ArrayList<String> getHistorialPartidas() {
        return historialPartidas;
    }
}
