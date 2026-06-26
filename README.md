# 🏋️ GymPro — SistemaGym-Proyecto-Java

Sistema de gestión integral para gimnasios, desarrollado en **Java** con interfaz
gráfica de escritorio (Swing). Permite administrar clientes, empleados, planes
de membresía, productos/tienda y ventas, con persistencia en archivo CSV,
concurrencia mediante hilos y manejo de excepciones personalizadas.

## 📂 Estructura del proyecto

El proyecto sigue una arquitectura por capas (modelo - controlador - vista),
organizada en los siguientes paquetes:

- **`Modelos/`** — Clases del dominio: `Usuario` (clase base), `Cliente`,
  `Empleado`, `Invitado`, `Plan`, `Producto`, `Tienda`, `TransaccionProducto`,
  `VentaProducto`, `EncargarProducto`, `RegistroTurno`, `GestorCSV`,
  `StockInsuficienteException` (excepción personalizada) y
  `HiloGuardadoAutomatico` (hilo de guardado en segundo plano).
- **`Interfaces/`** — Contratos que habilitan herencia múltiple de
  comportamiento: `IGestionable` (CRUD: registrar, actualizar, eliminar,
  buscar, listar), `IPagable` (calcularTotal, procesarPago,
  getDescripcionPago) e `IReportable` (generarReporte,
  mostrarEstadisticas).
- **`controladores/`** — Lógica de gestión de cada entidad:
  `controladorCliente`, `ControladorEmpleado`, `ControladorPlan`,
  `ControladorProducto`, `ControladorVenta` y `ControladorAdmin` (este
  último implementa tanto `IGestionable` como `IReportable`).
- **`vista/`** — Interfaz gráfica en Swing: `VentanaLogin`,
  `ventanaPrincipal`, `PanelAdmin`, `PanelClientes`, `PanelEmpleados`,
  `PanelPlanes`, `PanelProductos`, `PanelVentas` y `Estilos` (tema visual
  centralizado).
- **`miapp/`** — Punto de entrada del sistema (`SistemaGymProyecto`), encargado
  de cargar los datos, lanzar el hilo de guardado automático y abrir la
  ventana de login.

## 🧱 Conceptos de POO aplicados

| Concepto | Dónde se aplica |
|---|---|
| **Herencia** | `Cliente`, `Empleado` e `Invitado` extienden de `Usuario`; `VentaProducto` extiende de `TransaccionProducto` |
| **Polimorfismo / ligadura dinámica** | Referencias de tipo `Usuario` invocando `mostrarInfo()`, resuelto en tiempo de ejecución |
| **Interfaces / herencia múltiple** | `ControladorAdmin implements IGestionable, IReportable`; `VentaProducto implements IPagable` |
| **Sobrecarga de métodos** | Métodos con *binding* estático en los controladores |
| **Excepciones personalizadas** | `StockInsuficienteException`, lanzada al registrar una venta sin stock suficiente |
| **Concurrencia / hilos** | `HiloGuardadoAutomatico implements Runnable`, con bloque `synchronized` y flag `volatile` para guardado automático cada 60 segundos sin bloquear la GUI |
| **Persistencia en archivos** | `GestorCSV`, lectura/escritura de `gym.csv` con secciones marcadas (`[PLANES]`, `[CLIENTES]`, etc.) |

## ⚙️ Requisitos

- JDK 17 o superior (configurado para compilar con `--release 25`)
- Maven (incluido en NetBeans, o instalado de forma independiente)
- NetBeans IDE (recomendado, opcional si usas terminal)

## ▶️ Cómo ejecutar el proyecto

### Opción 1: Desde NetBeans

1. Clona el repositorio:

```bash
git clone https://github.com/alejandro-herrera-ballestas/SistemaGym-Proyecto-Java.git
```

2. Abre NetBeans y selecciona **File → Open Project**, eligiendo la carpeta
   `SistemaGym-Proyecto-Java`.
3. Click derecho sobre el proyecto en el panel izquierdo → **Clean and Build**.
4. Click derecho sobre el proyecto → **Run**, o usa el botón de play (▶) en la
   barra superior.

### Opción 2: Desde terminal con Maven

```bash
git clone https://github.com/alejandro-herrera-ballestas/SistemaGym-Proyecto-Java.git
cd SistemaGym-Proyecto-Java
mvn compile
mvn exec:java -Dexec.mainClass="com.mycompany.sistemagym.proyecto.miapp.SistemaGymProyecto"
```

### 🔑 Acceso al sistema

Al iniciar, se abre la ventana de **login**. Credenciales de prueba:

```
Usuario:    admin
Contraseña: 1234
```

## 💾 Persistencia de datos

El sistema guarda automáticamente toda la información (planes, clientes,
empleados, productos y ventas) en un archivo `gym.csv` en la raíz del
proyecto:

- **Guardado automático**: un hilo en segundo plano (`HiloGuardadoAutomatico`)
  persiste los datos cada 60 segundos sin interrumpir la interfaz gráfica.
- **Guardado al cerrar sesión / salir** de la aplicación.
- **Carga inicial**: si `gym.csv` existe, los datos se cargan automáticamente
  al abrir el programa. Si no existe (primera ejecución), se cargan datos de
  demostración (planes, clientes, empleados y productos de ejemplo) para
  probar el sistema de inmediato.

## 🧭 Funcionalidades principales

Una vez dentro, la aplicación presenta un panel de navegación lateral con las
siguientes secciones:

1. **📊 Dashboard (Admin)** — estadísticas generales del sistema y estado de
   clientes activos/inactivos.
2. **👤 Clientes** — registrar, buscar, listar, actualizar, eliminar,
   calcular IMC, cambiar de plan y cambiar estado.
3. **👷 Empleados** — registrar, buscar, listar, agregar estudios, registrar
   y consultar turnos, eliminar.
4. **📋 Planes** — crear y listar planes de membresía.
5. **🛍 Productos** — agregar, listar, buscar y eliminar productos del
   inventario de la tienda.
6. **💰 Ventas** — registrar ventas (con validación de stock mediante
   `StockInsuficienteException`) y consultar historial.

## 👥 Integrantes

- Alejandro Herrera
- Aaron Hernández
- Steven Sierra
- Juan Cassiani
