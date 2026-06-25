package com.mycompany.sistemagym.proyecto.vista;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import com.mycompany.sistemagym.proyecto.controladores.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class VentanaPrincipal extends JFrame {

    public final controladorCliente   ctrlCliente;
    public final ControladorEmpleado  ctrlEmpleado;
    public final ControladorPlan      ctrlPlan;
    public final ControladorProducto  ctrlProducto;
    public final ControladorVenta     ctrlVenta;
    public final ControladorAdmin     ctrlAdmin;
    public final GestorCSV            gestorCSV;

    private JPanel  areaPaneles;
    private CardLayout cardLayout;

    private PanelClientes   panelClientes;
    private PanelEmpleados  panelEmpleados;
    private PanelPlanes     panelPlanes;
    private PanelProductos  panelProductos;
    private PanelVentas     panelVentas;
    private PanelAdmin      panelAdmin;

    private HiloGuardadoAutomatico hiloGuardado;

    public VentanaPrincipal(controladorCliente ctrlCliente,
                            ControladorEmpleado ctrlEmpleado,
                            ControladorPlan ctrlPlan,
                            ControladorProducto ctrlProducto,
                            ControladorVenta ctrlVenta,
                            ControladorAdmin ctrlAdmin,
                            GestorCSV gestorCSV) {
        this.ctrlCliente  = ctrlCliente;
        this.ctrlEmpleado = ctrlEmpleado;
        this.ctrlPlan     = ctrlPlan;
        this.ctrlProducto = ctrlProducto;
        this.ctrlVenta    = ctrlVenta;
        this.ctrlAdmin    = ctrlAdmin;
        this.gestorCSV    = gestorCSV;
        initUI();
        iniciarHiloGuardado();
    }

    private void initUI() {
        setTitle("GymPro — Sistema de Gestión");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1100, 700);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { cerrarApp(); }
        });

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Estilos.COLOR_FONDO);
        setContentPane(root);

        root.add(crearSidebar(), BorderLayout.WEST);
        root.add(crearAreaPaneles(), BorderLayout.CENTER);

        mostrarPanel("ADMIN");
    }

    // ── Sidebar ──────────────────────────────────────────────────────────
    private JPanel crearSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Estilos.COLOR_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBorder(new EmptyBorder(0, 0, 0, 0));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Estilos.COLOR_ACENTO);
        header.setPreferredSize(new Dimension(200, 72));
        header.setMaximumSize(new Dimension(200, 72));
        header.setBorder(new EmptyBorder(14, 16, 14, 16));

        JLabel logoTxt = new JLabel("🏋 GymPro");
        logoTxt.setForeground(Color.WHITE);
        logoTxt.setFont(new Font("Segoe UI", Font.BOLD, 17));
        header.add(logoTxt, BorderLayout.CENTER);
        sidebar.add(header);

        sidebar.add(Box.createVerticalStrut(16));

        // Botones de navegación
        String[][] items = {
            {"📊", "Dashboard",  "ADMIN"},
            {"👤", "Clientes",   "CLIENTES"},
            {"👷", "Empleados",  "EMPLEADOS"},
            {"📋", "Planes",     "PLANES"},
            {"🛍", "Productos",  "PRODUCTOS"},
            {"💰", "Ventas",     "VENTAS"},
        };

        for (String[] item : items) {
            JButton btn = crearBotonSidebar(item[0] + "  " + item[1], item[2]);
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(4));
        }

        sidebar.add(Box.createVerticalGlue());

        // Botón salir
        JButton btnSalir = new JButton("⏻  Cerrar sesión");
        btnSalir.setBackground(new Color(127, 29, 29));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(Estilos.FUENTE_SIDEBAR);
        btnSalir.setBorderPainted(false);
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSalir.setOpaque(true);
        btnSalir.setMaximumSize(new Dimension(200, 44));
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.addActionListener(e -> cerrarApp());
        sidebar.add(btnSalir);
        sidebar.add(Box.createVerticalStrut(12));

        return sidebar;
    }

    private JButton crearBotonSidebar(String texto, String panel) {
        JButton btn = new JButton(texto);
        btn.setBackground(Estilos.COLOR_SIDEBAR);
        btn.setForeground(Estilos.COLOR_TEXTO_SUAVE);
        btn.setFont(Estilos.FUENTE_SIDEBAR);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setMaximumSize(new Dimension(200, 44));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(10, 20, 10, 10));

        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(30, 30, 50));
                btn.setForeground(Color.WHITE);
            }
            @Override public void mouseExited(MouseEvent e) {
                btn.setBackground(Estilos.COLOR_SIDEBAR);
                btn.setForeground(Estilos.COLOR_TEXTO_SUAVE);
            }
        });
        btn.addActionListener(e -> mostrarPanel(panel));
        return btn;
    }

    // ── Área de paneles ───────────────────────────────────────────────────
    private JPanel crearAreaPaneles() {
        cardLayout  = new CardLayout();
        areaPaneles = new JPanel(cardLayout);
        areaPaneles.setBackground(Estilos.COLOR_FONDO);

        panelAdmin     = new PanelAdmin(this);
        panelClientes  = new PanelClientes(this);
        panelEmpleados = new PanelEmpleados(this);
        panelPlanes    = new PanelPlanes(this);
        panelProductos = new PanelProductos(this);
        panelVentas    = new PanelVentas(this);

        areaPaneles.add(panelAdmin,     "ADMIN");
        areaPaneles.add(panelClientes,  "CLIENTES");
        areaPaneles.add(panelEmpleados, "EMPLEADOS");
        areaPaneles.add(panelPlanes,    "PLANES");
        areaPaneles.add(panelProductos, "PRODUCTOS");
        areaPaneles.add(panelVentas,    "VENTAS");

        return areaPaneles;
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(areaPaneles, nombre);
        // Refrescar datos al cambiar panel
        switch (nombre) {
            case "ADMIN"     -> panelAdmin.refrescar();
            case "CLIENTES"  -> panelClientes.refrescar();
            case "EMPLEADOS" -> panelEmpleados.refrescar();
            case "PLANES"    -> panelPlanes.refrescar();
            case "PRODUCTOS" -> panelProductos.refrescar();
            case "VENTAS"    -> panelVentas.refrescar();
        }
    }

    // ── Hilo de guardado ──────────────────────────────────────────────────
    private void iniciarHiloGuardado() {
        hiloGuardado = new HiloGuardadoAutomatico(
            gestorCSV,
            ctrlPlan.listarPlanes(),
            ctrlCliente.listarClientes(),
            ctrlEmpleado.listarEmpleados(),
            ctrlProducto.listarProductos(),
            ctrlVenta.listarVentas()
        );
        Thread hilo = new Thread(hiloGuardado);
        hilo.setDaemon(true);
        hilo.start();
    }

    private void cerrarApp() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Desea guardar y cerrar la aplicación?",
            "Cerrar GymPro",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            hiloGuardado.detener();
            hiloGuardado.guardarAhora();
            System.exit(0);
        } else if (confirm == JOptionPane.NO_OPTION) {
            hiloGuardado.detener();
            System.exit(0);
        }
    }

    // ── Utilidad: diálogo de mensaje ──────────────────────────────────────
    public void mostrarMensaje(String msg, boolean exito) {
        JOptionPane.showMessageDialog(this, msg,
            exito ? "Éxito" : "Error",
            exito ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
    }
}
