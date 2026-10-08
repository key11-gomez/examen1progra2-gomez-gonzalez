# Examen Práctico Integrador — Programación II

> Este `README.md` contiene la **guía oficial del examen**. No lo modifique.
> El README de su proyecto se escribe en [`proyecto/README.md`](proyecto/README.md) (ver sección 37).

---

## 0. Inicio rápido

### 0.1 Crear la copia de la pareja

1. Ingrese a este repositorio y use **Use this template → Create a new repository** (o *Fork*, si el docente así lo indica).
2. Nombre del repositorio: `examen1progra2-apellido1-apellido2` (primer apellido de cada integrante).
3. Si el repositorio es **privado**, agregue al docente como colaborador: *Settings → Collaborators → Add people*.
4. El integrante que creó el repositorio agrega a su compañero(a) como colaborador con permiso de escritura.
5. Registre la URL del repositorio en el medio que indique el docente.

### 0.2 Clonar y configurar Git (cada integrante, en su propio equipo)

```bash
git clone https://github.com/<usuario>/examen1progra2-apellido1-apellido2.git
cd examen1progra2-apellido1-apellido2

git config user.name  "Nombre Apellido"
git config user.email "correo-asociado-a-su-cuenta@ejemplo.com"
```

> El correo configurado debe coincidir con el de su cuenta de GitHub; de lo contrario sus commits no aparecerán asociados a usted.

### 0.3 Requisitos técnicos

| Herramienta | Versión recomendada |
|---|---|
| JDK | 21 (LTS). Se admite 17 cambiando `maven.compiler.release` en `pom.xml` |
| JavaFX | 21 (se descarga automáticamente mediante Maven) |
| Maven | 3.9 o superior (o el que integra el IDE) |
| IDE | NetBeans, IntelliJ IDEA o VS Code con soporte Maven |
| Scene Builder | 21 o superior (opcional) |
| Git | Cualquier versión reciente |

### 0.4 Ejecutar el proyecto base

```bash
cd proyecto
mvn clean javafx:run
```

Desde el IDE: abra la carpeta `proyecto/` como proyecto Maven y ejecute la meta `javafx:run`.

El proyecto base ya incluye `App.java` con un método de navegación (`App.cambiarVista("registro")`), una vista `principal.fxml` con su Controller, un `estilos.css` y los paquetes `model`, `controller`, `service` y `exception`. Cada pareja lo adapta a su caso.

### 0.5 Fechas de entrega

| Corte | Etiqueta | Fecha y hora límite |
|---|---|---|
| Corte 1 | `corte-1` | *por definir por el docente* |
| Corte 2 | `corte-2` | *por definir por el docente* |
| Corte 3 | `corte-3` | *por definir por el docente* |
| Defensa | — | *por definir por el docente* |

Se evalúa el commit al que apunta cada etiqueta **publicada en GitHub** antes de la fecha límite.

---

## 1. Descripción general

El examen práctico se desarrollará de forma incremental a partir de este **repositorio Git** compartido por el docente.

Cada pareja deberá crear su propia copia del repositorio (sección 0.1) y utilizarla como único medio de trabajo y entrega durante todo el examen.

El desarrollo se realizará en **tres cortes**, todos sobre el mismo sistema. Cada corte deberá quedar registrado mediante commits claros y una etiqueta (`tag`) específica.

La evolución esperada será:

```text
CORTE 1
Análisis y diseño
      ↓
CORTE 2
Modelo orientado a objetos funcional
      ↓
CORTE 3
Aplicación JavaFX integrada
```

---

## 2. Modalidad

- Trabajo en parejas.
- Un único repositorio por pareja.
- Ambos integrantes deben realizar aportes identificables mediante commits hechos desde su propia cuenta.
- Lenguaje: Java.
- Interfaz gráfica: JavaFX.
- Gestión del proyecto: Maven (se entrega el `pom.xml` configurado).
- Se permite Scene Builder para las vistas FXML.
- Los datos se manejarán en memoria mediante colecciones.
- No se requiere conexión JDBC.
- No se requiere base de datos.
- No se requiere DAO.
- No se requiere persistencia permanente.

---

## 3. Temas evaluados

