package org.example.ejerciciobiblioteca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaOpcionesController {

    @FXML
    void ButtonRegistrarEjemplares(ActionEvent event) throws IOException {
        cambiarPantalla("RegistrarEjemplar.fxml", event);
    }

    @FXML
    void ButtonRegistrarLibros(ActionEvent event) throws IOException {
        cambiarPantalla("RegistrarLibro.fxml", event);
    }

    @FXML
    void ButtonPedirPrestamo(ActionEvent event) throws IOException {
/*        cambiarPantalla("PedirPrestamo.fxml", event);*/
    }

    @FXML
    void ButtonDevolverPrestamo(ActionEvent event) throws IOException {
/*        cambiarPantalla("DevolverPrestamo.fxml", event);*/
    }


    @FXML
    void ButtonUsuarios(ActionEvent event) throws IOException {
/*        cambiarPantalla("Usuarios.fxml", event);*/
    }

    @FXML
    void ButtonInformacion(ActionEvent event) throws IOException {
/*        cambiarPantalla("Informacion.fxml", event);*/
    }

    private void cambiarPantalla(String fxml, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/org/example/ejerciciobiblioteca/" + fxml) );
        Scene nuevaScene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(nuevaScene);
    }

}
