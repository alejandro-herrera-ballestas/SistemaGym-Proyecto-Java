# SistemaGym-Proyecto-Java

Proyecto colaborativo desarrollado en Java para la gestión de un gimnasio.
Permite administrar clientes, empleados, planes, productos/tienda y ventas, con
persistencia de datos en archivo y manejo de excepciones personalizadas.

## 📂 Estructura del proyecto

El proyecto está organizado en paquetes para separar responsabilidades:

- `Modelos/` — Clases del dominio (`Usuario`, `Cliente`, `Empleado`, `Invitado`,
  `Plan`, `Producto`, `Tienda`, `TransaccionProducto`, `VentaProducto`,
  `EncargarProducto`, `RegistroTurno`, `GestorCSV`, `StockInsuficienteException`).
- `controladores/` — Lógica de gestión de cada entidad (`controladorCliente`,
  `ControladorEmpleado`, `ControladorPlan`, `ControladorProducto`,
  `ControladorVenta`, `ControladorAdmin`).
- `miapp/` — Punto de entrada del sistema (`SistemaGymProyecto`), con el menú
  de consola principal.

## ⚙️ Requisitos

Antes de ejecutar el proyecto asegúrate de tener instalado:

- JDK 17 o superior
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

## 💾 Persistencia de datos

El sistema guarda automáticamente toda la información (planes, clientes,
empleados, productos y ventas) en un archivo `gym.csv` ubicado en la raíz del
proyecto, cada vez que se cierra el programa desde la opción `[0] Salir` del
menú principal.

En la siguiente ejecución, si `gym.csv` existe, los datos se cargan
automáticamente. Si no existe (primera ejecución), el sistema carga un set de
datos de demostración para que puedas probar el sistema de inmediato.

## 🧭 Funcionalidades principales

Al ejecutar el programa se muestra un menú principal con las siguientes
opciones:

1. **Gestión de Clientes** — registrar, buscar, listar, actualizar, eliminar,
   calcular IMC, cambiar plan y estado.
2. **Gestión de Empleados** — registrar, buscar, listar, agregar estudios,
   registrar y consultar turnos, eliminar.
3. **Gestión de Planes** — crear y listar planes de membresía.
4. **Gestión de Productos / Tienda** — agregar, listar, buscar y eliminar
   productos del inventario.
5. **Ventas** — registrar ventas (con validación de stock mediante excepción
   personalizada) y consultar historial.
6. **Administración / Estadísticas** — estadísticas generales del sistema y
   estado de clientes activos/inactivos.

## 👥 Integrantes

- Alejandro Herrera
- Aaron Hernández
- Steven Sierra
- Juan Cassiani
