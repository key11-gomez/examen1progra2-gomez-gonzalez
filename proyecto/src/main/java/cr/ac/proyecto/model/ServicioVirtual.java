/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.model;

/**
 *
 * @author keisy
 */
public class ServicioVirtual extends Servicio {
 
    private static final double DESCUENTO_VIRTUAL = 0.10;
 
    private String plataforma;
 
    public ServicioVirtual(String codigo, String nombre, int duracionMinutos, double precio,
            String plataforma) {
        super(codigo, nombre, duracionMinutos, precio);
        setPlataforma(plataforma);
    }
 
    public String getPlataforma() {
        return plataforma;
    }
 
    public void setPlataforma(String plataforma) {
        if (plataforma == null || plataforma.isBlank()) {
            throw new IllegalArgumentException("La plataforma es obligatoria.");
        }
        this.plataforma = plataforma.trim();
    }
 
    @Override
    public double calcularCosto() {
        return getPrecio() * (1 - DESCUENTO_VIRTUAL);
    }
 
    @Override
    public String getModalidad() {
        return "Virtual";
    }
}
