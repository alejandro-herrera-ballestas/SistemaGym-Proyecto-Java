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
    protected String nombre;
    protected int edad;
    protected int id;
    protected String eps;
    protected String sexo;
    protected int telefono;
    protected String correo;

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