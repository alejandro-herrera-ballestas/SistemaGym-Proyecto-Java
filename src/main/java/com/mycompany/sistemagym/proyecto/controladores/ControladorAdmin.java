/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.controladores;

import com.mycompany.sistemagym.proyecto.Modelos.Cliente;
import com.mycompany.sistemagym.proyecto.Modelos.VentaProducto;
import java.util.List;

/**
 *
 * @author MAYERLIN
 */
public class ControladorAdmin {
     public int totalClientes(List<Cliente> clientes) {
        return clientes.size();
    }

    public double totalIngresos(List<VentaProducto> ventas) {
        double total = 0;
        for (VentaProducto v : ventas) {
            total += v.calcularCosto();
        }
        return total;
    }
}
