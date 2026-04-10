package com.mycompany.sistemagym.proyecto.Modelos;

public class Cliente extends Usuario {
    private Plan planContratado;
    private float peso;
    private float altura;
    private boolean estado;

    public Cliente(){}

    public Cliente(String nombre, String apellido, String email, String telefono, Plan planContratado, float peso, float altura, boolean estado) {
        super(nombre, apellido, email, telefono);
        this.planContratado = planContratado;
        this.peso = peso;
        this.altura = altura;
        this.estado = estado;
    }

    public void actualizarPlan(Plan nuevoPlan) {
        this.planContratado = nuevoPlan;
    }
    
    public Plan getPlanContratado() {
        return planContratado;
    }


}
