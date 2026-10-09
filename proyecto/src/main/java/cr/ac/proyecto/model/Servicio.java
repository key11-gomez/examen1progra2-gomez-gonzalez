/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.model;

/**
 *
 * @author keisy
 */
public abstract class Servicio {
 
    private final String codigo;
    private String nombre;
    private int duracionMinutos;
    private double precio;
 
    protected Servicio(String codigo, String nombre, int duracionMinutos, double precio) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código del servicio es obligatorio.");
        }
        this.codigo = codigo.trim();
        setNombre(nombre);
        setDuracionMinutos(duracionMinutos);
        setPrecio(precio);
    }
 
    /** Costo base del servicio; cambia según la modalidad (polimorfismo). */
    public abstract double calcularCosto();
 
    public abstract String getModalidad();
 
    public String getCodigo() {
        return codigo;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del servicio es obligatorio.");
        }
        this.nombre = nombre.trim();
    }
 
    public int getDuracionMinutos() {
        return duracionMinutos;
    }
 
    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a cero.");
        }
        this.duracionMinutos = duracionMinutos;
    }
 
    public double getPrecio() {
        return precio;
    }
 
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }
 
    @Override
    public String toString() {
        return nombre + " [" + getModalidad() + "]";
    }
}
 