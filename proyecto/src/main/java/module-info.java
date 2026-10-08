module cr.ac.proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    // FXMLLoader necesita acceso reflexivo a los Controllers (@FXML).
    opens cr.ac.proyecto.controller to javafx.fxml;

    // TableView + PropertyValueFactory necesita leer los getters del modelo.
    opens cr.ac.proyecto.model to javafx.base;

    exports cr.ac.proyecto;
}
