/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author USUARIO
 */
public class Invitado extends Usuario {
    
    private String fechaEntrada;
    private boolean entradaHabilitada;

    public Invitado() {
    }

    public Invitado(String fechaEntrada, boolean entradaHabilitada, String nombre, int edad, int id, String eps, String sexo, int telefono, String correo) {
        super(nombre, edad, id, eps, sexo, telefono, correo);
        this.fechaEntrada = fechaEntrada;
        this.entradaHabilitada = entradaHabilitada;
    }


    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isEntradaHabilitada() {
        return entradaHabilitada;
    }

    public void setEntradaHabilitada(boolean habilitarEntrada) {
        this.entradaHabilitada = habilitarEntrada;
    }
    
    public boolean calculoDiasCumplidos(){ 
                                           
        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");      //formato de la fecha

        LocalDate fechaInvitado =
                LocalDate.parse(fechaEntrada, formato);         // convertir de string a formato de fecha

        LocalDate hoy = LocalDate.now();
        long dias =
                ChronoUnit.DAYS.between(fechaInvitado, hoy);        // calcular dias

        if (dias >= 90) {
            entradaHabilitada = true;
        } else {
            entradaHabilitada = false;
        }

        return entradaHabilitada;



    }
    
    
    
}
