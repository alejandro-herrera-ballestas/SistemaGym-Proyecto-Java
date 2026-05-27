/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;

/**
 *
 * @author USUARIO
 */
public class Producto {
    
    private String nombre;
    private String tipoProducto;
    private int codigo;
    private int stock;
    private int precio;

    public Producto() {
    }

    
    public Producto(String nombre, String tipoProducto, int codigo, int stock, int precio) {
        this.nombre = nombre;
        this.tipoProducto = tipoProducto;
        this.codigo = codigo;
        this.stock = stock;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", tipoProducto=" + tipoProducto + ", codigo=" + codigo + ", stock=" + stock + ", precio=" + precio + '}';
    }
    
    
    
}
