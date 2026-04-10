/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;

/**
 *
 * @author imnot
 */
public class Usuario {
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

    public String getNombre() {
        return nombre;      // probando git
    }

    public void setNombre(String nombre)    {
        this.nombre = nombre;
    }

    public int getEdad()    {
        return edad;
    }

    public void setEdad(int edad)   {
        this.edad = edad;
    }

    public int getID()  {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
}