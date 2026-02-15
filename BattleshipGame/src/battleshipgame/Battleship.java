package battleshipgame;

import ENUM.TipoBarco;
import ENUM.DificultadJuego;
import java.util.Arrays;
import java.util.Random;

public class Battleship {

    private Player currentUser;
    private Player player1;
    private Player player2;

    private DificultadJuego dificultad;
    private boolean modoTutorial;

    private String[][] tableroP1;
    private String[][] tableroP2;

    private int[] vidasP1;
    private int[] vidasP2;
    private TipoBarco[] lineupP1;
    private TipoBarco[] lineupP2;

    private int seleccionP1; 
    private int seleccionP2; 
    private int colocacionP1; 
    private int colocacionP2;

    private int turno;
    private Random rand = new Random();

    public Battleship() {
        this.dificultad = DificultadJuego.NORMAL;
        this.modoTutorial = true;
    }
    
    public void setModoTutorial(boolean modo) {
        this.modoTutorial = modo;
    }

    public void setDificultad(DificultadJuego nuevaDif) { this.dificultad = nuevaDif; }
    public DificultadJuego getDificultad() { return dificultad; }
    public int getCantidadBarcosDificultad() { return dificultad.getCantidadBarcos(); }

    public void iniciarPartida(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        int cant = dificultad.getCantidadBarcos();
        lineupP1 = new TipoBarco[cant];
        lineupP2 = new TipoBarco[cant];
        seleccionP1 = 0; seleccionP2 = 0;
        colocacionP1 = 0; colocacionP2 = 0;
        inicializarTableros();
        turno = 1;
    }

    public void inicializarTableros() {
        tableroP1 = new String[8][8];
        tableroP2 = new String[8][8];
        limpiarTableroRec(0, 0, tableroP1);
        limpiarTableroRec(0, 0, tableroP2);
        int cant = dificultad.getCantidadBarcos();
        vidasP1 = new int[cant];
        vidasP2 = new int[cant];
    }

    private void limpiarTableroRec(int f, int c, String[][] tablero) {
        if (f == 8) return;
        if (c == 8) { limpiarTableroRec(f + 1, 0, tablero); return; }
        tablero[f][c] = "~";
        limpiarTableroRec(f, c + 1, tablero);
    }

    // --- FASE 1: SELECCIÓN CON REGLAS DE REPETICIÓN ---
    public boolean seleccionarBarco(Player jugador, TipoBarco barco) {
        TipoBarco[] lineup = (jugador == player1) ? lineupP1 : lineupP2;
        int limite = dificultad.getCantidadBarcos();
        int seleccionados = (jugador == player1) ? seleccionP1 : seleccionP2;
        
        if (seleccionados >= limite) return false;

        // LÓGICA DE REPETICIÓN
        int count = 0;
        for (TipoBarco b : lineup) {
            if (b == barco) count++;
        }

        if (count > 0) {
            // Si ya existe al menos uno...
            if (dificultad == DificultadJuego.EASY) {
                // En EASY solo se permite repetir el Destructor (DT), máximo 2 veces.
                if (barco != TipoBarco.DESTRUCTOR || count >= 2) {
                    return false; // No es DT o ya hay 2 DTs
                }
            } else {
                // En otras dificultades no se permite repetir NADA.
                return false;
            }
        }

        // Si pasa las validaciones, se agrega
        lineup[seleccionados] = barco;
        if (jugador == player1) seleccionP1++; else seleccionP2++;
        return true;
    }

    public boolean lineupCompleto(Player jugador) {
        int limite = dificultad.getCantidadBarcos();
        return (jugador == player1) ? seleccionP1 == limite : seleccionP2 == limite;
    }

    public void resetLineup(Player jugador) {
        if (jugador == player1) {
            if (lineupP1 != null) Arrays.fill(lineupP1, null);
            seleccionP1 = 0;
        } else {
            if (lineupP2 != null) Arrays.fill(lineupP2, null);
            seleccionP2 = 0;
        }
    }

    public int getIndiceActual(Player jugador) {
        return (jugador == player1) ? colocacionP1 : colocacionP2;
    }

    public void colocarBarcoSimple(Player jugador, int fila, int col) {
        String[][] tablero = (jugador == player1) ? tableroP1 : tableroP2;
        TipoBarco[] lineup = (jugador == player1) ? lineupP1 : lineupP2;
        int[] vidas = (jugador == player1) ? vidasP1 : vidasP2;
        int indice = getIndiceActual(jugador);
        if (indice >= lineup.length || lineup[indice] == null) return;
        TipoBarco barco = lineup[indice];
        tablero[fila][col] = barco.getCodigo();
        vidas[indice] = 1; // Vida inicial (1 impacto hunde)
        if (jugador == player1) colocacionP1++; else colocacionP2++;
    }

