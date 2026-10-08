package cr.ac.proyecto;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto de entrada de la aplicación JavaFX.
 *
 * Ofrece un mecanismo simple de navegación: todas las vistas comparten
 * la misma Scene y se cambia su raíz con {@link #cambiarVista(String)}.
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(cargarFXML("principal"), 900, 600);
        scene.getStylesheets().add(App.class.getResource("estilos.css").toExternalForm());
        stage.setTitle("Examen Práctico Integrador - Programación II");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia la vista actual.
     *
     * @param fxml nombre del archivo FXML sin extensión (por ejemplo "registro")
     */
    public static void cambiarVista(String fxml) throws IOException {
        scene.setRoot(cargarFXML(fxml));
    }

    private static Parent cargarFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return loader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
