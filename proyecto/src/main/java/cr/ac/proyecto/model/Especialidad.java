/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.proyecto.model;

/**
 *
 * @author keisy
 */
public enum Especialidad {
    MEDICINA_GENERAL("Medicina general"),
    ODONTOLOGIA("Odontología"),
    PSICOLOGIA("Psicología"),
    NUTRICION("Nutrición"),
    FISIOTERAPIA("Fisioterapia");
 
    private final String descripcion;
 
    Especialidad(String descripcion) {
        this.descripcion = descripcion;
    }
 
    public String getDescripcion() {
        return descripcion;
    }
 
    @Override
    public String toString() {
        return descripcion;
    }
}
