package com.mycompany.sistemagym.proyecto.miapp;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import com.mycompany.sistemagym.proyecto.controladores.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class SistemaGymProyecto {

    // -- Controladores globales -------------------------------------------
    static controladorCliente  ctrlCliente  = new controladorCliente();
    static ControladorEmpleado ctrlEmpleado = new ControladorEmpleado();
    static ControladorPlan     ctrlPlan     = new ControladorPlan();
    static ControladorProducto ctrlProducto = new ControladorProducto();
    static ControladorVenta    ctrlVenta    = new ControladorVenta();
    static ControladorAdmin    ctrlAdmin    = new ControladorAdmin();
    



    
    static GestorCSV gestorCSV = new GestorCSV();
    static Scanner sc = new Scanner(System.in);

    // =====================================================================
    //  MAIN
    // =====================================================================
    public static void main(String[] args) {
        cargarDatosDemostracion();

        int opcion;
        do {
            menuPrincipal();
            opcion = leerInt("Seleccione una opcion");
            switch (opcion) {
                case 1 -> menuClientes();
                case 2 -> menuEmpleados();
                case 3 -> menuPlanes();
                case 4 -> menuProductos();
                case 5 -> menuVentas();
                case 6 -> menuAdministracion();
                case 0 -> System.out.println("\n  Hasta luego! Cerrando el sistema...\n");
                default -> System.out.println("  Opcion invalida. Intente de nuevo.");
            }
        } while (opcion != 0);

        sc.close();


    }

    }

    // =====================================================================
    //  MENU PRINCIPAL
    // =====================================================================
    static void menuPrincipal() {
        separador();
        System.out.println("         SISTEMA DE GESTION - GYM PRO");
        separador();
        System.out.println("  [1]  Gestion de Clientes");
        System.out.println("  [2]  Gestion de Empleados");
        System.out.println("  [3]  Gestion de Planes");
        System.out.println("  [4]  Gestion de Productos / Tienda");
        System.out.println("  [5]  Ventas");
        System.out.println("  [6]  Administracion / Estadisticas");
        System.out.println("  [0]  Salir");
        separador();
    }

    // =====================================================================
    //  MENU CLIENTES
    // =====================================================================
    static void menuClientes() {
        int op;
        do {
            separador();
            System.out.println("          GESTION DE CLIENTES");
            separador();
            System.out.println("  [1]  Registrar nuevo cliente");
            System.out.println("  [2]  Buscar cliente por ID");
            System.out.println("  [3]  Listar todos los clientes");
            System.out.println("  [4]  Actualizar datos de cliente");
            System.out.println("  [5]  Eliminar cliente");
            System.out.println("  [6]  Calcular IMC de un cliente");
            System.out.println("  [7]  Cambiar plan de un cliente");
            System.out.println("  [8]  Cambiar estado activo/inactivo");
            System.out.println("  [0]  Volver al menu principal");
            separador();
            op = leerInt("Seleccione");

            switch (op) {
                case 1 -> registrarCliente();
                case 2 -> buscarCliente();
                case 3 -> listarClientes();
                case 4 -> actualizarCliente();
                case 5 -> eliminarCliente();
                case 6 -> calcularIMC();
                case 7 -> cambiarPlanCliente();
                case 8 -> cambiarEstadoCliente();
                case 0 -> System.out.println("  Volviendo...");
                default -> System.out.println("  Opcion invalida.");
            }
        } while (op != 0);
    }

    static void registrarCliente() {
        titulo("REGISTRAR CLIENTE");

        List<Plan> planes = ctrlPlan.listarPlanes();
        if (planes.isEmpty()) {
            System.out.println("  No hay planes disponibles. Cree al menos un plan primero.");
            return;
        }

        System.out.print("  Nombre       : "); String nombre = sc.nextLine();
        int    edad      = leerInt("  Edad");
        System.out.print("  ID / Cedula  : "); String id     = sc.nextLine();
        System.out.print("  EPS          : "); String eps    = sc.nextLine();
        System.out.print("  Sexo (M/F)   : "); String sexo   = sc.nextLine();
        int    tel       = leerInt("  Telefono");
        System.out.print("  Correo       : "); String correo = sc.nextLine();
        float  peso      = leerFloat("  Peso (kg)");
        float  altura    = leerFloat("  Altura (m)");

        System.out.println("\n  Planes disponibles:");
        for (int i = 0; i < planes.size(); i++)
            System.out.printf("    [%d] %s - $%d (%s)%n", i + 1,
                    planes.get(i).getNombrePlan(),
                    planes.get(i).getPrecioPlan(),
                    planes.get(i).getDuracionPlan());

        int idx = leerInt("  Seleccione plan (numero)") - 1;
        if (idx < 0 || idx >= planes.size()) { System.out.println("  Plan invalido."); return; }
        Plan planElegido = planes.get(idx);

        Cliente c = new Cliente(nombre, edad, id, eps, sexo, tel, correo, planElegido, peso, altura, true);
        ctrlCliente.agregarCliente(c);
        System.out.println("\n  [OK] Cliente registrado exitosamente.");
        planElegido.pagarPlan(sc);
    }

    static void buscarCliente() {
        titulo("BUSCAR CLIENTE");
        System.out.print("  Ingrese ID del cliente: "); String id = sc.nextLine();
        Cliente c = ctrlCliente.buscarCliente(id);
        if (c == null) System.out.println("  Cliente no encontrado.");
        else           c.mostrarInfo();
    }

    static void listarClientes() {
        titulo("LISTADO DE CLIENTES");
        List<Cliente> lista = ctrlCliente.listarClientes();
        if (lista.isEmpty()) { System.out.println("  No hay clientes registrados."); return; }
        for (Cliente c : lista) {
            System.out.printf("  > %-20s | ID: %-10s | Plan: %-15s | Estado: %s%n",
                    c.getNombre(), c.getId(),
                    c.getPlanContratado() != null ? c.getPlanContratado().getNombrePlan() : "Sin plan",
                    c.isEstado() ? "ACTIVO" : "INACTIVO");
        }
    }

    static void actualizarCliente() {
        titulo("ACTUALIZAR CLIENTE");
        System.out.print("  ID del cliente a actualizar: "); String id = sc.nextLine();
        Cliente viejo = ctrlCliente.buscarCliente(id);
        if (viejo == null) { System.out.println("  Cliente no encontrado."); return; }

        System.out.println("  Nuevo telefono (Enter para mantener " + viejo.getTelefono() + "): ");
        String telStr = sc.nextLine().trim();
        if (!telStr.isEmpty()) viejo.setTelefono(Integer.parseInt(telStr));

        System.out.println("  Nuevo correo (Enter para mantener " + viejo.getCorreo() + "): ");
        String correo = sc.nextLine().trim();
        if (!correo.isEmpty()) viejo.setCorreo(correo);

        System.out.println("  Nueva EPS (Enter para mantener " + viejo.getEps() + "): ");
        String eps = sc.nextLine().trim();
        if (!eps.isEmpty()) viejo.setEps(eps);

        System.out.println("  [OK] Datos actualizados.");
    }

    static void eliminarCliente() {
        titulo("ELIMINAR CLIENTE");
        System.out.print("  ID del cliente a eliminar: "); String id = sc.nextLine();
        if (ctrlCliente.eliminarCliente(id)) System.out.println("  [OK] Cliente eliminado.");
        else                                 System.out.println("  Cliente no encontrado.");
    }

    static void calcularIMC() {
        titulo("CALCULAR IMC");
        System.out.print("  ID del cliente: "); String id = sc.nextLine();
        Cliente c = ctrlCliente.buscarCliente(id);
        if (c == null) System.out.println("  Cliente no encontrado.");
        else           c.calcIMC();
    }

    static void cambiarPlanCliente() {
        titulo("CAMBIAR PLAN DE CLIENTE");
        System.out.print("  ID del cliente: "); String id = sc.nextLine();
        Cliente c = ctrlCliente.buscarCliente(id);
        if (c == null) { System.out.println("  Cliente no encontrado."); return; }

        List<Plan> planes = ctrlPlan.listarPlanes();
        if (planes.isEmpty()) { System.out.println("  No hay planes disponibles."); return; }

        System.out.println("\n  Planes disponibles:");
        for (int i = 0; i < planes.size(); i++)
            System.out.printf("    [%d] %s - $%d%n", i + 1,
                    planes.get(i).getNombrePlan(), planes.get(i).getPrecioPlan());

        int idx = leerInt("  Seleccione nuevo plan") - 1;
        if (idx < 0 || idx >= planes.size()) { System.out.println("  Plan invalido."); return; }

        ctrlPlan.asignarPlan(c, planes.get(idx));
        System.out.println("  [OK] Plan actualizado a: " + planes.get(idx).getNombrePlan());
        planes.get(idx).pagarPlan(sc);
    }

    static void cambiarEstadoCliente() {
        titulo("CAMBIAR ESTADO CLIENTE");
        System.out.print("  ID del cliente: "); String id = sc.nextLine();
        Cliente c = ctrlCliente.buscarCliente(id);
        if (c == null) { System.out.println("  Cliente no encontrado."); return; }
        c.setEstado(!c.isEstado());
        System.out.println("  [OK] Estado cambiado a: " + (c.isEstado() ? "ACTIVO" : "INACTIVO"));
    }

    // =====================================================================
    //  MENU EMPLEADOS
    // =====================================================================
    static void menuEmpleados() {
        int op;
        do {
            separador();
            System.out.println("          GESTION DE EMPLEADOS");
            separador();
            System.out.println("  [1]  Registrar nuevo empleado");
            System.out.println("  [2]  Buscar empleado por ID");
            System.out.println("  [3]  Listar todos los empleados");
            System.out.println("  [4]  Agregar estudio a empleado");
            System.out.println("  [5]  Registrar turno de empleado");
            System.out.println("  [6]  Ver historial de turnos");
            System.out.println("  [7]  Eliminar empleado");
            System.out.println("  [0]  Volver");
            separador();
            op = leerInt("Seleccione");

            switch (op) {
                case 1 -> registrarEmpleado();
                case 2 -> buscarEmpleado();
                case 3 -> listarEmpleados();
                case 4 -> agregarEstudio();
                case 5 -> registrarTurno();
                case 6 -> verTurnos();
                case 7 -> eliminarEmpleado();
                case 0 -> System.out.println("  Volviendo...");
                default -> System.out.println("  Opcion invalida.");
            }
        } while (op != 0);
    }

    static void registrarEmpleado() {
        titulo("REGISTRAR EMPLEADO");
        System.out.print("  Nombre   : "); String nombre = sc.nextLine();
        int    edad = leerInt("  Edad");
        System.out.print("  ID       : "); String id     = sc.nextLine();
        System.out.print("  EPS      : "); String eps    = sc.nextLine();
        System.out.print("  Sexo     : "); String sexo   = sc.nextLine();
        int    tel  = leerInt("  Telefono");
        System.out.print("  Correo   : "); String correo = sc.nextLine();

        Empleado e = new Empleado(nombre, edad, id, eps, sexo, tel, correo);
        ctrlEmpleado.agregarEmpleado(e);
        System.out.println("  [OK] Empleado registrado.");
    }

    static void buscarEmpleado() {
        titulo("BUSCAR EMPLEADO");
        System.out.print("  ID del empleado: "); String id = sc.nextLine();
        Empleado e = ctrlEmpleado.buscarEmpleado(id);
        if (e == null) System.out.println("  Empleado no encontrado.");
        else           e.mostrarInfo();
    }

    static void listarEmpleados() {
        titulo("LISTADO DE EMPLEADOS");
        List<Empleado> lista = ctrlEmpleado.listarEmpleados();
        if (lista.isEmpty()) { System.out.println("  No hay empleados registrados."); return; }
        for (Empleado e : lista)
            System.out.printf("  > %-20s | ID: %-10s | Tel: %d%n",
                    e.getNombre(), e.getId(), e.getTelefono());
    }

    static void agregarEstudio() {
        titulo("AGREGAR ESTUDIO A EMPLEADO");
        System.out.print("  ID del empleado: "); String id = sc.nextLine();
        Empleado e = ctrlEmpleado.buscarEmpleado(id);
        if (e == null) { System.out.println("  Empleado no encontrado."); return; }
        System.out.print("  Descripcion del estudio/certificado: "); String est = sc.nextLine();
        e.setEstudios(est);
        System.out.println("  [OK] Estudio agregado.");
    }

    static void registrarTurno() {
        titulo("REGISTRAR TURNO");
        System.out.print("  ID del empleado: "); String id = sc.nextLine();
        Empleado e = ctrlEmpleado.buscarEmpleado(id);
        if (e == null) { System.out.println("  Empleado no encontrado."); return; }
        e.registrarTurno();
    }

    static void verTurnos() {
        titulo("HISTORIAL DE TURNOS");
        System.out.print("  ID del empleado: "); String id = sc.nextLine();
        Empleado e = ctrlEmpleado.buscarEmpleado(id);
        if (e == null) { System.out.println("  Empleado no encontrado."); return; }
        List<RegistroTurno> turnos = e.getHistorialTurnos();
        if (turnos.isEmpty()) { System.out.println("  Sin turnos registrados."); return; }
        System.out.println("\n  Turnos de " + e.getNombre() + ":");
        for (RegistroTurno t : turnos)
            System.out.println("    > " + t);
    }

    static void eliminarEmpleado() {
        titulo("ELIMINAR EMPLEADO");
        System.out.print("  ID del empleado: "); String id = sc.nextLine();
        if (ctrlEmpleado.eliminarEmpleado(id)) System.out.println("  [OK] Empleado eliminado.");
        else                                   System.out.println("  Empleado no encontrado.");
    }

    // =====================================================================
    //  MENU PLANES
    // =====================================================================
    static void menuPlanes() {
        int op;
        do {
            separador();
            System.out.println("          GESTION DE PLANES");
            separador();
            System.out.println("  [1]  Crear nuevo plan");
            System.out.println("  [2]  Listar planes");
            System.out.println("  [0]  Volver");
            separador();
            op = leerInt("Seleccione");

            switch (op) {
                case 1 -> crearPlan();
                case 2 -> listarPlanes();
                case 0 -> System.out.println("  Volviendo...");
                default -> System.out.println("  Opcion invalida.");
            }
        } while (op != 0);
    }

    static void crearPlan() {
        titulo("CREAR PLAN");
        System.out.print("  Nombre del plan    : "); String nombre   = sc.nextLine();
        int    precio    = leerInt("  Precio ($)");
        System.out.print("  Duracion           : "); String duracion = sc.nextLine();
        int    invitados = leerInt("  Invitados por mes");
        ctrlPlan.crearPlan(new Plan(nombre, precio, duracion, invitados));
        System.out.println("  [OK] Plan creado.");
    }

    static void listarPlanes() {
        titulo("PLANES DISPONIBLES");
        List<Plan> planes = ctrlPlan.listarPlanes();
        if (planes.isEmpty()) { System.out.println("  No hay planes registrados."); return; }
        System.out.printf("  %-20s %-10s %-12s %s%n", "NOMBRE", "PRECIO", "DURACION", "INVITADOS/MES");
        separador();
        for (Plan p : planes)
            System.out.printf("  %-20s $%-9d %-12s %d%n",
                    p.getNombrePlan(), p.getPrecioPlan(),
                    p.getDuracionPlan(), p.getNumInvitadosMes());
    }

    // =====================================================================
    //  MENU PRODUCTOS / TIENDA
    // =====================================================================
    static void menuProductos() {
        int op;
        do {
            separador();
            System.out.println("          GESTION DE PRODUCTOS");
            separador();
            System.out.println("  [1]  Agregar producto");
            System.out.println("  [2]  Listar inventario");
            System.out.println("  [3]  Buscar producto por nombre");
            System.out.println("  [4]  Eliminar producto");
            System.out.println("  [0]  Volver");
            separador();
            op = leerInt("Seleccione");

            switch (op) {
                case 1 -> agregarProducto();
                case 2 -> listarProductos();
                case 3 -> buscarProducto();
                case 4 -> eliminarProducto();
                case 0 -> System.out.println("  Volviendo...");
                default -> System.out.println("  Opcion invalida.");
            }
        } while (op != 0);
    }

    static void agregarProducto() {
        titulo("AGREGAR PRODUCTO");
        System.out.print("  Nombre       : "); String nombre = sc.nextLine();
        System.out.print("  Tipo         : "); String tipo   = sc.nextLine();
        int codigo = leerInt("  Codigo");
        int stock  = leerInt("  Stock");
        int precio = leerInt("  Precio ($)");
        ctrlProducto.agregarProducto(new Producto(nombre, tipo, codigo, stock, precio));
        System.out.println("  [OK] Producto agregado al inventario.");
    }

    static void listarProductos() {
        titulo("INVENTARIO");
        List<Producto> lista = ctrlProducto.listarProductos();
        if (lista.isEmpty()) { System.out.println("  No hay productos en inventario."); return; }
        System.out.printf("  %-20s %-15s %-8s %-8s %s%n", "NOMBRE", "TIPO", "CODIGO", "STOCK", "PRECIO");
        separador();
        for (Producto p : lista)
            System.out.printf("  %-20s %-15s %-8d %-8d $%d%n",
                    p.getNombre(), p.getTipoProducto(),
                    p.getCodigo(), p.getStock(), p.getPrecio());
    }

    static void buscarProducto() {
        titulo("BUSCAR PRODUCTO");
        System.out.print("  Nombre del producto: "); String nombre = sc.nextLine();
        Producto p = ctrlProducto.buscarProducto(nombre);
        if (p == null) System.out.println("  Producto no encontrado.");
        else           System.out.println("  " + p);
    }

    static void eliminarProducto() {
        titulo("ELIMINAR PRODUCTO");
        System.out.print("  Nombre del producto: "); String nombre = sc.nextLine();
        if (ctrlProducto.eliminarProducto(nombre)) System.out.println("  [OK] Producto eliminado.");
        else                                        System.out.println("  Producto no encontrado.");
    }

    // =====================================================================
    //  MENU VENTAS
    // =====================================================================
    static void menuVentas() {
        int op;
        do {
            separador();
            System.out.println("          VENTAS");
            separador();
            System.out.println("  [1]  Registrar venta");
            System.out.println("  [2]  Historial de ventas");
            System.out.println("  [0]  Volver");
            separador();
            op = leerInt("Seleccione");

            switch (op) {
                case 1 -> registrarVenta();
                case 2 -> historialVentas();
                case 0 -> System.out.println("  Volviendo...");
                default -> System.out.println("  Opcion invalida.");
            }
        } while (op != 0);
    }

    static void registrarVenta() {
        titulo("REGISTRAR VENTA");
        List<Producto> lista = ctrlProducto.listarProductos();
        if (lista.isEmpty()) { System.out.println("  No hay productos en inventario."); return; }

        System.out.println("\n  Productos disponibles:");
        for (int i = 0; i < lista.size(); i++)
            System.out.printf("    [%d] %-20s | Stock: %-4d | $%d%n",
                    i + 1, lista.get(i).getNombre(),
                    lista.get(i).getStock(), lista.get(i).getPrecio());

        int idx = leerInt("  Seleccione producto") - 1;
        if (idx < 0 || idx >= lista.size()) { System.out.println("  Seleccion invalida."); return; }

        Producto prod = lista.get(idx);
        int cantidad  = leerInt("  Cantidad a vender");
        if (cantidad > prod.getStock()) {
            System.out.println("  Stock insuficiente. Disponible: " + prod.getStock());
            return;
        }

        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        VentaProducto venta = new VentaProducto(prod, cantidad, fecha);
        double total = ctrlVenta.procesarVenta(venta);

        prod.setStock(prod.getStock() - cantidad);

        venta.mostrarResumen();
        System.out.println("\n  [OK] Venta registrada. Total cobrado: $" + total);
    }

    static void historialVentas() {
        titulo("HISTORIAL DE VENTAS");
        List<VentaProducto> ventas = ctrlVenta.listarVentas();
        if (ventas.isEmpty()) { System.out.println("  No hay ventas registradas."); return; }
        double total = 0;
        for (VentaProducto v : ventas) {
            v.mostrarResumen();
            total += v.calcularCosto();
            System.out.println("  " + "-".repeat(38));
        }
        System.out.printf("%n  TOTAL DE INGRESOS POR VENTAS: $%.2f%n", total);
    }

    // =====================================================================
    //  MENU ADMINISTRACION
    // =====================================================================
    static void menuAdministracion() {
        int op;
        do {
            separador();
            System.out.println("          ADMINISTRACION");
            separador();
            System.out.println("  [1]  Ver estadisticas generales");
            System.out.println("  [2]  Clientes activos / inactivos");
            System.out.println("  [0]  Volver");
            separador();
            op = leerInt("Seleccione");

            switch (op) {
                case 1 -> estadisticasGenerales();
                case 2 -> estadoClientes();
                case 0 -> System.out.println("  Volviendo...");
                default -> System.out.println("  Opcion invalida.");
            }
        } while (op != 0);
    }

    static void estadisticasGenerales() {
        titulo("ESTADISTICAS GENERALES");
        int    totalClientes  = ctrlAdmin.totalClientes(ctrlCliente.listarClientes());
        double totalIngresos  = ctrlAdmin.totalIngresos(ctrlVenta.listarVentas());
        int    totalEmpleados = ctrlEmpleado.listarEmpleados().size();
        int    totalPlanes    = ctrlPlan.listarPlanes().size();
        int    totalProductos = ctrlProducto.listarProductos().size();

        System.out.println("  Clientes registrados  : " + totalClientes);
        System.out.println("  Empleados registrados : " + totalEmpleados);
        System.out.println("  Planes disponibles    : " + totalPlanes);
        System.out.println("  Productos en tienda   : " + totalProductos);
        System.out.printf( "  Ingresos por ventas   : $%.2f%n", totalIngresos);
    }

    static void estadoClientes() {
        titulo("ESTADO DE CLIENTES");
        List<Cliente> clientes = ctrlCliente.listarClientes();
        long activos   = clientes.stream().filter(Cliente::isEstado).count();
        long inactivos = clientes.size() - activos;
        System.out.println("  Activos  : " + activos);
        System.out.println("  Inactivos: " + inactivos);
        System.out.println();
        for (Cliente c : clientes)
            System.out.printf("  > %-20s  [%s]%n",
                    c.getNombre(), c.isEstado() ? "ACTIVO" : "INACTIVO");
    }

    // =====================================================================
    //  DATOS DE DEMOSTRACION
    // =====================================================================
    static void cargarDatosDemostracion() {
        Plan basico   = new Plan("Basico",   80000,  "1 mes", 2);
        Plan estandar = new Plan("Estandar", 140000, "1 mes", 4);
        Plan premium  = new Plan("Premium",  220000, "1 mes", 8);
        ctrlPlan.crearPlan(basico);
        ctrlPlan.crearPlan(estandar);
        ctrlPlan.crearPlan(premium);

        ctrlCliente.agregarCliente(new Cliente("Carlos Perez",  28, "1001", "Sura",    "M", 300123456, "carlos@mail.com", basico,   75f, 1.75f, true));
        ctrlCliente.agregarCliente(new Cliente("Laura Gomez",   34, "1002", "Sanitas", "F", 300987654, "laura@mail.com",  estandar, 60f, 1.62f, true));
        ctrlCliente.agregarCliente(new Cliente("Miguel Torres", 22, "1003", "Nueva EPS","M",300456123, "miguel@mail.com", premium,  85f, 1.80f, false));

        Empleado e1 = new Empleado("Ana Rueda",   30, "E001", "Coomeva", "F", 310111223, "ana@gym.com");
        e1.setEstudios("Licenciatura en Educacion Fisica");
        ctrlEmpleado.agregarEmpleado(e1);

        Empleado e2 = new Empleado("Jorge Silva", 25, "E002", "Sura", "M", 320444556, "jorge@gym.com");
        e2.setEstudios("Tecnico en Ventas");
        ctrlEmpleado.agregarEmpleado(e2);

        ctrlProducto.agregarProducto(new Producto("Proteina Whey",   "Suplemento", 101, 50, 120000));
        ctrlProducto.agregarProducto(new Producto("Creatina 500g",   "Suplemento", 102, 30,  80000));
        ctrlProducto.agregarProducto(new Producto("Guantes Gym",     "Accesorio",  103, 20,  35000));
        ctrlProducto.agregarProducto(new Producto("Botella Termica", "Accesorio",  104, 15,  45000));
    }

    // =====================================================================
    //  UTILITARIOS
    // =====================================================================
    static void separador() {
        System.out.println("  " + "=".repeat(46));
    }

    static void titulo(String texto) {
        System.out.println();
        separador();
        System.out.println("   " + texto);
        separador();
    }

    static int leerInt(String msg) {
        while (true) {
            try {
                System.out.print("  " + msg + ": ");
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un numero entero valido.");
            }
        }
    }

    static float leerFloat(String msg) {
        while (true) {
            try {
                System.out.print("  " + msg + ": ");
                return Float.parseFloat(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un numero decimal valido.");
            }
        }
    }
}