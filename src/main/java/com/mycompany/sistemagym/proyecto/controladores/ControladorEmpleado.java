/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.controladores;

import com.mycompany.sistemagym.proyecto.Modelos.Empleado;
import com.mycompany.sistemagym.proyecto.Modelos.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAYERLIN
 */
public class ControladorEmpleado {
        private List<Empleado> empleados = new ArrayList<>();

    
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    
    public List<Empleado> listarEmpleados() {
        return empleados;
    }

   
    public Empleado buscarEmpleado(String id) {
        for (Empleado e : empleados) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    
    public boolean eliminarEmpleado(String id) {
        Empleado e = buscarEmpleado(id);
        if (e != null) {
            empleados.remove(e);
            return true;
        }
        return false;
    }

    
    public boolean actualizarEmpleado(String id, Empleado nuevo) {
        Empleado e = buscarEmpleado(id);
        if (e != null) {
            empleados.remove(e);
            empleados.add(nuevo);
            return true;
        }
        return false;
    }

    
    public List<Empleado> buscarPorCargo(String cargo) {
        List<Empleado> resultado = new ArrayList<>();
        for (Empleado e : empleados) {
            if (e.getEstudios().equals(cargo)) {
                resultado.add(e);
            }
        }
        return resultado;
    }
}

