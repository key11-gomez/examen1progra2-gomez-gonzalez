/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.model;
import cr.ac.proyecto.exception.CitaNoModificable;
import cr.ac.proyecto.exception.HorarioNoDisponible;
import java.time.LocalDateTime;
 
/**
 *
 * @author keisy
 */
public class Cita implements Cancelable, Reprogramable {
 
    private static final double DESCUENTO_FRECUENTE = 0.10;
 
    private final String id;
    private final Cliente cliente;
    private final Profesional profesional;
    private final Servicio servicio;
    private LocalDateTime fechaHora;
    private EstadoCita estado;
 
    public Cita(String id, Cliente cliente, Profesional profesional, Servicio servicio,
            LocalDateTime fechaHora) {
        if (id == null || id.isBlank() || cliente == null || profesional == null
                || servicio == null || fechaHora == null) {
            throw new IllegalArgumentException("Todos los datos de la cita son obligatorios.");
        }
        this.id = id;
        this.cliente = cliente;
        this.profesional = profesional;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.estado = EstadoCita.PROGRAMADA;
    }
 
    @Override
    public void cancelar() throws CitaNoModificable {
        exigirProgramada("cancelar");
        estado = EstadoCita.CANCELADA;
    }
 
    @Override
    public void reprogramar(LocalDateTime nuevaFecha)
            throws CitaNoModificable, HorarioNoDisponible {
        exigirProgramada("reprogramar");
        if (nuevaFecha == null || nuevaFecha.isBefore(LocalDateTime.now())) {
            throw new HorarioNoDisponible("La nueva fecha no puede estar en el pasado.");
        }
        this.fechaHora = nuevaFecha;
    }
 
    public void marcarAtendida() throws CitaNoModificable {
        exigirProgramada("marcar como atendida");
        estado = EstadoCita.ATENDIDA;
    }
 
    public void marcarAusente() throws CitaNoModificable {
        exigirProgramada("marcar como ausente");
        estado = EstadoCita.AUSENTE;
    }
 
    private void exigirProgramada(String accion) throws CitaNoModificable {
        if (estado != EstadoCita.PROGRAMADA) {
            throw new CitaNoModificable(
                    "No se puede " + accion + " una cita en estado " + estado + ".");
        }
    }
 
    /** Costo final: el del servicio, con descuento si el cliente es frecuente. */
    public double calcularCosto() {
        double costo = servicio.calcularCosto();
        return cliente.isFrecuente() ? costo * (1 - DESCUENTO_FRECUENTE) : costo;
    }
 
    public LocalDateTime getFechaFin() {
        return fechaHora.plusMinutes(servicio.getDuracionMinutos());
    }
 
    public boolean traslapaCon(LocalDateTime inicio, LocalDateTime fin) {
        return fechaHora.isBefore(fin) && inicio.isBefore(getFechaFin());
    }
 
    public String getId() {
        return id;
    }
 
    public Cliente getCliente() {
        return cliente;
    }
 
    public Profesional getProfesional() {
        return profesional;
    }
 
    public Servicio getServicio() {
        return servicio;
    }
 
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
 
    public EstadoCita getEstado() {
        return estado;
    }
 
    @Override
    public String toString() {
        return id + " | " + cliente.getNombre() + " con " + profesional.getNombre()
                + " | " + servicio.getNombre() + " | " + fechaHora + " | " + estado;
    }
}