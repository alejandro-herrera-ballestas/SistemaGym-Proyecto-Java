/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemagym.proyecto.miapp;

import com.mycompany.sistemagym.proyecto.Modelos.Cliente;
import com.mycompany.sistemagym.proyecto.Modelos.Empleado;
import com.mycompany.sistemagym.proyecto.Modelos.Plan;
import com.mycompany.sistemagym.proyecto.Modelos.RegistroTurno;
import java.util.Scanner;

/**
 *
 * @author imnot
 */
public class SistemaGymProyecto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Plan plan=new Plan("basico",1000, "1 mes", 5);
        
        Cliente c1 = new Cliente("Aaron",19,"010101", "Salud total", "Hombre", 1234567890,"aa@",plan,68,170,true);
        Empleado el = new Empleado("aaron",19,"01010102","Salud total", "Hombre", 1234567890, "aa@");
        System.out.println(c1);
        
        c1.setEstado(false); c1.setTelefono(987654321);
        
        System.out.println();
        
        scanner.close();
    }
}
