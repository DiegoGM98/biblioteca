package Biblioteca;

// Clase abstracta Usuario
abstract class Usuario {
    protected String nombre;
    protected String tipo;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract String getTipo();
}
