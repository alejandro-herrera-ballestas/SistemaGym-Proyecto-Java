package com.mycompany.sistemagym.proyecto.vista;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import com.mycompany.sistemagym.proyecto.controladores.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

public class PanelAdmin extends JPanel {

    private final VentanaPrincipal vp;
    private JLabel lblClientes, lblEmpleados, lblPlanes, lblProductos, lblIngresos, lblActivos;
    private JTextArea areaReporte;

    public PanelAdmin(VentanaPrincipal vp) {
        this.vp = vp;
        setBackground(Estilos.COLOR_FONDO);
        setLayout(new BorderLayout(16, 16));
        setBorder(new EmptyBorder(24, 24, 24, 24));
        buildUI();
    }

    private void buildUI() {
        // Título
        JLabel titulo = Estilos.crearTitulo("📊  Dashboard General");
        titulo.setBorder(new EmptyBorder(0, 0, 16, 0));
        add(titulo, BorderLayout.NORTH);

        // Centro: tarjetas + reporte
        JPanel centro = new JPanel(new BorderLayout(16, 16));
        centro.setOpaque(false);

        // Fila de tarjetas KPI
        JPanel filaTarjetas = new JPanel(new GridLayout(1, 6, 12, 0));
        filaTarjetas.setOpaque(false);

        lblClientes  = crearTarjeta(filaTarjetas, "👤 Clientes",  "0", Estilos.COLOR_ACENTO);
        lblEmpleados = crearTarjeta(filaTarjetas, "👷 Empleados", "0", new Color(14,165,233));
        lblActivos   = crearTarjeta(filaTarjetas, "✅ Activos",   "0", Estilos.COLOR_EXITO);
        lblPlanes    = crearTarjeta(filaTarjetas, "📋 Planes",    "0", new Color(168,85,247));
        lblProductos = crearTarjeta(filaTarjetas, "🛍 Productos", "0", Estilos.COLOR_ADVERTENCIA);
        lblIngresos  = crearTarjeta(filaTarjetas, "💰 Ingresos",  "$0", new Color(236,72,153));

        centro.add(filaTarjetas, BorderLayout.NORTH);

        // Reporte textual
        JPanel panelReporte = new JPanel(new BorderLayout(0, 8));
        panelReporte.setBackground(Estilos.COLOR_PANEL);
        panelReporte.setBorder(new CompoundBorder(
            new LineBorder(Estilos.COLOR_BORDE, 1),
            new EmptyBorder(16, 16, 16, 16)
        ));

        JLabel lblReporteTitle = Estilos.crearTitulo("Reporte del sistema");
        lblReporteTitle.setFont(Estilos.FUENTE_SUBTITULO);
        panelReporte.add(lblReporteTitle, BorderLayout.NORTH);

        areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        areaReporte.setBackground(Estilos.COLOR_INPUT_FONDO);
        areaReporte.setForeground(Estilos.COLOR_TEXTO);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaReporte.setBorder(new EmptyBorder(12, 12, 12, 12));
        panelReporte.add(Estilos.crearScroll(areaReporte), BorderLayout.CENTER);

        JButton btnRefrescar = Estilos.crearBotonPrimario("↻  Actualizar reporte");
        btnRefrescar.addActionListener(e -> refrescar());
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBtn.setOpaque(false);
        panelBtn.add(btnRefrescar);
        panelReporte.add(panelBtn, BorderLayout.SOUTH);

        centro.add(panelReporte, BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);
    }

    /** Crea una tarjeta KPI y retorna la etiqueta del valor para actualizarla */
    private JLabel crearTarjeta(JPanel padre, String titulo, String valor, Color color) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Estilos.COLOR_PANEL);
        tarjeta.setBorder(new CompoundBorder(
            new LineBorder(color, 1, true),
            new EmptyBorder(16, 12, 16, 12)
        ));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setForeground(Estilos.COLOR_TEXTO_SUAVE);
        lblTitulo.setFont(Estilos.FUENTE_PEQUEÑA);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblValor = new JLabel(valor, SwingConstants.CENTER);
        lblValor.setForeground(color);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        tarjeta.add(Box.createVerticalStrut(4));
        tarjeta.add(lblTitulo);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(lblValor);
        padre.add(tarjeta);
        return lblValor;
    }

    public void refrescar() {
        ControladorAdmin ctrlAdmin = vp.ctrlAdmin;
        ctrlAdmin.setDatos(
            vp.ctrlCliente.listarClientes(),
            vp.ctrlEmpleado.listarEmpleados(),
            vp.ctrlPlan.listarPlanes(),
            vp.ctrlProducto.listarProductos(),
            vp.ctrlVenta.listarVentas()
        );

        List<com.mycompany.sistemagym.proyecto.Modelos.Cliente> clientes = vp.ctrlCliente.listarClientes();
        long activos = clientes.stream()
            .filter(com.mycompany.sistemagym.proyecto.Modelos.Cliente::isEstado).count();
        double ingresos = vp.ctrlVenta.listarVentas().stream()
            .mapToDouble(com.mycompany.sistemagym.proyecto.Modelos.VentaProducto::calcularCosto).sum();

        lblClientes.setText(String.valueOf(clientes.size()));
        lblEmpleados.setText(String.valueOf(vp.ctrlEmpleado.listarEmpleados().size()));
        lblActivos.setText(String.valueOf(activos));
        lblPlanes.setText(String.valueOf(vp.ctrlPlan.listarPlanes().size()));
        lblProductos.setText(String.valueOf(vp.ctrlProducto.listarProductos().size()));
        lblIngresos.setText(String.format("$%,.0f", ingresos));

        areaReporte.setText(ctrlAdmin.generarReporte());
    }
}
