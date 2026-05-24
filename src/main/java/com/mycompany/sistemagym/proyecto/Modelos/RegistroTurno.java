/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.Modelos;

/**
 *
 * @author USUARIO
 */
public class RegistroTurno {
    private Empleado datosEmpleado;
    private double horaEntrada;
    private double horaSalida;
    private String fecha;

    public RegistroTurno() {
    }

    public RegistroTurno(Empleado datosEmpleado, double horaEntrada, double horaSalida, String fecha) {
        this.datosEmpleado = datosEmpleado;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.fecha = fecha;
    }

    public Empleado getDatosEmpleado() {
        return datosEmpleado;
    }

    public void setDatosEmpleado(Empleado datosEmpleado) {
        this.datosEmpleado = datosEmpleado;
    }

    public double getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(double horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public double getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(double horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "RegistroTurno{" + "datosEmpleado=" + datosEmpleado + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida + ", fecha=" + fecha + '}';
    }
    
    
    
}
