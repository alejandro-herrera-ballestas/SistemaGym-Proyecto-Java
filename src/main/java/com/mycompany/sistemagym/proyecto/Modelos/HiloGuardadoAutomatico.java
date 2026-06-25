package com.mycompany.sistemagym.proyecto.Modelos;

import com.mycompany.sistemagym.proyecto.controladores.*;
import java.util.List;

/**
 * Hilo de guardado automático. Implementa Runnable para ejecutarse de forma
 * concurrente con la interfaz gráfica, guardando datos cada cierto intervalo.
 * Demuestra: uso de Thread/Runnable, sincronización y procesamiento concurrente.
 */
public class HiloGuardadoAutomatico implements Runnable {

    private static final int INTERVALO_MS = 60_000; // cada 60 segundos

    private final GestorCSV        gestor;
    private final List<Plan>       planes;
    private final List<Cliente>    clientes;
    private final List<Empleado>   empleados;
    private final List<Producto>   productos;
    private final List<VentaProducto> ventas;

    /** Flag volatile para detener el hilo de forma segura desde otro thread. */
    private volatile boolean activo = true;

    /** Objeto de bloqueo para sincronizar el acceso a los datos compartidos. */
    private final Object lock = new Object();

    public HiloGuardadoAutomatico(GestorCSV gestor,
                                  List<Plan> planes,
                                  List<Cliente> clientes,
                                  List<Empleado> empleados,
                                  List<Producto> productos,
                                  List<VentaProducto> ventas) {
        this.gestor    = gestor;
        this.planes    = planes;
        this.clientes  = clientes;
        this.empleados = empleados;
        this.productos = productos;
        this.ventas    = ventas;
    }

    @Override
    public void run() {
        System.out.println("[AutoSave] Hilo de guardado automático iniciado.");
        while (activo) {
            try {
                Thread.sleep(INTERVALO_MS);
                if (activo) {
                    guardarSincronizado();
                }
            } catch (InterruptedException e) {
                // El hilo fue interrumpido; terminamos limpiamente
                Thread.currentThread().interrupt();
                activo = false;
            }
        }
        System.out.println("[AutoSave] Hilo de guardado automático detenido.");
    }

    /**
     * Bloque sincronizado: previene escritura concurrente corrupta si en el
     * futuro se agregan más hilos que modifiquen los datos.
     */
    private void guardarSincronizado() {
        synchronized (lock) {
            try {
                gestor.guardarTodo(planes, clientes, empleados, productos, ventas);
                System.out.println("[AutoSave] Guardado automático realizado correctamente.");
            } catch (Exception e) {
                System.out.println("[AutoSave] Error en guardado automático: " + e.getMessage());
            }
        }
    }

    /** Permite detener el hilo de forma segura desde la GUI. */
    public void detener() {
        activo = false;
    }

    /** Fuerza un guardado inmediato sincronizado (llamado al cerrar la app). */
    public void guardarAhora() {
        guardarSincronizado();
    }
}
