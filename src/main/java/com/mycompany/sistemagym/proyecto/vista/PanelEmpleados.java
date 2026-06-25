package com.mycompany.sistemagym.proyecto.vista;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class PanelEmpleados extends JPanel {

    private final VentanaPrincipal vp;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField fNombre, fId, fEdad, fEps, fTelefono, fCorreo, fEstudio;
    private JComboBox<String> cbSexo;

    public PanelEmpleados(VentanaPrincipal vp) {
        this.vp = vp;
        setBackground(Estilos.COLOR_FONDO);
        setLayout(new BorderLayout(16, 16));
        setBorder(new EmptyBorder(24, 24, 24, 24));
        buildUI();
    }

    private void buildUI() {
        add(Estilos.crearTitulo("👷  Gestión de Empleados"), BorderLayout.NORTH);

        String[] cols = {"Nombre","ID","Edad","EPS","Sexo","Teléfono","Correo","Estudios"};
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
        panel.setBorder(new CompoundBorder(new LineBorder(Estilos.COLOR_BORDE,1), new EmptyBorder(16,16,16,16)));
        panel.setPreferredSize(new Dimension(290, 0));

        JLabel lbl = Estilos.crearTitulo("Datos del empleado");
        lbl.setFont(Estilos.FUENTE_SUBTITULO);
        panel.add(lbl); panel.add(Box.createVerticalStrut(14));

        fNombre   = Estilos.crearCampo(16);
        fId       = Estilos.crearCampo(16);
        fEdad     = Estilos.crearCampo(6);
        fEps      = Estilos.crearCampo(16);
        fTelefono = Estilos.crearCampo(12);
        fCorreo   = Estilos.crearCampo(16);
        fEstudio  = Estilos.crearCampo(16);
        cbSexo    = Estilos.crearCombo(); cbSexo.addItem("M"); cbSexo.addItem("F");

        Object[][] campos = {
            {"Nombre",    fNombre},{"ID / Cédula", fId},{"Edad",fEdad},
            {"EPS",fEps},{"Sexo",cbSexo},{"Teléfono",fTelefono},
            {"Correo",fCorreo},{"Estudio/Cert.",fEstudio}
        };
        for (Object[] par : campos) {
            JPanel fila = Estilos.filaFormulario((String)par[0], (JComponent)par[1]);
            fila.setMaximumSize(new Dimension(260,36));
            panel.add(fila); panel.add(Box.createVerticalStrut(8));
        }

        panel.add(Box.createVerticalStrut(10)); panel.add(Estilos.crearSeparador());
        panel.add(Box.createVerticalStrut(10));

        JButton btnGuardar   = Estilos.crearBotonExito("💾 Guardar empleado");
        JButton btnActualizar= Estilos.crearBoton("✏ Actualizar", new Color(234,179,8));
        JButton btnEliminar  = Estilos.crearBotonPeligro("🗑 Eliminar");
        JButton btnTurno     = Estilos.crearBotonPrimario("🕐 Registrar turno");
        JButton btnVerTurnos = Estilos.crearBotonSecundario("📋 Ver turnos");
        JButton btnLimpiar   = Estilos.crearBotonSecundario("✕ Limpiar");

        for (JButton b : new JButton[]{btnGuardar,btnActualizar,btnEliminar,btnTurno,btnVerTurnos,btnLimpiar}) {
            b.setMaximumSize(new Dimension(260,36)); b.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(b); panel.add(Box.createVerticalStrut(6));
        }

        btnGuardar.addActionListener(e -> guardar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnTurno.addActionListener(e -> registrarTurno());
        btnVerTurnos.addActionListener(e -> verTurnos());
        btnLimpiar.addActionListener(e -> limpiar());
        return panel;
    }

    private void guardar() {
        try {
            String nombre = fNombre.getText().trim(), id = fId.getText().trim();
            if (nombre.isEmpty() || id.isEmpty()) { vp.mostrarMensaje("Nombre e ID obligatorios.", false); return; }
            if (vp.ctrlEmpleado.buscarEmpleado(id) != null) { vp.mostrarMensaje("Ya existe ese ID.", false); return; }
            Empleado e = new Empleado(nombre, Integer.parseInt(fEdad.getText().trim()), id,
                fEps.getText().trim(), (String)cbSexo.getSelectedItem(),
                Integer.parseInt(fTelefono.getText().trim()), fCorreo.getText().trim());
            String est = fEstudio.getText().trim();
            if (!est.isEmpty()) e.setEstudios(est);
            vp.ctrlEmpleado.agregarEmpleado(e);
            refrescar(); limpiar();
            vp.mostrarMensaje("Empleado registrado.", true);
        } catch (NumberFormatException ex) { vp.mostrarMensaje("Edad/Teléfono inválidos.", false); }
    }

    private void actualizar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un empleado.", false); return; }
        String id = (String) modeloTabla.getValueAt(fila,1);
        Empleado e = vp.ctrlEmpleado.buscarEmpleado(id);
        if (e == null) return;
        try {
            if (!fTelefono.getText().trim().isEmpty()) e.setTelefono(Integer.parseInt(fTelefono.getText().trim()));
            if (!fCorreo.getText().trim().isEmpty()) e.setCorreo(fCorreo.getText().trim());
            if (!fEps.getText().trim().isEmpty()) e.setEps(fEps.getText().trim());
            String est = fEstudio.getText().trim();
            if (!est.isEmpty()) e.setEstudios(est);
            refrescar(); limpiar();
            vp.mostrarMensaje("Empleado actualizado.", true);
        } catch (NumberFormatException ex) { vp.mostrarMensaje("Teléfono inválido.", false); }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un empleado.", false); return; }
        String id = (String) modeloTabla.getValueAt(fila,1);
        if (JOptionPane.showConfirmDialog(vp,"¿Eliminar empleado "+id+"?","Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            vp.ctrlEmpleado.eliminarEmpleado(id);
            refrescar(); limpiar();
            vp.mostrarMensaje("Empleado eliminado.", true);
        }
    }

    private void registrarTurno() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un empleado.", false); return; }
        String id = (String) modeloTabla.getValueAt(fila,1);
        Empleado e = vp.ctrlEmpleado.buscarEmpleado(id);
        if (e == null) return;

        JTextField fEntrada = Estilos.crearCampo(8); fEntrada.setText("08:00");
        JTextField fSalida  = Estilos.crearCampo(8); fSalida.setText("17:00");
        JPanel form = new JPanel(new GridLayout(2,2,8,8));
        form.setBackground(Estilos.COLOR_PANEL);
        form.add(Estilos.crearEtiqueta("Hora entrada (HH:mm):")); form.add(fEntrada);
        form.add(Estilos.crearEtiqueta("Hora salida (HH:mm):")); form.add(fSalida);

        int res = JOptionPane.showConfirmDialog(vp, form, "Registrar turno — "+e.getNombre(),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime entrada = LocalTime.parse(fEntrada.getText().trim(), fmt);
                LocalTime salida  = LocalTime.parse(fSalida.getText().trim(), fmt);
                e.setHistorialTurnos(new RegistroTurno(e, entrada, salida, LocalDate.now()));
                vp.mostrarMensaje("Turno registrado correctamente.", true);
            } catch (Exception ex) { vp.mostrarMensaje("Formato de hora inválido (HH:mm).", false); }
        }
    }

    private void verTurnos() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un empleado.", false); return; }
        Empleado e = vp.ctrlEmpleado.buscarEmpleado((String)modeloTabla.getValueAt(fila,1));
        if (e == null) return;
        List<RegistroTurno> turnos = e.getHistorialTurnos();
        if (turnos.isEmpty()) { vp.mostrarMensaje("Sin turnos registrados.", false); return; }
        StringBuilder sb = new StringBuilder("Turnos de " + e.getNombre() + ":\n\n");
        for (RegistroTurno t : turnos) sb.append("• ").append(t).append("\n");
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false); area.setBackground(Estilos.COLOR_INPUT_FONDO);
        area.setForeground(Estilos.COLOR_TEXTO); area.setFont(Estilos.FUENTE_NORMAL);
        JOptionPane.showMessageDialog(vp, new JScrollPane(area), "Historial de turnos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cargarEnFormulario() {
        int fila = tabla.getSelectedRow(); if (fila < 0) return;
        Empleado e = vp.ctrlEmpleado.buscarEmpleado((String)modeloTabla.getValueAt(fila,1));
        if (e == null) return;
        fNombre.setText(e.getNombre()); fId.setText(e.getId());
        fEdad.setText(String.valueOf(e.getEdad())); fEps.setText(e.getEps());
        cbSexo.setSelectedItem(e.getSexo()); fTelefono.setText(String.valueOf(e.getTelefono()));
        fCorreo.setText(e.getCorreo());
        fEstudio.setText(e.getEstudios().isEmpty() ? "" : e.getEstudios().get(e.getEstudios().size()-1));
    }

    private void limpiar() {
        for (JTextField f : new JTextField[]{fNombre,fId,fEdad,fEps,fTelefono,fCorreo,fEstudio}) f.setText("");
        cbSexo.setSelectedIndex(0); tabla.clearSelection();
    }

    public void refrescar() {
        modeloTabla.setRowCount(0);
        for (Empleado e : vp.ctrlEmpleado.listarEmpleados())
            modeloTabla.addRow(new Object[]{e.getNombre(),e.getId(),e.getEdad(),e.getEps(),
                e.getSexo(),e.getTelefono(),e.getCorreo(),String.join(", ",e.getEstudios())});
    }
}
