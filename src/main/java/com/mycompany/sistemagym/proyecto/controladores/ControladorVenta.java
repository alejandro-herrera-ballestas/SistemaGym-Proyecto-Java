/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.controladores;

import com.mycompany.sistemagym.proyecto.Modelos.VentaProducto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAYERLIN
 */
public class ControladorVenta {
    private List<VentaProducto> ventas = new ArrayList<>();

    public double procesarVenta(VentaProducto venta) {
        double total = venta.calcularCosto();
        ventas.add(venta);
        return total;
    }

    public List<VentaProducto> listarVentas() {
        return ventas;
    }
}
