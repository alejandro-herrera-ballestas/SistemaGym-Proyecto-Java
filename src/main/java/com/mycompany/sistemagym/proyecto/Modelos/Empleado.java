package com.mycompany.sistemagym.proyecto.Modelos;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Empleado extends Usuario {

    private ArrayList <String> estudios;
    private ArrayList<RegistroTurno> historialTurnos;

    public Empleado() {
        historialTurnos = new ArrayList<>();
        estudios = new ArrayList<>();
    }

    public Empleado(String nombre, int edad, int id, String eps, String sexo, int telefono, String correo) {
        super(nombre, edad, id, eps, sexo, telefono, correo);
        
        historialTurnos = new ArrayList<>();
    estudios = new ArrayList<>();
    }

    public ArrayList<String> getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudio) {
        this.estudios.add(estudio);
    }

    public ArrayList<RegistroTurno> getHistorialTurnos() {
        return historialTurnos;
    }

    public void setHistorialTurnos(RegistroTurno turnoRegistrado) {
        this.historialTurnos.add(turnoRegistrado);
    }
    
   public void registrarTurno() {

    Scanner leer = new Scanner(System.in);

    System.out.println("\t--- BIENVENIDO AL SISTEMA DE REGISTRO DE TURNOS ----");

    // Fecha automática
    LocalDate fechaActual = LocalDate.now();

    // Pedir hora entrada
    System.out.print("Ingrese hora de entrada (HH:mm): ");
    String entradaTexto = leer.nextLine();

    // Pedir hora salida
    System.out.print("Ingrese hora de salida (HH:mm): ");
    String salidaTexto = leer.nextLine();

    // Formato de hora
    DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("HH:mm");

    // Convertir String -> LocalTime
    LocalTime horaEntrada =
            LocalTime.parse(entradaTexto, formato);

    LocalTime horaSalida =
            LocalTime.parse(salidaTexto, formato);

    
    RegistroTurno turno =
            new RegistroTurno(
                    this,
                    horaEntrada,
                    horaSalida,
                    fechaActual
            );

    // Guardar en historial
    historialTurnos.add(turno);

    System.out.println("Turno registrado correctamente.");
}

    @Override
    public void mostrarInfo() {
        System.out.println("---- DATOS DEL EMPLEADO ----");
        System.out.println("Nombre: " + getNombre());
        System.out.println("\nEdad: " + getEdad());
        System.out.println("\nID: " + getId());
        System.out.println("\nEPS: " + getEps());
        System.out.println("\nTelefono: " + getTelefono());
        System.out.println("\nCorreo: " + getCorreo());
        System.out.println("Estudios: " + getEstudios());
        System.out.println("Historial de turnos: " + getHistorialTurnos());
        
        }
    
    
}
