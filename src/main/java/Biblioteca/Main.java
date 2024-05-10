package Biblioteca;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear una biblioteca
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        // Menú principal
        while (true) {
            System.out.println("\n*** Biblioteca Digital ***");
            System.out.println("1. Agregar recurso");
            System.out.println("2. Buscar recursos");
            System.out.println("3. Realizar préstamo");
            System.out.println("4. Realizar devolución");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    agregarRecurso(biblioteca, scanner);
                    break;
                case 2:
                    buscarRecursos(biblioteca, scanner);
                    break;
                case 3:
                    realizarPrestamo(biblioteca, scanner);
                    break;
                case 4:
                    realizarDevolucion(biblioteca, scanner);
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Función para agregar un recurso
    private static void agregarRecurso(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n*** Agregar Recurso ***");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor(es): ");
        String autoresString = scanner.nextLine();
        String[] autores = autoresString.split(",");
        System.out.print("Año de Publicación: ");
        int anioPublicacion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Clasificación: ");
        String clasificacion = scanner.nextLine();

        // Determinar el tipo de recurso
        System.out.print("Tipo de Recurso (1: Libro, 2: Revista, 3: Tesis): ");
        int tipoRecurso = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        Recurso recurso;
        switch (tipoRecurso) {
            case 1:
                recurso = new Libro(titulo, autores, anioPublicacion, clasificacion);
                break;
            case 2:
                recurso = new Revista(titulo, autores, anioPublicacion, clasificacion);
                break;
            case 3:
                recurso = new Tesis(titulo, autores, anioPublicacion, clasificacion);
                break;
            default:
                System.out.println("Tipo de recurso no válido.");
                return;
        }

        // Agregar el recurso a la biblioteca
        biblioteca.agregarRecurso(recurso);
        System.out.println("Recurso agregado correctamente.");
    }

    // Función para buscar recursos
    private static void buscarRecursos(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n*** Buscar Recursos ***");
        System.out.print("Título (Deje en blanco si no desea filtrar por título): ");
        String titulo = scanner.nextLine();
        System.out.print("Autor (Deje en blanco si no desea filtrar por autor): ");
        String autor = scanner.nextLine();
        System.out.print("Año de Publicación (0 si no desea filtrar por año): ");
        int anioPublicacion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Clasificación (Deje en blanco si no desea filtrar por clasificación): ");
        String clasificacion = scanner.nextLine();

        // Realizar la búsqueda
        ArrayList<Recurso> resultados = biblioteca.buscarRecursos(titulo, autor, anioPublicacion, clasificacion);

        // Mostrar los resultados
        System.out.println("\nResultados de la búsqueda:");
        for (Recurso recurso : resultados) {
            System.out.println("Título: " + recurso.getTitulo());
            System.out.println("Autor(es): " + String.join(", ", recurso.getAutores()));
            System.out.println("Año de Publicación: " + recurso.getAnioPublicacion());
            System.out.println("Clasificación: " + recurso.getClasificacion());
            System.out.println("---------------------------------");
        }
    }

    // Función para realizar un préstamo
    private static void realizarPrestamo(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n*** Realizar Préstamo ***");
        System.out.print("Nombre del Usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Tipo de Usuario (1: Estudiante, 2: Profesor): ");
        int tipoUsuario = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        Usuario usuario;
        switch (tipoUsuario) {
            case 1:
                usuario = new Estudiante(nombreUsuario);
                break;
            case 2:
                usuario = new Profesor(nombreUsuario);
                break;
            default:
                System.out.println("Tipo de usuario no válido.");
                return;
        }
        System.out.print("Título del Recurso: ");
        String tituloRecurso = scanner.nextLine();

        // Buscar el recurso
        ArrayList<Recurso> resultados = biblioteca.buscarRecursos(tituloRecurso, null, 0, null);
        if (resultados.isEmpty()) {
            System.out.println("El recurso no fue encontrado.");
            return;
        }

        // Mostrar los resultados de la búsqueda
        System.out.println("\nResultados de la búsqueda:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i + 1) + ". " + resultados.get(i).getTitulo());
        }
        System.out.print("Seleccione el número del recurso que desea prestar: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        if (seleccion < 1 || seleccion > resultados.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        // Realizar el préstamo
        Recurso recursoSeleccionado = resultados.get(seleccion - 1);
        biblioteca.prestarRecurso(recursoSeleccionado, usuario, new Date());
        System.out.println("Préstamo realizado con éxito.");
    }

    // Función para realizar una devolución
    private static void realizarDevolucion(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n*** Realizar Devolución ***");
        System.out.print("Nombre del Usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Tipo de Usuario (1: Estudiante, 2: Profesor): ");
        int tipoUsuario = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        Usuario usuario;
        switch (tipoUsuario) {
            case 1:
                usuario = new Estudiante(nombreUsuario);
                break;
            case 2:
                usuario = new Profesor(nombreUsuario);
                break;
            default:
                System.out.println("Tipo de usuario no válido.");
                return;
        }
        System.out.print("Título del Recurso: ");
        String tituloRecurso = scanner.nextLine();

        // Buscar el recurso
        ArrayList<Recurso> resultados = biblioteca.buscarRecursos(tituloRecurso, null, 0, null);
        if (resultados.isEmpty()) {
            System.out.println("El recurso no fue encontrado.");
            return;
        }

        // Mostrar los resultados de la búsqueda
        System.out.println("\nResultados de la búsqueda:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i + 1) + ". " + resultados.get(i).getTitulo());
        }
        System.out.print("Seleccione el número del recurso que desea devolver: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        if (seleccion < 1 || seleccion > resultados.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        // Realizar la devolución
        Recurso recursoSeleccionado = resultados.get(seleccion - 1);
        biblioteca.devolverRecurso(recursoSeleccionado, usuario, new Date());
        System.out.println("Devolución realizada con éxito.");
    }
}
