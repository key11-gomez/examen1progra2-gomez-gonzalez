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
}
