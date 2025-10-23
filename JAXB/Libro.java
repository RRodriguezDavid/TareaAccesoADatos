package EjercicioEntrega;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"autor", "titulo", "editorial", "isbn", "anno","precio"})

public class Libro {
    private String autor;
    private String titulo;
    private String editorial;
    private String isbn;
    private int anno;
    private Double precio;

    public Libro() {}
    public Libro(String autor, String titulo, String editorial, String isbn, Double precio,int anno) {
        this.autor = autor;
        this.titulo = titulo;
        this.editorial = editorial;
        this.isbn = isbn;
        this.precio =  precio;
        this.anno = anno;

    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) {this.precio = precio;}
    public int getAnno() {return anno;}
    public void setAnno(int anno) {this.anno = anno;}

    @Override
    public String toString() {
        return autor + ", " +  titulo + ", " + editorial + ", " + isbn + ", " + precio + "€"+ " ," + anno;
    }
    public String toString2(){
        return titulo;
    }
}
