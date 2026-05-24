package com.mycompany.sistemagym.proyecto.Modelos;

import java.util.ArrayList;

public class Empleado extends Usuario {

    private ArrayList <String> estudios;
    private ArrayList<RegistroTurno> historialTurnos;

    public Empleado() {
    }

    public Empleado(String nombre, int edad, int id, String eps, String sexo, int telefono, String correo) {
        super(nombre, edad, id, eps, sexo, telefono, correo);
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

    @Override
    public String toString() {
        return "Empleado{" + "Nombre: "+ nombre + ", Edad: "+ edad + ", Id: "+ id + ", Eps: "+ eps +  
                ", Sexo: "+ sexo + ", Telefono: "+ telefono + ", Correo: "+ correo + 
                ", estudios=" + estudios + ", historialTurnos=" + historialTurnos + '}';
    }
    
    
}
