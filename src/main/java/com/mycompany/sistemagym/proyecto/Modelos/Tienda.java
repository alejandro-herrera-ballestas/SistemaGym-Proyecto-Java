/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;

import java.util.ArrayList;
import java.util.Scanner;

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
    
    public void addProducto(){
        Scanner leerProd = new Scanner(System.in);
        
        System.out.println("\t------ AÑADIR PRODUCTO ------");
        
        System.out.println("\nIngrese el Nombre del producto a añadir: ");
        String nombre = leerProd.nextLine();
        
        System.out.println("Ingrese el tipo de producto a añadir:");
        String tipoProducto = leerProd.nextLine();
        
        System.out.println("Ingrese el codigo del producto a añadir: ");
        int codigo = leerProd.nextInt();
        
        System.out.println("Ingrese el Stock del producto a añadir / La cantidad que quiere añadir: ");
        int stock = leerProd.nextInt();
        
        System.out.println("Ingrese el Precio del prodcuto a añadir: ");
        int precio = leerProd.nextInt();
        
        Producto nuevoProducto = new Producto(nombre, tipoProducto, codigo, stock, precio);
        
        this.inventario.add(nuevoProducto);
        
        System.out.println("\n\t---- PRODUCTO REGISTRADO CORRECTAMENTE ----");
        
        leerProd.close();
    }
}
