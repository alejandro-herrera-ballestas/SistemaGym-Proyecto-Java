
package com.mycompany.sistemagym.proyecto.Modelos;


public abstract class TransaccionProducto {
 
    protected Producto producto;
    protected int cantidad;
    protected double costoTotal;
    protected String fecha;
 
    public TransaccionProducto() {}
 
    public TransaccionProducto(Producto producto, int cantidad, String fecha) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
   
    public abstract double calcularCosto();
     
    public abstract void mostrarResumen();
 
    public Producto getProducto() {
        return producto;
    }
 
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
 
    public int getCantidad() {
        return cantidad;
    }
 
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
 
    public double getCostoTotal() {
        return costoTotal;
    }
 
    public String getFecha() {
        return fecha;
    }
 
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
