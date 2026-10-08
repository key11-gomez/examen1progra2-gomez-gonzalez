# Caso 6. Gestión de biblioteca

## Contexto

Se requiere administrar materiales bibliográficos, usuarios, préstamos y devoluciones.
El sistema debe contemplar diferentes tipos de materiales y usuarios.

## Usuarios del sistema

Personal de la biblioteca.

## Entidades sugeridas

| Clase | Idea |
|---|---|
| `Material` | código, título, año, estado |
| `Libro`, `Revista`, `Tesis`, `MaterialAudiovisual` | Especializaciones (ISBN, número de edición, carrera, duración, etc.) |
| `Usuario` | identificación, nombre |
| `UsuarioEstudiante`, `UsuarioDocente` | Tipos de usuario con límites distintos |
| `Prestamo` | usuario, material, fecha de préstamo, fecha de devolución, estado |

## Pistas de diseño

- **Herencia:** `Material` (abstracta) con sus tipos; `Usuario` (abstracta) `→ UsuarioEstudiante / UsuarioDocente`.
- **Polimorfismo:** `getDiasPrestamo()` según el tipo de material o usuario; `calcularMulta(int diasAtraso)`.
- **Interface:** `Prestable`, `Reservable`.
- **Enum:** `EstadoMaterial { DISPONIBLE, PRESTADO, RESERVADO, DANADO }`, `EstadoPrestamo`.

## Reglas de negocio sugeridas

- Algunos materiales (por ejemplo, tesis) solo se consultan en sala y no se prestan a domicilio.
- Cada tipo de usuario tiene un máximo de préstamos simultáneos.
- No se presta un material que no esté `DISPONIBLE`.
- Un usuario con préstamos vencidos no puede solicitar nuevos préstamos.

## Excepciones sugeridas

`MaterialNoDisponibleException`, `UsuarioMorosoException`.

## Pantalla adicional sugerida (Corte 3)

Registro de devoluciones con cálculo de multa, o historial de préstamos de un usuario.
