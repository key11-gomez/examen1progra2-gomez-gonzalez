package cr.ac.proyecto.controller;

import cr.ac.proyecto.App;
import cr.ac.proyecto.model.Cita;
import cr.ac.proyecto.service.GestorCitas;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.time.LocalDateTime;

public class ConsultaController {

    @FXML private TableView<Cita> tablaCitas;
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

        if (tablaCitas != null) {
            tablaCitas.setItems(FXCollections.observableArrayList(GestorCitas.getInstancia().listarCitas()));
            tablaCitas.refresh();
        }
    }

    @FXML
    private void handleVolver() {
        try {
            App.cambiarVista("principal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}