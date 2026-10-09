/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.model;
import cr.ac.proyecto.exception.CitaNoModificable;
/**
 *
 * @author keisy
 */
public interface Cancelable {
 
    void cancelar() throws CitaNoModificable;
}
