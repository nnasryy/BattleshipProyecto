package ENUM;

public enum DificultadJuego {
    EASY(5),
    NORMAL(4),
    EXPERT(2),
    GENIUS(1);

    private final int cantidadBarcos;

    DificultadJuego(int cantidadBarcos) {
        this.cantidadBarcos = cantidadBarcos;
    }

    public int getCantidadBarcos() {
        return cantidadBarcos;
    }
}