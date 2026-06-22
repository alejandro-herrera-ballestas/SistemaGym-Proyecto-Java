package com.mycompany.sistemagym.proyecto.Modelos;

import java.util.ArrayList;

public class Cliente extends Usuario {
    private Plan planContratado;
    private float peso;
    private float altura;
    private boolean estado;
    private ArrayList <Invitado> invitados;
    private double IMC;

    public Cliente(){
    invitados = new ArrayList<>();
    }

    public Cliente(String nombre, int edad, String id, String eps, String sexo, int telefono, String correo,Plan planContratado, float peso, float altura, boolean estado) {
        super(nombre, edad, id, eps, sexo, telefono, correo);
        this.planContratado = planContratado;
        this.peso = peso;
        this.altura = altura;
        this.estado = estado;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public void actualizarPlan(Plan nuevoPlan) {
        this.planContratado = nuevoPlan;
    }
    
    public Plan getPlanContratado() {
        return planContratado;
    }

    public ArrayList<Invitado> getInvitados() {
        return invitados;
    }

    public void setInvitados(Invitado invitados) {
        this.invitados.add(invitados);
    }

    public void calcIMC()   {
        System.out.println("\n\n\t---- IMC: ----");
        
        IMC = getPeso() / (getAltura() * getAltura() );
        System.out.println("El IMC del cliente " + getNombre() + " es: " + IMC);
    }

@Override
    public void mostrarInfo() {
    System.out.println("\t---- DATOS DEL CLIENTE ----\n");
        System.out.println("Nombre: " + getNombre());
        System.out.println("\nEdad: " + getEdad());
        System.out.println("\nID: " + getId());
        System.out.println("\nEPS: " + getEps());
        System.out.println("\nSexo: " + getSexo());
        System.out.println("\nTelefono: " + getTelefono());
        System.out.println("\nCorreo: " + getCorreo());
        System.out.println("\nPlan Contratado: " + getPlanContratado());
        System.out.println("\nPeso: " + getPeso());
        System.out.println("\nAltura: " + getAltura());
        System.out.println("\nEstado: " + this.estado);
    
    }
    public void mostrarInfo(boolean detallado) {
    if (!detallado) {
        System.out.println("\t---- RESUMEN DEL CLIENTE ----");
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID: " + getId());
        System.out.println("Estado: " + (estado ? "ACTIVO" : "INACTIVO"));
    } else {
        mostrarInfo();
    }
}
}
    
