/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipgame;

import ENUM.TipoBarco;
import java.util.Random;

/**
 *
 * @author nasry
 */
public class Battleship {
     // ====== JUGADORES ======
    private Player currentUser;
    private Player player1;
    private Player player2;

    // ====== CONFIGURACIÓN ======
    private int dificultad;       // 5,4,2,1 según EASY, NORMAL, EXPERT, GENIUS
    private boolean modoTutorial; // true = tutorial (mostrar barcos), false = arcade

    // ====== TABLEROS ======
    private String[][] tableroP1;
    private String[][] tableroP2;

    // ====== BARCOS ======
    private int[] sizes = {5, 4, 3, 2};                  // Tamaños de los barcos
    private int[] vidasP1;                                // Vidas de cada barco P1
    private int[] vidasP2;                                // Vidas de cada barco P2
    private boolean[] colocadosP1;                        // Barcos colocados P1
    private boolean[] colocadosP2;                        // Barcos colocados P2
    private TipoBarco[] lineupP1;
    private TipoBarco[] lineupP2;

    // ====== CONTROL ======
    private int turno;         // 1 = P1, 2 = P2
    private Random rand = new Random();

    // ====== CONSTRUCTOR ======
    public Battleship() {
        dificultad = 4;           // NORMAL por default
        modoTutorial = true;      // TUTORIAL por default
    }

    // ====== LOGIN / REGISTRO ======
    public boolean login(String user, String pass) {
        Player p = Player.login(user, pass);
        if (p != null) {
            currentUser = p;
            return true;
        }
        return false;
    }

    public boolean crearPlayer(String user, String pass) {
        boolean exito = Player.registrar(user, pass);
        if (exito) {
            currentUser = Player.login(user, pass);
        }
        return exito;
    }

    public Player getCurrentUser() {
        return currentUser;
    }

    // ====== PARTIDA ======
    public void iniciarPartida(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;

        lineupP1 = new TipoBarco[dificultad];
        lineupP2 = new TipoBarco[dificultad];

        barcosSeleccionadosP1 = 0;
        barcosSeleccionadosP2 = 0;

        inicializarTableros();
        turno = 1; // Turno para Player 1
    }

    // ====== LINEUP ======
    private int barcosSeleccionadosP1;
    private int barcosSeleccionadosP2;

    public boolean seleccionarBarco(Player jugador, TipoBarco barco) {
        TipoBarco[] lineup = (jugador == player1) ? lineupP1 : lineupP2;
        int seleccionados = (jugador == player1) ? barcosSeleccionadosP1 : barcosSeleccionadosP2;

        if (seleccionados >= dificultad) {
            return false;
        }

        // Evitar duplicados (si quieres permitir alguno, se puede ajustar)
        for (TipoBarco b : lineup) {
            if (b == barco) {
                return false;
            }
        }

        lineup[seleccionados] = barco;

        if (jugador == player1) {
            barcosSeleccionadosP1++;
        } else {
            barcosSeleccionadosP2++;
        }

        return true;
    }

    public void resetLineup(Player jugador) {
        if (jugador == player1) {
            lineupP1 = new TipoBarco[dificultad];
            barcosSeleccionadosP1 = 0;
        } else {
            lineupP2 = new TipoBarco[dificultad];
            barcosSeleccionadosP2 = 0;
        }
    }

    public boolean lineupCompleto(Player jugador) {
        return (jugador == player1)
                ? barcosSeleccionadosP1 == dificultad
                : barcosSeleccionadosP2 == dificultad;
    }

    private void cambiarTurno() {
        turno = (turno == 1) ? 2 : 1;
    }

    // ====== COLOCAR BARCOS ======
    public boolean colocarBarco(String[][] tablero, int fila, int col, TipoBarco barco, boolean horizontal) {
        int size = barco.getSize();

        if (horizontal && col + size > tablero[0].length) {
            return false;
        }
        if (!horizontal && fila + size > tablero.length) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            int f = fila + (horizontal ? 0 : i);
            int c = col + (horizontal ? i : 0);
            if (!tablero[f][c].equals("~")) {
                return false;
            }
        }

        for (int i = 0; i < size; i++) {
            int f = fila + (horizontal ? 0 : i);
            int c = col + (horizontal ? i : 0);
            tablero[f][c] = barco.getCodigo();
        }

