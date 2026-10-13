# Corte 2 — Modelo orientado a objetos funcional

**Caso:** Gestión de citas

**Integrantes:** Keisy Gomez, Jhoyner Gonzalez

**Repositorio:** https://github.com/key11-gomez/examen1progra2-gomez-gonzalez

## 1. Cambios al diseño

Respecto al Corte 1 se hicieron los siguientes ajustes:

- Se implementó la clase `GestorCitas` (`service`), que concentra las colecciones y las reglas de negocio. Así la lógica no queda dispersa en los Controllers.
- `Cita` calcula su costo final: toma el costo del servicio y aplica un 10 % de descuento si el cliente es frecuente.
- `GestorCitas` se implementó como **singleton**, para que todas las pantallas del Corte 3 compartan los mismos datos.
- Las excepciones se nombraron `HorarioNoDisponible` y `CitaNoModificable`.

## 2. Clases implementadas

| Paquete | Clases |
|---|---|
| `model` | `Persona` (abstracta), `Cliente`, `Profesional`, `Servicio` (abstracta), `ServicioPresencial`, `ServicioVirtual`, `Cita`, `EstadoCita` (enum), `Especialidad` (enum), `Cancelable` (interface), `Reprogramable` (interface) |
| `exception` | `HorarioNoDisponible`, `CitaNoModificable` |
| `service` | `GestorCitas` |
| `cr.ac.proyecto` | `PruebaModelo` |

## 3. Colecciones utilizadas

Las colecciones están en `GestorCitas`:

```java
private final Map<String, Cliente> clientes = new LinkedHashMap<>();
private final Map<String, Profesional> profesionales = new LinkedHashMap<>();
private final Map<String, Servicio> servicios = new LinkedHashMap<>();
private final List<Cita> citas = new ArrayList<>();
```

Además, `Profesional` mantiene un `List<Servicio>` con los servicios que ofrece.

Se usan `Map` con la identificación o el código como llave para búsquedas directas y para impedir duplicados, y `List` para las citas, que se recorren al listar y al validar traslapes. No se utilizan arreglos tradicionales.

**Operaciones disponibles en `GestorCitas`:**

- Registrar: `registrarCliente`, `registrarProfesional`, `registrarServicio`, `asignarServicio`.
- Citas: `programarCita`, `reprogramarCita`, `cancelarCita`, `marcarAtendida`, `marcarAusente`.
- Buscar: `buscarCliente`, `buscarProfesional`, `buscarServicio`, `buscarCita`.
- Listar: `listarClientes`, `listarProfesionales`, `listarServicios`, `listarCitas`, `listarCitasPorEstado`, `listarCitasPorProfesional`, `listarCitasPorCliente`, `listarProfesionalesPorServicio`.

## 4. Ejemplo de herencia

```java
public abstract class Persona { ... }
public class Cliente extends Persona { ... }
public class Profesional extends Persona { ... }

public abstract class Servicio { ... }
public class ServicioPresencial extends Servicio { ... }
public class ServicioVirtual extends Servicio { ... }
```

`Cliente` agrega el atributo `frecuente`. `Profesional` agrega especialidad, horario y los servicios que ofrece. `ServicioPresencial` agrega un recargo de consultorio y `ServicioVirtual` agrega la plataforma.

## 5. Ejemplo de polimorfismo

**`describirRol()`** 

```java
List<Persona> personas = new ArrayList<>();
personas.addAll(gestor.listarClientes());
personas.addAll(gestor.listarProfesionales());
for (Persona p : personas) {
    System.out.println(p + " -> " + p.describirRol());
}
```

Cada objeto responde distinto: un `Cliente` devuelve "Cliente" o "Cliente frecuente" y un `Profesional` devuelve "Profesional de <especialidad>".

**`calcularCosto()`** (método abstracto en `Servicio`):

- `ServicioPresencial`: `precio + recargoConsultorio`.
- `ServicioVirtual`: `precio` con un 10 % de descuento.

```java
for (Servicio s : gestor.listarServicios()) {
    System.out.println(s + " -> " + s.calcularCosto());
}
```

## 6. Interface

`Cita` implementa `Cancelable` y `Reprogramable`:

```java
public class Cita implements Cancelable, Reprogramable { ... }
```

Se utiliza a través de una variable del tipo de la interface en `PruebaModelo`:

```java
Cancelable cancelable = c1;
cancelable.cancelar();
```

## 7. Excepciones personalizadas

- `HorarioNoDisponible`: se lanza cuando la fecha está en el pasado, cuando la cita queda fuera del horario del profesional o cuando se traslapa con otra cita del mismo profesional.
- `CitaNoModificable`: se lanza al intentar cancelar, reprogramar o cambiar el estado de una cita que no está en `PROGRAMADA`.

Se lanzan desde `Cita` y `GestorCitas`, y se capturan en quien invoca la operación.

Las validaciones de formato (campos vacíos, identificaciones duplicadas, ids inexistentes, precio negativo) lanzan `IllegalArgumentException`.

## 8. Reglas implementadas

| Regla | Dónde se valida | Excepción |
|---|---|---|
| Un profesional no puede tener dos citas que se traslapen | `GestorCitas.validarDisponibilidad` | `HorarioNoDisponible` |
| No se pueden programar citas en fechas pasadas | `GestorCitas.programarCita` y `reprogramarCita` | `HorarioNoDisponible` |
| La cita debe estar dentro del horario del profesional | `GestorCitas.validarDisponibilidad` | `HorarioNoDisponible` |
| Solo se puede reprogramar o cancelar una cita `PROGRAMADA` | `Cita` y `GestorCitas.reprogramarCita` | `CitaNoModificable` |
| El profesional debe ofrecer el servicio solicitado | `GestorCitas.programarCita` | `IllegalArgumentException` |
| No se permiten identificaciones o códigos duplicados | `GestorCitas.registrar` | `IllegalArgumentException` |
| Campos obligatorios, correo válido, duración y precio válidos | Constructores y setters del modelo | `IllegalArgumentException` |

## 9. Evidencia de ejecución de la prueba del modelo

Clase: `cr.ac.proyecto.PruebaModelo`.

Archivo: `docs/corte_2/PruebaModelo.png`



## 10. UML actualizado

Archivo: `docs/corte_2/uml.jpng`
## 11. Distribución del trabajo

| Integrante | Aportes principales |
|---|---|
| Keisy Gomez | Clase `GestorCitas` del paquete `service`: colecciones genéricas, operaciones de registro, búsqueda y listado, y validación de las reglas de negocio. |
| Jhoyner Gonzalez | Clase `PruebaModelo` del paquete `cr.ac.proyecto`: prueba manual por consola que demuestra el polimorfismo, la interface y las excepciones controladas. |


