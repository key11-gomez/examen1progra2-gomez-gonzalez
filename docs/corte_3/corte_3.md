# Corte 3 — Aplicación JavaFX integrada

**Caso:** Gestión de citas

**Integrantes:** Keisy Gomez, Jhoyner Gonzalez

**Repositorio:** https://github.com/key11-gomez/examen1progra2-gomez-gonzalez

## Pantallas implementadas

| Vista (FXML) | Controller | Propósito | Captura |
|---|---|---|---|
| `principal.fxml` | `PrincipalController` |Menú central de bienvenida de la aplicación. | `img/principal.png` |
| `registro.fxml` |`RegistroController` | Permite registrar un profesional, agendar una cita y agregar un nuevo cliente|`img/registro.png` |
| `consulta.fxml` | `ConsultaController`| Muestra en tiempo real la colección de citas almacenadas en memoria|`img/consulta.png` | 
| `gestion.fxml` | `GestionController`|  Permite seleccionar una cita específica de la tabla y realizar acciones sobre su flujo operativo|`img/gestion.png` |

## Navegación

La navegación entre vistas se realiza de forma bidireccional mediante un 
mecanismo centralizado en la clase principal App.java: 
- Todas las vistas comparten la misma escena (Scene) primaria, cambiando 
únicamente su nodo raíz mediante la invocación estática de 
App.cambiarVista("nombreVista"). 
- Cada pantalla secundaria contiene un botón de navegación hacia atrás ("Volver 
al Menú Principal") que retorna al usuario a la vista principal.fxml de forma fluida y 
sin abrir ventanas secundarias ni duplicar instancias.

## Controllers

- PrincipalController: Gestiona las acciones de los botones del menú inicial para 
cambiar hacia las demás pantallas. 
- RegistroController: Maneja la captura y validación visual de datos, la inserción de 
nuevos clientes y profesionales en las colecciones, y la programación de las citas. 
- ConsultaController: Sincroniza la lista observable de citas del gestor en memoria 
con las columnas de la tabla de listado. 
- GestionController: Captura la cita seleccionada en la tabla y coordina la 
ejecución de las acciones de cambio de estado, forzando la actualización visual 
de la interfaz. 



## Validaciones

Se comprueba la presencia de datos obligatorios en campos de texto, fechas 
seleccionadas y elementos elegidos en las listas desplegables.

Se valida el formato de entrada de tiempo (HH:mm) capturando posibles fallos 
de conversión (DateTimeParseException) para guiar al usuario. 

El modelo valida internamente la no duplicidad de identificaciones, la validez de 
los rangos de fechas/horas y la disponibilidad de horarios.

## Manejo de excepciones

<Ejemplo del flujo Usuario → Controller → Modelo → Excepción → Controller → Mensaje.>

```java
 try {
            App.cambiarVista("principal");
        } catch (IOException e) {
            e.printStackTrace();
        }
```

## Relación interfaz–modelo

- Aislamiento: Las clases de los paquetes model, service y exception no poseen 
referencias ni importaciones a librerías de JavaFX (javafx.*), manteniendo total 
independencia. 
- Enlace de Datos (TableView): La tabla se mapea dinámicamente utilizando 
PropertyValueFactory, vinculando las columnas directamente con los métodos de 
lectura públicos (getters) de la clase del modelo.
- Configuración de Módulos: El archivo module-info.java expone y abre los 
paquetes requeridos (opens cr.ac.proyecto.model to javafx.base;) para posibilitar 
la lectura por reflexión de JavaFX.

## Decisiones relevantes

- Gestor Compartido en Memoria : Se centralizó la administración de colecciones 
en la clase GestorCitas garantizando la persistencia de los datos en memoria 
durante la navegación entre pantallas. 
- Carga Automática de Datos de Prueba: La aplicación inicia con un conjunto base 
de clientes, profesionales y servicios precargados para dinamizar la demostración 
operativa del sistema. 
- Asignación Automática de Servicios: Al registrar un nuevo profesional por la 
interfaz, se le otorgan permisos sobre los servicios existentes para habilitar 
inmediatamente la programación de citas con su perfil. 
- Diseño Visual Personalizado: Se adaptó la hoja de estilos estilos.css con una 
paleta de colores corporativa basada en tonos verdes clínicos (#005227, #038554, 
#DCEBE3), optimizando el contraste y la legibilidad de títulos, botones y filas de 
tablas. 

## Aspectos pendientes

- Integración futura con un sistema de almacenamiento persistente en base de 
datos o archivos planos. 


## UML final

Archivo: `docs/corte_3/img/uml.png` 

## Distribución del trabajo

| Integrante | Aportes principales |
|-|---|
|Keisy | Diseño de la paleta de colores, carga automatica de datos|
|Jhoyner | Encargado de realizar la parte grafica y diseño visual|