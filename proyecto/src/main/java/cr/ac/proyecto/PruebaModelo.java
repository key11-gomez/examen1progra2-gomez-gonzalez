/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto;
import cr.ac.proyecto.exception.CitaNoModificable;
import cr.ac.proyecto.exception.HorarioNoDisponible;
import cr.ac.proyecto.model.Cancelable;
import cr.ac.proyecto.model.Cita;
import cr.ac.proyecto.model.Persona;
import cr.ac.proyecto.model.Servicio;
import cr.ac.proyecto.service.GestorCitas;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author keisy
 */
public class PruebaModelo {
    public static void main(String[] args) {
        GestorCitas gestor = GestorCitas.getInstancia();
        gestor.cargarDatosDePrueba();
 
        System.out.println("DescribirRol() ==");
        List<Persona> personas = new ArrayList<>();
        personas.addAll(gestor.listarClientes());
        personas.addAll(gestor.listarProfesionales());
        for (Persona p : personas) {
            System.out.println(p + " -> " + p.describirRol());
        }
 
        System.out.println("CalcularCosto() ==");
        for (Servicio s : gestor.listarServicios()) {
            System.out.println(s + " -> " + s.calcularCosto());
        }
 
        LocalDateTime manana10 = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0)
                .withSecond(0).withNano(0);
        try {
            System.out.println("\n== Programar citas ==");
            Cita c1 = gestor.programarCita("1-1111-1111", "3-3333-3333", "S-01", manana10);
            System.out.println(c1 + " | costo: " + c1.calcularCosto() + " (cliente frecuente)");
            Cita c2 = gestor.programarCita("2-2222-2222", "3-3333-3333", "S-02", manana10.plusHours(1));
            System.out.println(c2 + " | costo: " + c2.calcularCosto());
 
            System.out.println("Traslape ==");
            try {
                gestor.programarCita("2-2222-2222", "3-3333-3333", "S-01", manana10.plusMinutes(15));
            } catch (HorarioNoDisponible e) {
                System.out.println("Excepción controlada: " + e.getMessage());
            }
 
            System.out.println("Fecha pasada ==");
            try {
                gestor.programarCita("2-2222-2222", "3-3333-3333", "S-01", LocalDateTime.now().minusDays(1));
            } catch (HorarioNoDisponible e) {
                System.out.println("Excepción controlada: " + e.getMessage());
            }
 
            System.out.println("\n== Interface Cancelable ==");
            Cancelable cancelable = c1;
            cancelable.cancelar();
            System.out.println(c1);
 
            System.out.println("Cancelar una cita ya CANCELADA ==");
            try {
                cancelable.cancelar();
            } catch (CitaNoModificable e) {
                System.out.println("Excepción controlada: " + e.getMessage());
            }
 
            System.out.println("\n== Reprogramar ==");
            gestor.reprogramarCita(c2.getId(), manana10.plusHours(3));
            System.out.println(c2);
        } catch (HorarioNoDisponible | CitaNoModificable e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
