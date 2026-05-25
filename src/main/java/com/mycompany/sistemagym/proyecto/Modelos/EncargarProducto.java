
package com.mycompany.sistemagym.proyecto.Modelos;

import java.util.Scanner;

public class EncargarProducto extends TransaccionProducto{
    
        private int costoUnidad;
 
    public EncargarProducto() {}
 
    public EncargarProducto(Producto producto, int cantidad, int costoUnidad, String fecha) {
        super(producto, cantidad, fecha);
        this.costoUnidad = costoUnidad;
        this.costoTotal = calcularCosto();
    }

    public int getCostoUnidad() {
        return costoUnidad;
    }
 
    public void setCostoUnidad(int costoUnidad) {
        this.costoUnidad = costoUnidad;
        this.costoTotal = calcularCosto();
    }
    
    @Override
    public double calcularCosto() {
        return costoUnidad * cantidad;
    }

    
    @Override
    public void mostrarResumen() {
        System.out.println("\t---- RESUMEN DE ENCARGO ----");
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Cantidad encargada: " + cantidad);
        System.out.println("Costo por unidad: $" + costoUnidad);
        System.out.println("Costo total del pedido: $" + costoTotal);
        System.out.println("Fecha: " + fecha);
    }
 
    public void encontrarProducto(Tienda tienda) {
        Scanner product = new Scanner(System.in);
        System.out.println("Ingrese el codigo del producto a buscar: ");
        int codigoBuscado = product.nextInt();
 
        for (Producto p : tienda.getInventario()) {
            if (p.getCodigo() == codigoBuscado) {
                System.out.println("Producto encontrado:");
                System.out.println(p);
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }
 
    @Override
    public String toString() {
        return "EncargarProducto{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", costoUnidad=" + costoUnidad +
                ", costoTotal=" + costoTotal +
                ", fecha=" + fecha + '}';
    }

   
}
