package com.mycompany.sistemagym.proyecto.vista;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class PanelPlanes extends JPanel {

    private final VentanaPrincipal vp;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField fNombre, fPrecio, fDuracion, fInvitados;

    public PanelPlanes(VentanaPrincipal vp) {
        this.vp = vp;
        setBackground(Estilos.COLOR_FONDO);
        setLayout(new BorderLayout(16,16));
        setBorder(new EmptyBorder(24,24,24,24));
        buildUI();
    }

    private void buildUI() {
        add(Estilos.crearTitulo("📋  Gestión de Planes"), BorderLayout.NORTH);

        String[] cols = {"Nombre","Precio ($)","Duración","Invitados/mes"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        Estilos.estilizarTabla(tabla);
        tabla.getSelectionModel().addListSelectionListener(e -> cargarEnFormulario());

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            Estilos.crearScroll(tabla), crearFormulario());
        split.setDividerLocation(700);
        split.setDividerSize(4);
        split.setOpaque(false); split.setBorder(null);
        add(split, BorderLayout.CENTER);
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Estilos.COLOR_PANEL);
        panel.setBorder(new CompoundBorder(new LineBorder(Estilos.COLOR_BORDE,1),new EmptyBorder(16,16,16,16)));
        panel.setPreferredSize(new Dimension(290,0));

        JLabel lbl = Estilos.crearTitulo("Datos del plan");
        lbl.setFont(Estilos.FUENTE_SUBTITULO);
        panel.add(lbl); panel.add(Box.createVerticalStrut(14));

        fNombre    = Estilos.crearCampo(16);
        fPrecio    = Estilos.crearCampo(10);
        fDuracion  = Estilos.crearCampo(16);
        fInvitados = Estilos.crearCampo(6);

        Object[][] campos = {{"Nombre del plan",fNombre},{"Precio ($)",fPrecio},
                             {"Duración",fDuracion},{"Invitados/mes",fInvitados}};
        for (Object[] par : campos) {
            JPanel fila = Estilos.filaFormulario((String)par[0],(JComponent)par[1]);
            fila.setMaximumSize(new Dimension(260,36));
            panel.add(fila); panel.add(Box.createVerticalStrut(8));
        }

        // Descripción visual del plan seleccionado
        panel.add(Box.createVerticalStrut(12));
        panel.add(Estilos.crearSeparador());
        panel.add(Box.createVerticalStrut(8));
        JLabel lblInfo = Estilos.crearEtiqueta("Seleccione un plan de la tabla para ver detalles.");
        lblInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(12));
        panel.add(Estilos.crearSeparador());
        panel.add(Box.createVerticalStrut(10));

        JButton btnGuardar  = Estilos.crearBotonExito("💾 Crear plan");
        JButton btnEliminar = Estilos.crearBotonPeligro("🗑 Eliminar plan");
        JButton btnLimpiar  = Estilos.crearBotonSecundario("✕ Limpiar");

        for (JButton b : new JButton[]{btnGuardar,btnEliminar,btnLimpiar}) {
            b.setMaximumSize(new Dimension(260,36)); b.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(b); panel.add(Box.createVerticalStrut(6));
        }

        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
        return panel;
    }

    private void guardar() {
        try {
            String nombre = fNombre.getText().trim();
            if (nombre.isEmpty()) { vp.mostrarMensaje("El nombre es obligatorio.", false); return; }
            int precio    = Integer.parseInt(fPrecio.getText().trim());
            String dur    = fDuracion.getText().trim();
            int invitados = Integer.parseInt(fInvitados.getText().trim());
            // Verificar duplicado
            for (Plan p : vp.ctrlPlan.listarPlanes())
                if (p.getNombrePlan().equalsIgnoreCase(nombre)) {
                    vp.mostrarMensaje("Ya existe un plan con ese nombre.", false); return;
                }
            vp.ctrlPlan.crearPlan(new Plan(nombre, precio, dur, invitados));
            refrescar(); limpiar();
            vp.mostrarMensaje("Plan creado correctamente.", true);
        } catch (NumberFormatException ex) { vp.mostrarMensaje("Precio e invitados deben ser números.", false); }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un plan.", false); return; }
        String nombre = (String) modeloTabla.getValueAt(fila,0);
        if (JOptionPane.showConfirmDialog(vp,"¿Eliminar plan '"+nombre+"'?","Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            vp.ctrlPlan.listarPlanes().removeIf(p -> p.getNombrePlan().equals(nombre));
            refrescar(); limpiar();
            vp.mostrarMensaje("Plan eliminado.", true);
        }
    }

    private void cargarEnFormulario() {
        int fila = tabla.getSelectedRow(); if (fila < 0) return;
        fNombre.setText((String)modeloTabla.getValueAt(fila,0));
        fPrecio.setText(modeloTabla.getValueAt(fila,1).toString());
        fDuracion.setText((String)modeloTabla.getValueAt(fila,2));
        fInvitados.setText(modeloTabla.getValueAt(fila,3).toString());
    }

    private void limpiar() {
        for (JTextField f : new JTextField[]{fNombre,fPrecio,fDuracion,fInvitados}) f.setText("");
        tabla.clearSelection();
    }

    public void refrescar() {
        modeloTabla.setRowCount(0);
        for (Plan p : vp.ctrlPlan.listarPlanes())
            modeloTabla.addRow(new Object[]{p.getNombrePlan(),p.getPrecioPlan(),p.getDuracionPlan(),p.getNumInvitadosMes()});
    }
}
