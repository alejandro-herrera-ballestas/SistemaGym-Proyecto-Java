/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;

/**
 *
 * @author USUARIO
 */
public class VentaProducto {
    private Producto infoProducto;
    private int cantidadVendida;
    private double costoTotal;
    private String fecha;

    public VentaProducto() {
    }

    public VentaProducto(Producto infoProducto, int cantidadVendida, String fecha) {
        this.infoProducto = infoProducto;
        this.cantidadVendida = cantidadVendida;
        this.costoTotal = cantidadVendida*infoProducto.getPrecio();
        this.fecha = fecha;
    }

    public Producto getInfoProducto() {
        return infoProducto;
    }

    public void setInfoProducto(Producto producto) {
        this.infoProducto = producto;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal() {
        this.costoTotal = this.cantidadVendida*this.infoProducto.getPrecio();
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "VentaProducto{" + "infoProducto=" + infoProducto + ", cantidadVendida=" + cantidadVendida + ", costoTotal=" + costoTotal + ", fecha=" + fecha + '}';
    }
    
    
    
}