package com.mycompany.sistemagym.proyecto.Modelos;

import com.mycompany.sistemagym.proyecto.Interfaces.IPagable;

/**
 * Representa una venta de producto. Extiende TransaccionProducto e implementa
 * IPagable, demostrando implementación de interfaz + herencia múltiple de comportamiento.
 */
public class VentaProducto extends TransaccionProducto implements IPagable {

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

    // ---- Implementación de IPagable ----
    @Override
    public double calcularTotal() {
        return calcularCosto();
    }

    @Override
    public boolean procesarPago() {
        if (producto == null || cantidad <= 0) return false;
        return cantidad <= producto.getStock();
    }

    @Override
    public String getDescripcionPago() {
        return "Venta de " + cantidad + "x " + producto.getNombre()
                + " — Total: $" + costoTotal + " — Fecha: " + fecha;
    }

    // ---- Validación de stock ----
    public void validarStock() throws StockInsuficienteException {
        if (cantidad > producto.getStock()) {
            throw new StockInsuficienteException(
                "Stock insuficiente para " + producto.getNombre(),
                producto.getStock(),
                cantidad
            );
        }
    }

    @Override
    public String toString() {
        return "VentaProducto{" +
                "producto=" + producto +
                ", cantidadVendida=" + cantidad +
                ", costoTotal=" + costoTotal +
                ", fecha=" + fecha + '}';
    }
}
