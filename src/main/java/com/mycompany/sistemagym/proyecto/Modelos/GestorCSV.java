package com.mycompany.sistemagym.proyecto.Modelos;

import com.mycompany.sistemagym.proyecto.controladores.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GestorCSV {

    private final String ARCHIVO = "gym.csv";

    public void guardarTodo(
        List<Plan> planes,
        List<Cliente> clientes,
        List<Empleado> empleados,
        List<Producto> productos,
        List<VentaProducto> ventas) {

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
                String estudios = String.join(";", e.getEstudios());
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
            pw.println();

            pw.println("[PRODUCTOS]");
            for (Producto p : productos) {
                pw.println(
                        p.getNombre() + "," +
                        p.getTipoProducto() + "," +
                        p.getCodigo() + "," +
                        p.getStock() + "," +
                        p.getPrecio()
                );
            }
            pw.println();

            pw.println("[VENTAS]");
            for (VentaProducto v : ventas) {
                pw.println(
                        v.getProducto().getCodigo() + "," +
                        v.getCantidad() + "," +
                        v.getFecha()
                );
            }

        } catch (IOException e) {
            System.out.println("  [ERROR] No se pudo guardar el archivo: " + e.getMessage());
        } finally {
            System.out.println("  Proceso de guardado finalizado.");
        }
    }

    public boolean cargarTodo(
        controladorCliente ctrlCliente,
        ControladorEmpleado ctrlEmpleado,
        ControladorPlan ctrlPlan,
        ControladorProducto ctrlProducto,
        ControladorVenta ctrlVenta) {

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

                if (linea.equals("[PLANES]"))    { seccion = "PLANES";    continue; }
                if (linea.equals("[CLIENTES]"))  { seccion = "CLIENTES";  continue; }
                if (linea.equals("[EMPLEADOS]")) { seccion = "EMPLEADOS"; continue; }
                if (linea.equals("[PRODUCTOS]")) { seccion = "PRODUCTOS"; continue; }
                if (linea.equals("[VENTAS]"))    { seccion = "VENTAS";    continue; }

                String[] datos = linea.split(",");

                switch (seccion) {

                    case "PLANES" -> {
                        Plan plan = new Plan(
                                datos[0],
                                Integer.parseInt(datos[1]),
                                datos[2],
                                Integer.parseInt(datos[3])
                        );
                        ctrlPlan.crearPlan(plan);
                    }

                    case "CLIENTES" -> {
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
                    }

                    case "EMPLEADOS" -> {
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
                    }

                    case "PRODUCTOS" -> {
                        Producto producto = new Producto(
                                datos[0],
                                datos[1],
                                Integer.parseInt(datos[2]),
                                Integer.parseInt(datos[3]),
                                Integer.parseInt(datos[4])
                        );
                        ctrlProducto.agregarProducto(producto);
                    }

                    case "VENTAS" -> {
                        int codigoProducto = Integer.parseInt(datos[0]);
                        int cantidad = Integer.parseInt(datos[1]);
                        String fecha = datos[2];

                        Producto productoVendido = null;
                        for (Producto p : ctrlProducto.listarProductos()) {
                            if (p.getCodigo() == codigoProducto) {
                                productoVendido = p;
                                break;
                            }
                        }

                        if (productoVendido != null) {
                            VentaProducto venta = new VentaProducto(productoVendido, cantidad, fecha);
                            ctrlVenta.procesarVenta(venta);
                        }
                    }
                }
            }

            return true;

        } catch (IOException e) {
            System.out.println("  [ERROR] No se pudo leer el archivo: " + e.getMessage());
            return false;
        } finally {
            System.out.println("  Proceso de carga finalizado.");
        }
    }
}