- clases y objetos;
- encapsulamiento;
- constructores;
- relaciones entre objetos;
- herencia;
- polimorfismo;
- interfaces;
- clases abstractas cuando correspondan;
- enumeraciones;
- colecciones genéricas;
- manejo de excepciones;
- excepciones personalizadas;
- validación de datos;
- separación de responsabilidades;
- JavaFX;
- controles;
- layouts;
- eventos;
- FXML;
- Controllers;
- Scene Builder;
- navegación entre vistas;
- CSS básico;
- organización inicial tipo MVC.

---

## 4. Estructura del repositorio base

```text
examen1progra2/
│
├── README.md                  ← esta guía (no modificar)
├── .gitignore
│
├── casos/
│   ├── caso_01.md             ← Gestión de préstamos de equipo
│   ├── caso_02.md             ← Gestión de actividades académicas
│   ├── caso_03.md             ← Gestión de reservas de espacios
│   ├── caso_04.md             ← Gestión de torneos
│   ├── caso_05.md             ← Gestión de citas
│   └── caso_06.md             ← Gestión de biblioteca
│
├── docs/
│   ├── corte_1/
│   │   ├── entrega.md
│   │   └── uml.png | uml.pdf  ← UML preliminar (lo agrega la pareja)
│   ├── corte_2/
│   │   └── entrega.md
│   └── corte_3/
│       ├── entrega.md
│       ├── uml.png | uml.pdf  ← UML final actualizado (lo agrega la pareja)
│       └── img/               ← capturas de pantalla
│
└── proyecto/
    ├── README.md              ← README final de la pareja
    ├── pom.xml
    └── src/main/...           ← ver sección 35
```

Cada pareja deberá trabajar sobre una copia propia de este repositorio. Las plantillas de `docs/` ya contienen los encabezados que deben completarse.

---

## 5. Casos disponibles

El detalle de cada caso (entidades sugeridas, reglas de negocio y pistas de diseño) está en la carpeta [`casos/`](casos/). Las sugerencias son orientativas: la pareja puede proponer otras clases siempre que justifique su diseño.

### Caso 1. Gestión de préstamos de equipo

Una institución necesita controlar préstamos de computadoras, proyectores, cámaras y otros equipos.

El sistema debe permitir registrar personas, equipos, préstamos, devoluciones y estados.

### Caso 2. Gestión de actividades académicas

Una institución desea administrar talleres, charlas, cursos cortos y conferencias.

El sistema debe permitir registrar actividades, participantes, responsables e inscripciones.

### Caso 3. Gestión de reservas de espacios

Se desea administrar reservas de aulas, laboratorios, salas de reuniones y auditorios.

El sistema debe controlar espacios, solicitantes, reservas, horarios y estados.

### Caso 4. Gestión de torneos

Una organización necesita administrar torneos, equipos, participantes, encuentros y resultados.

### Caso 5. Gestión de citas

Una organización necesita controlar citas entre clientes y profesionales.

El sistema debe administrar personas, profesionales, servicios, citas y estados.

### Caso 6. Gestión de biblioteca

Se requiere administrar materiales bibliográficos, usuarios, préstamos y devoluciones.

El sistema debe contemplar diferentes tipos de materiales y usuarios.

---

## 6. Selección del caso

Cada pareja deberá revisar los casos y seleccionar uno. Si el docente limita la cantidad de parejas por caso, la asignación se hará según el orden de registro.

La selección debe quedar documentada en `docs/corte_1/entrega.md` (secciones 8.1 a 8.8), donde se incluyen:

- caso seleccionado;
- justificación;
- principales entidades;
- operaciones esperadas;
- posibles usos de herencia;
- posibles usos de interfaces.

---

## CORTE 1 — Análisis, diseño y estructura inicial

### 7. Objetivo

Comprender el problema y construir una propuesta orientada a objetos antes de desarrollar la solución completa.

### 8. Entregables

Completar `docs/corte_1/entrega.md` con:

#### 8.1 Identificación

```text
Caso seleccionado:
Integrante 1:
Integrante 2:
URL del repositorio:
```

#### 8.2 Descripción del problema

Explicar:

- qué problema se desea resolver;
- quién utilizaría el sistema;
- cuáles son las principales operaciones;
- por qué se eligió este caso (justificación).

#### 8.3 Requerimientos funcionales

Definir al menos **ocho requerimientos funcionales**.

Ejemplo:

```text
RF-01. El sistema permitirá registrar actividades.
RF-02. El sistema permitirá inscribir participantes.
RF-03. El sistema permitirá cancelar una inscripción.
```

