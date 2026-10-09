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
public interface Reprogramable {
 
    void reprogramar(LocalDateTime nuevaFecha)
            throws CitaNoModificable, HorarioNoDisponible;
}