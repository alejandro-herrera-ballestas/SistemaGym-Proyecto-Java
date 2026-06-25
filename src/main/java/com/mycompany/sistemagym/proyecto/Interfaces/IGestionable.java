package com.mycompany.sistemagym.proyecto.Interfaces;

/**
 * Interfaz que define las operaciones CRUD básicas para cualquier entidad
 * gestionable dentro del sistema del gimnasio.
 * Permite herencia múltiple de comportamiento mediante interfaces.
 */
public interface IGestionable {
    void registrar();
    void actualizar();
    void eliminar();
    void buscar();
    void listar();
}
