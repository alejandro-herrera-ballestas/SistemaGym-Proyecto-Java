package com.mycompany.sistemagym.proyecto.Interfaces;

/**
 * Interfaz que define el comportamiento de cualquier entidad que
 * implique un proceso de pago dentro del sistema.
 */
public interface IPagable {
    double calcularTotal();
    boolean procesarPago();
    String getDescripcionPago();
}
