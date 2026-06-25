package com.mycompany.sistemagym.proyecto.vista;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import com.mycompany.sistemagym.proyecto.controladores.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class PanelClientes extends JPanel {

    private final VentanaPrincipal vp;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField fNombre, fId, fEdad, fEps, fTelefono, fCorreo, fPeso, fAltura, fBuscar;
    private JComboBox<String> cbSexo, cbPlan, cbEstado;

    public PanelClientes(VentanaPrincipal vp) {
        this.vp = vp;
        setBackground(Estilos.COLOR_FONDO);
        setLayout(new BorderLayout(16, 16));
        setBorder(new EmptyBorder(24, 24, 24, 24));
        buildUI();
    }

    private void buildUI() {
        // Encabezado
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(Estilos.crearTitulo("👤  Gestión de Clientes"), BorderLayout.WEST);

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        busqueda.setOpaque(false);
        fBuscar = Estilos.crearCampo(18);
        fBuscar.setToolTipText("Buscar por nombre o ID");
        JButton btnBuscar = Estilos.crearBotonPrimario("🔍 Buscar");
        btnBuscar.addActionListener(e -> buscar());
        JButton btnLimpiar = Estilos.crearBotonSecundario("✕ Limpiar");
        btnLimpiar.addActionListener(e -> { fBuscar.setText(""); refrescar(); });
        busqueda.add(Estilos.crearEtiqueta("Buscar:"));
        busqueda.add(fBuscar);
        busqueda.add(btnBuscar);
        busqueda.add(btnLimpiar);
        header.add(busqueda, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // Tabla
        String[] cols = {"Nombre","ID","Edad","EPS","Sexo","Tel","Correo","Plan","Peso","Altura","Estado"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        Estilos.estilizarTabla(tabla);
        tabla.getSelectionModel().addListSelectionListener(e -> cargarSeleccionEnFormulario());

        JScrollPane scroll = Estilos.crearScroll(tabla);

        // Formulario lateral
        JPanel formulario = crearFormulario();
        formulario.setPreferredSize(new Dimension(280, 0));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, formulario);
        split.setDividerLocation(740);
        split.setDividerSize(4);
        split.setOpaque(false);
        split.setBorder(null);
        add(split, BorderLayout.CENTER);
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Estilos.COLOR_PANEL);
        panel.setBorder(new CompoundBorder(
            new LineBorder(Estilos.COLOR_BORDE, 1),
            new EmptyBorder(16, 16, 16, 16)
        ));

        JLabel lblTitulo = Estilos.crearTitulo("Datos del cliente");
        lblTitulo.setFont(Estilos.FUENTE_SUBTITULO);
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(14));

        fNombre   = Estilos.crearCampo(16);
        fId       = Estilos.crearCampo(16);
        fEdad     = Estilos.crearCampo(6);
        fEps      = Estilos.crearCampo(16);
        fTelefono = Estilos.crearCampo(12);
        fCorreo   = Estilos.crearCampo(16);
        fPeso     = Estilos.crearCampo(6);
        fAltura   = Estilos.crearCampo(6);
        cbSexo    = Estilos.crearCombo(); cbSexo.addItem("M"); cbSexo.addItem("F");
        cbEstado  = Estilos.crearCombo(); cbEstado.addItem("Activo"); cbEstado.addItem("Inactivo");

        cbPlan = Estilos.crearCombo();

        JComponent[][] campos = {
            {Estilos.crearEtiqueta("Nombre"),  fNombre},
            {Estilos.crearEtiqueta("ID / Cédula"), fId},
            {Estilos.crearEtiqueta("Edad"),    fEdad},
            {Estilos.crearEtiqueta("EPS"),     fEps},
            {Estilos.crearEtiqueta("Sexo"),    cbSexo},
            {Estilos.crearEtiqueta("Teléfono"),fTelefono},
            {Estilos.crearEtiqueta("Correo"),  fCorreo},
            {Estilos.crearEtiqueta("Peso (kg)"),fPeso},
            {Estilos.crearEtiqueta("Altura (m)"),fAltura},
            {Estilos.crearEtiqueta("Plan"),    cbPlan},
            {Estilos.crearEtiqueta("Estado"),  cbEstado},
        };

        for (JComponent[] par : campos) {
            JPanel fila = Estilos.filaFormulario(((JLabel)par[0]).getText(), par[1]);
            fila.setMaximumSize(new Dimension(260, 36));
            panel.add(fila);
            panel.add(Box.createVerticalStrut(8));
        }

        panel.add(Box.createVerticalStrut(12));
        panel.add(Estilos.crearSeparador());
        panel.add(Box.createVerticalStrut(12));

        // Botones CRUD
        JButton btnGuardar   = Estilos.crearBotonExito("💾 Guardar");
        JButton btnActualizar = Estilos.crearBoton("✏ Actualizar", new Color(234,179,8));
        JButton btnEliminar  = Estilos.crearBotonPeligro("🗑 Eliminar");
        JButton btnLimpiarF  = Estilos.crearBotonSecundario("✕ Limpiar");
        JButton btnIMC       = Estilos.crearBotonPrimario("⚖ Calcular IMC");

        for (JButton b : new JButton[]{btnGuardar, btnActualizar, btnEliminar, btnLimpiarF, btnIMC}) {
            b.setMaximumSize(new Dimension(260, 36));
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(b);
            panel.add(Box.createVerticalStrut(6));
        }

        btnGuardar.addActionListener(e -> guardarCliente());
        btnActualizar.addActionListener(e -> actualizarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnLimpiarF.addActionListener(e -> limpiarFormulario());
        btnIMC.addActionListener(e -> calcularIMC());

        return panel;
    }

    // ── Operaciones ───────────────────────────────────────────────────────

    private void guardarCliente() {
        try {
            String nombre = fNombre.getText().trim();
            String id     = fId.getText().trim();
            if (nombre.isEmpty() || id.isEmpty()) {
                vp.mostrarMensaje("Nombre e ID son obligatorios.", false); return;
            }
            if (vp.ctrlCliente.buscarCliente(id) != null) {
                vp.mostrarMensaje("Ya existe un cliente con ese ID.", false); return;
            }
            int    edad   = Integer.parseInt(fEdad.getText().trim());
            String eps    = fEps.getText().trim();
            String sexo   = (String) cbSexo.getSelectedItem();
            int    tel    = Integer.parseInt(fTelefono.getText().trim());
            String correo = fCorreo.getText().trim();
            float  peso   = Float.parseFloat(fPeso.getText().trim());
            float  altura = Float.parseFloat(fAltura.getText().trim());
            boolean activo = cbEstado.getSelectedItem().equals("Activo");

            Plan plan = obtenerPlanSeleccionado();
            if (plan == null) { vp.mostrarMensaje("Seleccione un plan válido.", false); return; }

            Cliente c = new Cliente(nombre, edad, id, eps, sexo, tel, correo, plan, peso, altura, activo);
            vp.ctrlCliente.agregarCliente(c);
            refrescar();
            limpiarFormulario();
            vp.mostrarMensaje("Cliente registrado correctamente.", true);
        } catch (NumberFormatException ex) {
            vp.mostrarMensaje("Verifique los campos numéricos (Edad, Teléfono, Peso, Altura).", false);
        }
    }

    private void actualizarCliente() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un cliente de la tabla.", false); return; }
        String id = (String) modeloTabla.getValueAt(fila, 1);
        Cliente c = vp.ctrlCliente.buscarCliente(id);
        if (c == null) return;
        try {
            if (!fTelefono.getText().trim().isEmpty())
                c.setTelefono(Integer.parseInt(fTelefono.getText().trim()));
            if (!fCorreo.getText().trim().isEmpty())
                c.setCorreo(fCorreo.getText().trim());
            if (!fEps.getText().trim().isEmpty())
                c.setEps(fEps.getText().trim());
            c.setEstado(cbEstado.getSelectedItem().equals("Activo"));

            Plan plan = obtenerPlanSeleccionado();
            if (plan != null) vp.ctrlPlan.asignarPlan(c, plan);

            refrescar(); limpiarFormulario();
            vp.mostrarMensaje("Cliente actualizado.", true);
        } catch (NumberFormatException ex) {
            vp.mostrarMensaje("Teléfono inválido.", false);
        }
    }

    private void eliminarCliente() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un cliente.", false); return; }
        String id = (String) modeloTabla.getValueAt(fila, 1);
        int conf = JOptionPane.showConfirmDialog(vp,
            "¿Eliminar cliente con ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            vp.ctrlCliente.eliminarCliente(id);
            refrescar(); limpiarFormulario();
            vp.mostrarMensaje("Cliente eliminado.", true);
        }
    }

    private void calcularIMC() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) { vp.mostrarMensaje("Seleccione un cliente.", false); return; }
        String id = (String) modeloTabla.getValueAt(fila, 1);
        Cliente c = vp.ctrlCliente.buscarCliente(id);
        if (c == null) return;
        double imc = c.getPeso() / (c.getAltura() * c.getAltura());
        String categoria = imc < 18.5 ? "Bajo peso" : imc < 25 ? "Normal" : imc < 30 ? "Sobrepeso" : "Obesidad";
        JOptionPane.showMessageDialog(vp,
            String.format("IMC de %s:\n\n%.2f  —  %s", c.getNombre(), imc, categoria),
            "Cálculo de IMC", JOptionPane.INFORMATION_MESSAGE);
    }

    private void buscar() {
        String texto = fBuscar.getText().trim().toLowerCase();
        modeloTabla.setRowCount(0);
        for (Cliente c : vp.ctrlCliente.listarClientes()) {
            if (c.getNombre().toLowerCase().contains(texto) || c.getId().toLowerCase().contains(texto))
                agregarFila(c);
        }
    }

    private void cargarSeleccionEnFormulario() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) return;
        String id = (String) modeloTabla.getValueAt(fila, 1);
        Cliente c = vp.ctrlCliente.buscarCliente(id);
        if (c == null) return;
        fNombre.setText(c.getNombre());
        fId.setText(c.getId());
        fEdad.setText(String.valueOf(c.getEdad()));
        fEps.setText(c.getEps());
        cbSexo.setSelectedItem(c.getSexo());
        fTelefono.setText(String.valueOf(c.getTelefono()));
        fCorreo.setText(c.getCorreo());
        fPeso.setText(String.valueOf(c.getPeso()));
        fAltura.setText(String.valueOf(c.getAltura()));
        cbEstado.setSelectedItem(c.isEstado() ? "Activo" : "Inactivo");
        if (c.getPlanContratado() != null)
            cbPlan.setSelectedItem(c.getPlanContratado().getNombrePlan());
    }

    private void limpiarFormulario() {
        for (JTextField f : new JTextField[]{fNombre,fId,fEdad,fEps,fTelefono,fCorreo,fPeso,fAltura})
            f.setText("");
        cbSexo.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
        tabla.clearSelection();
    }

    private Plan obtenerPlanSeleccionado() {
        String nombre = (String) cbPlan.getSelectedItem();
        if (nombre == null) return null;
        for (Plan p : vp.ctrlPlan.listarPlanes())
            if (p.getNombrePlan().equals(nombre)) return p;
        return null;
    }

    private void agregarFila(Cliente c) {
        modeloTabla.addRow(new Object[]{
            c.getNombre(), c.getId(), c.getEdad(), c.getEps(), c.getSexo(),
            c.getTelefono(), c.getCorreo(),
            c.getPlanContratado() != null ? c.getPlanContratado().getNombrePlan() : "—",
            c.getPeso(), c.getAltura(),
            c.isEstado() ? "Activo" : "Inactivo"
        });
    }

    public void refrescar() {
        modeloTabla.setRowCount(0);
        for (Cliente c : vp.ctrlCliente.listarClientes()) agregarFila(c);
        // Actualizar combo de planes
        String selActual = (String) cbPlan.getSelectedItem();
        cbPlan.removeAllItems();
        for (Plan p : vp.ctrlPlan.listarPlanes()) cbPlan.addItem(p.getNombrePlan());
        if (selActual != null) cbPlan.setSelectedItem(selActual);
    }
}
