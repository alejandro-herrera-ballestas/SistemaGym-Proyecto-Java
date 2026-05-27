/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.controladores;

import com.mycompany.sistemagym.proyecto.Modelos.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAYERLIN
 */
public class ControladorProducto {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public Producto buscarProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarProducto(String nombre) {
        Producto p = buscarProducto(nombre);
        if (p != null) {
            productos.remove(p);
            return true;
        }
        return false;
    }
}
