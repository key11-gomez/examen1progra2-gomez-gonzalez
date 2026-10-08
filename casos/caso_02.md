# Caso 2. Gestión de actividades académicas

## Contexto

Una institución desea administrar talleres, charlas, cursos cortos y conferencias.
El sistema debe permitir registrar actividades, participantes, responsables e inscripciones.

## Usuarios del sistema

Personal de coordinación académica o de extensión.

## Entidades sugeridas

| Clase | Idea |
|---|---|
| `Persona` | identificación, nombre, correo |
| `Participante`, `Responsable` | Persona inscrita / persona a cargo de la actividad |
| `Actividad` | código, nombre, fecha, cupo, responsable, estado |
| `Taller`, `Charla`, `CursoCorto`, `Conferencia` | Especializaciones (duración en horas, materiales, sesiones, etc.) |
| `Inscripcion` | participante, actividad, fecha, estado |

## Pistas de diseño

- **Herencia:** `Actividad` (abstracta) con sus cuatro tipos; `Persona → Participante / Responsable`.
- **Polimorfismo:** `calcularHorasCertificables()` o `generarDescripcion()` según el tipo de actividad.
- **Interface:** `Cancelable` para actividades e inscripciones; `Certificable`.
- **Enum:** `EstadoActividad { PROGRAMADA, EN_CURSO, FINALIZADA, CANCELADA }`, `EstadoInscripcion`.

## Reglas de negocio sugeridas

- El cupo debe ser positivo y no puede excederse.
- Un participante no puede inscribirse dos veces en la misma actividad.
- No se puede inscribir en una actividad cancelada o finalizada.
- Toda actividad debe tener un responsable.

## Excepciones sugeridas

`CupoAgotadoException`, `InscripcionDuplicadaException`.

## Pantalla adicional sugerida (Corte 3)

Gestión de inscripciones de una actividad (inscribir / cancelar).