#### 8.4 Identificación de clases

| Clase | Responsabilidad | Atributos principales |
|---|---|---|
| Actividad | Representar una actividad | nombre, fecha, cupo |
| Participante | Representar una persona inscrita | identificación, nombre |

#### 8.5 Relaciones entre clases

Identificar y explicar, cuando correspondan:

- asociación;
- agregación;
- composición;
- herencia.

#### 8.6 Herencia o abstracción

El diseño debe incluir al menos una situación justificada donde pueda utilizarse una superclase, clase abstracta o jerarquía de especialización.

Ejemplo:

```text
Persona (abstracta)
├── Participante
└── Responsable
```

La jerarquía debe tener sentido en el caso: las subclases deben diferenciarse por atributos o comportamiento, no solo por el nombre.

#### 8.7 Interface

Identificar al menos un comportamiento que pueda modelarse mediante una interface.

Ejemplo:

```java
public interface Cancelable {
    void cancelar();
}
```

#### 8.8 Enumeración

Identificar al menos una situación apropiada para `enum`.

Ejemplo:

```java
public enum EstadoReserva {
    PENDIENTE,
    CONFIRMADA,
    CANCELADA
}
```

#### 8.9 UML preliminar

Incluir un diagrama de clases UML preliminar en `docs/corte_1/uml.png` o `docs/corte_1/uml.pdf`.

Puede elaborarse con cualquier herramienta (draw.io, PlantUML, StarUML, Visual Paradigm, etc.). Si usa una herramienta con archivo fuente (`.drawio`, `.puml`), inclúyalo también en la misma carpeta.

Debe mostrar:

- clases principales;
- atributos relevantes;
- relaciones;
- multiplicidades cuando correspondan;
- herencia;
- interfaces.

### 9. Código mínimo del Corte 1

El proyecto Java deberá existir dentro de la carpeta `proyecto/` del repositorio (se parte del proyecto base entregado).

Como mínimo:

- clases principales en el paquete `model`;
- constructores;
- atributos encapsulados (`private`);
- getters/setters cuando correspondan;
- enumeraciones;
- interface;
- jerarquía principal.

No es necesario desarrollar todavía la interfaz JavaFX. El proyecto debe compilar.

> El código del Corte 1 se valora dentro de los criterios «Identificación de clases y responsabilidades» y «Organización inicial del repositorio y commits» de la rúbrica.

### 10. Evidencia

Debe existir la etiqueta `corte-1` publicada en GitHub (ver sección 43.1).

---

## CORTE 2 — Modelo orientado a objetos funcional

### 11. Objetivo

Convertir el diseño inicial en un modelo Java funcional.

### 12. Colecciones

El sistema deberá utilizar colecciones genéricas.

Ejemplos:

```java
List<Actividad> actividades = new ArrayList<>();
List<Participante> participantes = new ArrayList<>();
Map<String, Reserva> reservasPorCodigo = new HashMap<>();
```

No se deberán utilizar arreglos tradicionales como mecanismo principal de almacenamiento.

Se recomienda que las colecciones estén en una clase gestora (paquete `service`, por ejemplo `GestorActividades`) y no dispersas en los Controllers.

### 13. Operaciones mínimas

El modelo debe permitir, según el caso:

- registrar;
- consultar;
- buscar;
- modificar estados;
- cancelar o eliminar cuando corresponda;
- listar información.

### 14. Polimorfismo

Debe existir **uso real** de polimorfismo: no basta con guardar subclases en una lista de la superclase; debe invocarse un método sobrescrito cuyo comportamiento cambie según el tipo concreto.

Ejemplo:

```java
public abstract class Persona {
    // ...
    public abstract String describirRol();
}

List<Persona> personas = new ArrayList<>();
personas.add(new Participante(...));
personas.add(new Responsable(...));

for (Persona p : personas) {
    System.out.println(p.describirRol()); // cada subclase responde distinto
}
```

### 15. Uso de interfaces

La interface propuesta en el Corte 1 deberá implementarse y utilizarse (por ejemplo, invocándola a través de una variable del tipo de la interface).

### 16. Validaciones

El modelo deberá validar reglas relevantes.

Ejemplos:

- cupos positivos;
- campos obligatorios;
- evitar identificadores duplicados;
- evitar operaciones incompatibles con el estado actual;
- fechas válidas (use `java.time.LocalDate` / `LocalDateTime`).

