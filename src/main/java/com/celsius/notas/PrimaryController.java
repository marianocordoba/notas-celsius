package com.celsius.notas;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

public class PrimaryController {
  @FXML
  private ListView<Nota> listaNotas;

  @FXML
  private TextField textoTitulo;
  
  @FXML
  private TextArea textoContenido;

  @FXML
  private Button botonGuardar;

  @FXML
  void salir(ActionEvent event) {
    Alert confirmacion = new Alert(
      AlertType.CONFIRMATION,
      "¿Está seguro de que desea salir?",
      new ButtonType("Cancelar"),
      new ButtonType("Salir")
    );
    // Modificamos el título y texto de cabecera del Alert
    confirmacion.setTitle("Confirmación");
    confirmacion.setHeaderText("Confirmación");

    // respuesta -> {} es una función lambda, que es una
    // función anónima. Es un concepto complejo que no vamos
    // a ver en el curso.
    confirmacion.showAndWait().ifPresent(respuesta -> {
      // Si el ButtonType tiene el texto "Salir"
      // cerramos la aplicación
      if (respuesta.getText().equals("Salir")) {
        // Método para salir de una aplicación de JavaFX
        Platform.exit();

        // Método para terminar un proceso de Java
        // El parámetro es el código de error. Si no hay
        // error, va 0.
        System.exit(0);
      }
    });
  }

  @FXML
  void acercaDe(ActionEvent event) {
    // Mostrar un Alert con información acerca del
    // programa.

    // Ej: Celsius Notas v1.0
    //     Desarrollado por Mariano Córdoba
  }

  @FXML
  void eliminarNota(ActionEvent event) {

  }

  @FXML
  void crearNota(ActionEvent event) {
    Nota nota = new Nota("Nota sin título", "");
    listaNotas.getItems().add(nota);
  }

  @FXML
  void guardarNota(ActionEvent event) {

  }
}
