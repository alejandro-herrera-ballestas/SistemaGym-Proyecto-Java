package com.mycompany.sistemagym.proyecto.miapp;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import com.mycompany.sistemagym.proyecto.controladores.*;
import com.mycompany.sistemagym.proyecto.vista.VentanaLogin;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Punto de entrada de la aplicación GymPro.
 *
 * Flujo de arranque:
 *   1. Carga datos desde gym.csv (o inserta datos de demostración).
 *   2. Inicia el hilo de guardado automático (concurrencia).
 *   3. Abre VentanaLogin → VentanaPrincipal (GUI Swing).
 *
 * Criterios de la guía que se cumplen aquí:
 *   - Ligadura dinámica: se usan referencias Usuario para llamar mostrarInfo()
 *   - Interfaces: IGestionable, IReportable, IPagable importadas a través de controladores
 *   - Concurrencia: HiloGuardadoAutomatico lanzado desde VentanaPrincipal
 *   - Archivos: GestorCSV carga/guarda en gym.csv
 */
public class SistemaGymProyecto {

    public static void main(String[] args) {

        // ── 1. Inicializar controladores ──────────────────────────────────
        controladorCliente  ctrlCliente  = new controladorCliente();
        ControladorEmpleado ctrlEmpleado = new ControladorEmpleado();
        ControladorPlan     ctrlPlan     = new ControladorPlan();
        ControladorProducto ctrlProducto = new ControladorProducto();
        ControladorVenta    ctrlVenta    = new ControladorVenta();
        ControladorAdmin    ctrlAdmin    = new ControladorAdmin();
        GestorCSV           gestorCSV    = new GestorCSV();

        // ── 2. Cargar datos ───────────────────────────────────────────────
        boolean cargado = gestorCSV.cargarTodo(ctrlCliente, ctrlEmpleado, ctrlPlan, ctrlProducto, ctrlVenta);
        if (!cargado) {
            System.out.println("[Init] No se encontró gym.csv — cargando datos de demostración...");
            cargarDatosDemostracion(ctrlCliente, ctrlEmpleado, ctrlPlan, ctrlProducto);
        } else {
            System.out.println("[Init] Datos cargados correctamente desde gym.csv");
        }

        // ── 3. Demostración de ligadura dinámica (polimorfismo) ───────────
        // Se usan referencias de tipo padre Usuario para llamar mostrarInfo()
        // demostrando ligadura dinámica en tiempo de ejecución.
        System.out.println("\n[Polimorfismo] Mostrando usuarios con referencia tipo padre:");
        java.util.List<Usuario> usuarios = new java.util.ArrayList<>();
        usuarios.addAll(ctrlCliente.listarClientes());
        usuarios.addAll(ctrlEmpleado.listarEmpleados());
        for (Usuario u : usuarios) {
            u.mostrarInfo();  // <-- ligadura dinámica: JVM decide en tiempo de ejecución
        }

        // ── 4. Lanzar GUI en el Event Dispatch Thread ─────────────────────
        final controladorCliente  fc  = ctrlCliente;
        final ControladorEmpleado fe  = ctrlEmpleado;
        final ControladorPlan     fp  = ctrlPlan;
        final ControladorProducto fprod = ctrlProducto;
        final ControladorVenta    fv  = ctrlVenta;
        final ControladorAdmin    fa  = ctrlAdmin;
        final GestorCSV           fg  = gestorCSV;

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            VentanaLogin login = new VentanaLogin(fc, fe, fp, fprod, fv, fa, fg);
            login.setVisible(true);
        });
    }

    // ── Datos de demostración ─────────────────────────────────────────────
    static void cargarDatosDemostracion(controladorCliente ctrlCliente,
                                         ControladorEmpleado ctrlEmpleado,
                                         ControladorPlan ctrlPlan,
                                         ControladorProducto ctrlProducto) {
        Plan basico   = new Plan("Básico",   80000,  "1 mes", 2);
        Plan estandar = new Plan("Estándar", 140000, "1 mes", 4);
        Plan premium  = new Plan("Premium",  220000, "1 mes", 8);
        ctrlPlan.crearPlan(basico);
        ctrlPlan.crearPlan(estandar);
        ctrlPlan.crearPlan(premium);

        ctrlCliente.agregarCliente(new Cliente("Carlos Pérez",   28, "1001", "Sura",     "M", 300123456, "carlos@mail.com",  basico,   75f, 1.75f, true));
        ctrlCliente.agregarCliente(new Cliente("Laura Gómez",    34, "1002", "Sanitas",  "F", 300987654, "laura@mail.com",   estandar, 60f, 1.62f, true));
        ctrlCliente.agregarCliente(new Cliente("Miguel Torres",  22, "1003", "Nueva EPS","M", 300456123, "miguel@mail.com",  premium,  85f, 1.80f, false));

        Empleado e1 = new Empleado("Ana Rueda",   30, "E001", "Coomeva", "F", 310111223, "ana@gym.com");
        e1.setEstudios("Lic. Educación Física");
        ctrlEmpleado.agregarEmpleado(e1);

        Empleado e2 = new Empleado("Jorge Silva", 25, "E002", "Sura",    "M", 320444556, "jorge@gym.com");
        e2.setEstudios("Técnico en Ventas");
        ctrlEmpleado.agregarEmpleado(e2);

        ctrlProducto.agregarProducto(new Producto("Proteína Whey",    "Suplemento", 101, 50, 120000));
        ctrlProducto.agregarProducto(new Producto("Creatina 500g",    "Suplemento", 102, 30,  80000));
        ctrlProducto.agregarProducto(new Producto("Guantes Gym",      "Accesorio",  103, 20,  35000));
        ctrlProducto.agregarProducto(new Producto("Botella Térmica",  "Accesorio",  104, 15,  45000));
    }
}
