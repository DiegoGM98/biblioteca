package Biblioteca;

class Notificacion {
    private Usuario usuario;
    private String mensaje;

    public Notificacion(Usuario usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public void enviar() {
        System.out.println("Notificación para " + usuario.getNombre() + ": " + mensaje);
    }
}
