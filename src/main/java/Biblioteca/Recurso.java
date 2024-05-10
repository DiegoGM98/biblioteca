package Biblioteca;

abstract class Recurso {
    protected String titulo;
    protected String[] autores;
    protected int anioPublicacion;
    protected String clasificacion;

    public Recurso(String titulo, String[] autores, int anioPublicacion, String clasificacion) {
        this.titulo = titulo;
        this.autores = autores;
        this.anioPublicacion = anioPublicacion;
        this.clasificacion = clasificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String[] getAutores() {
        return autores;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getClasificacion() {
        return clasificacion;
    }
}

