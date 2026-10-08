# Caso 5. Gestión de citas

## Contexto

Una organización necesita controlar citas entre clientes y profesionales.
El sistema debe administrar personas, profesionales, servicios, citas y estados.

## Usuarios del sistema

Recepcionista o personal administrativo.

## Entidades sugeridas

| Clase | Idea |
|---|---|
| `Persona` | identificación, nombre, teléfono, correo |
| `Cliente`, `Profesional` | Especializaciones (el profesional tiene especialidad y horario) |
| `Servicio` | código, nombre, duración, precio |
| `Cita` | cliente, profesional, servicio, fecha y hora, estado |

## Pistas de diseño

- **Herencia:** `Persona → Cliente / Profesional`; opcionalmente `Servicio` (abstracta) `→ ServicioPresencial / ServicioVirtual`.
- **Polimorfismo:** `calcularCosto()` según el tipo de servicio o de cliente (por ejemplo, cliente frecuente).
- **Interface:** `Cancelable`, `Reprogramable` (`reprogramar(LocalDateTime nuevaFecha)`).
- **Enum:** `EstadoCita { PROGRAMADA, ATENDIDA, CANCELADA, AUSENTE }`, `Especialidad`.

## Reglas de negocio sugeridas

- Un profesional no puede tener dos citas que se traslapen.
- No se pueden programar citas en fechas pasadas.
- Solo se puede reprogramar o cancelar una cita `PROGRAMADA`.
- El profesional debe ofrecer el servicio solicitado.

## Excepciones sugeridas

`HorarioNoDisponibleException`, `CitaNoModificableException`.

## Pantalla adicional sugerida (Corte 3)

Agenda de un profesional por fecha, o cambio de estado de citas.
