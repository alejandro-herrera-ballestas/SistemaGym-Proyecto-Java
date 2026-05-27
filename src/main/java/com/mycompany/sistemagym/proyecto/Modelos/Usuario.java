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
    protected String id;
    protected String eps;
    protected String sexo;
    protected int telefono;
    protected String correo;

        public Usuario(){} 

        public Usuario(String nombre, int edad, String id, String eps, String sexo, int telefono, String correo) {
            this.nombre = nombre;
            this.edad = edad;
            this.id = id;
            this.eps = eps;
            this.sexo = sexo;
            this.telefono = telefono;
            this.correo = correo;
        }
        
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getId() { return id; }
    public String getEps() { return eps; }
    public void setEps(String eps) { this.eps = eps; }
    public String getSexo() { return sexo; }
    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    
    public abstract void mostrarInfo();
    
}