/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;

/**
 *
 * @author USUARIO
 */
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
    
    public double calcularCostoPedido(int costoUnidad, int cantidad){
        
        double resultado= costoUnidad * cantidad;
        return resultado;
    }
   
}
