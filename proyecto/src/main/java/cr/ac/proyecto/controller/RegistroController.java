package cr.ac.proyecto.controller;

import cr.ac.proyecto.App;
import cr.ac.proyecto.model.Cliente;
import cr.ac.proyecto.model.Especialidad;
import cr.ac.proyecto.model.Profesional;
import cr.ac.proyecto.model.Servicio;
import cr.ac.proyecto.service.GestorCitas;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class RegistroController {

    
    @FXML private ComboBox<Cliente> cmbCliente;
    @FXML private ComboBox<Profesional> cmbProfesional;
    @FXML private ComboBox<Servicio> cmbServicio;
    @FXML private DatePicker dpFecha;
    @FXML private TextField txtHora;

    
    @FXML private TextField txtClienteId;
    @FXML private TextField txtClienteNombre;
    @FXML private TextField txtClienteTelefono;

    
    @FXML private TextField txtProfeId;
    @FXML private TextField txtProfeNombre;
    @FXML private TextField txtProfeTelefono;
    @FXML private ComboBox<Especialidad> cmbEspecialidad;

    @FXML
    private void initialize() {
        GestorCitas gestor = GestorCitas.getInstancia();
        
        
        if (gestor.listarClientes().isEmpty()) {
            gestor.cargarDatosDePrueba();
        }

        
        refrescarCombos();

        
        if (cmbEspecialidad != null) {
            cmbEspecialidad.getItems().setAll(Especialidad.values());
        }

        
        configurarFormatosComboBox();
    }

    private void refrescarCombos() {
        GestorCitas gestor = GestorCitas.getInstancia();
        cmbCliente.getItems().setAll(gestor.listarClientes());
        cmbProfesional.getItems().setAll(gestor.listarProfesionales());
        cmbServicio.getItems().setAll(gestor.listarServicios());
    }

    
    @FXML
    private void handleRegistrarCliente() {
        try {
            String id = txtClienteId.getText().trim();
            String nombre = txtClienteNombre.getText().trim();
            String telefono = txtClienteTelefono.getText().trim();

            if (id.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Complete la cédula, nombre y teléfono del cliente.");
                return;
            }

            String correo = id + "@correo.com"; 
            Cliente nuevoCliente = new Cliente(id, nombre, telefono, correo, false);
            GestorCitas.getInstancia().registrarCliente(nuevoCliente);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente registrado correctamente.");
            txtClienteId.clear();
            txtClienteNombre.clear();
            txtClienteTelefono.clear();
            refrescarCombos();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", e.getMessage());
        }
    }

    
    @FXML
    private void handleRegistrarProfesional() {
        try {
            String id = txtProfeId.getText().trim();
            String nombre = txtProfeNombre.getText().trim();
            String telefono = txtProfeTelefono.getText().trim();
            Especialidad especialidad = cmbEspecialidad.getValue();

            if (id.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || especialidad == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Complete todos los datos del profesional.");
                return;
            }

            String correo = id + "@hospital.com";
            Profesional nuevoProfe = new Profesional(id, nombre, telefono, correo, especialidad, LocalTime.of(8, 0), LocalTime.of(17, 0));
            GestorCitas gestor = GestorCitas.getInstancia();
            gestor.registrarProfesional(nuevoProfe);

            
            for (Servicio servicio : gestor.listarServicios()) {
                gestor.asignarServicio(id, servicio.getCodigo());
            }

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Profesional registrado y autorizado para todos los servicios correctamente.");
            txtProfeId.clear();
            txtProfeNombre.clear();
            txtProfeTelefono.clear();
            cmbEspecialidad.getSelectionModel().clearSelection();
            refrescarCombos();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Validación", e.getMessage());
        }
    }

   
    @FXML
    private void handleGuardarCita() {
        try {
            if (cmbCliente.getValue() == null || cmbProfesional.getValue() == null ||
                cmbServicio.getValue() == null || dpFecha.getValue() == null || 
                txtHora.getText() == null || txtHora.getText().isBlank()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Incompletos", "Por favor complete todos los datos de la cita.");
                return;
            }

            LocalTime hora;
            try {
                hora = LocalTime.parse(txtHora.getText().trim());
            } catch (DateTimeParseException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Hora Inválida", "Use el formato HH:mm (Ejemplo: 09:30).");
                return;
            }

            LocalDateTime fechaHora = LocalDateTime.of(dpFecha.getValue(), hora);

            GestorCitas.getInstancia().programarCita(
                cmbCliente.getValue().getIdentificacion(),
                cmbProfesional.getValue().getIdentificacion(),
                cmbServicio.getValue().getCodigo(),
                fechaHora
            );

            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cita programada exitosamente.");
            handleVolver();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al Cargar Cita", e.getMessage());
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

    private void configurarFormatosComboBox() {
        cmbCliente.setCellFactory(p -> new ListCell<>() {
            @Override protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre() + " (" + item.getIdentificacion() + ")");
            }
        });
        cmbCliente.setConverter(new StringConverter<>() {
            @Override public String toString(Cliente c) { return c == null ? "" : c.getNombre() + " (" + c.getIdentificacion() + ")"; }
            @Override public Cliente fromString(String s) { return null; }
        });

        cmbProfesional.setCellFactory(p -> new ListCell<>() {
            @Override protected void updateItem(Profesional item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre() + " - " + item.getEspecialidad());
            }
        });
        cmbProfesional.setConverter(new StringConverter<>() {
            @Override public String toString(Profesional p) { return p == null ? "" : p.getNombre() + " - " + p.getEspecialidad(); }
            @Override public Profesional fromString(String s) { return null; }
        });

        cmbServicio.setCellFactory(p -> new ListCell<>() {
            @Override protected void updateItem(Servicio item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre() + " (₡" + item.getPrecio() + ")");
            }
        });
        cmbServicio.setConverter(new StringConverter<>() {
            @Override public String toString(Servicio s) { return s == null ? "" : s.getNombre() + " (₡" + s.getPrecio() + ")"; }
            @Override public Servicio fromString(String s) { return null; }
        });
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}