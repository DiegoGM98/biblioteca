package Biblioteca;

class Profesor extends Usuario {
    public Profesor(String nombre) {
        super(nombre);
    }

    @Override
    public String getTipo() {
        return "Profesor";
    }
}
