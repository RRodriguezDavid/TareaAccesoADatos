package EjercicioEntrega;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.Libro1;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Libreria")
public class Libreria {

    private String nombre;
    private String lugar;
    private List<Libro> libros = new ArrayList<>();

    public Libreria() {
    }

    public Libreria(String nombre, String lugar) {
        this.nombre = nombre;
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @XmlElementWrapper(name = "libros")
    @XmlElement(name = "Libro")

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro1> libro1s) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Librería: ").append(this.nombre).append(" (").append(this.lugar).append(")\n");
        sb.append("Libros:\n");
        for (Libro libro : this.libros) {
            sb.append(" - ").append(libro).append("\n");
        }
        return sb.toString();
    }


    public String mostrarTitulos() {
        StringBuilder sb = new StringBuilder();
        sb.append("Títulos de libros:\n");
        for (Libro libro : this.libros) {
            sb.append(" - ").append(libro.getTitulo()).append("\n");
        }
        return sb.toString();
    }

    public void CargarLibros() {
        libros.add(new Libro("Miguel de Cervantes","Quijote", "El barco de Vapor" , "1234", 25.50, 1605));
        libros.add(new Libro("Fernando de Rojas", "La Celestina", "Anaya", "1456", 18.90, 1499));
        libros.add(new Libro("Gabriel García Márquez", "Cien Años de Soledad", "Sudamericana", "2345", 22.75, 1967));
        libros.add(new Libro("J.K. Rowling", "Harry Potter y la Piedra Filosofal", "Bloomsbury", "3456", 29.90, 1997));
        libros.add(new Libro("George Orwell", "1984", "Secker & Warburg", "4567", 19.50, 1949));
        libros.add(new Libro("Jane Austen", "Orgullo y Prejuicio", "Penguin", "5678", 21.00, 1813));
        libros.add(new Libro("Homer", "La Iliada", "Alianza Editorial", "6789", 17.80, -750));
        libros.add(new Libro("Fiódor Dostoyevski", "Crimen y Castigo", "Editorial Planeta", "7890", 20.50, 1866));
        libros.add(new Libro("Miguel de Unamuno", "Niebla", "Cátedra", "8901", 16.90, 1914));
        libros.add(new Libro("J.R.R. Tolkien", "El Señor de los Anillos", "Minotauro", "9012", 35.00, 1954));
        libros.add(new Libro("Leo Tolstoy", "Guerra y Paz", "Editorial Acantilado", "0123", 40.00, 1869));
        libros.add(new Libro("Isabel Allende", "La Casa de los Espíritus", "Plaza & Janés", "1123", 24.50, 1982));
        libros.add(new Libro("Mark Twain", "Las aventuras de Tom Sawyer", "Editorial Juventud", "2234", 18.00, 1876));
        libros.add(new Libro("Emily Brontë", "Cumbres Borrascosas", "Editorial Austral", "3345", 19.75, 1847));
        libros.add(new Libro("Herman Melville", "Moby Dick", "Ediciones Destino", "4456", 22.00, 1851));
        libros.add(new Libro("Charles Dickens", "Oliver Twist", "Editorial Planeta", "5567", 20.00, 1839));
        libros.add(new Libro("Virginia Woolf", "La señora Dalloway", "Alianza Editorial", "6678", 21.50, 1925));
        libros.add(new Libro("Ernest Hemingway", "El viejo y el mar", "Editorial Sudamericana", "7789", 18.90, 1952));
        libros.add(new Libro("F. Scott Fitzgerald", "El gran Gatsby", "Editorial Anaya", "8890", 19.50, 1925));
        libros.add(new Libro("Miguel de Cervantes", "Novelas ejemplares", "Editorial Espasa", "9901", 23.00, 1613));
        libros.add(new Libro("Jules Verne", "Veinte mil leguas de viaje submarino", "Editorial Molino", "1012", 24.00, 1870));
        libros.add(new Libro("Albert Camus", "El extranjero", "Editorial Alianza", "1123", 17.50, 1942));
        libros.add(new Libro("H. G. Wells", "La máquina del tiempo", "Editorial Juventud", "1235", 16.50, 1895));
        libros.add(new Libro("Oscar Wilde", "El retrato de Dorian Gray", "Editorial Cátedra", "1346", 20.00, 1890));
        libros.add(new Libro("Gabriel García Márquez", "El amor en los tiempos del cólera", "Sudamericana", "1457", 22.50, 1985));
        libros.add(new Libro("J.R.R. Tolkien", "El Hobbit", "Minotauro", "1568", 28.00, 1937));
        libros.add(new Libro("Leo Tolstoy", "Anna Karenina", "Editorial Acantilado", "1679", 38.00, 1877));
    }
}