    public void resetColocacion(Player jugador) {
        if (jugador == player1) {
            colocacionP1 = 0;
            limpiarTableroRec(0, 0, tableroP1);
        } else {
            colocacionP2 = 0;
            limpiarTableroRec(0, 0, tableroP2);
        }
    }

    public boolean flotaCompleta(Player jugador) {
        int limite = dificultad.getCantidadBarcos();
        return (jugador == player1) ? colocacionP1 == limite : colocacionP2 == limite;
    }

    // --- FASE 3: BATALLA ---

    private void limpiarFallas(String[][] tablero) {
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                if (tablero[f][c].equals("F")) {
                    tablero[f][c] = "~";
                }
            }
        }
    }

    private void randomizarFlota(Player jugador) {
        String[][] tablero = (jugador == player1) ? tableroP1 : tableroP2;
        TipoBarco[] lineup = (jugador == player1) ? lineupP1 : lineupP2;
        int[] vidas = (jugador == player1) ? vidasP1 : vidasP2;

        limpiarTableroRec(0, 0, tablero);

        for (int i = 0; i < lineup.length; i++) {
            if (lineup[i] != null && vidas[i] > 0) {
                boolean colocado = false;
                while (!colocado) {
                    int f = rand.nextInt(8);
                    int c = rand.nextInt(8);
                    if (tablero[f][c].equals("~")) {
                        tablero[f][c] = lineup[i].getCodigo();
                        colocado = true;
                    }
                }
            }
        }
    }

    public String bombardear(int turnoJugador, int fila, int col) {
        String[][] tableroObjetivo = (turnoJugador == 1) ? tableroP2 : tableroP1;
        int[] vidasObjetivo = (turnoJugador == 1) ? vidasP2 : vidasP1;
        TipoBarco[] lineupObjetivo = (turnoJugador == 1) ? lineupP2 : lineupP1;
        Player victima = (turnoJugador == 1) ? player2 : player1;

        limpiarFallas(tableroObjetivo);

        String celda = tableroObjetivo[fila][col];

        if (celda.equals("X") || celda.equals("F")) return "N";

        if (celda.equals("~")) {
            tableroObjetivo[fila][col] = "F";
            cambiarTurno();
            return "F";
        }

        // Impacto en barco
        tableroObjetivo[fila][col] = "X"; 

        // Buscar qué barco específico fue impactado (manejo de duplicados)
        for (int i = 0; i < lineupObjetivo.length; i++) {
            if (lineupObjetivo[i] != null && lineupObjetivo[i].getCodigo().equals(celda) && vidasObjetivo[i] > 0) {
                vidasObjetivo[i]--;
                
                if (vidasObjetivo[i] == 0) {
                    randomizarFlota(victima);
                    cambiarTurno();
                    return celda; // Retornamos código para mensaje de hundido
                }
                
                // Si no se hundió, retornamos "X" (golpe normal)
                // Importante: Salimos del loop aquí para no dañar otro barco con el mismo código
                cambiarTurno();
                return "X"; 
            }
        }
        
        // Si por alguna razón el código no coincidió con vidas > 0 (caso raro)
        cambiarTurno();
        return "X";
    }

    private void cambiarTurno() { turno = (turno == 1) ? 2 : 1; }
    
    public boolean hayGanador() { return getBarcosVivosP1() == 0 || getBarcosVivosP2() == 0; }
    
    public int getBarcosVivosP1() { int v=0; for(int i:vidasP1) if(i>0) v++; return v; }
    public int getBarcosVivosP2() { int v=0; for(int i:vidasP2) if(i>0) v++; return v; }

    // Getters
    public boolean isModoTutorial() { return modoTutorial; }
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public String[][] getTableroP1() { return tableroP1; }
    public String[][] getTableroP2() { return tableroP2; }
    public TipoBarco[] getLineupP1() { return lineupP1; }
    public TipoBarco[] getLineupP2() { return lineupP2; }
    public int[] getVidasP1() { return vidasP1; }
    public int[] getVidasP2() { return vidasP2; }
    public int getTurno() { return turno; }
    public Player getGanador() { return (getBarcosVivosP1()==0) ? player2 : (getBarcosVivosP2()==0) ? player1 : null; }
    public boolean login(String user, String pass) { Player p = Player.login(user, pass); if(p!=null){currentUser=p; return true;} return false; }
    public void logout() { currentUser = null; }
    public Player getCurrentUser() { return currentUser; }
}