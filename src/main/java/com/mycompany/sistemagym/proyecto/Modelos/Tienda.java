/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;

import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Tienda {
    
    private ArrayList <Producto> inventario;

    public Tienda() {
    }

    
    public Tienda(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }

    public ArrayList<Producto> getInventario() {
        return inventario;
    }

    public void setInventario(Producto producto) {
        this.inventario.add(producto);
    }
    
    public void encargarProducto(){
        
    }
}