Las validaciones deben vivir en el modelo (o en `service`), de modo que se cumplan sin importar desde dónde se invoquen.

### 17. Excepción personalizada

Crear y utilizar al menos una excepción personalizada en el paquete `exception`. Debe lanzarse desde el modelo cuando se viola una regla del negocio y capturarse en quien invoca la operación.

Ejemplo:

```java
public class CupoAgotadoException extends Exception {

    public CupoAgotadoException(String mensaje) {
        super(mensaje);
    }
}
```

### 18. Prueba del modelo

El funcionamiento deberá poder demostrarse mediante código antes de la interfaz final.

Puede utilizarse temporalmente una clase de prueba manual con `main()` (por ejemplo `cr.ac.proyecto.PruebaModelo`) que cree objetos, ejecute operaciones y provoque al menos una excepción controlada.

### 19. Regla de independencia del modelo

Las clases de `model`, `service` y `exception` **no deben importar nada de `javafx.*`**. El modelo debe poder usarse igual desde un `main()` de consola o desde la interfaz gráfica.

### 20. Documento del Corte 2

Completar `docs/corte_2/entrega.md` incluyendo:

- cambios al diseño;
- clases implementadas;
- colecciones utilizadas;
- ejemplo de herencia;
- ejemplo de polimorfismo;
- interface;
- excepción personalizada;
- reglas implementadas;
- evidencia de ejecución de la prueba del modelo.

### 21. Evidencia

Debe existir la etiqueta `corte-2` publicada en GitHub.

---

## CORTE 3 — Aplicación JavaFX integrada

### 22. Objetivo

Construir una aplicación gráfica que utilice el modelo de los cortes anteriores.

La integración esperada es:

```text
JavaFX
+
FXML
+
Controllers
+
Modelo orientado a objetos
+
Colecciones en memoria
```

### 23. Scene Builder

Se permite utilizar Scene Builder para diseñar las vistas.

Los archivos FXML deben permanecer dentro del repositorio, en `proyecto/src/main/resources/cr/ac/proyecto/`.

### 24. Pantallas mínimas

La aplicación deberá contener al menos:

1. pantalla principal;
2. formulario de registro;
3. pantalla de consulta o listado;
4. una pantalla adicional relacionada con el caso (por ejemplo: gestión de estados, devoluciones, inscripciones, resultados).

Las cuatro vistas deben pertenecer a la misma aplicación.

### 25. Navegación

Debe existir navegación funcional entre vistas (ida y regreso a la pantalla principal).

No se aceptarán aplicaciones independientes como sustituto de la navegación.

El proyecto base incluye `App.cambiarVista("nombreVista")` como mecanismo sugerido.

### 26. Datos compartidos entre vistas

Como no hay persistencia, al cambiar de vista **no deben perderse los datos**. Utilice una única instancia del gestor de colecciones compartida por todos los Controllers (por ejemplo, un *singleton* o un atributo estático en una clase del paquete `service`).

Se recomienda cargar algunos **datos de prueba** al iniciar la aplicación para agilizar la demostración y la defensa.

### 27. FXML

Las vistas deberán construirse mediante FXML.

Ejemplo:

```text
principal.fxml
registro.fxml
consulta.fxml
gestion.fxml
```

### 28. Controllers

Cada vista deberá tener un Controller cuando corresponda (paquete `controller`).

Los Controllers deberán coordinar la interacción entre la vista y los objetos del dominio. No deben contener las reglas del negocio: estas permanecen en el modelo.

### 29. Controles

Al menos uno de los formularios deberá utilizar varios tipos de controles apropiados, por ejemplo:

```text
TextField
ComboBox
DatePicker
Button
Label
TableView
```

Sugerencia: llene los `ComboBox` con los valores de sus `enum` (`EstadoReserva.values()`).

### 30. Layouts

Utilizar layouts apropiados:

```text
BorderPane
GridPane
VBox
HBox
```

Evitar construir toda la interfaz mediante coordenadas manuales (`AnchorPane` con posiciones fijas para todo).

### 31. Validaciones desde la interfaz

Los datos deberán validarse antes de crear o modificar objetos (campos vacíos, números no válidos, fechas sin seleccionar, etc.).

Una entrada inválida no deberá provocar el cierre de la aplicación.

### 32. Manejo de excepciones

La interfaz deberá manejar las excepciones generadas por el modelo.

