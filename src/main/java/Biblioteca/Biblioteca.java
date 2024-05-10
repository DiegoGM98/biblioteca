package Biblioteca;
import java.util.ArrayList;
import java.util.Date;
class Biblioteca {
    private ArrayList<Recurso> recursos;
    private ArrayList<Prestamo> prestamos;

    public Biblioteca() {
        recursos = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public void agregarRecurso(Recurso recurso) {
        recursos.add(recurso);
    }

    public ArrayList<Recurso> buscarRecursos(String titulo, String autor, int anioPublicacion, String clasificacion) {
        ArrayList<Recurso> resultados = new ArrayList<>();
        for (Recurso recurso : recursos) {
            if ((titulo == null || recurso.getTitulo().toLowerCase().contains(titulo.toLowerCase())) &&
                    (autor == null || contieneAutor(recurso, autor)) &&
                    (anioPublicacion == 0 || recurso.getAnioPublicacion() == anioPublicacion) &&
                    (clasificacion == null || recurso.getClasificacion().equalsIgnoreCase(clasificacion))) {
                resultados.add(recurso);
            }
        }
        return resultados;
    }

    private boolean contieneAutor(Recurso recurso, String autor) {
        for (String a : recurso.getAutores()) {
            if (a.toLowerCase().contains(autor.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void prestarRecurso(Recurso recurso, Usuario usuario, Date fechaPrestamo) {
        prestamos.add(new Prestamo(recurso, usuario, fechaPrestamo));
    }

    public void devolverRecurso(Recurso recurso, Usuario usuario, Date fechaDevolucion) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getRecurso() == recurso && prestamo.getUsuario() == usuario && prestamo.getFechaDevolucion() == null) {
                prestamo.setFechaDevolucion(fechaDevolucion);
                break;
            }
        }
    }

    // Función para extender el préstamo de un recurso
    public void extenderPrestamo(Recurso recurso, Usuario usuario) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getRecurso() == recurso && prestamo.getUsuario() == usuario && prestamo.getFechaDevolucion() == null) {
                // Simplemente actualizamos la fecha de devolución
                Date fechaActual = new Date();
                prestamo.setFechaDevolucion(new Date(fechaActual.getTime() + 7 * 24 * 60 * 60 * 1000)); // Extender por una semana (7 días)
                break;
            }
        }
    }
}
