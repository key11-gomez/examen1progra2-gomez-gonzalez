/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.model;

/**
 *
 * @author keisy
 */
public abstract class Persona {
    private final String identificacion;
    private String nombre;
    private String telefono;
    private String correo;
 
    protected Persona(String identificacion, String nombre, String telefono, String correo) {
        this.identificacion = requerido(identificacion, "La identificación");
        this.nombre = requerido(nombre, "El nombre");
        this.telefono = requerido(telefono, "El teléfono");
        this.correo = validarCorreo(correo);
    }
 
    public abstract String describirRol();
 
    protected static String requerido(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " es obligatorio.");
        }
        return valor.trim();
    }
 
    private static String validarCorreo(String correo) {
        String c = requerido(correo, "El correo");
        if (!c.contains("@") || c.startsWith("@") || c.endsWith("@")) {
            throw new IllegalArgumentException("El correo no tiene un formato válido.");
        }
        return c;
    }
 
    public String getIdentificacion() {
        return identificacion;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = requerido(nombre, "El nombre");
    }
 
    public String getTelefono() {
        return telefono;
    }
 
    public void setTelefono(String telefono) {
        this.telefono = requerido(telefono, "El teléfono");
    }
 
    public String getCorreo() {
        return correo;
    }
 
    public void setCorreo(String correo) {
        this.correo = validarCorreo(correo);
    }
 
    @Override
    public String toString() {
        return nombre + " (" + identificacion + ")";
    }
}