        return true;
    }

    // ====== TABLEROS ======
 // Añade este Getter para saber la dificultad actual
public boolean isModoTutorial() {
    return this.modoTutorial;
}

// Modifica inicializarTableros para que las vidas coincidan con el tamaño de los barcos elegidos
public void inicializarTableros() {
    tableroP1 = new String[8][8];
    tableroP2 = new String[8][8];
    limpiarTableroRec(0, 0, tableroP1);
    limpiarTableroRec(0, 0, tableroP2);

    // Las vidas deben ser el tamaño de los barcos en el lineup
    vidasP1 = new int[dificultad];
    vidasP2 = new int[dificultad];
    
    for(int i = 0; i < dificultad; i++) {
        if(lineupP1[i] != null) vidasP1[i] = lineupP1[i].getSize();
        if(lineupP2[i] != null) vidasP2[i] = lineupP2[i].getSize();
    }
    turno = 1;
}

    private void limpiarTableroRec(int f, int c, String[][] tablero) {
        if (f == 8) {
            return;
        }
        if (c == 8) {
            limpiarTableroRec(f + 1, 0, tablero);
            return;
        }
        tablero[f][c] = "~";
        limpiarTableroRec(f, c + 1, tablero);
    }

    private int indiceDeBarco(String codigo) {
        TipoBarco[] barcos = TipoBarco.values();
        for (int i = 0; i < barcos.length; i++) {
            if (barcos[i].getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    //====BOMBARDEO=====
    public String bombardear(int turnoJugador, int fila, int col) {
        String[][] tablero = (turnoJugador == 1) ? tableroP2 : tableroP1;
        int[] vidas = (turnoJugador == 1) ? vidasP2 : vidasP1;

        String celda = tablero[fila][col];

        // Ya fue atacada
        if (celda.equals("X") || celda.equals("F")) {
            return "N"; // Nada
        }

        // Fallo
        if (celda.equals("~")) {
            tablero[fila][col] = "F";
            cambiarTurno();
            return "F";
        }

        // Impacto
        tablero[fila][col] = "X";

        // Reducir vida del barco correspondiente
        int idx = indiceDeBarco(celda);

        if (idx != -1) {
            vidas[idx]--;
            if (vidas[idx] == 0) {
                return "H"; // Hundido
            }
        }

        cambiarTurno();
        return "X"; // Impacto
    }

    //===BARCOS RIVAL====
    public int getBarcosRestantesRival(int turnoJugador) {
        return (turnoJugador == 1)
                ? getBarcosVivosP2()
                : getBarcosVivosP1();
    }

//====GANADOR=====
    public boolean hayGanador() {
        boolean p1Vivo = false;
        boolean p2Vivo = false;

        for (int v : vidasP1) {
            if (v > 0) {
                p1Vivo = true;
            }
        }
        for (int v : vidasP2) {
            if (v > 0) {
                p2Vivo = true;
            }
        }

        return !p1Vivo || !p2Vivo;
    }

    // ====== GETTERS ======
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public TipoBarco[] getLineupP1() {
        return lineupP1;
    }

    public TipoBarco[] getLineupP2() {
        return lineupP2;
    }

    public String[][] getTableroP1() {
        return tableroP1;
    }

    public String[][] getTableroP2() {
        return tableroP2;
    }

    public Player getGanador() {
        boolean p1Vivo = false;
        boolean p2Vivo = false;

        for (int v : vidasP1) {
            if (v > 0) {
                p1Vivo = true;
            }
        }
        for (int v : vidasP2) {
            if (v > 0) {
                p2Vivo = true;
            }
        }

        if (!p1Vivo && p2Vivo) {
            return player2;
        }
        if (!p2Vivo && p1Vivo) {
            return player1;
        }

        return null; // Aún no hay ganador
    }

    public int[] getVidasP1() {
        return vidasP1;
    }

    public int[] getVidasP2() {
        return vidasP2;
    }

    public int getBarcosVivosP1() {
        int vivos = 0;
        for (int v : vidasP1) {
            if (v > 0) {
                vivos++;
            }
        }
        return vivos;
    }

    public int getBarcosVivosP2() {
        int vivos = 0;
        for (int v : vidasP2) {
            if (v > 0) {
                vivos++;
            }
        }
        return vivos;
    }

}
