/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;

/**
 *
 * @author imnot
 */
public abstract class Usuario {
    String nombre;
    int edad;
    int id;
    String eps;
    String sexo;
    int telefono;
    String correo;

        public Usuario(){} 

        public Usuario(String nombre, int edad, int id, String eps, String sexo, int telefono, String correo) {
            this.nombre = nombre;
            this.edad = edad;
            this.id = id;
            this.eps = eps;
            this.sexo = sexo;
            this.telefono = telefono;
            this.correo = correo;
        }

    
}