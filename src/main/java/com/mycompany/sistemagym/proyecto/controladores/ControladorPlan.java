/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.controladores;

import com.mycompany.sistemagym.proyecto.Modelos.Cliente;
import com.mycompany.sistemagym.proyecto.Modelos.Plan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAYERLIN
 */
public class ControladorPlan {
     private List<Plan> planes = new ArrayList<>();

    public void crearPlan(Plan plan) {
        planes.add(plan);
    }

    public List<Plan> listarPlanes() {
        return planes;
    }

    public void asignarPlan(Cliente cliente, Plan plan) {
        cliente.actualizarPlan(plan);
    }
}
