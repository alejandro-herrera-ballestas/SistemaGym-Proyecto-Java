package com.mycompany.sistemagym.proyecto.Interfaces;

/**
 * Interfaz que define el comportamiento de generación de reportes e información.
 * Una clase puede implementar tanto IGestionable como IReportable
 * para demostrar herencia múltiple mediante interfaces.
 */
public interface IReportable {
    String generarReporte();
    void mostrarEstadisticas();
}
