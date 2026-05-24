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

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getId() {
        return id;
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
    public String toString() {
        return "Empleado{" + "Nombre: "+ nombre + ", Edad: "+ edad + ", Id: "+ id + ", Eps: "+ eps +  
                ", Sexo: "+ sexo + ", Telefono: "+ telefono + ", Correo: "+ correo + 
                ", estudios=" + estudios + ", historialTurnos=" + historialTurnos + '}';
    }
    
    
}
