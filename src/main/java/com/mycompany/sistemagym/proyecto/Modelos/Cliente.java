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

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public boolean isEstado() {
        return estado;
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

    public String getSexo() {
        return sexo;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setPlanContratado(Plan planContratado) {
        this.planContratado = planContratado;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "planContratado=" + planContratado + ", peso=" + peso + ", altura=" + altura + ", estado=" + estado + '}';
    }

    
    public void actualizarPlan(Plan nuevoPlan) {
        this.planContratado = nuevoPlan;
    }
    
    public Plan getPlanContratado() {
        return planContratado;
    }


}
