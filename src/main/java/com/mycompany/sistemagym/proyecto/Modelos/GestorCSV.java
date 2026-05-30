package com.mycompany.sistemagym.proyecto.Modelos;
import com.mycompany.sistemagym.proyecto.controladores.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GestorCSV {

    private final String ARCHIVO = "gym.csv";

    public void guardarTodo(
        ArrayList<Plan> planes,
        ArrayList<Cliente> clientes,
        ArrayList<Empleado> empleados) {

    try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {

        pw.println("[PLANES]");

        for (Plan p : planes) {
            pw.println(
                    p.getNombrePlan() + "," +
                    p.getPrecioPlan() + "," +
                    p.getDuracionPlan() + "," +
                    p.getNumInvitadosMes()
            );
        }

        pw.println();

        pw.println("[CLIENTES]");

        for (Cliente c : clientes) {

            String nombrePlan = "";

            if (c.getPlanContratado() != null) {
                nombrePlan = c.getPlanContratado().getNombrePlan();
            }

            pw.println(
                    c.getNombre() + "," +
                    c.getEdad() + "," +
                    c.getId() + "," +
                    c.getEps() + "," +
                    c.getSexo() + "," +
                    c.getTelefono() + "," +
                    c.getCorreo() + "," +
                    nombrePlan + "," +
                    c.getPeso() + "," +
                    c.getAltura() + "," +
                    c.isEstado()
            );
        }

        pw.println();

        pw.println("[EMPLEADOS]");

        for (Empleado e : empleados) {

            String estudios =
                    String.join(";", e.getEstudios());

            pw.println(
                    e.getNombre() + "," +
                    e.getEdad() + "," +
                    e.getId() + "," +
                    e.getEps() + "," +
                    e.getSexo() + "," +
                    e.getTelefono() + "," +
                    e.getCorreo() + "," +
                    estudios
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void cargarTodo() {

        // leer archivo

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Empleado> empleados = new ArrayList<>();
        ArrayList<Plan> planes = new ArrayList<>();

        
    }
}
