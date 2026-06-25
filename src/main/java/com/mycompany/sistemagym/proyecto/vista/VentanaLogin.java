package com.mycompany.sistemagym.proyecto.vista;

import com.mycompany.sistemagym.proyecto.Modelos.*;
import com.mycompany.sistemagym.proyecto.controladores.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class VentanaLogin extends JFrame {

    private final controladorCliente   ctrlCliente;
    private final ControladorEmpleado  ctrlEmpleado;
    private final ControladorPlan      ctrlPlan;
    private final ControladorProducto  ctrlProducto;
    private final ControladorVenta     ctrlVenta;
    private final ControladorAdmin     ctrlAdmin;
    private final GestorCSV            gestorCSV;

    private JTextField     campoUsuario;
    private JPasswordField campoPassword;
    private JLabel         lblError;

    public VentanaLogin(controladorCliente ctrlCliente,
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
    }

    private void initUI() {
        setTitle("GymPro — Iniciar Sesión");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(460, 560);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel fondo = new JPanel(new GridBagLayout());
        fondo.setBackground(Estilos.COLOR_FONDO);
        setContentPane(fondo);

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Estilos.COLOR_PANEL);
        tarjeta.setBorder(new CompoundBorder(
            new LineBorder(Estilos.COLOR_BORDE, 1, true),
            new EmptyBorder(40, 40, 40, 40)
        ));
        tarjeta.setMaximumSize(new Dimension(360, 600));

        // Logo / Ícono
        JLabel icono = new JLabel("🏋", SwingConstants.CENTER);
        icono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 56));
        icono.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = Estilos.crearTitulo("GymPro Sistema");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitulo = Estilos.crearEtiqueta("Gestión integral del gimnasio");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JSeparator sep = Estilos.crearSeparador();
        sep.setMaximumSize(new Dimension(280, 1));

        JLabel lblUsuario = Estilos.crearEtiqueta("Usuario");
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoUsuario = Estilos.crearCampo(20);
        campoUsuario.setMaximumSize(new Dimension(280, 38));
        campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblPass = Estilos.crearEtiqueta("Contraseña");
        lblPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPassword = Estilos.crearCampoPassword(20);
        campoPassword.setMaximumSize(new Dimension(280, 38));
        campoPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblError = new JLabel(" ");
        lblError.setForeground(Estilos.COLOR_PELIGRO);
        lblError.setFont(Estilos.FUENTE_PEQUEÑA);
        lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblError.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnEntrar = Estilos.crearBotonPrimario("Iniciar sesión");
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setMaximumSize(new Dimension(280, 40));

        JLabel hint = new JLabel("Usuario: admin  |  Contraseña: 1234");
        hint.setForeground(new Color(71, 85, 105));
        hint.setFont(Estilos.FUENTE_PEQUEÑA);
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);
        hint.setHorizontalAlignment(SwingConstants.CENTER);

        btnEntrar.addActionListener(e -> intentarLogin());
        campoPassword.addActionListener(e -> intentarLogin());

        tarjeta.add(Box.createVerticalStrut(4));
        tarjeta.add(icono);
        tarjeta.add(Box.createVerticalStrut(12));
        tarjeta.add(titulo);
        tarjeta.add(Box.createVerticalStrut(4));
        tarjeta.add(subtitulo);
        tarjeta.add(Box.createVerticalStrut(24));
        tarjeta.add(sep);
        tarjeta.add(Box.createVerticalStrut(24));
        tarjeta.add(lblUsuario);
        tarjeta.add(Box.createVerticalStrut(6));
        tarjeta.add(campoUsuario);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(lblPass);
        tarjeta.add(Box.createVerticalStrut(6));
        tarjeta.add(campoPassword);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(lblError);
        tarjeta.add(Box.createVerticalStrut(16));
        tarjeta.add(btnEntrar);
        tarjeta.add(Box.createVerticalStrut(20));
        tarjeta.add(hint);

        fondo.add(tarjeta);
    }

    private void intentarLogin() {
        String usuario = campoUsuario.getText().trim();
        String pass    = new String(campoPassword.getPassword()).trim();
        if (usuario.equals("admin") && pass.equals("1234")) {
            dispose();
            VentanaPrincipal vp = new VentanaPrincipal(
                ctrlCliente, ctrlEmpleado, ctrlPlan,
                ctrlProducto, ctrlVenta, ctrlAdmin, gestorCSV
            );
            vp.setVisible(true);
        } else {
            lblError.setText("Usuario o contraseña incorrectos");
            campoPassword.setText("");
        }
    }
}
