package org.example.ejerciciobiblioteca;

import entidades.Usuario;
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

public class PantallaCrearUserController {

    @FXML
    private TextField DNI;

    @FXML
    private TextField Email;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Password;


    @FXML
    void Registrar(ActionEvent event) throws IOException {
        if (DNI.getText().isEmpty() || Nombre.getText().isEmpty() ||
                Email.getText().isEmpty() || Password.getText().isEmpty()) {
            System.out.println("Faltan datos");
            return;
        }

        Usuario nuevo = new Usuario();
        nuevo.setDni(DNI.getText());
        nuevo.setNombre(Nombre.getText());
        nuevo.setEmail(Email.getText());
        nuevo.setPassword(Password.getText());

        EntityManager em = Persistence
                .createEntityManagerFactory("EjercicioBiblioteca")
                .createEntityManager();

        em.getTransaction().begin();
        em.persist(nuevo);
        em.getTransaction().commit();
        em.close();

        System.out.println("Usuario registrado correctamente");

        cambiarPantalla("PantallaOpciones.fxml", event);

    }

    private void cambiarPantalla(String fxml, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/org/example/ejerciciobiblioteca/" + fxml) );
        Scene nuevaScene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(nuevaScene);
    }

}
