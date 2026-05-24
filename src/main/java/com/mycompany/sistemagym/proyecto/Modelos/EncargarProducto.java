
package com.mycompany.sistemagym.proyecto.Modelos;

import java.util.Scanner;

public class EncargarProducto {
    
    private int codigo;
    private int cantidad;
    private int constoUnidad;
    private int costoTotal;

    public EncargarProducto() {
    }

    
    public EncargarProducto(int codigo, int cantidad, int constoUnidad, int costoTotal) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.constoUnidad = constoUnidad;
        this.costoTotal = costoTotal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getConstoUnidad() {
        return constoUnidad;
    }

    public void setConstoUnidad(int constoUnidad) {
        this.constoUnidad = constoUnidad;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    public void encontrarProduct(Tienda tienda) {

    Scanner product = new Scanner(System.in);

    System.out.println("Ingrese el codigo del producto a buscar: ");

    int codigoBuscado = product.nextInt();

    for (int i = 0; i < tienda.getInventario().size(); i++) {

        Producto producto =
                tienda.getInventario().get(i);

        if (producto.getCodigo() == codigoBuscado) {

            System.out.println("Producto encontrado:");
            System.out.println(producto);

            return;
        }
    }

    System.out.println("Producto no encontrado.");
}
    
    public double calcularCostoPedido(int costoUnidad, int cantidad){
        
        double resultado= costoUnidad * cantidad;
        return resultado;
    }
   
}
