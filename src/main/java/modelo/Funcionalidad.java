package modelo;

public class Funcionalidad {

    private String nombre;
    private String descripcion;

    public Funcionalidad() {}
    
    public Funcionalidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean acceso() {
        return false;
    }

}
