/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.model;

/**
 *
 * @author keisy
 */
public class Cliente extends Persona {
 
    private boolean frecuente;
 
    public Cliente(String identificacion, String nombre, String telefono, String correo,
            boolean frecuente) {
        super(identificacion, nombre, telefono, correo);
        this.frecuente = frecuente;
    }
 
    public boolean isFrecuente() {
        return frecuente;
    }
 
    public void setFrecuente(boolean frecuente) {
        this.frecuente = frecuente;
    }
 
    @Override
    public String describirRol() {
        return frecuente ? "Cliente frecuente" : "Cliente";
    }
}
