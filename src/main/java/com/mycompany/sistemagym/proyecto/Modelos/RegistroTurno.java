package com.mycompany.sistemagym.proyecto.Modelos;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroTurno {

    private Empleado datosEmpleado;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private LocalDate fecha;

    public RegistroTurno() {
    }

    public RegistroTurno(Empleado datosEmpleado,
                         LocalTime horaEntrada,
                         LocalTime horaSalida,
                         LocalDate fecha) {

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

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {

        return "Fecha: " + fecha +
               ", Hora entrada: " + horaEntrada +
               ", Hora salida: " + horaSalida;
    }
}
