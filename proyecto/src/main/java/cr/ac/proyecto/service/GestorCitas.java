/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.service;
import cr.ac.proyecto.exception.CitaNoModificable;
import cr.ac.proyecto.exception.HorarioNoDisponible;
import cr.ac.proyecto.model.Cita;
import cr.ac.proyecto.model.Cliente;
import cr.ac.proyecto.model.Especialidad;
import cr.ac.proyecto.model.EstadoCita;
import cr.ac.proyecto.model.Profesional;
import cr.ac.proyecto.model.Servicio;
import cr.ac.proyecto.model.ServicioPresencial;
import cr.ac.proyecto.model.ServicioVirtual;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author keisy
 */
public class GestorCitas {
    private static final GestorCitas INSTANCIA = new GestorCitas();
    private final Map<String, Cliente> clientes = new LinkedHashMap<>();
    private final Map<String, Profesional> profesionales = new LinkedHashMap<>();
    private final Map<String, Servicio> servicios = new LinkedHashMap<>();
    private final List<Cita> citas = new ArrayList<>();
    private int contadorCitas = 0;
    private GestorCitas() {
    }
 
    public static GestorCitas getInstancia() {
        return INSTANCIA;
    }
 
    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio.");
        }
        if (clientes.containsKey(cliente.getIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un cliente con esa identificación.");
        }
        clientes.put(cliente.getIdentificacion(), cliente);
    }
 
    public void registrarProfesional(Profesional profesional) {
        if (profesional == null) {
            throw new IllegalArgumentException("El profesional es obligatorio.");
        }
        if (profesionales.containsKey(profesional.getIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un profesional con esa identificación.");
        }
        profesionales.put(profesional.getIdentificacion(), profesional);
    }
 
    public void registrarServicio(Servicio servicio) {
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio es obligatorio.");
        }
        if (servicios.containsKey(servicio.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un servicio con ese código.");
        }
        servicios.put(servicio.getCodigo(), servicio);
    }
 
    public void asignarServicio(String idProfesional, String codigoServicio) {
        buscarProfesional(idProfesional).agregarServicio(buscarServicio(codigoServicio));
    }
    public Cita programarCita(String idCliente, String idProfesional, String codigoServicio,
            LocalDateTime fechaHora) throws HorarioNoDisponible {
        if (fechaHora == null) {
            throw new IllegalArgumentException("Debe indicar la fecha y la hora.");
        }
        Cliente cliente = buscarCliente(idCliente);
        Profesional profesional = buscarProfesional(idProfesional);
        Servicio servicio = buscarServicio(codigoServicio);
 
        if (!profesional.ofreceServicio(servicio)) {
            throw new IllegalArgumentException(
                    profesional.getNombre() + " no ofrece el servicio " + servicio.getNombre() + ".");
        }
        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new HorarioNoDisponible("No se pueden programar citas en fechas pasadas.");
        }
        validarDisponibilidad(profesional, fechaHora, servicio, null);
 
        Cita cita = new Cita(String.format("C-%03d", ++contadorCitas),
                cliente, profesional, servicio, fechaHora);
        citas.add(cita);
        return cita;
    }
 
    public void cancelarCita(String idCita) throws CitaNoModificable {
        buscarCita(idCita).cancelar();
    }
 
    public void reprogramarCita(String idCita, LocalDateTime nuevaFecha)
            throws CitaNoModificable, HorarioNoDisponible {
        Cita cita = buscarCita(idCita);
        if (cita.getEstado() != EstadoCita.PROGRAMADA) {
            throw new CitaNoModificable(
                    "No se puede reprogramar una cita en estado " + cita.getEstado() + ".");
        }
        if (nuevaFecha == null || nuevaFecha.isBefore(LocalDateTime.now())) {
            throw new HorarioNoDisponible("La nueva fecha no puede estar en el pasado.");
        }
        validarDisponibilidad(cita.getProfesional(), nuevaFecha, cita.getServicio(), cita);
        cita.reprogramar(nuevaFecha);
    }
 
    public void marcarAtendida(String idCita) throws CitaNoModificable {
        buscarCita(idCita).marcarAtendida();
    }
 
    public void marcarAusente(String idCita) throws CitaNoModificable {
        buscarCita(idCita).marcarAusente();
    }
    private void validarDisponibilidad(Profesional profesional, LocalDateTime inicio,
            Servicio servicio, Cita ignorar) throws HorarioNoDisponible {
        LocalDateTime fin = inicio.plusMinutes(servicio.getDuracionMinutos());
        if (!profesional.atiendeEn(inicio, fin)) {
            throw new HorarioNoDisponible("La cita queda fuera del horario de "
                    + profesional.getNombre() + " (" + profesional.getHoraInicio()
                    + " a " + profesional.getHoraFin() + ").");
        }
        for (Cita otra : citas) {
            if (otra != ignorar
                    && otra.getProfesional().equals(profesional)
                    && otra.getEstado() == EstadoCita.PROGRAMADA
                    && otra.traslapaCon(inicio, fin)) {
                throw new HorarioNoDisponible(
                        profesional.getNombre() + " ya tiene una cita en ese horario (" + otra.getId() + ").");
            }
        }
    }
}
