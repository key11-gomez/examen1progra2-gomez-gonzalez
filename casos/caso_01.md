# Caso 1. Gestión de préstamos de equipo

## Contexto

Una institución necesita controlar préstamos de computadoras, proyectores, cámaras y otros equipos.
El sistema debe permitir registrar personas, equipos, préstamos, devoluciones y estados.

## Usuarios del sistema

Encargado(a) del área de préstamos.

## Entidades sugeridas

| Clase | Idea |
|---|---|
| `Persona` | Datos comunes: identificación, nombre, correo |
| `Estudiante`, `Funcionario` | Tipos de solicitante con reglas distintas (por ejemplo, días máximos de préstamo) |
| `Equipo` | Código, marca, modelo, estado |
| `Computadora`, `Proyector`, `Camara` | Especializaciones de `Equipo` con atributos propios |
| `Prestamo` | Solicitante, equipo, fecha de préstamo, fecha esperada y fecha real de devolución, estado |

## Pistas de diseño

- **Herencia:** `Persona → Estudiante / Funcionario`; `Equipo` (abstracta) `→ Computadora / Proyector / Camara`.
- **Polimorfismo:** `calcularDiasMaximos()` distinto según el tipo de solicitante o de equipo.
- **Interface:** `Prestable` (`prestar()`, `devolver()`) o `Cancelable`.
- **Enum:** `EstadoEquipo { DISPONIBLE, PRESTADO, EN_REPARACION }`, `EstadoPrestamo { ACTIVO, DEVUELTO, VENCIDO }`.

## Reglas de negocio sugeridas

- No se puede prestar un equipo que no esté `DISPONIBLE`.
- Una persona no puede tener más de N préstamos activos.
- La fecha esperada de devolución debe ser posterior a la fecha de préstamo.
- No se permiten códigos de equipo ni identificaciones duplicadas.

## Excepciones sugeridas

`EquipoNoDisponibleException`, `LimitePrestamosException`.

## Pantalla adicional sugerida (Corte 3)

Registro de devoluciones o consulta de préstamos vencidos.
