/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemagym.proyecto.controladores;

import com.mycompany.sistemagym.proyecto.Modelos.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class controladorCliente {
     private List<Cliente> clientes = new ArrayList<>();

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente buscarCliente(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public boolean eliminarCliente(String id) {
        Cliente c = buscarCliente(id);
        if (c != null) {
            clientes.remove(c);
            return true;
        }
        return false;
    }

    public boolean actualizarCliente(String id, Cliente nuevo) {
        Cliente c = buscarCliente(id);
        if (c != null) {
            clientes.remove(c);
            clientes.add(nuevo);
            return true;
        }
        return false;
    }
}
    
