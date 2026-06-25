package com.mycompany.sistemagym.proyecto.vista;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class PanelProductos extends JPanel {

    private final VentanaPrincipal vp;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField fNombre, fTipo, fCodigo, fStock, fPrecio, fBuscar;

    public PanelProductos(VentanaPrincipal vp) {
        this.vp = vp;
        setBackground(Estilos.COLOR_FONDO);
        setLayout(new BorderLayout(16,16));
        setBorder(new EmptyBorder(24,24,24,24));
        buildUI();
    }

    private void buildUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(Estilos.crearTitulo("🛍  Inventario de Productos"), BorderLayout.WEST);

        JPanel busq = new JPanel(new FlowLayout(FlowLayout.RIGHT,8,0));
        busq.setOpaque(false);
        fBuscar = Estilos.crearCampo(16);
        JButton btnB = Estilos.crearBotonPrimario("🔍 Buscar");
        btnB.addActionListener(e -> buscar());
        JButton btnL = Estilos.crearBotonSecundario("✕");
        btnL.addActionListener(e -> { fBuscar.setText(""); refrescar(); });
        busq.add(Estilos.crearEtiqueta("Buscar:")); busq.add(fBuscar); busq.add(btnB); busq.add(btnL);
        header.add(busq, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        String[] cols = {"Nombre","Tipo","Código","Stock","Precio ($)"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        Estilos.estilizarTabla(tabla);
        tabla.getSelectionModel().addListSelectionListener(e -> cargarEnFormulario());

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            Estilos.crearScroll(tabla), crearFormulario());
        split.setDividerLocation(700); split.setDividerSize(4);
        split.setOpaque(false); split.setBorder(null);
        add(split, BorderLayout.CENTER);
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Estilos.COLOR_PANEL);
        panel.setBorder(new CompoundBorder(new LineBorder(Estilos.COLOR_BORDE,1),new EmptyBorder(16,16,16,16)));
        panel.setPreferredSize(new Dimension(290,0));

        JLabel lbl = Estilos.crearTitulo("Datos del producto");
        lbl.setFont(Estilos.FUENTE_SUBTITULO);
        panel.add(lbl); panel.add(Box.createVerticalStrut(14));

        fNombre = Estilos.crearCampo(16); fTipo   = Estilos.crearCampo(16);
        fCodigo = Estilos.crearCampo(8);  fStock  = Estilos.crearCampo(8);
        fPrecio = Estilos.crearCampo(10);

        Object[][] campos = {{"Nombre",fNombre},{"Tipo",fTipo},{"Código",fCodigo},
                             {"Stock",fStock},{"Precio ($)",fPrecio}};
        for (Object[] par : campos) {
            JPanel fila = Estilos.filaFormulario((String)par[0],(JComponent)par[1]);
            fila.setMaximumSize(new Dimension(260,36));
            panel.add(fila); panel.add(Box.createVerticalStrut(8));
        }

        panel.add(Box.createVerticalStrut(10)); panel.add(Estilos.crearSeparador());
        panel.add(Box.createVerticalStrut(10));

        JButton btnGuardar    = Estilos.crearBotonExito("💾 Agregar producto");
        JButton btnActualizar = Estilos.crearBoton("✏ Actualizar stock", new Color(234,179,8));
        JButton btnEliminar   = Estilos.crearBotonPeligro("🗑 Eliminar");
        JButton btnLimpiar    = Estilos.crearBotonSecundario("✕ Limpiar");

        for (JButton b : new JButton[]{btnGuardar,btnActualizar,btnEliminar,btnLimpiar}) {
            b.setMaximumSize(new Dimension(260,36)); b.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(b); panel.add(Box.createVerticalStrut(6));
        }

        btnGuardar.addActionListener(e -> guardar());
        btnActualizar.addActionListener(e -> actualizarStock());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
        return panel;
    }

    private void guardar() {
        try {
            String nombre = fNombre.getText().trim(), tipo = fTipo.getText().trim();
            if (nombre.isEmpty()) { vp.mostrarMensaje("Nombre obligatorio.", false); return; }
            int codigo = Integer.parseInt(fCodigo.getText().trim());
            int stock  = Integer.parseInt(fStock.getText().trim());
            int precio = Integer.parseInt(fPrecio.getText().trim());
            vp.ctrlProducto.agregarProducto(new Producto(nombre,tipo,codigo,stock,precio));
            refrescar(); limpiar();
            vp.mostrarMensaje("Producto agregado.", true);
        } catch (NumberFormatException ex) { vp.mostrarMensaje("Código, stock y precio deben ser números.", false); }
    }

    private void actualizarStock() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un producto.", false); return; }
        String nombre = (String) modeloTabla.getValueAt(fila,0);
        Producto p = vp.ctrlProducto.buscarProducto(nombre);
        if (p == null) return;
        String input = JOptionPane.showInputDialog(vp, "Nuevo stock para '"+nombre+"':", p.getStock());
        if (input == null || input.trim().isEmpty()) return;
        try {
            p.setStock(Integer.parseInt(input.trim()));
            refrescar(); vp.mostrarMensaje("Stock actualizado.", true);
        } catch (NumberFormatException ex) { vp.mostrarMensaje("Ingrese un número válido.", false); }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un producto.", false); return; }
        String nombre = (String) modeloTabla.getValueAt(fila,0);
        if (JOptionPane.showConfirmDialog(vp,"¿Eliminar '"+nombre+"'?","Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            vp.ctrlProducto.eliminarProducto(nombre);
            refrescar(); limpiar();
            vp.mostrarMensaje("Producto eliminado.", true);
        }
    }

    private void buscar() {
        String txt = fBuscar.getText().trim().toLowerCase();
        modeloTabla.setRowCount(0);
        for (Producto p : vp.ctrlProducto.listarProductos())
            if (p.getNombre().toLowerCase().contains(txt) || p.getTipoProducto().toLowerCase().contains(txt))
                agregarFila(p);
    }

    private void cargarEnFormulario() {
        int fila = tabla.getSelectedRow(); if (fila < 0) return;
        fNombre.setText((String)modeloTabla.getValueAt(fila,0));
        fTipo.setText((String)modeloTabla.getValueAt(fila,1));
        fCodigo.setText(modeloTabla.getValueAt(fila,2).toString());
        fStock.setText(modeloTabla.getValueAt(fila,3).toString());
        fPrecio.setText(modeloTabla.getValueAt(fila,4).toString());
    }

    private void limpiar() {
        for (JTextField f : new JTextField[]{fNombre,fTipo,fCodigo,fStock,fPrecio}) f.setText("");
        tabla.clearSelection();
    }

    private void agregarFila(Producto p) {
        modeloTabla.addRow(new Object[]{p.getNombre(),p.getTipoProducto(),p.getCodigo(),p.getStock(),p.getPrecio()});
    }

    public void refrescar() {
        modeloTabla.setRowCount(0);
        for (Producto p : vp.ctrlProducto.listarProductos()) agregarFila(p);
    }
}
