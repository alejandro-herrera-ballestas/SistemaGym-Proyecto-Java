# SistemaGym-Proyecto-Java

Proyecto colaborativo desarrollado en Java para la gestión integral de un gimnasio.  
Permite administrar clientes, empleados, planes de membresía, productos, ventas e inventario, utilizando una arquitectura organizada por capas, persistencia de datos en archivos CSV y manejo de excepciones personalizadas.

El sistema cuenta con una interfaz gráfica para la interacción del usuario y almacenamiento automático de información.

---

## 📂 Estructura del proyecto

El proyecto está organizado en paquetes para separar responsabilidades:

### `Modelos/`
Contiene las clases principales del dominio del sistema:

- Usuario
- Cliente
- Empleado
- Invitado
- Plan
- Producto
- Tienda
- TransaccionProducto
- VentaProducto
- EncargarProducto
- RegistroTurno
- GestorCSV
- HiloGuardadoAutomatico
- StockInsuficienteException

Además incluye la lógica de entidades y validaciones principales.

---

### `controladores/`
Contiene la lógica de gestión y comunicación entre la interfaz y los modelos:

- controladorCliente
- ControladorEmpleado
- ControladorPlan
- ControladorProducto
- ControladorVenta
- ControladorAdmin

Cada controlador administra las operaciones CRUD y procesos específicos de cada módulo.

---

### `vista/`
Contiene la interfaz gráfica del sistema:

- VentanaLogin
- ventanaPrincipal
- PanelClientes
- PanelEmpleados
- PanelPlanes
- PanelProductos
- PanelVentas
- PanelAdmin
- Estilos

Permite interactuar con el sistema mediante ventanas y paneles.

---

### `Interfaces/`
Contiene contratos utilizados para aplicar abstracción y organización del código:

- IGestionable
- IPagable
- IReportable

---

### `miapp/`
Punto de entrada del programa:

- SistemaGymProyecto

Inicializa la aplicación y carga el sistema principal.

---

# ⚙️ Requisitos

Antes de ejecutar el proyecto asegúrate de tener instalado:

- JDK 17 o superior
- Apache Maven
- NetBeans IDE (recomendado)

---

# ▶️ Cómo ejecutar el proyecto

## Opción 1: Desde NetBeans

Clona el repositorio:

```bash
git clone https://github.com/alejandro-herrera-ballestas/SistemaGym-Proyecto-Java.git
