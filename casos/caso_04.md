# Caso 4. Gestión de torneos

## Contexto

Una organización necesita administrar torneos, equipos, participantes, encuentros y resultados.

## Usuarios del sistema

Personal organizador del torneo.

## Entidades sugeridas

| Clase | Idea |
|---|---|
| `Torneo` | nombre, disciplina, fechas, lista de equipos y encuentros, estado |
| `Equipo` | nombre, lista de jugadores, entrenador |
| `Persona` | identificación, nombre |
| `Jugador`, `Entrenador`, `Arbitro` | Especializaciones |
| `Encuentro` | equipo local, equipo visitante, fecha, árbitro, resultado, estado |

## Pistas de diseño

- **Herencia:** `Persona → Jugador / Entrenador / Arbitro`; opcionalmente `Torneo` (abstracta) `→ TorneoLiga / TorneoEliminacion`.
- **Polimorfismo:** `calcularPuntos()` o `generarEncuentros()` según el tipo de torneo.
- **Composición:** un `Torneo` contiene sus `Encuentro`.
- **Interface:** `Puntuable`, `Cancelable`.
- **Enum:** `EstadoEncuentro { PROGRAMADO, JUGADO, SUSPENDIDO }`, `Disciplina`.

## Reglas de negocio sugeridas

- Un equipo no puede enfrentarse a sí mismo.
- Un jugador no puede pertenecer a dos equipos del mismo torneo.
- No se registra resultado en un encuentro suspendido; los marcadores no pueden ser negativos.
- Un equipo debe tener un mínimo de jugadores para inscribirse.

## Excepciones sugeridas

`EncuentroInvalidoException`, `JugadorDuplicadoException`.

## Pantalla adicional sugerida (Corte 3)

Registro de resultados y tabla de posiciones.