Flujo esperado:

```text
Usuario
   ↓
Controller
   ↓
Modelo
   ↓
Excepción
   ↓
Controller (try/catch)
   ↓
Mensaje al usuario (Alert o Label de error)
```

### 33. Tabla o listado

La aplicación deberá mostrar una colección de objetos mediante `TableView` o un control equivalente apropiado.

> Si usa `PropertyValueFactory`, el nombre de la columna debe coincidir con un getter público del modelo (por ejemplo `"nombre"` → `getNombre()`), y el paquete `model` debe estar abierto a `javafx.base` en `module-info.java` (el proyecto base ya lo incluye).

### 34. CSS

La aplicación deberá utilizar al menos un archivo CSS (el proyecto base incluye `estilos.css` como punto de partida).

### 35. Organización del proyecto

```text
proyecto/
├── pom.xml
└── src/main
    ├── java
    │   ├── module-info.java
    │   └── cr/ac/proyecto
    │       ├── App.java
    │       ├── model/
    │       ├── controller/
    │       ├── service/        (opcional)
    │       └── exception/
    │
    └── resources
        └── cr/ac/proyecto
            ├── principal.fxml
            ├── registro.fxml
            ├── consulta.fxml
            ├── gestion.fxml
            └── estilos.css
```

Notas:

- La carpeta `service` es opcional.
- Si agrega un paquete nuevo con Controllers, ábralo a `javafx.fxml` en `module-info.java` (`opens ... to javafx.fxml;`).
- En cada FXML, el atributo `fx:controller` debe tener el nombre completo de la clase, por ejemplo `cr.ac.proyecto.controller.RegistroController`.

### 36. Restricción de persistencia

Para este examen:

```text
NO se requiere JDBC.
NO se requiere base de datos.
NO se requiere DAO.
NO se requiere persistencia permanente.
```

Los datos podrán mantenerse en colecciones en memoria.

### 37. README final

El archivo [`proyecto/README.md`](proyecto/README.md) deberá incluir:

- curso;
- integrantes;
- caso seleccionado;
- descripción del sistema;
- funcionalidades implementadas;
- estructura del proyecto;
- pasos para ejecutar;
- tecnologías utilizadas (con versiones);
- capturas de las pantallas principales.

Las capturas podrán almacenarse en `docs/corte_3/img/` y enlazarse desde el README.

### 38. Documento del Corte 3

Completar `docs/corte_3/entrega.md` incluyendo:

- pantallas implementadas;
- navegación;
- Controllers;
- validaciones;
- manejo de excepciones;
- relación interfaz–modelo;
- decisiones relevantes;
- aspectos pendientes.

Además, actualizar el diagrama UML para que corresponda con el código final: `docs/corte_3/uml.png` o `docs/corte_3/uml.pdf`.

### 39. Evidencia

Debe existir la etiqueta `corte-3` publicada en GitHub.

---

## 40. Defensa

Cada pareja deberá ejecutar el sistema y responder preguntas individualmente.

El docente podrá solicitar explicaciones o pequeños cambios relacionados con:

- clases;
- relaciones;
- herencia;
- polimorfismo;
- interfaces;
- colecciones;
- excepciones;
- FXML;
- Controllers;
- eventos;
- navegación;
- validaciones.

La comprensión individual forma parte de la evaluación. Lleve el proyecto clonado y probado en el equipo donde realizará la defensa.

---

## 41. Distribución de la evaluación

| Corte | Componentes principales | Valor |
|---|---|---:|
| Corte 1 | Análisis, UML y estructura OO | 25 % |
| Corte 2 | Modelo funcional, colecciones, polimorfismo y excepciones | 35 % |
| Corte 3 | JavaFX, FXML, Controllers, integración y defensa | 40 % |
| **Total** | | **100 %** |

---

## 42. Rúbricas

### 42.1 Corte 1 — 25 puntos

| Criterio | Puntos |
|---|---:|
| Comprensión y descripción del caso | 4 |
| Requerimientos funcionales | 4 |
| Identificación de clases y responsabilidades | 5 |
| Relaciones y decisiones de modelado | 4 |
| UML preliminar | 5 |
| Organización inicial del repositorio y commits | 3 |
| **Total** | **25** |

### 42.2 Corte 2 — 35 puntos

