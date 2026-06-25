package com.mycompany.sistemagym.proyecto.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * Clase utilitaria con constantes de color, fuentes y métodos de fábrica
 * para construir componentes con un estilo visual uniforme en toda la aplicación.
 */
public class Estilos {

    // ── Paleta de colores ────────────────────────────────────────────────
    public static final Color COLOR_FONDO        = new Color(18,  18,  28);
    public static final Color COLOR_PANEL        = new Color(28,  28,  45);
    public static final Color COLOR_SIDEBAR      = new Color(13,  13,  20);
    public static final Color COLOR_ACENTO       = new Color(99, 102, 241);   // índigo
    public static final Color COLOR_ACENTO_HOVER = new Color(129,132,255);
    public static final Color COLOR_EXITO        = new Color(34, 197,  94);   // verde
    public static final Color COLOR_PELIGRO      = new Color(239, 68,  68);   // rojo
    public static final Color COLOR_ADVERTENCIA  = new Color(251,191,  36);   // amarillo
    public static final Color COLOR_TEXTO        = new Color(226,232,240);
    public static final Color COLOR_TEXTO_SUAVE  = new Color(148,163,184);
    public static final Color COLOR_BORDE        = new Color(51,  65,  85);
    public static final Color COLOR_INPUT_FONDO  = new Color(15,  23,  42);
    public static final Color COLOR_TABLA_FILA1  = new Color(30,  30,  50);
    public static final Color COLOR_TABLA_FILA2  = new Color(24,  24,  40);
    public static final Color COLOR_HEADER_TABLA = new Color(99, 102, 241);

    // ── Fuentes ─────────────────────────────────────────────────────────
    public static final Font FUENTE_TITULO   = new Font("Segoe UI", Font.BOLD,  22);
    public static final Font FUENTE_SUBTITULO= new Font("Segoe UI", Font.BOLD,  15);
    public static final Font FUENTE_NORMAL   = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_PEQUEÑA  = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FUENTE_BOTON    = new Font("Segoe UI", Font.BOLD,  13);
    public static final Font FUENTE_SIDEBAR  = new Font("Segoe UI", Font.BOLD,  13);

    // ── Botones ──────────────────────────────────────────────────────────
    public static JButton crearBoton(String texto, Color fondo) {
        JButton btn = new JButton(texto);
        btn.setBackground(fondo);
        btn.setForeground(Color.WHITE);
        btn.setFont(FUENTE_BOTON);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(9, 20, 9, 20));
        btn.setOpaque(true);
        return btn;
    }

    public static JButton crearBotonPrimario(String texto) {
        return crearBoton(texto, COLOR_ACENTO);
    }

    public static JButton crearBotonExito(String texto) {
        return crearBoton(texto, COLOR_EXITO);
    }

    public static JButton crearBotonPeligro(String texto) {
        return crearBoton(texto, COLOR_PELIGRO);
    }

    public static JButton crearBotonSecundario(String texto) {
        return crearBoton(texto, new Color(51, 65, 85));
    }

    // ── Campos de texto ──────────────────────────────────────────────────
    public static JTextField crearCampo(int columnas) {
        JTextField campo = new JTextField(columnas);
        campo.setBackground(COLOR_INPUT_FONDO);
        campo.setForeground(COLOR_TEXTO);
        campo.setCaretColor(COLOR_TEXTO);
        campo.setFont(FUENTE_NORMAL);
        campo.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_BORDE, 1, true),
            new EmptyBorder(6, 10, 6, 10)
        ));
        return campo;
    }

    public static JPasswordField crearCampoPassword(int columnas) {
        JPasswordField campo = new JPasswordField(columnas);
        campo.setBackground(COLOR_INPUT_FONDO);
        campo.setForeground(COLOR_TEXTO);
        campo.setCaretColor(COLOR_TEXTO);
        campo.setFont(FUENTE_NORMAL);
        campo.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_BORDE, 1, true),
            new EmptyBorder(6, 10, 6, 10)
        ));
        return campo;
    }

    // ── ComboBox ─────────────────────────────────────────────────────────
    public static <T> JComboBox<T> crearCombo() {
        JComboBox<T> combo = new JComboBox<>();
        combo.setBackground(COLOR_INPUT_FONDO);
        combo.setForeground(COLOR_TEXTO);
        combo.setFont(FUENTE_NORMAL);
        combo.setBorder(new LineBorder(COLOR_BORDE, 1));
        return combo;
    }

    // ── Etiquetas ────────────────────────────────────────────────────────
    public static JLabel crearEtiqueta(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(COLOR_TEXTO_SUAVE);
        lbl.setFont(FUENTE_NORMAL);
        return lbl;
    }

    public static JLabel crearTitulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(COLOR_TEXTO);
        lbl.setFont(FUENTE_TITULO);
        return lbl;
    }

    // ── Panel con borde redondeado ────────────────────────────────────────
    public static JPanel crearPanel(Color fondo) {
        JPanel panel = new JPanel();
        panel.setBackground(fondo);
        panel.setBorder(new EmptyBorder(16, 16, 16, 16));
        return panel;
    }

    // ── Tabla ─────────────────────────────────────────────────────────────
    public static void estilizarTabla(JTable tabla) {
        tabla.setBackground(COLOR_TABLA_FILA1);
        tabla.setForeground(COLOR_TEXTO);
        tabla.setFont(FUENTE_NORMAL);
        tabla.setRowHeight(30);
        tabla.setGridColor(COLOR_BORDE);
        tabla.setSelectionBackground(COLOR_ACENTO);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(false);
        tabla.setIntercellSpacing(new Dimension(0, 1));

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(COLOR_HEADER_TABLA);
        header.setForeground(Color.WHITE);
        header.setFont(FUENTE_SUBTITULO);
        header.setBorder(BorderFactory.createEmptyBorder());
        header.setReorderingAllowed(false);

        // Renderer alternado
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, v, sel, foc, row, col);
                if (!sel) {
                    setBackground(row % 2 == 0 ? COLOR_TABLA_FILA1 : COLOR_TABLA_FILA2);
                    setForeground(COLOR_TEXTO);
                }
                setBorder(new EmptyBorder(0, 10, 0, 10));
                return this;
            }
        });
    }

    // ── ScrollPane ────────────────────────────────────────────────────────
    public static JScrollPane crearScroll(Component contenido) {
        JScrollPane scroll = new JScrollPane(contenido);
        scroll.setBackground(COLOR_PANEL);
        scroll.setBorder(new LineBorder(COLOR_BORDE, 1));
        scroll.getViewport().setBackground(COLOR_PANEL);
        scroll.getVerticalScrollBar().setBackground(COLOR_PANEL);
        return scroll;
    }

    // ── Separador visual ─────────────────────────────────────────────────
    public static JSeparator crearSeparador() {
        JSeparator sep = new JSeparator();
        sep.setForeground(COLOR_BORDE);
        sep.setBackground(COLOR_PANEL);
        return sep;
    }

    // ── Formulario: construye un par etiqueta+campo en una fila ──────────
    public static JPanel filaFormulario(String etiqueta, JComponent campo) {
        JPanel fila = new JPanel(new BorderLayout(10, 0));
        fila.setOpaque(false);
        JLabel lbl = crearEtiqueta(etiqueta);
        lbl.setPreferredSize(new Dimension(130, 30));
        fila.add(lbl, BorderLayout.WEST);
        fila.add(campo, BorderLayout.CENTER);
        return fila;
    }
}
