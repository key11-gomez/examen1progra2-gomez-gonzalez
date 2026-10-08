package cr.ac.proyecto.controller;

import cr.ac.proyecto.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * Controller de la pantalla principal.
 *
 * Es un punto de partida: cada pareja debe adaptarlo a su caso
 * y agregar los botones o menús que lleven a sus demás vistas.
 */
public class PrincipalController {

    @FXML
    private Label lblEstado;

    @FXML
    private void initialize() {
        lblEstado.setText("Proyecto base listo. Adapte esta pantalla a su caso.");
    }

    /**
     * Ejemplo de navegación. Cree registro.fxml y su Controller
     * para que este botón funcione.
     */
    @FXML
    private void irARegistro() {
        navegar("registro");
    }

    @FXML
    private void irAConsulta() {
        navegar("consulta");
    }

    @FXML
    private void irAGestion() {
        navegar("gestion");
    }

    private void navegar(String vista) {
        try {
            App.cambiarVista(vista);
        } catch (IOException | NullPointerException | IllegalStateException e) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Vista pendiente");
            alerta.setContentText("La vista \"" + vista + ".fxml\" aún no existe o tiene errores.");
            alerta.showAndWait();
        }
    }
}
