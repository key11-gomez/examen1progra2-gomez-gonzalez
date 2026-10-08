# Caso 3. Gestión de reservas de espacios

## Contexto

Se desea administrar reservas de aulas, laboratorios, salas de reuniones y auditorios.
El sistema debe controlar espacios, solicitantes, reservas, horarios y estados.

## Usuarios del sistema

Personal administrativo encargado de los espacios.

## Entidades sugeridas

| Clase | Idea |
|---|---|
| `Espacio` | código, nombre, capacidad, ubicación |
| `Aula`, `Laboratorio`, `SalaReuniones`, `Auditorio` | Especializaciones (cantidad de equipos, equipo audiovisual, etc.) |
| `Solicitante` | identificación, nombre, tipo |
| `Reserva` | espacio, solicitante, fecha, hora de inicio, hora de fin, motivo, estado |

## Pistas de diseño

- **Herencia:** `Espacio` (abstracta) con sus tipos; opcionalmente `Persona → Docente / Estudiante / Administrativo`.
- **Polimorfismo:** `requiereAprobacion()` o `getTiempoMaximoReserva()` según el tipo de espacio.
- **Interface:** `Cancelable`, `Aprobable` (`aprobar()`, `rechazar()`).
- **Enum:** `EstadoReserva { PENDIENTE, CONFIRMADA, CANCELADA }`, `TipoSolicitante`.

## Reglas de negocio sugeridas

- Dos reservas confirmadas del mismo espacio no pueden traslaparse en horario.
- La hora de fin debe ser posterior a la hora de inicio.
- La cantidad de asistentes no puede superar la capacidad del espacio.
- No se puede confirmar una reserva cancelada.

## Excepciones sugeridas

`ChoqueHorarioException`, `CapacidadExcedidaException`.

## Pantalla adicional sugerida (Corte 3)

Disponibilidad de un espacio por fecha, o aprobación de reservas pendientes.
