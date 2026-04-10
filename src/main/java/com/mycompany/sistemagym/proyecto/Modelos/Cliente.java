package com.mycompany.sistemagym.proyecto.Modelos;

public class Cliente extends Usuario {
    private Plan planContratado;
    private float peso;
    private float altura;
    private boolean estado;

    public Cliente(){}

    public Cliente(String nombre, int edad, int id, String eps,String sexo, int telefono, String correo, Plan planContratado, float peso, float altura, boolean estado) {
        super(nombre, edad, id, eps, sexo, telefono, correo);
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
