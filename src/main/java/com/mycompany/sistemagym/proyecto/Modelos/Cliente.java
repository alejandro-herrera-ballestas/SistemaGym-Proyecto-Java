package com.mycompany.sistemagym.proyecto.Modelos;

import java.util.ArrayList;

public class Cliente extends Usuario {
    private Plan planContratado;
    private float peso;
    private float altura;
    private boolean estado;
    private ArrayList <Invitado> invitados;

    public Cliente(){}

    public Cliente(Plan planContratado, float peso, float altura, boolean estado, ArrayList<Invitado> invitados, String nombre, int edad, int id, String eps, String sexo, int telefono, String correo) {
        super(nombre, edad, id, eps, sexo, telefono, correo);
        this.planContratado = planContratado;
        this.peso = peso;
        this.altura = altura;
        this.estado = estado;
        this.invitados = invitados;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Cliente{" + "planContratado=" + planContratado + ", peso=" + peso + ", altura=" + altura + ", estado=" + estado + ", invitados=" + invitados + '}';
    }

}
