/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;

/**
 *
 * @author USUARIO
 */
public class VentaProducto extends TransaccionProducto {

    public VentaProducto() {}

    public VentaProducto(Producto producto, int cantidad, String fecha) {
        super(producto, cantidad, fecha);
        this.costoTotal = calcularCosto();
    }
    
    public void setCostoTotal() {
        this.costoTotal = calcularCosto();
    }
     
        @Override
    public double calcularCosto() {
        return cantidad * producto.getPrecio();
    }

    @Override
    public void mostrarResumen() {
        System.out.println("\t---- RESUMEN DE VENTA ----");
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Cantidad vendida: " + cantidad);
        System.out.println("Costo total: $" + costoTotal);
        System.out.println("Fecha: " + fecha);
    }
    
        @Override
    public String toString() {
        return "VentaProducto{" +
                "producto=" + producto +
                ", cantidadVendida=" + cantidad +
                ", costoTotal=" + costoTotal +
                ", fecha=" + fecha + '}';
    }

    public void validarStock() throws StockInsuficienteException {
        if (cantidad > producto.getStock()) {
            throw new StockInsuficienteException(
                "Stock insuficiente para " + producto.getNombre(),
                producto.getStock(),
                cantidad
            );
        }
    }
}