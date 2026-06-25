package com.mycompany.sistemagym.proyecto.vista;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class PanelVentas extends JPanel {

    private final VentanaPrincipal vp;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JComboBox<String> cbProducto;
    private JTextField fCantidad;
    private JLabel lblPrecioUnit;   // precio del producto seleccionado
    private JLabel lblStockDisp;    // stock del producto seleccionado
    private JLabel lblPreviewTotal; // total en el formulario de nueva venta
    private JLabel lblResumenTotal; // total acumulado en el historial

    public PanelVentas(VentanaPrincipal vp) {
        this.vp = vp;
        setBackground(Estilos.COLOR_FONDO);
        setLayout(new BorderLayout(16,16));
        setBorder(new EmptyBorder(24,24,24,24));
        buildUI();
    }

    private void buildUI() {
        add(Estilos.crearTitulo("💰  Ventas"), BorderLayout.NORTH);

        // Panel izquierdo: historial
        JPanel izq = new JPanel(new BorderLayout(0,8));
        izq.setOpaque(false);
        JLabel lblHist = Estilos.crearTitulo("Historial de ventas");
        lblHist.setFont(Estilos.FUENTE_SUBTITULO);
        izq.add(lblHist, BorderLayout.NORTH);

        String[] cols = {"Producto","Cantidad","Total ($)","Fecha","Descripción pago"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        Estilos.estilizarTabla(tabla);

        // Label total del historial
        lblResumenTotal = new JLabel("Total ingresos: $0");
        lblResumenTotal.setForeground(Estilos.COLOR_EXITO);
        lblResumenTotal.setFont(new Font("Segoe UI", Font.BOLD, 15));
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setOpaque(false);
        totalPanel.add(lblResumenTotal);

        izq.add(Estilos.crearScroll(tabla), BorderLayout.CENTER);
        izq.add(totalPanel, BorderLayout.SOUTH);

        // Panel derecho: nueva venta
        JPanel der = crearFormularioVenta();
        der.setPreferredSize(new Dimension(300,0));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, izq, der);
        split.setDividerLocation(720); split.setDividerSize(4);
        split.setOpaque(false); split.setBorder(null);
        add(split, BorderLayout.CENTER);
    }

    private JPanel crearFormularioVenta() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Estilos.COLOR_PANEL);
        panel.setBorder(new CompoundBorder(
            new LineBorder(Estilos.COLOR_BORDE,1),new EmptyBorder(16,16,16,16)));

        JLabel lbl = Estilos.crearTitulo("Nueva venta");
        lbl.setFont(Estilos.FUENTE_SUBTITULO);
        panel.add(lbl);
        panel.add(Box.createVerticalStrut(16));

        cbProducto = Estilos.crearCombo();
        cbProducto.setMaximumSize(new Dimension(260,36));
        cbProducto.addActionListener(e -> actualizarInfoProducto());

        fCantidad = Estilos.crearCampo(8);
        fCantidad.setMaximumSize(new Dimension(260,36));
        fCantidad.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
            public void insertUpdate(javax.swing.event.DocumentEvent e){calcularPreview();}
            public void removeUpdate(javax.swing.event.DocumentEvent e){calcularPreview();}
            public void changedUpdate(javax.swing.event.DocumentEvent e){calcularPreview();}
        });

        lblPrecioUnit = Estilos.crearEtiqueta("Precio unitario: —");
        lblPrecioUnit.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblStockDisp = Estilos.crearEtiqueta("Stock disponible: —");
        lblStockDisp.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTotalVenta = Estilos.crearEtiqueta("Total a cobrar:");
        lblTotalVenta.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblPreviewTotal = new JLabel("$0");
        lblPreviewTotal.setForeground(Estilos.COLOR_EXITO);
        lblPreviewTotal.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblPreviewTotal.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel filaProducto  = Estilos.filaFormulario("Producto:", cbProducto);
        JPanel filaCantidad  = Estilos.filaFormulario("Cantidad:", fCantidad);
        filaProducto.setMaximumSize(new Dimension(260,36));
        filaCantidad.setMaximumSize(new Dimension(260,36));

        panel.add(filaProducto);
        panel.add(Box.createVerticalStrut(10));
        panel.add(filaCantidad);
        panel.add(Box.createVerticalStrut(12));
        panel.add(lblPrecioUnit);
        panel.add(Box.createVerticalStrut(4));
        panel.add(lblStockDisp);
        panel.add(Box.createVerticalStrut(16));
        panel.add(Estilos.crearSeparador());
        panel.add(Box.createVerticalStrut(12));
        panel.add(lblTotalVenta);
        panel.add(Box.createVerticalStrut(4));
        panel.add(lblPreviewTotal);
        panel.add(Box.createVerticalStrut(20));

        JButton btnVender = Estilos.crearBotonExito("✅  Registrar venta");
        btnVender.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVender.setMaximumSize(new Dimension(260,42));
        btnVender.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnVender.addActionListener(e -> registrarVenta());
        panel.add(btnVender);

        return panel;
    }

    // ── Lógica ────────────────────────────────────────────────────────────

    private void actualizarInfoProducto() {
        Producto p = getProductoSeleccionado();
        if (p == null) {
            lblPrecioUnit.setText("Precio unitario: —");
            lblStockDisp.setText("Stock disponible: —");
            return;
        }
        lblPrecioUnit.setText("Precio unitario: $" + p.getPrecio());
        lblStockDisp.setText("Stock disponible: " + p.getStock());
        calcularPreview();
    }

    private void calcularPreview() {
        Producto p = getProductoSeleccionado(); if (p == null) return;
        try {
            int cant = Integer.parseInt(fCantidad.getText().trim());
            double total = cant * (double) p.getPrecio();
            lblPreviewTotal.setText("$" + String.format("%,.0f", total));
            lblPreviewTotal.setForeground(cant > p.getStock() ? Estilos.COLOR_PELIGRO : Estilos.COLOR_EXITO);
        } catch (NumberFormatException ex) {
            lblPreviewTotal.setText("$0");
            lblPreviewTotal.setForeground(Estilos.COLOR_EXITO);
        }
    }

    private void registrarVenta() {
        Producto p = getProductoSeleccionado();
        if (p == null) { vp.mostrarMensaje("Seleccione un producto.", false); return; }
        int cantidad;
        try {
            cantidad = Integer.parseInt(fCantidad.getText().trim());
        } catch (NumberFormatException ex) {
            vp.mostrarMensaje("Cantidad inválida.", false); return;
        }
        if (cantidad <= 0) { vp.mostrarMensaje("La cantidad debe ser mayor a 0.", false); return; }

        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        VentaProducto venta = new VentaProducto(p, cantidad, fecha);

        try {
            venta.validarStock();  // lanza StockInsuficienteException
            double total = vp.ctrlVenta.procesarVenta(venta);
            p.setStock(p.getStock() - cantidad);
            refrescar();
            fCantidad.setText("");
            vp.mostrarMensaje(
                String.format("✅ Venta registrada\n%s\nTotal: $%.2f", venta.getDescripcionPago(), total),
                true
            );
        } catch (StockInsuficienteException ex) {
            vp.mostrarMensaje(
                String.format("⚠ Stock insuficiente\nDisponible: %d  |  Solicitado: %d",
                    ex.getStockDisponible(), ex.getCantidadSolicitada()),
                false
            );
        } finally {
            // finally demuestra el criterio de manejo de excepciones
            System.out.println("[Venta] Proceso finalizado — producto: " + p.getNombre());
        }
    }

    private Producto getProductoSeleccionado() {
        String nombre = (String) cbProducto.getSelectedItem();
        if (nombre == null) return null;
        return vp.ctrlProducto.buscarProducto(nombre);
    }

    public void refrescar() {
        // Refrescar combo
        String sel = (String) cbProducto.getSelectedItem();
        cbProducto.removeAllItems();
        for (Producto p : vp.ctrlProducto.listarProductos()) cbProducto.addItem(p.getNombre());
        if (sel != null) cbProducto.setSelectedItem(sel);
        actualizarInfoProducto();

        // Refrescar tabla historial
        modeloTabla.setRowCount(0);
        double totalIngresos = 0;
        for (VentaProducto v : vp.ctrlVenta.listarVentas()) {
            double costo = v.calcularCosto();
            totalIngresos += costo;
            modeloTabla.addRow(new Object[]{
                v.getProducto().getNombre(),
                v.getCantidad(),
                String.format("$%,.0f", costo),
                v.getFecha(),
                v.getDescripcionPago()  // muestra IPagable en acción
            });
        }
        lblResumenTotal.setText("Total ingresos: $" + String.format("%,.0f", totalIngresos));
    }
}
