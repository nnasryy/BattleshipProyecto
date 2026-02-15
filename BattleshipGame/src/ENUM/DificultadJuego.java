/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ENUM;

public enum DificultadJuego {
    EASY(5), NORMAL(4), EXPERT(2), GENIUS(1);

    private final int cantidadBarcos;

    DificultadJuego(int cantidad) {
        this.cantidadBarcos = cantidad;
    }

    public int getCantidadBarcos() {
        return cantidadBarcos;
    }
}