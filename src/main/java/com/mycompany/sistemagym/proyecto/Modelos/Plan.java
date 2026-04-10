package com.mycompany.sistemagym.proyecto.Modelos;

import java.util.Scanner;

public class Plan {
    private String nombrePlan;
    private int precioPlan;
    private String duracionPlan;
    private int numInvitadosMes;

    public Plan() {}
    
    public Plan(String nombrePlan, int precioPlan, String duracionPlan, int numInvitadosMes) {
        this.nombrePlan = nombrePlan;
        this.precioPlan = precioPlan;
        this.duracionPlan = duracionPlan;
        this.numInvitadosMes = numInvitadosMes;
    }

    public void pagarPlan(Scanner scanner) {  
        System.out.println("Bienvenido al Sistema de pago del gimnasio. \nEl precio del plan es: " + precioPlan);
        System.out.println("\nDetalles del plan: ");
        System.out.println("Nombre: " + this.nombrePlan);
        System.out.println("Duración: " + this.duracionPlan);
        System.out.println("Número de invitados por mes: " + this.numInvitadosMes);
        System.out.println("Desea continuar con el pago? (Si/No)");
        String respuesta = scanner.nextLine();
        if(!respuesta.equalsIgnoreCase("Si") && !respuesta.equalsIgnoreCase("No")) {
            System.out.println("Respuesta no válida. Por favor, ingrese 'Si' o 'No'.");
            return;
        }
        if(respuesta.equalsIgnoreCase("Si")) {
            System.out.println("Pago realizado con éxito. ¡Gracias por elegir nuestro gimnasio!");
        } 
            else if(respuesta.equalsIgnoreCase("No")) {
            System.out.println("Pago cancelado por el usuario. No se ha realizado ningún cargo.");
        }
        
        
    }
    
    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }
    public int getPrecioPlan() {
        return precioPlan;
    }

    public void setPrecioPlan(int precioPlan) {
        this.precioPlan = precioPlan;
    }

    public String getDuracionPlan() {
        return duracionPlan;
    }

    public void setDuracionPlan(String duracionPlan) {
        this.duracionPlan = duracionPlan;
    }

    public int getNumInvitadosMes() {
        return numInvitadosMes;
    }

    public void setNumInvitadosMes(int numInvitadosMes) {
        this.numInvitadosMes = numInvitadosMes;
    }

    public void consultPlan(Cliente cliente) {
        System.out.println("Detalles del plan: ");
        System.out.println("Nombre: " + this.nombrePlan);
        System.out.println("Duración: " + this.duracionPlan);
        System.out.println("Número de invitados por mes: " + this.numInvitadosMes);
    }
}

