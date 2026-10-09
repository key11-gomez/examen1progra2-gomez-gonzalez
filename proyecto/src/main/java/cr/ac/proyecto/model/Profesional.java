/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.model;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author keisy
 */
public class Profesional extends Persona {
 
    private Especialidad especialidad;
    private final LocalTime horaInicio;
    private final LocalTime horaFin;
    private final List<Servicio> servicios = new ArrayList<>();
 
    public Profesional(String identificacion, String nombre, String telefono, String correo,
            Especialidad especialidad, LocalTime horaInicio, LocalTime horaFin) {
        super(identificacion, nombre, telefono, correo);
        if (especialidad == null) {
            throw new IllegalArgumentException("La especialidad es obligatoria.");
        }
        if (horaInicio == null || horaFin == null || !horaInicio.isBefore(horaFin)) {
            throw new IllegalArgumentException(
                    "Horario inválido: la hora de inicio debe ser anterior a la de fin.");
        }
        this.especialidad = especialidad;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
 
    public void agregarServicio(Servicio servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio es obligatorio.");
        }
        if (!servicios.contains(servicio)) {
            servicios.add(servicio);
        }
    }
 
    public boolean ofreceServicio(Servicio servicio) {
        return servicios.contains(servicio);
    }
 
    /** Indica si el intervalo cae completo dentro del horario laboral del mismo día. */
    public boolean atiendeEn(LocalDateTime inicio, LocalDateTime fin) {
        return inicio.toLocalDate().equals(fin.toLocalDate())
                && !inicio.toLocalTime().isBefore(horaInicio)
                && !fin.toLocalTime().isAfter(horaFin);
    }
 
    public Especialidad getEspecialidad() {
        return especialidad;
    }
 
    public void setEspecialidad(Especialidad especialidad) {
        if (especialidad == null) {
            throw new IllegalArgumentException("La especialidad es obligatoria.");
        }
        this.especialidad = especialidad;
    }
 
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
 
    public LocalTime getHoraFin() {
        return horaFin;
    }
 
    public List<Servicio> getServicios() {
        return Collections.unmodifiableList(servicios);
    }
 
    @Override
    public String describirRol() {
        return "Profesional de " + especialidad.getDescripcion();
    }
}
