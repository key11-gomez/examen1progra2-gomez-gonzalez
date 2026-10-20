package cr.ac.proyecto.controller;

import cr.ac.proyecto.App;
import cr.ac.proyecto.model.Cita;
import cr.ac.proyecto.service.GestorCitas;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.time.LocalDateTime;

public class GestionController {

    @FXML private TableView<Cita> tablaGestion;
    @FXML private TableColumn<Cita, String> colId;
    @FXML private TableColumn<Cita, Object> colCliente;
    @FXML private TableColumn<Cita, Object> colProfesional;
    @FXML private TableColumn<Cita, LocalDateTime> colFecha;
    @FXML private TableColumn<Cita, Object> colEstado;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colProfesional.setCellValueFactory(new PropertyValueFactory<>("profesional"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        refrescar();
    }

    private void refrescar() {
        if (tablaGestion != null) {
            
            tablaGestion.getItems().clear();
            tablaGestion.setItems(FXCollections.observableArrayList(GestorCitas.getInstancia().listarCitas()));
            tablaGestion.refresh(); 
        }
    }

    @FXML
    private void handleAtendida() {
        Cita c = tablaGestion.getSelectionModel().getSelectedItem();
        if (c == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Por favor seleccione una cita de la tabla.");
            return;
        }
        try {
            GestorCitas.getInstancia().marcarAtendida(c.getId());
            refrescar(); 
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cita marcada como atendida.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "No se puede modificar", e.getMessage());
        }
    }

    @FXML
    private void handleCancelar() {
        Cita c = tablaGestion.getSelectionModel().getSelectedItem();
        if (c == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Por favor seleccione una cita de la tabla.");
            return;
        }
        try {
            GestorCitas.getInstancia().cancelarCita(c.getId());
            refrescar(); 
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cita cancelada correctamente.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "No se puede modificar", e.getMessage());
        }
    }

    @FXML
    private void handleVolver() {
        try {
            App.cambiarVista("principal");
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Navegación", "No se pudo regresar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}