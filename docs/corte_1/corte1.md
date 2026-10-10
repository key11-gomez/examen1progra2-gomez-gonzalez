# Corte 1 — Análisis, diseño y estructura inicial

## 8.1 Identificación

```text
Caso seleccionado: Caso 5. Gestión de citas
Integrante 1: Keisy Gomez
Integrante 2: Jhoyner Gonzalez
URL del repositorio: https://github.com/key11-gomez/examen1progra2-gomez-gonzalez
```

## 8.2 Descripción del problema

**Problema.** Una organización necesita controlar las citas entre clientes y profesionales. Hoy ese control se haría de forma manual, lo que facilita errores como asignar dos citas al mismo profesional a la misma hora, agendar en fechas pasadas o programar un servicio que el profesional no ofrece.

**Quién utiliza el sistema.** Una recepcionista o personal administrativo, que registra los datos y gestiona las citas en nombre de los clientes.

**Principales operaciones.**
- Registrar clientes, profesionales y servicios.
- Asignar a cada profesional los servicios que ofrece.
- Programar, reprogramar y cancelar citas.
- Marcar una cita como atendida o como ausente.
- Consultar y listar citas (por estado, profesional o cliente).
- Calcular el costo de una cita.

**Justificación.** Se eligió este caso porque tiene reglas de negocio claras y verificables, y permite aplicar de forma natural herencia (en clases como personas y servicios), polimorfismo (cálculo de costo y descripción de rol), interfaces y enumeraciones.

## 8.3 Requerimientos funcionales

```text
RF-01. El sistema permitirá registrar clientes (identificación, nombre, teléfono, correo).
RF-02. El sistema permitirá registrar profesionales con su especialidad y horario de atención.
RF-03. El sistema permitirá registrar servicios presenciales y virtuales (código, nombre, duración, precio).
RF-04. El sistema permitirá asignar a un profesional los servicios que ofrece.
RF-05. El sistema permitirá programar una cita entre un cliente y un profesional para un servicio, fecha y hora.
RF-06. El sistema impedirá programar citas en fechas pasadas, fuera del horario del profesional o que se traslapen con otra cita del mismo profesional.
RF-07. El sistema impedirá programar un servicio que el profesional no ofrece.
RF-08. El sistema permitirá reprogramar una cita que esté en estado PROGRAMADA.
RF-09. El sistema permitirá cancelar una cita que esté en estado PROGRAMADA.
RF-10. El sistema permitirá marcar una cita como ATENDIDA o AUSENTE.
RF-11. El sistema permitirá consultar y listar citas por estado, por profesional y por cliente.
RF-12. El sistema calculará el costo de una cita según el tipo de servicio y si el cliente es frecuente.
```

## 8.4 Identificación de clases

| Clase | Responsabilidad | Atributos principales |
|---|---|---|
| `Persona` (abstracta) | Representar los datos comunes de una persona | identificacion, nombre, telefono, correo |
| `Cliente` | Persona que solicita citas | frecuente |
| `Profesional` | Persona que atiende citas | especialidad, horaInicio, horaFin, servicios |
| `Servicio` (abstracta) | Representar un servicio ofrecido | codigo, nombre, duracionMinutos, precio |
| `ServicioPresencial` | Servicio que se presta en el consultorio | recargoConsultorio |
| `ServicioVirtual` | Servicio que se presta en línea | plataforma |
| `Cita` | Representar una cita y su ciclo de estados | id, cliente, profesional, servicio, fechaHora, estado |
| `GestorCitas` (paquete `service`) | Administrar las colecciones y aplicar las reglas | clientes, profesionales, servicios, citas |
| `EstadoCita` (enum) | Estados de una cita | PROGRAMADA, ATENDIDA, CANCELADA, AUSENTE |
| `Especialidad` (enum) | Especialidades de los profesionales | MEDICINA_GENERAL, ODONTOLOGIA, PSICOLOGIA, NUTRICION, FISIOTERAPIA |
| `HorarioNoDisponible (exception)` | Error por fecha inválida, fuera de horario o traslape | mensaje |
| `CitaNoModificable (exception)` | Error al modificar una cita que no está PROGRAMADA | mensaje |

## 8.5 Relaciones entre clases

- **Asociación:** `Cita` se asocia con exactamente un `Cliente`, un `Profesional` y un `Servicio`; un cliente, un profesional o un servicio pueden aparecer en muchas citas (multiplicidad `*` a `1`).
- **Agregación:** `Profesional` ofrece varios `Servicio` y un mismo servicio puede ser ofrecido por varios profesionales (`*` a `*`); los servicios existen aunque se quite al profesional. `GestorCitas` agrupa clientes, profesionales y servicios, que no dependen de él para existir como objetos.
- **Composición:** `GestorCitas` contiene las `Cita`; una cita se crea y se administra únicamente a través del gestor.
- **Herencia:** `Persona` → `Cliente`, `Profesional`; `Servicio` → `ServicioPresencial`, `ServicioVirtual`.
- **Dependencia:** `GestorCitas` lanza `HorarioNoDisponible` y `CitaNoModificable`; `Cita` usa los enums `EstadoCita`, y `Profesional` usa `Especialidad`.

## 8.6 Herencia o abstracción

```text
Persona (abstracta)
├── Cliente        (cuando un cliente es frecuente)
└── Profesional    (tipo de especialidad del profesional: especialidad, horario, servicios)

Servicio (abstracta)
├── ServicioPresencial   (recargoConsultorio)
└── ServicioVirtual      (plataforma)
```

Las subclases se diferencian en atributos y en comportamiento:
- `describirRol()` devuelve "Cliente"/"Cliente frecuente" en `Cliente` y "Profesional de <especialidad>" en `Profesional`.
- `calcularCosto()` suma un recargo de consultorio en `ServicioPresencial` y aplica un 10 % de descuento en `ServicioVirtual`. Además, `Cita.calcularCosto()` aplica otro 10 % si el cliente es frecuente.

## 8.7 Interface

```java
public interface Cancelable {
    void cancelar() throws CitaNoModificable;
}

public interface Reprogramable {
    void reprogramar(LocalDateTime nuevaFecha)
            throws CitaNoModificable, HorarioNoDisponible;
}
```

`Cita` implementa ambas. Solo una cita en estado `PROGRAMADA` puede cancelarse o reprogramarse.

## 8.8 Enumeración

```java
public enum EstadoCita { PROGRAMADA, ATENDIDA, CANCELADA, AUSENTE }

public enum Especialidad {
    MEDICINA_GENERAL, ODONTOLOGIA, PSICOLOGIA, NUTRICION, FISIOTERAPIA
}
```

`EstadoCita` modela el ciclo de vida de la cita y permite validar qué operaciones son válidas en cada momento. `Especialidad` evita usar texto libre para clasificar a los profesionales.

## 8.9 UML preliminar

Archivo: `docs/corte_1/uml.jpng`. 


## Código del Corte 1

El proyecto compila y contiene en `proyecto/src/main/java/cr/ac/proyecto/`:

- `model/`: `Persona`, `Cliente`, `Profesional`, `Servicio`, `ServicioPresencial`, `ServicioVirtual`, `Cita`, `EstadoCita`, `Especialidad`, `Cancelable`, `Reprogramable`.
- `exception/`: `HorarioNoDisponibleException`, `CitaNoModificableException`.


Todos los atributos son privados, con getters/setters que validan los datos cuando corresponden. 
