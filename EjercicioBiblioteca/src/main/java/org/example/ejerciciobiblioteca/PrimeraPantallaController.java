package org.example.ejerciciobiblioteca;

import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PrimeraPantallaController {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("EjercicioBiblioteca");

    @FXML
    private TextField DNI;

    @FXML
    private TextField Password;

    @FXML
    void LogIn(ActionEvent event) {
        if (DNI.getText().isEmpty() || Password.getText().isEmpty()) {
            System.out.println("Faltan datos");
            return;
        }

        EntityManager em = emf.createEntityManager();

        String dni = DNI.getText();
        String password = Password.getText();

        List<Usuario> resultado = em.createQuery(
                        "SELECT u FROM Usuario u WHERE u.dni = :dni AND u.password = :password",
                        Usuario.class
                )
                .setParameter("dni", dni)
                .setParameter("password", password)
                .getResultList();

        em.close();

        if (!resultado.isEmpty()) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }

    @FXML
    void PantallaOpciones(ActionEvent event) throws IOException {
        cambiarPantalla("PantallaOpciones.fxml", event);
    }

    @FXML
    void RegistrarUsuario(ActionEvent event) throws IOException {
        cambiarPantalla("PantallaCrearUser.fxml", event);
    }

    private void cambiarPantalla(String fxml, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/ejerciciobiblioteca/" + fxml)
        );
        Scene nuevaScene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(nuevaScene);
    }
}
