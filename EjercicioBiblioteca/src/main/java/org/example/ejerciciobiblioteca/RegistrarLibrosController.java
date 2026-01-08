package org.example.ejerciciobiblioteca;

import entidades.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrarLibrosController {

    @FXML
    private TextField Autor;

    @FXML
    private TextField ISBN13;

    @FXML
    private TextField Titulo;

    @FXML
    void Registrar(ActionEvent event) throws IOException {
        if (Autor.getText().isEmpty() || ISBN13.getText().isEmpty() || Titulo.getText().isEmpty()) {
            System.out.println("Faltan datos");
            return;
        }

        Libro libro = new Libro();
        libro.setAutor(Autor.getText());
        libro.setIsbn13(ISBN13.getText());
        libro.setTitulo(Titulo.getText());

        EntityManager em = Persistence
                .createEntityManagerFactory("EjercicioBiblioteca")
                .createEntityManager();

        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        em.close();

        System.out.println("Libro registrado correctamente");

        cambiarPantalla("PantallaOpciones.fxml", event);
    }

    private void cambiarPantalla(String fxml, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ejerciciobiblioteca/" + fxml));
        Scene nuevaScene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(nuevaScene);
    }
}
