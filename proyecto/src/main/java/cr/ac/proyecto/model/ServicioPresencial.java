/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.model;

/**
 *
 * @author keisy
 */
public class ServicioPresencial extends Servicio {
 
    private double recargoConsultorio;
 
    public ServicioPresencial(String codigo, String nombre, int duracionMinutos, double precio,
            double recargoConsultorio) {
        super(codigo, nombre, duracionMinutos, precio);
        setRecargoConsultorio(recargoConsultorio);
    }
 
    public double getRecargoConsultorio() {
        return recargoConsultorio;
    }
 
    public void setRecargoConsultorio(double recargoConsultorio) {
        if (recargoConsultorio < 0) {
            throw new IllegalArgumentException("El recargo no puede ser negativo.");
        }
        this.recargoConsultorio = recargoConsultorio;
    }
 
    @Override
    public double calcularCosto() {
        return getPrecio() + recargoConsultorio;
    }
 
    @Override
    public String getModalidad() {
        return "Presencial";
    }
}
