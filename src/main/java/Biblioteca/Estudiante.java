package Biblioteca;

// Clase Estudiante que extiende Usuario
class Estudiante extends Usuario {
    public Estudiante(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "Estudiante";
    }
}