| Criterio | Puntos |
|---|---:|
| Implementación correcta de clases | 6 |
| Encapsulamiento y relaciones | 5 |
| Herencia y polimorfismo | 6 |
| Interface y enum | 4 |
| Colecciones genéricas y operaciones | 6 |
| Validaciones y excepción personalizada | 5 |
| Calidad de commits y documentación | 3 |
| **Total** | **35** |

### 42.3 Corte 3 — 40 puntos

| Criterio | Puntos |
|---|---:|
| Pantalla principal y navegación | 5 |
| Formularios y controles JavaFX | 6 |
| FXML y Controllers | 6 |
| Integración interfaz–modelo | 7 |
| Validaciones y manejo de excepciones | 5 |
| Consulta/listado de objetos | 4 |
| CSS y organización general | 2 |
| README, repositorio y evidencias | 2 |
| Defensa y comprensión individual | 3 |
| **Total** | **40** |

---

## 43. Reglas de trabajo con Git

Los commits deben describir cambios reales.

Ejemplos adecuados:

```text
Agrega modelo inicial de participantes
Implementa jerarquía de equipos
Agrega validaciones de reserva
Crea vista principal en JavaFX
Integra formulario de actividades
```

Evite mensajes como:

```text
cambios
avance
prueba
final
```

El historial debe mostrar evolución real y ambos integrantes deben tener aportes identificables. Si trabajan juntos en un mismo equipo (programación en pareja), agreguen al final del mensaje del commit:

```text
Co-authored-by: Nombre Compañero <correo-del-companero@ejemplo.com>
```

Otras reglas:

- Haga `git pull` antes de empezar a trabajar y `git push` al terminar cada sesión.
- No suba archivos generados (`target/`, `.class`, configuraciones del IDE); el `.gitignore` ya los excluye.
- No suba el proyecto comprimido (`.zip`, `.rar`).

### 43.1 Cómo crear y publicar la etiqueta de cada corte

```bash
# 1. Asegúrese de que todo está confirmado y publicado
git status
git push

# 2. Cree la etiqueta sobre el último commit del corte
git tag -a corte-1 -m "Entrega del Corte 1"

# 3. Publique la etiqueta (git push NO publica etiquetas por sí solo)
git push origin corte-1
```

Verifique en GitHub (*Code → Tags*) que la etiqueta aparece. Use `corte-2` y `corte-3` en los cortes siguientes.

No elimine ni mueva una etiqueta ya publicada. Si necesita corregir algo después de la fecha límite, consulte al docente.

---

## 44. Lista de comprobación final

```text
[ ] Trabajamos sobre el repositorio asignado.
[ ] Ambos integrantes tienen commits identificables.
[ ] Existe la etiqueta corte-1 (publicada en GitHub).
[ ] Existe la etiqueta corte-2 (publicada en GitHub).
[ ] Existe la etiqueta corte-3 (publicada en GitHub).
[ ] El archivo proyecto/README.md está actualizado.
[ ] El UML final (docs/corte_3/) corresponde con el código actual.
[ ] Utilizamos encapsulamiento.
[ ] Utilizamos relaciones entre objetos.
[ ] Existe una jerarquía de herencia justificada.
[ ] Existe polimorfismo real.
[ ] Implementamos una interface.
[ ] Utilizamos al menos un enum.
[ ] Utilizamos colecciones genéricas.
[ ] Existe una excepción personalizada.
[ ] Las reglas principales están validadas.
[ ] La aplicación utiliza JavaFX.
[ ] Utilizamos FXML.
[ ] Utilizamos Controllers.
[ ] Existe navegación entre vistas.
[ ] Los datos se conservan al navegar entre vistas.
[ ] El modelo no depende de controles JavaFX.
[ ] La aplicación no requiere JDBC.
[ ] La aplicación ejecuta sin errores con: mvn clean javafx:run
[ ] Ambos integrantes pueden explicar el código.
```

---

## 45. Resultado esperado

El repositorio deberá evidenciar claramente la evolución:

```text
CASO
  ↓
ANÁLISIS
  ↓
UML
  ↓
MODELO ORIENTADO A OBJETOS
  ↓
COLECCIONES + REGLAS
  ↓
EXCEPCIONES
  ↓
JAVAFX
  ↓
FXML + CONTROLLERS
  ↓
APLICACIÓN INTEGRADA
```

El repositorio no es únicamente el medio de entrega: también constituye evidencia del proceso de desarrollo realizado durante los tres cortes.
