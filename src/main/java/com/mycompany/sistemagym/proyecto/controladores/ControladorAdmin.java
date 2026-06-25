package com.mycompany.sistemagym.proyecto.controladores;

import com.mycompany.sistemagym.proyecto.Interfaces.IGestionable;
import com.mycompany.sistemagym.proyecto.Interfaces.IReportable;
import com.mycompany.sistemagym.proyecto.Modelos.Cliente;
import com.mycompany.sistemagym.proyecto.Modelos.Empleado;
import com.mycompany.sistemagym.proyecto.Modelos.Plan;
import com.mycompany.sistemagym.proyecto.Modelos.Producto;
import com.mycompany.sistemagym.proyecto.Modelos.VentaProducto;
import java.util.List;

/**
 * Controlador de administración. Implementa IGestionable e IReportable,
 * demostrando herencia múltiple de comportamiento mediante interfaces.
 */
public class ControladorAdmin implements IGestionable, IReportable {

    private List<Cliente>      clientes;
    private List<Empleado>     empleados;
    private List<Plan>         planes;
    private List<Producto>     productos;
    private List<VentaProducto> ventas;

    public void setDatos(List<Cliente> clientes, List<Empleado> empleados,
                         List<Plan> planes, List<Producto> productos,
                         List<VentaProducto> ventas) {
        this.clientes  = clientes;
        this.empleados = empleados;
        this.planes    = planes;
        this.productos = productos;
        this.ventas    = ventas;
    }

    public int totalClientes(List<Cliente> clientes) {
        return clientes.size();
    }

    public double totalIngresos(List<VentaProducto> ventas) {
        double total = 0;
        for (VentaProducto v : ventas) total += v.calcularCosto();
        return total;
    }

    // ---- IGestionable ----
    @Override public void registrar()  { /* administrador único, no aplica */ }
    @Override public void actualizar() { }
    @Override public void eliminar()   { }
    @Override public void buscar()     { }
    @Override public void listar()     { mostrarEstadisticas(); }

    // ---- IReportable ----
    @Override
    public String generarReporte() {
        if (clientes == null) return "Sin datos cargados.";
        long activos   = clientes.stream().filter(Cliente::isEstado).count();
        long inactivos = clientes.size() - activos;
        double ingresos = ventas == null ? 0 : ventas.stream().mapToDouble(VentaProducto::calcularCosto).sum();
        return String.format(
            "=== REPORTE GENERAL ===\n" +
            "Clientes registrados : %d (Activos: %d | Inactivos: %d)\n" +
            "Empleados            : %d\n" +
            "Planes disponibles   : %d\n" +
            "Productos en tienda  : %d\n" +
            "Ingresos por ventas  : $%.2f",
            clientes.size(), activos, inactivos,
            empleados == null ? 0 : empleados.size(),
            planes    == null ? 0 : planes.size(),
            productos == null ? 0 : productos.size(),
            ingresos
        );
    }

    @Override
    public void mostrarEstadisticas() {
        System.out.println(generarReporte());
    }
}
