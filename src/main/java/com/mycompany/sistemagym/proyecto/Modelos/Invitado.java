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

    @Override
        public void mostrarInfo() {
        System.out.println("---- DATOS DEL INVITADO ----");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Fecha entrada: " + getFechaEntrada());
        System.out.println("\nEdad: " + getEdad());
        System.out.println("\nTelefono: " + getTelefono());
        System.out.println("\nCorreo: " + getCorreo());
        }
    
    
    
}
