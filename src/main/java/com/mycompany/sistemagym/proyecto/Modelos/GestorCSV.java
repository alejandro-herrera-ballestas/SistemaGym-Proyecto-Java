package com.mycompany.sistemagym.proyecto.Modelos;
import com.mycompany.sistemagym.proyecto.controladores.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GestorCSV {

    private final String ARCHIVO = "gym.csv";

    public void guardarTodo(
        List<Plan> planes,
        List<Cliente> clientes,
        List<Empleado> empleados) {

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

    public boolean cargarTodo(
        controladorCliente ctrlCliente,
        ControladorEmpleado ctrlEmpleado,
        ControladorPlan ctrlPlan) {

    File archivo = new File(ARCHIVO);

    if (!archivo.exists()) {
        return false;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

        String linea;
        String seccion = "";

        while ((linea = br.readLine()) != null) {

            linea = linea.trim();

            if (linea.isEmpty()) {
                continue;
            }

            if (linea.equals("[PLANES]")) {
                seccion = "PLANES";
                continue;
            }

            if (linea.equals("[CLIENTES]")) {
                seccion = "CLIENTES";
                continue;
            }

            if (linea.equals("[EMPLEADOS]")) {
                seccion = "EMPLEADOS";
                continue;
            }

            String[] datos = linea.split(",");

            switch (seccion) {

                case "PLANES":

                    Plan plan = new Plan(
                            datos[0],
                            Integer.parseInt(datos[1]),
                            datos[2],
                            Integer.parseInt(datos[3])
                    );

                    ctrlPlan.crearPlan(plan);

                    break;

                case "CLIENTES":

                    Plan planCliente = null;

                    for (Plan p : ctrlPlan.listarPlanes()) {

                        if (p.getNombrePlan().equals(datos[7])) {
                            planCliente = p;
                            break;
                        }
                    }

                    Cliente cliente = new Cliente(
                            datos[0],
                            Integer.parseInt(datos[1]),
                            datos[2],
                            datos[3],
                            datos[4],
                            Integer.parseInt(datos[5]),
                            datos[6],
                            planCliente,
                            Float.parseFloat(datos[8]),
                            Float.parseFloat(datos[9]),
                            Boolean.parseBoolean(datos[10])
                    );

                    ctrlCliente.agregarCliente(cliente);

                    break;

                case "EMPLEADOS":

                    Empleado empleado = new Empleado(
                            datos[0],
                            Integer.parseInt(datos[1]),
                            datos[2],
                            datos[3],
                            datos[4],
                            Integer.parseInt(datos[5]),
                            datos[6]
                    );

                    if (datos.length > 7 && !datos[7].isEmpty()) {

                        String[] estudios = datos[7].split(";");

                        for (String est : estudios) {
                            empleado.setEstudios(est);
                        }
                    }

                    ctrlEmpleado.agregarEmpleado(empleado);

                    break;
            }
        }

        return true;

    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
    }
}
