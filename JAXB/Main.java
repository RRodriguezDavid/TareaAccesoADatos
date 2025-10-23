package EjercicioEntrega;

import jakarta.xml.bind.*;
import org.example.Libreria1;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static int decision = 1;
    public static  void main(String[] args) throws JAXBException {
        Scanner sc = new Scanner(System.in);
        CargarLibros();
        while (decision !=0) {
            System.out.println(Menu());
            decision = sc.nextInt();
            switch (decision) {
                case 1:
                    System.out.println("Leyendo el XML....");
                    System.out.println(MostrarLibros());
                    break;

                case 2:
                    System.out.println("Leyendo el XML....");
                    System.out.println(MostrarSoloTitulos());
                    break;

                case 3:
                    sc.nextLine(); // limpiar buffer
                    System.out.print("Introduce el nombre del autor: ");
                    String autor = sc.nextLine();
                    System.out.println(MostrarLibrosPorAutor(autor));
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);

                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        }
        System.out.println(Menu());


    }

    public static void CargarLibros() throws JAXBException {
        Libreria libreria = new Libreria();
        libreria.CargarLibros(); // Este método debe agregar todos los libros

        JAXBContext contexto = JAXBContext.newInstance(Libreria.class);
        Marshaller marshaller = contexto.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Guardar en archivo XML
        marshaller.marshal(libreria, new File("Libreria.xml"));
    }

    public static Libreria MostrarLibros() throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance(Libreria.class);
        Unmarshaller unmarshaller = contexto.createUnmarshaller();
        Libreria libreriaGetafe = (Libreria) unmarshaller.unmarshal(new File("Libreria.xml"));
        return libreriaGetafe;
    }
    public static String MostrarSoloTitulos() throws JAXBException {
        Libreria libreriaGetafe = MostrarLibros();
        System.out.println(libreriaGetafe.mostrarTitulos());
        return ("");
    }

    public static String MostrarLibrosPorAutor(String autor) throws JAXBException {
        Libreria libreriaGetafe = MostrarLibros(); // Obtener librería desde XML
        StringBuilder sb = new StringBuilder();

        for (Libro libro : libreriaGetafe.getLibros()) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                sb.append(" - ").append(libro).append("\n");
            }
        }

        if (sb.isEmpty()) {
            return "No se encontraron libros de " + autor;
        }
        return sb.toString();
    }


    public static String Menu(){
        System.out.println("""
    ------Menu LIBRERIA------
    0º Salir
    1º Obtener Lista Completa
    2º Obtener solo titulos
    3º Obtener libros de autor determinado
    """);
    return "";
    }

}
