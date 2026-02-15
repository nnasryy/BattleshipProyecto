/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ENUM;

/**
 *
 * @author nasry
 */
public enum TipoBarco {
       PORTAAVIONES("PA", 5),
    ACORAZADO("AZ", 4),
    SUBMARINO("SM", 3),
    DESTRUCTOR("DT", 2);

    private final String codigo;
    private final int size;

    // Constructor privado del enum
    TipoBarco(String codigo, int size) {
        this.codigo = codigo;
        this.size = size;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public int getSize() {
        return size;
    }

    // Nombre completo para mostrar
    public String getNombreCompleto() {
        switch (this) {
            case PORTAAVIONES: return "PORTAAVIONES";
            case ACORAZADO: return "ACORAZADO";
            case SUBMARINO: return "SUBMARINO";
            case DESTRUCTOR: return "DESTRUCTOR";
        }
        return "";
    }
}
